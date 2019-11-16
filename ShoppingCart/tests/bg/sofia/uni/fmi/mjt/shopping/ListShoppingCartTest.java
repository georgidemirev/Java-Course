package bg.sofia.uni.fmi.mjt.shopping;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

public class ListShoppingCartTest {

    private ListShoppingCart listShoppingCart;

    @Before
    public void setUp() throws Exception {
        listShoppingCart = new ListShoppingCart();
    }

    @Test
    public void testGetFromEmptyCart() {
        Assert.assertEquals(Collections.emptySet(), listShoppingCart.getUniqueItems());
    }

    @Test
    public void getSortedItems() {
    }

    @Test
    public void addItem() {
    }

    @Test
    public void removeItem() {
    }

    @Test
    public void getTotal() {
    }
}
