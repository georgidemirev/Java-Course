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

public class ListShoppingCartTest {

    private static final String NAME_APPLE = "apple";
    private static final String NAME_CHOCOLATE = "chocolate";
    private static final String DESCRIPTION_APPLE = "it is good";
    private static final String DESCRIPTION_CHOCOLATE = "it is tasty";
    private static final double PRICE_APPLE = 2.25;
    private static final double PRICE_CHOCOLATE = 12.50;
    private static final double DELTA = 0.001;

    private Item apple = new Apple(NAME_APPLE, DESCRIPTION_APPLE, PRICE_APPLE);
    private Item chocolate = new Chocolate(NAME_CHOCOLATE, DESCRIPTION_CHOCOLATE, PRICE_CHOCOLATE);

    private ListShoppingCart listShoppingCart;

    @Before
    public void setUp() throws Exception {
        listShoppingCart = new ListShoppingCart();
    }

    @Test
    public void testGetFromEmptyCart() {
        Assert.assertEquals(Collections.emptySet(), listShoppingCart.getUniqueItems());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddingNullElement() {
        listShoppingCart.addItem(null);
    }

    @Test
    public void testSuccessfulAdd() {
        listShoppingCart.addItem(apple);
        listShoppingCart.addItem(chocolate);
        listShoppingCart.addItem(apple);
    }

    @Test(expected = ItemNotFoundException.class)
    public void testRemovingNotExistingItem() throws ItemNotFoundException {
        listShoppingCart.addItem(apple);
        listShoppingCart.removeItem(chocolate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemovingNullItem() throws ItemNotFoundException {
        listShoppingCart.removeItem(null);
    }

    @Test
    public void testRemovingExistingItem() throws ItemNotFoundException {
        listShoppingCart.addItem(apple);
        listShoppingCart.addItem(apple);
        listShoppingCart.removeItem(apple);
        listShoppingCart.removeItem(apple);
    }

    @Test
    public void testGetTotal() {
        listShoppingCart.addItem(apple);
        listShoppingCart.addItem(chocolate);
        Assert.assertEquals(PRICE_APPLE + PRICE_CHOCOLATE, listShoppingCart.getTotal(), DELTA);
    }

    @Test
    public void testSorting() {
        listShoppingCart.addItem(apple);
        listShoppingCart.addItem(apple);
        listShoppingCart.addItem(chocolate);
        listShoppingCart.addItem(chocolate);
        listShoppingCart.addItem(chocolate);
        List<Item> expectedItems = new ArrayList<>();

        expectedItems.add(chocolate);
        expectedItems.add(apple);

        Assert.assertArrayEquals(expectedItems.toArray(), listShoppingCart.getSortedItems().toArray());
    }
}
