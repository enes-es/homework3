import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        
        Inventory inventory = new Inventory();

        DeviceObj example = inventory.deviceObjCreator("Laptop", "Lenovo laptop", 100.0, 10);
        DeviceObj example2 = inventory.deviceObjCreator("TV", "Samsung TV", 1000.0, 5);
        DeviceObj example3 = inventory.deviceObjCreator("Headphones", "SONY WH-100XM4", 250.0, 3);
        DeviceObj example4 = inventory.deviceObjCreator("Smartphone", "iPhone 12", 2000.0, 5);
        DeviceObj example5 = inventory.deviceObjCreator("Smartwatch", "apple watch", 799.0, 2);



        inventory.addDevice(example);
        inventory.addDevice(example2);
        inventory.addDevice(example3);
        inventory.addDevice(example4);
        inventory.addDevice(example5);


        inventory.mainMenu();



        
    }

    
}