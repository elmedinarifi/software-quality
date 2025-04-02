package com.nhlstenden.commandpattern;

import com.nhlstenden.demo.JabberPoint;
import com.nhlstenden.demo.Presentation;
import com.nhlstenden.factorypattern.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenFileCommandTest {

    private JabberPoint jabberPoint;
    private Presentation presentation;
    private OpenFileCommand openFileCommand;
    private Receiver receiver;
    private final String filename = "testfile.xml";

    @BeforeEach
    void setup() {
        this.jabberPoint = new JabberPoint();
        this.presentation = new Presentation();
        this.receiver = new Receiver(this.presentation, this.jabberPoint);
        this.openFileCommand = new OpenFileCommand(this.receiver, this.filename);

    }

    @Test
    void testExecute_OpenTestFile_ShouldNotThrowException() {
        assertDoesNotThrow(() -> this.openFileCommand.execute());
    }
}