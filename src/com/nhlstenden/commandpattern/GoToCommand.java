package com.nhlstenden.commandpattern;

import com.nhlstenden.demo.Presentation;

import java.io.IOException;

public class GoToCommand extends Command {

    private Presentation presentation;
    private int slideNumber;
    public GoToCommand(Receiver receiver, Presentation presentation, int slideNumber) {
        super(receiver);
        this.presentation = presentation;
        this.slideNumber = slideNumber;
    }

    @Override
    public void execute() {
        if (slideNumber >= 0 && slideNumber < presentation.getSize()) {
            presentation.setSlideNumber(slideNumber);
        } else {
            System.out.println("Ongeldig slide nummer: " + slideNumber);
        }
    }
}
