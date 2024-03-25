import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Inventory {

    // DISCUSS: should it throw if newDevice category invalid?
    // DISCUSS: what about other invalid datas?
    public void addDevice(DeviceObj newDevice) {

        String category_str = newDevice.getCategory();
        Integer category_idx = getCategoryIdx(category_str);

        categoriesLinkedList.get(category_idx).add(newDevice);

    }

    private Integer getCategoryIdx(String category_str) {
        return null;
    }

    private boolean isCategory(ArrayList<DeviceObj> category, String categoryName) {
        return true;
    }

    // DISCUSS: shouldn't this be a method of DeviceObj?
    private DeviceObj addDeviceFromUser() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter category name: ");
        String category = input.nextLine();

        System.out.print("Enter device name: ");
        String name = input.nextLine();

        System.out.print("Enter price: ");
        Double price = processPrice(input.nextLine());

        System.out.print("Enter quantity: ");
        Integer quantity = processQuantity(input.nextLine());

        // check if entered data is correct

        // creat the appropriate device and return

        return null;

    }

    public DeviceObj removeDevice(String name) {
        // find device
        // if exists remove and return it
        // if doesn't exist throw //CHECK: is throwing ok? //DISCUSS: what to throw?
        return null;
    }

    public DeviceObj updateDevice() {
        // ask new data
        // find the device
        // verify data format
        // update device
        // return device

        String name;
        String price_str;
        Double price_double;
        Integer quantity;
        Scanner scan = new Scanner(System.in);
        DeviceObj deviceToUpdate = null;
        try {
            System.out.print("Enter the name of the device to update: ");
            name = scan.nextLine();

            System.out.print("Enter new price");
            price_str = scan.nextLine();

            System.out.print("Enter new quantity");
            quantity = scan.nextInt();

            // find device
            // if it exist
            // change values
            // if it doesn't exist (handeld by catch statement)
            // notify user and don't update any device

            //might throw NoSuchElementException, handled within catch
            deviceToUpdate = findDevice(name); 

            price_double = processPrice(price_str);
            if (price_double == null)
                price_double deviceToUpdate.getPrice();

            deviceToUpdate.setPrice(price);
            deviceToUpdate.setQuantity(quantity);

            System.out.printf("%s details updated: Price - %.2f$, Quantity - %d", name, price, quantity);

        }

        catch (InputMismatchException e) {
            System.err.println(e);
            System.err.println("Ignoring update request...");

        }

        catch (NoSuchElementException e) {
            System.err.println("Can't find the device");
            System.err.println("Ignoring update request...");

        }

        return null;
    }

    public void displayAllDevices() {
        // DISCUSS: listing all? in what format? category by category? does order
        // matter?
        // DECISION: Let's go, category by category, and whatever order they're within
        // categories.

        // ATTENTION: hard-coded values.
        // DISCUSS: can we make the linked-list node indeces more dynamic
        Integer objectCount = 0;
        ArrayList<DeviceObj> categoryToPrint = null;
        for (int i = 0, length = categoriesLinkedList.size(); i < length; ++i) {
            categoryToPrint = categoriesLinkedList.get(i);

            for (int j = 0, array_length = categoryToPrint.size(); j < array_length; ++j) {
                System.out.printf("%d. ", objectCount)
                categoryToPrint.get(j).print(); // DISCUSS: change method name from print?
                
            }

        }
    }

    public DeviceObj findCheapest() {
        Integer objectCount = 0;
        for (int i = 0, length = categoriesLinkedList.size(); i < length; ++i) {
            ArrayList<DeviceObj> categoryToPrint = categoriesLinkedList.get(i);

            for (int j = 0, array_length = categoryToPrint.size(); j < array_length; ++j) {
                
            }
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
        /*
         * String pattern = new String("^[+]?[0-9]*\\.?[0-9]+\\$$");
         * 
         * if (price.matches(pattern)) {
         * 
         * price.substring(-1)
         * }
         */
        // CHECK: assigned null to suppress `possibly uninitialized` is this the best
        // way of doing this?
        Double price_as_double = null;
        String price_without_dollar_sign;
        try {
            if (price.endsWith("$")) {
                price_without_dollar_sign = price.substring(0, price.length());
                price_as_double = Double.parseDouble(price_without_dollar_sign);
                if (price_as_double < 0)
                    price_as_double = null;
            }
        }

        // CHECK: is return null fine?
        // CHECK: should we even catch the exception?
        catch (NumberFormatException e) {
            price_as_double = null;
        }

        return price_as_double;
    }

    // Index of the categories within the linked-list: categoriesLinkedList
    final private Integer SMARTPHONE = 0;
    final private Integer LAPTOP = 1;
    final private Integer TV = 2;
    final private Integer HEADPHONES = 3;
    final private Integer SMARTWATCH = 4;

    private static Integer processQuantity(String quantity) {
        Integer quantity_integer;
        try {
            quantity_integer = Integer.valueOf(quantity);

            if (quantity_integer < 0)
                quantity_integer = null;

            return quantity_integer;
        } catch (NumberFormatException e) {
            return null;
        }

    }
}
