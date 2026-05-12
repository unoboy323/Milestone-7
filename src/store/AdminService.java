package store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Sends admin commands to the Store Front application.
 */
public class AdminService {

    private String host;
    private int port;
    private ObjectMapper mapper;

    /**
     * Creates the admin service.
     */
    public AdminService() {
        host = "localhost";
        port = 6666;
        mapper = new ObjectMapper();
    }

    /**
     * Sends the R command to get inventory from the Store Front.
     *
     * @return the inventory list
     * @throws IOException if the server cannot be reached
     */
    public ArrayList<SalableProduct> getInventory() throws IOException {
        String response = sendMessage("R|");

        if (response.startsWith("ERROR|")) {
            throw new IOException(response);
        }

        return mapper.readValue(response, new TypeReference<ArrayList<SalableProduct>>() {});
    }

    /**
     * Sends the U command to update inventory in the Store Front.
     *
     * @param products the products to send
     * @return the server response
     * @throws IOException if the server cannot be reached
     */
    public String updateInventory(ArrayList<SalableProduct> products) throws IOException {
        String json = mapper.writerFor(new TypeReference<ArrayList<SalableProduct>>() {})
                            .writeValueAsString(products);

        return sendMessage("U|" + json);
    }

    /**
     * Sends a message to the Store Front server.
     *
     * @param message the command message
     * @return the server response
     * @throws IOException if the message cannot be sent
     */
    private String sendMessage(String message) throws IOException {
        Socket socket = new Socket(host, port);

        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        output.println(message);

        String response = input.readLine();

        input.close();
        output.close();
        socket.close();

        return response;
    }
}