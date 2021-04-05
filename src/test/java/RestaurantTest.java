import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    @BeforeEach
    private void AddRestaurant() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("Pastry d'or", 180);
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
//    @BeforeEach
//    public LocalTime setDummyCurrentTime(){
//        return LocalTime.parse("09:30:00");
//    }
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("11:30:00"));
        assertTrue(spyRestaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("09:30:00"));
        assertFalse(spyRestaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>CALCULATING TOTAL PRICE OF MENU ITEMS SELECTED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    public void no_menu_items_selected_should_return_zero_price(){
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        assertEquals(0,spyRestaurant.calculateTotalPriceOfMenu());
    }

    @Test
    public void unselect_menu_item_should_reduce_total_price_of_menu_selected() throws restaurantNotFoundException {

        Restaurant spyRestaurant = Mockito.spy(restaurant);
        spyRestaurant.addMenuSelection("Sweet corn soup");
        spyRestaurant.addMenuSelection("Vegetable lasagne");
        assertEquals(269+119,spyRestaurant.calculateTotalPriceOfMenu());
        spyRestaurant.removeMenuSelection("Vegetable lasagne");
        assertEquals(119,spyRestaurant.calculateTotalPriceOfMenu());
    }

    @Test
    public void select_menuItem_should_increase_total_price_of_menu_selected(){

        Restaurant spyRestaurant = Mockito.spy(restaurant);
        spyRestaurant.addMenuSelection("Sweet corn soup");
        assertEquals(119,spyRestaurant.calculateTotalPriceOfMenu());
        spyRestaurant.addMenuSelection("Vegetable lasagne");
        assertEquals(269+119,spyRestaurant.calculateTotalPriceOfMenu());
    }
    //<<<<<<<<<<<<<<<<<<<<CALCULATING TOTAL PRICE OF MENU ITEMS SELECTED>>>>>>>>>>>>>>>>>>>>>>>>>>
}