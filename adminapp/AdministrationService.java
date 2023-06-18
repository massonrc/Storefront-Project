package adminapp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import storeapp.InventoryManager;
import storeapp.SalableProduct;
import storeapp.StoreFront;

/**
 * The AdministrationService class provides functionality for managing the store's inventory
 * through a command-based interface. It listens for admin commands on a separate thread and
 * processes the commands to update or retrieve the store's inventory.
 * 
 * @pauthor rargueta
 */
public class AdministrationService {
    private static final int PORT = 2222;
    private StoreFront storeFront;

    /**
     * Constructs an AdministrationService object with the given StoreFront instance.
     *
     * @param storeFront the StoreFront object representing the store
     */
    public AdministrationService(StoreFront storeFront) {
        this.storeFront = storeFront;
    }

    /**
     * Starts the administration service by creating a separate thread to listen for admin commands.
     * The thread continuously accepts incoming connections and processes the received commands.
     */
    public void start() {
        // Create a separate thread to listen for admin commands
        Thread commandListener = new Thread(this::listenForCommands);
        commandListener.start();
    }

    /**
     * Listens for admin commands on the specified port. Once a command is received,
     * it is processed and an appropriate response is sent back to the client.
     * The method runs in an infinite loop until the server is stopped.
     */
    private void listenForCommands() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

                String command = reader.readLine();

                // Process the received command
                switch (command) {
                    case "U":
                        // Handle the update command for store's inventory
                        handleUpdateCommand();
                        writer.println("Update command processed successfully.");
                        break;
                    case "R":
                        // Handle the retrieve command to return Salable Products from the Inventory Management System
                        String jsonData = handleRetrieveCommand();
                        writer.println(jsonData);
                        break;
                        
                    case "Q":
                    	// Allows user to exit the application
                    	writer.println("Exiting the Admin Service.");
                    	return;
                    	
                    default:
                        // Invalid command
                        writer.println("Invalid command: " + command);
                }

                socket.close();
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles an admin command received from the client socket. It reads the command,
     * processes it, and sends an appropriate response back to the client.
     *
     * @param clientSocket the Socket representing the client connection
     */
    private void handleCommand(Socket clientSocket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {

            // Read the command from the client
            String command = reader.readLine();

            // Process the received command
            switch (command) {
                case "U":
                    // Handle the update command for store's inventory
                    handleUpdateCommand();
                    writer.println("Update command processed successfully.");
                    break;
                case "R":
                    // Handle the retrieve command to return Salable Products from Inventory Management System
                    String jsonData = handleRetrieveCommand();
                    writer.println(jsonData);
                    break;
                default:
                    // Invalid command
                    writer.println("Invalid command: " + command);
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        } 
        finally {
            try {
                clientSocket.close();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Handles the update command by reading the JSON data from the inventory file,
     * converting it to a list of SalableProduct objects, and updating the store's inventory.
     * Any errors during the process are printed to the console.
     */
    private void handleUpdateCommand() {
        try {
            // Read the JSON data from the inventory file
            Path filePath = Paths.get("inventory.json");
            String jsonData = Files.readString(filePath);

            // Convert the JSON data to a list of SalableProduct objects
            ObjectMapper objectMapper = new ObjectMapper();
            List<SalableProduct> salableProducts = objectMapper.readValue(jsonData, new TypeReference<List<SalableProduct>>() {});

            // Update the Store's inventory with the new Salable Products
            InventoryManager inventoryManager = storeFront.getInventoryManager();
            for (SalableProduct product : salableProducts) {
                inventoryManager.addProduct(product);
            }

            System.out.println("Update command processed successfully.");
        } 
        catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
        }
    }

    /**
     * Handles the retrieve command by returning the store's inventory as a JSON string.
     * Any errors during the conversion process are printed to the console.
     *
     * @return the JSON representation of the store's inventory
     */
    private String handleRetrieveCommand() {
        // Retrieve all Salable Products from the Store Inventory Management System
        Map<String, SalableProduct> inventory = storeFront.getInventoryManager().getInventory();

        // Convert the inventory map to a JSON string
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(inventory);
        } 
        catch (JsonProcessingException e) {
            System.out.println("Error converting inventory to JSON: " + e.getMessage());
            return "";
        }
    }
}