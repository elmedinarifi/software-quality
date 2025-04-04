package com.nhlstenden.commandpattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nhlstenden.factorypattern.Slide;
import com.nhlstenden.jabberpoint.JabberPoint;
import com.nhlstenden.jabberpoint.core.DefaultPresentationData;

class NextSlideCommandTest {
    private JabberPoint jabberPoint;
    private DefaultPresentationData presentation;
    private NextSlideCommand nextSlideCommand;
    private Receiver receiver;

    @BeforeEach
    void setup() {
        this.jabberPoint = new JabberPoint();
        this.presentation = new DefaultPresentationData();
        this.receiver = new Receiver(presentation, jabberPoint);
        this.nextSlideCommand = new NextSlideCommand(receiver);

        this.presentation.addSlide(new Slide());
        this.presentation.addSlide(new Slide());
        this.presentation.addSlide(new Slide());
    }

    @Test
    void testExecute() {
        this.presentation.setCurrentSlideNumber(1);
        this.nextSlideCommand.execute();
        assertEquals(2, this.presentation.getCurrentSlideNumber());
    }
}