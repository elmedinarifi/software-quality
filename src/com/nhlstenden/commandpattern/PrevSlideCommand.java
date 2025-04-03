package com.nhlstenden.commandpattern;

public class PrevSlideCommand extends Command
{
    public PrevSlideCommand(Receiver receiver)
    {
        super(receiver);
    }

    @Override
    public void execute()
    {
        this.receiver.prevSlideCommand();
    }
}
