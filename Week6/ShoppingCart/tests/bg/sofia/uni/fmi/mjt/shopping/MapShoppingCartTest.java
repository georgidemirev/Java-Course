package bg.sofia.uni.fmi.mjt.shopping;

import bg.sofia.uni.fmi.mjt.shopping.item.Apple;
import bg.sofia.uni.fmi.mjt.shopping.item.Chocolate;
import bg.sofia.uni.fmi.mjt.shopping.item.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MapShoppingCartTest {

    private static final String NAME_APPLE = "apple";
    private static final String NAME_CHOCOLATE = "chocolate";
    private static final String DESCRIPTION_APPLE = "it is good";
    private static final String DESCRIPTION_CHOCOLATE = "it is tasty";
    private static final double PRICE_APPLE = 2.25;
    private static final double PRICE_CHOCOLATE = 12.50;
    private static final double DELTA = 0.001;

    private Item apple = new Apple(NAME_APPLE, DESCRIPTION_APPLE, PRICE_APPLE);
    private Item chocolate = new Chocolate(NAME_CHOCOLATE, DESCRIPTION_CHOCOLATE, PRICE_CHOCOLATE);

    private MapShoppingCart mapShoppingCart;

    @Before
    public void setUp() throws Exception {
        mapShoppingCart = new MapShoppingCart();
    }

    @Test
    public void testGetFromEmptyCart() {
        Assert.assertEquals(Collections.emptySet(), mapShoppingCart.getUniqueItems());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddingNullElement() {
        mapShoppingCart.addItem(null);
    }

    @Test
    public void testSuccessfulAdd() {
        mapShoppingCart.addItem(apple);
        mapShoppingCart.addItem(chocolate);
        mapShoppingCart.addItem(apple);
    }

    @Test(expected = ItemNotFoundException.class)
    public void testRemovingNotExistingItem() throws ItemNotFoundException {
        mapShoppingCart.addItem(apple);
        mapShoppingCart.removeItem(chocolate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemovingNullItem() throws ItemNotFoundException {
        mapShoppingCart.removeItem(null);
    }

    @Test
    public void testRemovingExistingItem() throws ItemNotFoundException {
        mapShoppingCart.addItem(apple);
        mapShoppingCart.addItem(apple);
        mapShoppingCart.removeItem(apple);
        mapShoppingCart.removeItem(apple);
    }

    @Test
    public void testGetTotal() {
        mapShoppingCart.addItem(apple);
        mapShoppingCart.addItem(chocolate);
        Assert.assertEquals(PRICE_APPLE + PRICE_CHOCOLATE, mapShoppingCart.getTotal(), DELTA);
    }

    @Test
    public void testSorting() {
        mapShoppingCart.addItem(apple);
        mapShoppingCart.addItem(apple);
        mapShoppingCart.addItem(chocolate);
        mapShoppingCart.addItem(chocolate);
        mapShoppingCart.addItem(chocolate);

        List<Item> expectedItems = new ArrayList<>();

        expectedItems.add(chocolate);
        expectedItems.add(apple);

        Assert.assertArrayEquals(expectedItems.toArray(), mapShoppingCart.getSortedItems().toArray());
    }
}