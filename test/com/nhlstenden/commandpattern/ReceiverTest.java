package com.nhlstenden.commandpattern;

import com.nhlstenden.demo.JabberPoint;
import com.nhlstenden.demo.Presentation;
import com.nhlstenden.demo.XMLAccessor;
import com.nhlstenden.factorypattern.Slide;
import com.nhlstenden.factorypattern.SlideItemFactory;
import org.junit.jupiter.api.BeforeEach;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ReceiverTest {
    private Receiver receiver;
    private Presentation presentation;
    private JabberPoint jabberPoint;
    private XMLAccessor xmlAccessor;
    private SlideItemFactory slideItemFactory;

    @BeforeEach
    void setup() {
        this.jabberPoint = new JabberPoint();
        this.presentation = new Presentation();
        this.receiver = new Receiver(this.presentation, this.jabberPoint);
        this.presentation.append(new Slide());
        this.presentation.append(new Slide());
        this.presentation.append(new Slide());
        this.slideItemFactory = new SlideItemFactory();

        this.xmlAccessor = new XMLAccessor(this.slideItemFactory);
    }

    @org.junit.jupiter.api.Test
    void testSaveFileCommand() {
        String filename = "testfile.xml";
        assertDoesNotThrow(() -> this.receiver.saveFileCommand(filename));
    }

    @org.junit.jupiter.api.Test
    void testOpenFileCommand() {
        String filename = "testfile.xml";
        assertDoesNotThrow(() -> this.receiver.openFileCommand(filename));
    }

    @org.junit.jupiter.api.Test
    void testGoToCommandFirstPage() {

        this.presentation.setSlideNumber(0);
        this.receiver.goToCommand(1);
        assertEquals(1, this.presentation.getSlideNumber());
    }

    @org.junit.jupiter.api.Test
    void testGoToCommandSecondPage() {
        this.presentation.setSlideNumber(0);
        this.receiver.goToCommand(2);
        assertEquals(2, this.presentation.getSlideNumber());
    }

    @org.junit.jupiter.api.Test
    void testGoToCommandInvalidPageThrowsException () {
        this.presentation.setSlideNumber(0);
        assertThrows(IllegalArgumentException.class, () -> {
            this.receiver.goToCommand(500);
        });    }

    @org.junit.jupiter.api.Test
    void testPrevSlideCommand() {
        this.presentation.setSlideNumber(2);
        this.receiver.prevSlideCommand();
        assertEquals(1, this.presentation.getSlideNumber());
    }

    @org.junit.jupiter.api.Test
    void testNextSlideCommand() {
        this.presentation.setSlideNumber(0);
        this.receiver.nextSlideCommand();
        assertEquals(1, this.presentation.getSlideNumber());
    }

    @org.junit.jupiter.api.Test
    void testNewPageCommand() {
        this.presentation.setSlideNumber(2);
        this.receiver.newPageCommand();
        assertEquals(-1, this.presentation.getSlideNumber());
    }

    @org.junit.jupiter.api.Test
    void testGetPresentation() {
        assertEquals(this.presentation, this.receiver.getPresentation());
    }

    @org.junit.jupiter.api.Test
    void testSetPresentation() {
        Presentation newPresentation = new Presentation();
        this.receiver.setPresentation(newPresentation);
        assertEquals(newPresentation, this.receiver.getPresentation());
    }

    @org.junit.jupiter.api.Test
    void testGetJabberPoint() {
        assertEquals(this.jabberPoint, this.receiver.getJabberPoint());
    }

    @org.junit.jupiter.api.Test
    void testSetJabberPoint() {
        JabberPoint newJabberPoint = new JabberPoint();
        this.receiver.setJabberPoint(newJabberPoint);
        assertEquals(newJabberPoint, this.receiver.getJabberPoint());
    }
}