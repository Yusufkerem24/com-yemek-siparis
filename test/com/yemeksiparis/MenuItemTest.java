package com.yemeksiparis;

import org.junit.Test;
import static org.junit.Assert.*;

public class MenuItemTest {

    @Test
    public void menuItem_shouldReturnCorrectNameAndPrice() {
        MenuItem item = new MenuItem("Pizza", 120.0);

        assertEquals("Pizza", item.getName());
        assertEquals(120.0, item.getPrice(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativePrice_shouldThrowException() {
        new MenuItem("Test", -10.0);
    }
}
