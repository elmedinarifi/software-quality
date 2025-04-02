package com.nhlstenden.commandpattern;

import com.nhlstenden.demo.JabberPoint;
import com.nhlstenden.demo.Presentation;
import com.nhlstenden.factorypattern.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrevSlideCommandTest {

    private JabberPoint jabberPoint;
    private Presentation presentation;
    private PrevSlideCommand prevSlideCommand;
    private Receiver receiver;

    @BeforeEach
    void setup() {
        this.jabberPoint = new JabberPoint();
        this.presentation = new Presentation();
        this.receiver = new Receiver(this.presentation, this.jabberPoint);
        this.prevSlideCommand = new PrevSlideCommand(this.receiver);

        this.presentation.append(new Slide());
        this.presentation.append(new Slide());
        this.presentation.append(new Slide());
    }

    @Test
    void testExecute_PrevSlide_ShouldReturn2() {
        this.presentation.setSlideNumber(3);
        this.prevSlideCommand.execute();
        assertEquals(2, this.presentation.getSlideNumber());
    }
}