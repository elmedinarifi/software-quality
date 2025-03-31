package com.nhlstenden.commandpattern;

import java.io.IOException;

public class SaveFileCommand extends Command {
    private String filename;

    public SaveFileCommand(Receiver receiver, String filename) {
        super(receiver);
        this.filename = filename;
    }

    @Override
    public void execute() throws IOException {
        receiver.saveFileCommand(filename);
    }
}
