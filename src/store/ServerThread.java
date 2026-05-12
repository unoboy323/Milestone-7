package store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Runs the network server for the Store Front application.
 * This server listens for admin commands from the Administration Application.
 */
public class ServerThread extends Thread {

    private InventoryManager inventory;
    private String inventoryFile;
    private int port;
    private ObjectMapper mapper;

    /**
     * Creates the server thread.
     *
     * @param inventory the store inventory manager
     * @param inventoryFile the inventory file name
     */
    public ServerThread(InventoryManager inventory, String inventoryFile) {
        this.inventory = inventory;
        this.inventoryFile = inventoryFile;
        this.port = 6666;
        this.mapper = new ObjectMapper();
    }

    /**
     * Runs the server in the background.
     */
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("Admin server started on port " + port + ".");
            System.out.println("The Store Front can now receive admin commands.\n");

            while (true) {
                Socket clientSocket = serverSocket.accept();

                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

                String message = input.readLine();
                String response = processCommand(message);

                output.println(response);

                input.close();
                output.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            System.out.println("Server error.");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Processes a command sent from the Administration Application.
     *
     * @param message the command message
     * @return the response message
     */
    private String processCommand(String message) {
        if (message == null || message.length() == 0) {
            return "ERROR|No command received.";
        }

        String command = "";
        String data = "";

        int pipeLocation = message.indexOf("|");

        if (pipeLocation >= 0) {
            command = message.substring(0, pipeLocation);
            data = message.substring(pipeLocation + 1);
        } else {
            command = message;
        }

        if (command.equalsIgnoreCase("R")) {
            return readInventory();
        } else if (command.equalsIgnoreCase("U")) {
            return updateInventory(data);
        } else {
            return "ERROR|Invalid command.";
        }
    }

    /**
     * Handles the R command.
     * This sends the inventory back to the admin application as JSON.
     *
     * @return the inventory in JSON format
     */
    private String readInventory() {
        try {
            return mapper.writerFor(new TypeReference<ArrayList<SalableProduct>>() {})
                         .writeValueAsString(inventory.getProducts());
        } catch (Exception e) {
            return "ERROR|Could not read inventory.";
        }
    }

    /**
     * Handles the U command.
     * This updates the Store Front inventory with products from the admin application.
     *
     * @param data the JSON product data
     * @return the result message
     */
    private String updateInventory(String data) {
        try {
            ArrayList<SalableProduct> products =
                mapper.readValue(data, new TypeReference<ArrayList<SalableProduct>>() {});

            inventory.updateInventory(products);
            inventory.saveInventory(inventoryFile);

            return "OK|Inventory updated.";
        } catch (Exception e) {
            return "ERROR|Could not update inventory.";
        }
    }
}