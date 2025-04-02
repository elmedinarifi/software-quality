package com.nhlstenden.commandpattern;

import com.nhlstenden.demo.JabberPoint;
import com.nhlstenden.demo.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaveFileCommandTest {

    private JabberPoint jabberPoint;
    private Presentation presentation;
    private SaveFileCommand saveFileCommand;
    private Receiver receiver;
    private final String filename = "testfile.xml";

    @BeforeEach
    void setup() {
        this.jabberPoint = new JabberPoint();
        this.presentation = new Presentation();
        this.receiver = new Receiver(this.presentation, this.jabberPoint);
        this.saveFileCommand = new SaveFileCommand(this.receiver, this.filename);

    }

    @Test
    void testExecute_SaveFileCommand_ShouldNotThrow() {
        assertDoesNotThrow(() -> this.saveFileCommand.execute());
    }
}