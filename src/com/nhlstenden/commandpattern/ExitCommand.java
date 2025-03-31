package com.nhlstenden.commandpattern;

public class ExitCommand extends Command {

    public ExitCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        receiver.exitCommand();
    }
}
