import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;

public class Inventory {

    public void addDevice(DeviceObj newDevice) {

    }

    private DeviceObj addDeviceHelper() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter category name: ");
        String category = input.nextLine();

        System.out.print("Enter device name: ");
        String name = input.nextLine();

        System.out.print("Enter price: ");

    }

    public boolean removeDevice(String name) {
        return true;
    }

    public void updateDevice() {
    }

    public void listDevices() {
    }

    public DeviceObj findCheapest() {
        return null;
    }

    private LinkedList<ArrayList<DeviceObj>> categoriesLinkedList;

    private void printMainMenu() {
        System.out.println("This is the main menu.");
    }

    private void mainMenu() {
        int selection;
        Scanner input = new Scanner(System.in);
        do {
            try {
                printMainMenu();
                selection = input.nextInt();
                if (selection < 0 || selection > 9) {
                    System.out.println("Invalid input.");
                    continue;
                }

                switch (selection) {
                    case 1:
                        // Add device
                        break;
                    case 2:
                        // Remove device
                        break;
                    case 3:
                        // Update device
                        break;
                    case 4:
                        // List devices
                        break;
                    case 5:
                        // Find cheapest device
                        break;
                    case 6:
                        // Find most expensive device
                        break;
                    case 7:
                        // Find device by name
                        break;
                    case 8:
                        // Find device by category
                        break;
                    case 9:
                        // Find device by price
                        break;
                    case 0:
                        // Exit
                        break;
                    default:
                        System.out.println("Invalid input.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                input.nextLine();
                selection = -1;
                continue;
            }
        } while (selection != 0);

    }

    private static Double processPrice(String price) {
        // price input includes a $ at the end.
        // ex: 526.65$
        // always a decimal point is used, never a comma.

        // The pattern:
        // a non-negative double ending with "$"
        // optional: starts with "+" or has a decimal field. Always a "." is used for
        // the decimal point
       /* String pattern = new String("^[+]?[0-9]*\\.?[0-9]+\\$$");

        if (price.matches(pattern)) {

            price.substring(-1)
        }
*/
        Double price_as_double;
        String price_without_dollar_sign;
        try {
            if (price.endsWith("$")) {
                price_without_dollar_sign = price.substring(0, price.length());
                price_as_double = Double.parseDouble(price_without_dollar_sign);

            }
        }

        catch(NumberFormatException e)
        {
            
        }


        return null;
    }
}
