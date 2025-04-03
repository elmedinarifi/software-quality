package com.nhlstenden.commandpattern;

public class NextSlideCommand extends Command
{
    public NextSlideCommand(Receiver receiver)
    {
        super(receiver);
    }

    @Override
    public void execute()
    {
        this.receiver.nextSlideCommand();
    }
}
