public class TV extends DeviceObj{
    TV(String name, Double price, Integer quantity){
        super(category_string, name, price, quantity);
    }

    private static final String category_string = "TV";

    @Override
    public int compareTo(DeviceObj o) {
        if (this.getPrice() == o.getPrice())
            return 0;
        else
            return this.getPrice() > o.getPrice() ? 1 : -1;
    }
}
