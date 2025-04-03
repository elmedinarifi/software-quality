package com.nhlstenden.commandpattern;

import com.nhlstenden.jabberpoint.JabberPoint;
import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.XMLAccessor;
import com.nhlstenden.factorypattern.SlideItemFactory;

import java.io.IOException;


public class Receiver {
    private Presentation presentation;
    private JabberPoint jabberPoint;

    public Receiver(Presentation presentation, JabberPoint jabberPoint)
    {
        this.presentation = presentation;
        this.jabberPoint = jabberPoint;
    }

    public Presentation getPresentation()
    {
        return this.presentation;
    }

    public void setPresentation(Presentation presentation)
    {
        this.presentation = presentation;
    }

    public JabberPoint getJabberPoint()
    {
        return this.jabberPoint;
    }

    public void setJabberPoint(JabberPoint jabberPoint)
    {
        this.jabberPoint = jabberPoint;
    }

    public void saveFileCommand(String filename) throws IOException{
        try{
            SlideItemFactory slideItemFactory = new SlideItemFactory();
            new XMLAccessor(slideItemFactory).saveFile(this.presentation, filename);
        } catch (IOException exception) {
            throw new RuntimeException("Fout bij openen van bestand", exception);
        }
    }

    public void openFileCommand(String filename) {
        try {
            SlideItemFactory slideItemFactory = new SlideItemFactory();
            new XMLAccessor(slideItemFactory).loadFile(this.presentation, filename);
        } catch (IOException exception) {
            System.err.println("Fout bij openen: " + exception.getMessage());
        }
    }

    public void prevSlideCommand() {
        this.presentation.prevSlide();
    }

    public void nextSlideCommand() {
        this.presentation.nextSlide();
    }

    public void newPageCommand() {
        this.presentation.clear();
    }

    public void exitCommand() {
        System.exit(0);
    }

    public void goToCommand(int slideNumber) {
        if (slideNumber >= 0 && slideNumber < this.presentation.getSize()) {
            this.presentation.setSlideNumber(slideNumber);
        } else {
            throw new IllegalArgumentException("Ongeldig paginanummer");
        }
    }

}
