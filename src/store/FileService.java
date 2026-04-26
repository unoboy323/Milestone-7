package store;

import java.util.ArrayList;

/**
 * Defines methods for reading and writing product files.
 */
public interface FileService {

    /**
     * Reads products from a file.
     */
    ArrayList<SalableProduct> readProducts(String fileName) throws FileServiceException;

    /**
     * Writes products to a file.
     */
    void writeProducts(String fileName, ArrayList<SalableProduct> products) throws FileServiceException;
}