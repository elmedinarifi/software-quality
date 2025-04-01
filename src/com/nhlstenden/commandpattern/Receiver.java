package com.nhlstenden.commandpattern;

import com.nhlstenden.demo.JabberPoint;
import com.nhlstenden.demo.Presentation;
import com.nhlstenden.factorypattern.Slide;
import com.nhlstenden.demo.XMLAccessor;

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
            new XMLAccessor().saveFile(this.presentation, filename);
            System.out.println("Bestand is opgeslagen" + filename);
        } catch (IOException exception) {
            System.err.println("Bestand is niet opgeslagen:" + exception.getMessage());
        }
    }

    public void openFileCommand(String filename) {
        try {
            new XMLAccessor().loadFile(this.presentation, filename);
            System.out.println("Bestand geopend: " + filename);
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
            System.out.println("Ongeldig slide nummer: " + slideNumber);
        }
    }

}
