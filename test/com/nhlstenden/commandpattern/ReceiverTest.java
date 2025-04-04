package com.nhlstenden.commandpattern;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nhlstenden.factorypattern.Slide;
import com.nhlstenden.jabberpoint.JabberPoint;
import com.nhlstenden.jabberpoint.core.DefaultPresentationData;

class ReceiverTest {
    private Receiver receiver;
    private DefaultPresentationData presentation;
    private JabberPoint jabberPoint;
    private final String filename = "testfile.xml";

    @BeforeEach
    void setup() {
        this.jabberPoint = new JabberPoint();
        this.presentation = new DefaultPresentationData();
        this.receiver = new Receiver(presentation, jabberPoint);
        
        // Add test slides
        this.presentation.addSlide(new Slide());
        this.presentation.addSlide(new Slide());
        this.presentation.addSlide(new Slide());
    }

    @Test
    void testSaveFileCommand() {
        assertDoesNotThrow(() -> this.receiver.saveFileCommand(filename));
    }

    @Test
    void testOpenFileCommand() {
        assertDoesNotThrow(() -> this.receiver.openFileCommand(filename));
    }

    @Test
    void testPrevSlideCommand() {
        this.presentation.setCurrentSlideNumber(2);
        this.receiver.prevSlideCommand();
        assertEquals(1, this.presentation.getCurrentSlideNumber());
    }

    @Test
    void testNextSlideCommand() {
        this.presentation.setCurrentSlideNumber(1);
        this.receiver.nextSlideCommand();
        assertEquals(2, this.presentation.getCurrentSlideNumber());
    }

    @Test
    void testNewPageCommand() {
        this.receiver.newPageCommand();
        assertEquals(0, this.presentation.getSize());
        assertEquals(-1, this.presentation.getCurrentSlideNumber());
    }

    @Test
    void testGoToCommand() {
        this.receiver.goToCommand(1);
        assertEquals(1, this.presentation.getCurrentSlideNumber());
    }
}