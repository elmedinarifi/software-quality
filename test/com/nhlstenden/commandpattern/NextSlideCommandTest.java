package com.nhlstenden.commandpattern;

import com.nhlstenden.demo.JabberPoint;
import com.nhlstenden.demo.Presentation;
import com.nhlstenden.factorypattern.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NextSlideCommandTest {
    private JabberPoint jabberPoint;
    private Presentation presentation;
    private NextSlideCommand nextSlideCommand;
    private Receiver receiver;

    @BeforeEach
    void setup() {
        this.jabberPoint = new JabberPoint();
        this.presentation = new Presentation();
        this.receiver = new Receiver(this.presentation, this.jabberPoint);
        this.nextSlideCommand = new NextSlideCommand(this.receiver);

        this.presentation.append(new Slide());
        this.presentation.append(new Slide());
        this.presentation.append(new Slide());
    }

    @Test
    void execute() {
        this.presentation.setSlideNumber(0);
        this.nextSlideCommand.execute();

        assertEquals(1, this.presentation.getSlideNumber());
    }
}