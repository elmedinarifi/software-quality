package com.nhlstenden.commandpattern;

import com.nhlstenden.jabberpoint.JabberPoint;
import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.factorypattern.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class KeyControllerTest {
    private Receiver receiver;
    private KeyController keyController;
    private Presentation presentation;
    private JabberPoint jabberPoint;

    @BeforeEach
    void setup() {
        this.jabberPoint = new JabberPoint();
        this.presentation = new Presentation();
        this.receiver = new Receiver(this.presentation, this.jabberPoint);

        this.keyController = new KeyController(this.receiver);

        this.presentation.append(new Slide());
        this.presentation.append(new Slide());
        this.presentation.append(new Slide());
        this.presentation.append(new Slide());
    }

    private void simulateKeyPress(int keyCode) {
        KeyEvent keyEvent = new KeyEvent(
                new JFrame(),
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0,
                keyCode,
                KeyEvent.CHAR_UNDEFINED
        );
        this.keyController.keyPressed(keyEvent);
    }

    @Test
    void keyPressed_NextSlideFirstCommand_ShouldReturn3() {
        this.presentation.setSlideNumber(2);

        simulateKeyPress(KeyEvent.VK_PAGE_DOWN);
        assertEquals(3, this.presentation.getSlideNumber());
    }

    @Test
    void keyPressed_NextSlideSecondCommand_ShouldReturn3() {
        this.presentation.setSlideNumber(2);

        simulateKeyPress(KeyEvent.VK_DOWN);
        assertEquals(3, this.presentation.getSlideNumber());
    }

    @Test
    void keyPressed_NextSlideThirdCommand_ShouldReturn3() {
        this.presentation.setSlideNumber(2);

        simulateKeyPress(KeyEvent.VK_ENTER);
        assertEquals(3, this.presentation.getSlideNumber());
    }

    @Test
    void keyPressed_NextSlideFourthCommand_ShouldReturn3() {
        this.presentation.setSlideNumber(2);

        simulateKeyPress((int) '+');
        assertEquals(3, this.presentation.getSlideNumber());
    }



    @Test
    void keyPressed_PrevSlideFirstCommand_ShouldReturn2() {
        this.presentation.setSlideNumber(3);

        simulateKeyPress(KeyEvent.VK_PAGE_UP);
        assertEquals(2, this.presentation.getSlideNumber());
    }

    @Test
    void keyPressed_PrevSlideSecondCommand_ShouldReturn2() {
        this.presentation.setSlideNumber(3);

        simulateKeyPress(KeyEvent.VK_UP);
        assertEquals(2, this.presentation.getSlideNumber());
    }

    @Test
    void keyPressed_PrevSlideThirdCommand_ShouldReturn2() {
        this.presentation.setSlideNumber(3);
        simulateKeyPress((int) '-');
        assertEquals(2, this.presentation.getSlideNumber());
    }

    @Test
    void keyPressed_NoCommand_ShouldReturn2() {
        this.presentation.setSlideNumber(2);
        simulateKeyPress(KeyEvent.VK_A);
        assertEquals(2, this.presentation.getSlideNumber());
    }
}
