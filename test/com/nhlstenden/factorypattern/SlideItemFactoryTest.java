package com.nhlstenden.factorypattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlideItemFactoryTest {

    private SlideItemFactory slideItemFactory;

    @BeforeEach
    void setup() {
        this.slideItemFactory = new SlideItemFactory();
    }

    @Test
    void createSlideItem_ShouldReturnTextItem() {
        SlideItem textItem = this.slideItemFactory.createSlideItem(SlideItemTypes.TEXT_ITEM, 1, "Test Content");

        assertTrue(textItem instanceof TextItem);
        assertEquals("Test Content", ((TextItem) textItem).getText());
    }

    @Test
    void createSlideItem_ShouldReturnBitmapItem() {
        SlideItem bitmapItem = this.slideItemFactory.createSlideItem(SlideItemTypes.BITMAP_ITEM, 2, "src/com/nhlstenden/resources/JabberPoint.jpg");

        assertTrue(bitmapItem instanceof BitmapItem);
        assertEquals("src/com/nhlstenden/resources/JabberPoint.jpg", ((BitmapItem) bitmapItem).getName());
    }
}
