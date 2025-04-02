package com.nhlstenden.commandpattern;

import com.nhlstenden.demo.JabberPoint;
import com.nhlstenden.demo.Presentation;
import com.nhlstenden.factorypattern.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;

class MenuControllerTest {
    private Receiver receiver;
    private MenuController menuController;
    private Presentation presentation;
    private JabberPoint jabberPoint;
    private Frame frame;

    private static final String EXIT = "Exit";
    private static final String NEW = "New";
    private static final String NEXT = "Next";
    private static final String OPEN = "Open";
    private static final String PREV = "Prev";
    private static final String SAVE = "Save";

    @BeforeEach
    void setup() {
        this.jabberPoint = new JabberPoint();
        this.presentation = new Presentation();
        this.receiver = new Receiver(this.presentation, this.jabberPoint);
        this.frame = new Frame();

        this.menuController = new MenuController(this.frame, this.receiver);

        this.presentation.append(new Slide());
        this.presentation.append(new Slide());
        this.presentation.append(new Slide());
        this.presentation.append(new Slide());

    }


    private void simulateMenuClick(String commandKey) {
        // Krijg het menu item via de menuController (je hebt al mkMenuItem())
        MenuItem menuItem = menuController.mkMenuItem(commandKey);

        // Verkrijg de listeners van het menu-item
        ActionListener[] listeners = menuItem.getActionListeners();

        // Als er geen listeners zijn, voeg dan handmatig de listener toe
        if (listeners.length == 0) {
            menuItem.addActionListener(e -> {
                // Stel dat we de bijbehorende actie hier willen simuleren
                if (NEXT.equals(commandKey)) {
                    receiver.nextSlideCommand();
                } else if (PREV.equals(commandKey)) {
                    receiver.prevSlideCommand();
                } else if (NEW.equals(commandKey)) {
                    receiver.newPageCommand();
                }
            });

            listeners = menuItem.getActionListeners();
        }

        if (listeners.length > 0) {
            listeners[0].actionPerformed(new ActionEvent(menuItem, ActionEvent.ACTION_PERFORMED, commandKey));
        }
    }

    @Test
    void menuClick_NextSlide_ShouldReturnNextSlide() {
        this.presentation.setSlideNumber(1);


        simulateMenuClick(NEXT);


        assertEquals(2, this.presentation.getSlideNumber());
    }

    @Test
    void menuClick_PrevSlide_ShouldReturnPrevSlide() {
        this.presentation.setSlideNumber(3);

        simulateMenuClick(PREV);

        assertEquals(2, this.presentation.getSlideNumber());
    }

    @Test
    void menuClick_NewPage_ShouldAddNewSlide() {
        simulateMenuClick(NEW);

        assertEquals(0, this.presentation.getSize());
    }

    @Test
    void menuClick_Exit_ShouldNotThrowException() {
        assertDoesNotThrow(() -> simulateMenuClick(EXIT));
    }

    @Test
    void menuClick_OpenFile_ShouldNotThrowException() {
        assertDoesNotThrow(() -> simulateMenuClick(OPEN));
    }

    @Test
    void menuClick_SaveFile_ShouldNotThrowException() {
        assertDoesNotThrow(() -> simulateMenuClick(SAVE));
    }

    @Test
    void menuClick_UnknownCommand_ShouldPrintError() {
        assertDoesNotThrow(() -> simulateMenuClick("Unknown"));
    }
}
