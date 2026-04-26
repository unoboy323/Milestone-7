package store;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Handles reading and writing products using JSON files.
 */
public class JsonFileService implements FileService {

    // used by Jackson to convert Java objects to JSON and JSON to Java objects
    private ObjectMapper mapper;

    // constructor creates the object mappere
    public JsonFileService() {
        mapper = new ObjectMapper();
    }

    /**
     * Reads products from a JSON file.
     */
    @Override
    public ArrayList<SalableProduct> readProducts(String fileName) throws FileServiceException {
        try {
            return mapper.readValue(new File(fileName), new TypeReference<ArrayList<SalableProduct>>() {});
        } catch (IOException e) {
            throw new FileServiceException("Could not read inventory file.", e);
        }
    }

    /**
     * Writes products to a JSON file.
     */
    @Override
    public void writeProducts(String fileName, ArrayList<SalableProduct> products) throws FileServiceException {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), products);
        } catch (IOException e) {
            throw new FileServiceException("Could not write inventory file.", e);
        }
    }
} 