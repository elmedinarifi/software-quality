package com.nhlstenden.commandpattern;

public class NewPageCommand extends Command {
    public NewPageCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        this.receiver.newPageCommand();
    }
}
