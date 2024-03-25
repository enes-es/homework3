public abstract class DeviceObj implements Device {
    

    final private String category;
    final private String name;
    private Double price;
    private Integer quantity;

    public DeviceObj(String category, String name, Double price, Integer quantity) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    @Override
    public String getCategory() {
        return String.valueOf(category);
    }    

    @Override
    public String getName() {
        return String.valueOf(name);
    }

    @Override
    public double getPrice() {
        double copy = price;
        return copy;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int getQuantity() {
        int copy = quantity;
        return copy;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("Category: %s, Name: %s, Price: %.2f, Quantity: %d", category, name, price, quantity);
    }

    //DISCUSS: should we do argument validity check?
    public DeviceObj addQuantity(Integer quantity) {
        this.quantity += quantity;
        return this;
    }

    public DeviceObj removeQuantity(Integer quantity) {
        this.quantity -= quantity;
        return this;
    }

    //Functionality-wise, this is the same as addQuantity but allows for negative values
    public DeviceObj changeQuantityBy(Integer change) {
        this.quantity += change;
        return this;
    }

    void print() {
        System.out.println(this.toString());
    }


}
