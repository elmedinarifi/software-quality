package com.nhlstenden.factorypattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class SlideTest {
    private Slide slide;
    private BufferedImage bufferedImage;
    private Graphics graphics;
    private Rectangle area;

    @BeforeEach
    void setup() {
        this.slide = new Slide();
        this.slide.setTitle("Test Slide");
        this.bufferedImage = new BufferedImage(Slide.WIDTH, Slide.HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.graphics = this.bufferedImage.getGraphics();
        this.area = new Rectangle(0, 0, Slide.WIDTH, Slide.HEIGHT);
        Style.createStyles();
    }

    @Test
    void draw_ShouldModifyBufferedImage_WhenSlideHasItems() {
        this.slide.append(new TextItem(1, "Test item"));

        this.graphics.setColor(Color.WHITE);
        this.graphics.fillRect(0, 0, Slide.WIDTH, Slide.HEIGHT);

        int initialPixel = this.bufferedImage.getRGB(50, 50);
        this.slide.draw(this.graphics, this.area, null);
        int modifiedPixel = this.bufferedImage.getRGB(50, 50);

        assertNotEquals(initialPixel, modifiedPixel);
    }


    @Test
    void draw_ShouldNotThrowException_WhenSlideIsEmpty() {
        assertDoesNotThrow(() -> this.slide.draw(this.graphics, this.area, null));
    }

    @Test
    void append_ShouldIncreaseSize() {
        assertEquals(0, this.slide.getSize());
        this.slide.append(new TextItem(2, "Nieuw item"));
        assertEquals(1, this.slide.getSize());
    }
}
