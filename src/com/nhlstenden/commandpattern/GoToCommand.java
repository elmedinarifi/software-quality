package com.nhlstenden.commandpattern;

import com.nhlstenden.demo.Presentation;

import java.io.IOException;

public class GoToCommand extends Command {
    private int pageNumber;
    public GoToCommand(Receiver receiver, int pageNumber)
    {
        super(receiver);
        this.pageNumber = pageNumber;
    }

    @Override
    public void execute() {
        this.receiver.goToCommand(this.pageNumber);
    }
}
