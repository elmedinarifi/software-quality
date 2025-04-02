package com.nhlstenden.commandpattern;

import com.nhlstenden.demo.JabberPoint;
import com.nhlstenden.demo.Presentation;
import com.nhlstenden.factorypattern.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GoToCommandTest {
    private Receiver receiver;
    private GoToCommand goToCommand;
    private Presentation presentation;
    private JabberPoint jabberPoint;

    @BeforeEach
    void setup() {
        this.presentation = new Presentation();
        this.jabberPoint = new JabberPoint();
        this.receiver = new Receiver(this.presentation, this.jabberPoint);
        this.goToCommand = new GoToCommand(this.receiver, 2);

        this.presentation.append(new Slide());
        this.presentation.append(new Slide());
        this.presentation.append(new Slide());
    }
    @Test
    void execute() {
        this.goToCommand.execute();

        assertEquals(2, this.receiver.getPresentation().getSlideNumber());
    }
}