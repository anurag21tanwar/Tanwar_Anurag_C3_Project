import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;

class RestaurantTest {
    Restaurant restaurant;

    @BeforeEach
    public void init() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = Mockito.spy(new Restaurant("Amelie's cafe","Chennai", openingTime, closingTime));

        Mockito.when(restaurant.getCurrentTime()).thenReturn(LocalTime.parse("11:00:00"));

        assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = Mockito.spy(new Restaurant("Amelie's cafe","Chennai", openingTime, closingTime));

        Mockito.when(restaurant.getCurrentTime()).thenReturn(LocalTime.parse("23:00:00"));
        assertFalse(restaurant.isRestaurantOpen());

        Mockito.when(restaurant.getCurrentTime()).thenReturn(LocalTime.parse("09:00:00"));
        assertFalse(restaurant.isRestaurantOpen());
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
    @Test
    public void calculate_selected_items_order_should_return_correct_total_price() {
        double orderTotal = restaurant.calculateOrderTotal(restaurant.getMenu());
        assertEquals(orderTotal, 119+269);
    }
    @Test
    public void calculate_selected_items_order_should_return_0_when_no_item_is_selected() {
        List<Item> emptyList = Collections.emptyList();
        double orderTotal = restaurant.calculateOrderTotal(emptyList);
        assertEquals(orderTotal, 0);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}