package com.nhlstenden.factorypattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import static org.junit.jupiter.api.Assertions.*;

class SlideItemTest {

    @BeforeEach
    void setup() {
        // Initialiseer de styles array handmatig in je test.
        Style.createStyles(); // Zorgt ervoor dat de stijlen correct worden geïnitialiseerd
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
        // Maak een voorbeeld SlideItem, bijvoorbeeld een TextItem
        SlideItem slideItem = new TextItem(1, "This is a text item");

        // Roep de render methode aan met een BufferedImage als Graphics
        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        slideItem.render(10, 10, 1.0f, g, null);

        // Controleer of de stijl correct is toegewezen
        Style style = Style.getStyle(slideItem.getLevel());
        assertNotNull(style, "De stijl van het item mag niet null zijn.");
        assertEquals(Color.blue, style.color, "De kleur moet blauw zijn voor niveau 1.");
        assertEquals(40, style.font.getSize(), "Het lettertype moet een grootte van 40 hebben voor niveau 1.");
    }

    @Test
    void testGetBoundingBox_ShouldReturnRectangleForTextItem() {
        SlideItem slideItem = new TextItem(1, "This is a text item");
        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();

        Rectangle boundingBox = slideItem.getBoundingBox(g, null, 1.0f, Style.getStyle(1));
        assertNotNull(boundingBox, "De bounding box mag niet null zijn.");
        assertTrue(boundingBox.width > 0, "De breedte van de bounding box moet groter dan 0 zijn.");
        assertTrue(boundingBox.height > 0, "De hoogte van de bounding box moet groter dan 0 zijn.");
    }

    @Test
    void testRender_ShouldRenderBitmapItem() {
        SlideItem slideItem = new TestBitmapItem(2, "image.png");

        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        slideItem.render(10, 10, 1.0f, g, null);

        Style style = Style.getStyle(slideItem.getLevel());
        assertNotNull(style, "De stijl van het item mag niet null zijn.");
        assertEquals(Color.black, style.color, "De kleur moet zwart zijn voor niveau 2.");
        assertEquals(36, style.font.getSize(), "Het lettertype moet een grootte van 36 hebben voor niveau 2.");
    }

    @Test
    void testGetBoundingBox_ShouldReturnRectangleForBitmapItem() {
        SlideItem slideItem = new TestBitmapItem(2, "image.png");
        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();

        Rectangle boundingBox = slideItem.getBoundingBox(g, null, 1.0f, Style.getStyle(2));
        assertNotNull(boundingBox, "De bounding box mag niet null zijn.");
        assertTrue(boundingBox.width > 0, "De breedte van de bounding box moet groter dan 0 zijn.");
        assertTrue(boundingBox.height > 0, "De hoogte van de bounding box moet groter dan 0 zijn.");
    }
}