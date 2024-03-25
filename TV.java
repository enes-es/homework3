public class TV extends DeviceObj{
    TV(String name, Double price, Integer quantity){
        super(category_string, name, price, quantity);
    }

    private static final String category_string = "TV";

    /**
     * Compares the price of this TV object with the price of another DeviceObj object.
     * @param o the DeviceObj object to compare with
     * @return 0 if the prices are equal (in price), 1 if this TV object's price is greater, -1 if this TV object's price is less
     */
    @Override
    public int compareTo(DeviceObj o) {
        if (this.getPrice() == o.getPrice())
            return 0;
        else
            return this.getPrice() > o.getPrice() ? 1 : -1;
    }
}
