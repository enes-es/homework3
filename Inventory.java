import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

//TODO: find functions and variables to add final identifier.

public class Inventory {
    private LinkedList<ArrayList<DeviceObj>> categoriesLinkedList;

    // DISCUSS: should it throw if newDevice category invalid?
    // DISCUSS: what about other invalid datas?
    public void addDevice(DeviceObj newDevice) {

        String category_str = newDevice.getCategory();
        Integer category_idx = getCategoryIdx(category_str);

        categoriesLinkedList.get(category_idx).add(newDevice);

    }

    private Integer getCategoryIdx(String category_str) {
        switch (category_str.toLowerCase()) {
            case "smartphone":
                return SMARTPHONE;

            case "laptop":
                return LAPTOP;

            case "tv":
                return TV;
            case "headphones":
                return HEADPHONES;

            case "smartwatch":
                return SMARTWATCH;

            default:
                return null;
        }

    }



    // DISCUSS: shouldn't this be a method of DeviceObj?
    private DeviceObj addDevice() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter category name: ");
        String category = input.nextLine();

        System.out.print("Enter device name: ");
        String name = input.nextLine();

        System.out.print("Enter price: ");
        Double price = processPrice(input.nextLine());

        System.out.print("Enter quantity: ");
        Integer quantity = processQuantity(input.nextLine());

        // check if entered data is correct ?

        // creat the appropriate device and return

        DeviceObj newDevice = new DeviceObj(category, name, price, quantity);

        input.close();

        return newDevice;

    }

    public DeviceObj removeDevice(String name) throws NoSuchElementException {
        // find device
        // if exists remove and return it
        // if doesn't exist throw //CHECK: is throwing ok? //DISCUSS: what to throw?

        for (ArrayList<DeviceObj> category : categoriesLinkedList)
            for (DeviceObj device : category) {
                if (device.getName().equals(name)) {
                    category.remove(device);
                    return device;
                }
            }

        throw new NoSuchElementException("Can't find the device");
    }

    private DeviceObj removeDevice() throws NoSuchElementException {
        // ask for name
        // call removeDevice(name)
        // return removed device

        Scanner scan = new Scanner(System.in);
        String name;
        DeviceObj removedDevice = null;

        System.out.print("Enter the name of the device to remove: ");
        name = scan.nextLine();

        removedDevice = removeDevice(name);

        scan.close();
        return removedDevice;
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

            // might throw NoSuchElementException, handled within catch
            deviceToUpdate = findDevice(name);

            price_double = processPrice(price_str);
            if (price_double == null)
                price_double = deviceToUpdate.getPrice();

            deviceToUpdate.setPrice(price_double);
            deviceToUpdate.setQuantity(quantity);

            System.out.printf("%s details updated: Price - %.2f$, Quantity - %d", name, price_double, quantity);

        }

        catch (InputMismatchException e) {
            System.err.println(e);
            System.err.println("Ignoring update request...");

        }

        catch (NoSuchElementException e) {
            System.err.println("Can't find the device");
            System.err.println("Ignoring update request...");

        }

        finally {
            scan.close();
        }
        return null;
    }








    public DeviceObj findDevice(String name) {
        DeviceObj targetDevice = null;

        for (ArrayList<DeviceObj> category : categoriesLinkedList)
            for (DeviceObj device : category) {
                if (device.getName().equals(name)) {
                    targetDevice = device;
                    break;
                }
            }

        return targetDevice;
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
                System.out.printf("%d. ", objectCount);
                categoryToPrint.get(j).print(); // DISCUSS: change method name from print?

            }

        }
    }

    private void printObjectList(ArrayList<DeviceObj> objectList) {
        for (int count = 0; count < objectList.size(); ++count) {
            System.out.printf("%d. ", count);
            objectList.get(count).print();
        }
    }

    public DeviceObj findCheapest() {
        DeviceObj currentCheapest = null;
        DeviceObj currentObject = null;
        ArrayList<DeviceObj> currentCategory = null;
        Integer objectCount = 0;

        for (int i = 0, length = categoriesLinkedList.size(); i < length; ++i) {
            currentCategory = categoriesLinkedList.get(i);

            for (int j = 0, array_length = currentCategory.size(); j < array_length; ++j) {
                currentObject = currentCategory.get(j);
                if (currentObject.getPrice() < currentCheapest.getPrice())
                    currentCheapest = currentObject;
            }
        }
        return currentCheapest;
    }

    public void printSortedByPrice() {
        ArrayList<DeviceObj> objectList = new ArrayList<>();
        for (ArrayList<DeviceObj> currentCategory : categoriesLinkedList) {
            // CHECK: does this work
            // DISCUSS: should we use a different approach/method ?
            objectList.addAll(currentCategory);
        }

        // TODO: implement comparator, or provide custom one here.

        // Collections.sort(objectList);

        printObjectList(objectList);

    }

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
                        addDevice();
                        break;
                    case 2:
                        removeDevice();
                    break;
                    case 3:
                        updateDevice();
                        break;
                    case 4:
                        displayAllDevices();
                        break;
                    case 5:
                        DeviceObj cheapestDevice = findCheapest();
                        System.out.println("Cheapest device: ");
                        System.out.printf("Category: %s, Name: %s, Price: %.2f$, Quantity: %d\n",
                                cheapestDevice.getCategory(), cheapestDevice.getName(), cheapestDevice.getPrice(),
                                cheapestDevice.getQuantity());
                        break;
                    case 6:
                        // Sort by price
                        printSortedByPrice();  
                        break;
                    case 7:
                        // Total inventory value
                        printTotalValue();
                        break;
                    case 8:
                        // Restocking a device
                        restockDevice();
                        break;
                    case 9:
                        // Export inventory report
                        exportInventoryReport();
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

        input.close();
    }

    // DISCUSS: should I catch InputMismatchException from the scanner?
    private DeviceObj restockDevice() {
        String name;
        Integer quantityChange;
        DeviceObj device;

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the name of the device to restock: ");
        name = scan.nextLine();
        device = findDevice(name);

        System.out.print("Do you want to add or remove stock? (Add/Remove): ");
        if (scan.nextLine().equals("Add")) {
            System.out.print("Enter the quantity to add: ");
            quantityChange = scan.nextInt();
            device.addQuantity(quantityChange);

        } else if (scan.nextLine().equals("Remove")) {
            System.out.print("Enter the wuantity to remove: ");
            quantityChange = scan.nextInt();
            device.removeQuantity(quantityChange);
        }

        scan.close();
        return device;
    }

    public void exportInventoryReport() {
        // CHECK: filename compatible with assignment
        // CHECK: this overwrites the file with same name, if it exists. this ok?
        // DISCUSS: is this +1 to filename approach appropriate?
        String fileName = "report";
        String filePath = fileName + "txt";
        Integer index = 1;
        File baseFile = new File(filePath);
        try {
            while (baseFile.exists()) {
                filePath = fileName + index + ".txt";
                baseFile = new File(filePath);
                ++index;
            }

            File file = new File(filePath);
            file.createNewFile();

            PrintWriter writer = new PrintWriter(new FileWriter(filePath));
            Integer count = Integer.valueOf(0);
            Double value = Double.valueOf(0);

            writer.println("Electronics Shop Inventroy Report");
            // TODO: println "Generated on: `date`"
            LocalDate date = LocalDate.now();
            writer.printf("Generated on: %s\n", reportDateFormat(date));
            writer.println("---------------------------------------");
            writer.printf("| %s  | %s\t| %s\t\t| %s\t| %s |\n", "No.", "Category", "Name", "Price", "Quantity");
            writer.println("---------------------------------------");

            for (ArrayList<DeviceObj> category : categoriesLinkedList)
                for (DeviceObj device : category) {
                    writer.println(formattedExportLine(device, count));
                    ++count;
                    value += device.getPrice() * device.getQuantity();
                }

            writer.println("---------------------------------------");
            writer.print("\n\n");

            writer.println("Summary: ");
            writer.printf("- Total Number of Devices: %d\n", count);
            writer.printf("- Total Inventory Value: %.2f\n\n", value);

            writer.print("End of Report");

        }

        // TODO: caught exception type.
        catch (Exception e) {

        }
    }

    private String reportDateFormat(LocalDate date) {
        String formattedDate = new String();
        // example format: 26th March 2024
        formattedDate = String.format("%d%s %s %d", date.getDayOfMonth(), getDaySuffix(date.getDayOfMonth()),
                date.getMonth(), date.getYear());

        return formattedDate;
    }

    private String getDaySuffix(Integer dayOfMonth) {
        String suffix;
        if (dayOfMonth >= 11 && dayOfMonth <= 13)
            suffix = "th";

        else {
            switch (dayOfMonth % 10) {
                case 1:
                    suffix = "st";
                    break;
                case 2:
                    suffix = "nd";
                    break;
                case 3:
                    suffix = "rd";
                    break;
                default:
                    suffix = "th";
                    break;
            }
        }

        return suffix;
    }

    private String formattedExportLine(DeviceObj device, int no) {
        String line = new String();
        // CHECK: is this format ok ?
        String.format("| %d  | %s | %s | %.2f | %d |", no, device.getCategory(), device.getName(), device.getPrice(),
                device.getQuantity());

        return line;
    }

    public Double totalValue() {
        Double value = Double.valueOf(0);

        for (ArrayList<DeviceObj> category : categoriesLinkedList)
            for (DeviceObj device : category) {
                value += device.getPrice();
            }

        return value;
    }

    public void printTotalValue() {
        System.out.printf("Total inventory value: $%.2f", totalValue());
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
