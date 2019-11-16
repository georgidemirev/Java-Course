package bg.sofia.uni.fmi.mjt.shopping.item;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {

    private static final String NAME_APPLE = "apple";
    private static final String NAME_CHOCOLATE = "chocolate";
    private static final String DESCRIPTION_APPLE = "it is good";
    private static final String DESCRIPTION_CHOCOLATE = "it is tasty";
    private static final double PRICE_APPLE = 2.25;
    private static final double PRICE_CHOCOLATE = 12.50;

    private Apple apple;
    private Item chocolate;

    @Before
    public void setUp() throws Exception {
        apple = new Apple(NAME_APPLE, "it is good", 2.5);
        chocolate = new Chocolate("chocolate", "it is tasty", 12.50);
    }

    @Test
    public void getName() {
        Assert.assertEquals(NAME_APPLE, apple.getName());
        Assert.assertEquals(NAME_CHOCOLATE, chocolate.getName());
    }

    @Test
    public void getDescription() {
        Assert.assertEquals(DESCRIPTION_APPLE, apple.getDescription());
        Assert.assertEquals(DESCRIPTION_CHOCOLATE, chocolate.getDescription());
    }

    @Test
    public void getPrice() {
        Assert.assertEquals(PRICE_APPLE, apple.getPrice(), 2);
        Assert.assertEquals(PRICE_CHOCOLATE, chocolate.getPrice(), 2);
    }

    @Test
    public void testEqualsAndHashCode(){
        Assert.assertFalse(apple.equals(chocolate) && chocolate.equals(apple));
        Assert.assertNotEquals(apple.hashCode(), chocolate.hashCode());

        Item apple2 = new Apple(apple.getName(),apple.getDescription(),apple.getPrice());
        Assert.assertEquals(apple.hashCode(), apple2.hashCode());
        Assert.assertEquals(apple, apple2);

        Item chocolate2 = new Chocolate(chocolate.getName(),chocolate.getDescription(),chocolate.getPrice());
        Assert.assertEquals(chocolate.hashCode(), chocolate2.hashCode());
        Assert.assertEquals(chocolate, chocolate2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppleConstructorWithIllegalArguments(){
        Item apple2 = new Apple(null,null,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChocolateConstructorWithIllegalArguments(){
        Item chocolate2 = new Chocolate(null,null,0);
    }

}
