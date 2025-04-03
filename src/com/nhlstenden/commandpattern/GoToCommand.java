package com.nhlstenden.commandpattern;

public class GoToCommand extends Command
{
    private int pageNumber;

    public GoToCommand(Receiver receiver, int pageNumber)
    {
        super(receiver);
        this.pageNumber = pageNumber;
    }

    @Override
    public void execute()
    {
        this.receiver.goToCommand(this.pageNumber);
    }
}
