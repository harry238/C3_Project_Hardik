import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();
    private List<Item> selectedMenu = new ArrayList<Item>();

    public String getLocation() {
        return location;
    }

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        if(getCurrentTime().isAfter(this.openingTime) && getCurrentTime().isBefore(this.closingTime)){
            return true;
        } else{
            return false;
        }
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }

    public void addMenuSelection(String itemName){
        Item itemToBeSelected = findItemByName(itemName);
        selectedMenu.add(itemToBeSelected);
        calculateTotalPriceOfMenu();
    }

    public void removeMenuSelection(String itemName){
        Item itemToBeUnSelected = findItemByName(itemName);
        selectedMenu.remove(itemToBeUnSelected);
        calculateTotalPriceOfMenu();
    }

    public int calculateTotalPriceOfMenu(){
        int totalPrice = 0;
        for (Item selectedItem: selectedMenu){
            for(Item item: menu) {
                if(item.getName().equals(selectedItem.getName()))
                    totalPrice += selectedItem.getPrice();
            }
        }
        return totalPrice;
    }

    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

}
