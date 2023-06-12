import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * The AdminApplication class represents an administration application that interacts with the AdministrationService
 * running on a server. It allows the user to send admin commands to update the store inventory or retrieve salable
 * products from the inventory management system.
 * @author rargueta
 *
 */
public class AdminApplication {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 2222;

    /**
     * Runs the admin application. It establishes a connection with the server, displays the admin menu,
     * reads user input, sends the command to the server, and processes the response.
     */
    public void run() {
        System.out.println("Welcome, Admin!");

        Socket socket = null;
        PrintWriter writer = null;
        Scanner scanner = null;

        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            writer = new PrintWriter(socket.getOutputStream(), true);
            scanner = new Scanner(System.in);

            String command;
            do {
                displayAdminMenu();
                command = scanner.nextLine();
                sendCommand(writer, command);
                processResponse(socket.getInputStream()); // Pass the input stream to processResponse()
            } while (!command.equals("Q")); // Repeat until the user chooses to quit
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the resources in the finally block to ensure they are properly released
            if (scanner != null) {
                scanner.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Displays the admin menu options to the user.
     */
    private void displayAdminMenu() {
        System.out.println("What would you like to do?");
        System.out.println("U - Update Store Inventory");
        System.out.println("R - Retrieve Salable Products");
        System.out.println("Q - Quit");
    }

    /**
     * Sends the admin command to the server.
     *
     * @param writer  the PrintWriter used to send data to the server
     * @param command the admin command to send
     */
    private void sendCommand(PrintWriter writer, String command) {
        writer.println(command);
    }

   /**
    * Processes the response received from the server and prints it to the console.
    *
    * @param socket the Socket representing the server connection
    * @throws IOException if an I/O error occurs while processing the response
    */
    private void processResponse(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String response = scanner.nextLine();
            System.out.println("Response: " + response.toUpperCase());
        }
    }

    /**
     * The entry point of the AdminApplication. Creates an instance of AdminApplication and runs the application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        AdminApplication application = new AdminApplication();
        application.run();
    }
}