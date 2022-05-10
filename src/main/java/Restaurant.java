import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        return getCurrentTime().isAfter(getOpeningTime()) && getCurrentTime().isBefore(getClosingTime());
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName) throws itemNotFoundException {
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        throw new itemNotFoundException(itemName);
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        getMenu().add(newItem);
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException {
        Item itemToBeRemoved = findItemByName(itemName);
        getMenu().remove(itemToBeRemoved);
    }

    public void displayDetails(){
        System.out.println("Restaurant:"+ getName() + "\n"
                +"Location:"+ getLocation() + "\n"
                +"Opening time:"+ getOpeningTime() +"\n"
                +"Closing time:"+ getClosingTime() +"\n"
                +"Menu:"+"\n"+ getMenu());

    }

    public double calculateOrderTotal(List<Item> selectedMenuItems) {
        double totalAmount = 0.0;
        for(Item item: selectedMenuItems) {
            totalAmount += item.getPrice();
        }
        return totalAmount;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }
}
