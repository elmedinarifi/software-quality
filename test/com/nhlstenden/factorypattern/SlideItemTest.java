package com.nhlstenden.factorypattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class SlideItemTest {

    @BeforeEach
    void setup() {
        Style.createStyles();
    }

    // Aangepaste BitmapItem die een dummy BufferedImage gebruikt
    class TestBitmapItem extends BitmapItem {
        public TestBitmapItem(int level, String name) {
            super(level, name);
            this.setBufferedImage(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB));
        }
    }

    @Test
    void testRender_ShouldRenderTextItem() {
        SlideItem slideItem = new TextItem(1, "Text item");

        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        slideItem.render(10, 10, 1.0f, g, null);

        Style style = Style.getStyle(slideItem.getLevel());
        assertNotNull(style);
        assertEquals(Color.blue, style.color);
        assertEquals(40, style.font.getSize());
    }

    @Test
    void testGetBoundingBox_ShouldReturnRectangleForTextItem() {
        SlideItem slideItem = new TextItem(1, "Text item");
        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();

        Rectangle boundingBox = slideItem.getBoundingBox(g, null, 1.0f, Style.getStyle(1));
        assertNotNull(boundingBox);
        assertTrue(boundingBox.width > 0);
        assertTrue(boundingBox.height > 0);
    }

    @Test
    void testRender_ShouldRenderBitmapItem() {
        SlideItem slideItem = new TestBitmapItem(2, "src/com/nhlstenden/resources/JabberPoint.jpg");

        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        slideItem.render(10, 10, 1.0f, g, null);

        Style style = Style.getStyle(slideItem.getLevel());
        assertNotNull(style);
        assertEquals(Color.black, style.color);
        assertEquals(36, style.font.getSize());
    }

    @Test
    void testGetBoundingBox_ShouldReturnRectangleForBitmapItem() {
        SlideItem slideItem = new TestBitmapItem(2, "src/com/nhlstenden/resources/JabberPoint.jpg");
        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();

        Rectangle boundingBox = slideItem.getBoundingBox(g, null, 1.0f, Style.getStyle(2));
        assertNotNull(boundingBox);
        assertTrue(boundingBox.width > 0);
        assertTrue(boundingBox.height > 0);
    }
}