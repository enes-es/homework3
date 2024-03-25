public class Laptop extends DeviceObj{
    Laptop(String name, Double price, Integer quantity){
        super(category_string, name, price, quantity);
    }
    private static final String category_string = "Laptop";

    @Override
    public int compareTo(DeviceObj o) {
        if (this.getPrice() == o.getPrice())
            return 0;
        else
            return this.getPrice() > o.getPrice() ? 1 : -1;
    }
}
