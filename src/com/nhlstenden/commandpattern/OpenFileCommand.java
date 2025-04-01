package com.nhlstenden.commandpattern;

import java.io.IOException;

public class OpenFileCommand extends Command {
    private String filename;
    public OpenFileCommand(Receiver receiver, String filename) {
        super(receiver);
        this.filename = filename;
    }

    @Override
    public void execute() throws IOException {
        this.receiver.openFileCommand(this.filename);
    }
}
