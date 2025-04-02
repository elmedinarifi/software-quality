package com.nhlstenden.commandpattern;

import com.nhlstenden.demo.JabberPoint;
import com.nhlstenden.demo.Presentation;
import com.nhlstenden.factorypattern.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewPageCommandTest {
    private JabberPoint jabberPoint;
    private Presentation presentation;
    private NewPageCommand newPageCommand;
    private Receiver receiver;

    @BeforeEach
    void setup() {
        this.jabberPoint = new JabberPoint();
        this.presentation = new Presentation();
        this.receiver = new Receiver(this.presentation, this.jabberPoint);
        this.newPageCommand = new NewPageCommand(this.receiver);

        this.presentation.append(new Slide());
        this.presentation.append(new Slide());
        this.presentation.append(new Slide());
    }

    @Test
    void testExecute_NewPage_ShouldStartOnPageNegative1() {
        this.newPageCommand.execute();

        assertEquals(-1, this.presentation.getSlideNumber());
    }
}