package com.nhlstenden.factorypattern;

import com.nhlstenden.commandpattern.Receiver;
import com.nhlstenden.jabberpoint.JabberPoint;
import com.nhlstenden.jabberpoint.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SlideViewerFrameTest {
    private SlideViewerFrame slideViewerFrame;
    private Receiver receiver;
    private Slide slide;
    private Presentation presentation;
    private JabberPoint jabberPoint;

    @BeforeEach
    void setup() {
        this.jabberPoint = new JabberPoint();
        this.presentation = new Presentation();
        this.receiver = new Receiver(this.presentation, this.jabberPoint);
        this.slide = new Slide();
        this. slide.setTitle("Test Slide");
        this.slideViewerFrame = new SlideViewerFrame("Test", this.receiver);
    }

    @Test
    void setupWindow_ShouldSetTitleCorrectly() {
        assertEquals("Jabberpoint 1.6 - OU", this.slideViewerFrame.getTitle());
    }

    @Test
    void setupWindow_ShouldSetSizeCorrectly() {
        assertEquals(new Dimension(SlideViewerFrame.WIDTH, SlideViewerFrame.HEIGHT), this.slideViewerFrame.getSize());
    }

    @Test
    void setupWindow_ShouldAddSlideViewerComponent() {
        assertNotNull(this.slideViewerFrame.getContentPane().getComponent(0));
    }
}
