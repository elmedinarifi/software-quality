package com.nhlstenden.jabberpoint;

import com.nhlstenden.factorypattern.BitmapItem;
import com.nhlstenden.factorypattern.Slide;

/**
 * Een ingebouwde demo-presentatie
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class DemoPresentation extends Accessor
{

    public void loadFile(Presentation presentation, String unusedFilename)
    {
        presentation.setTitle("Demo Presentation");
        Slide slide;
        slide = new Slide();
        slide.setTitle("JabberPoint");
        slide.append(1, "Het Java Presentatie Tool");
        slide.append(2, "Copyright (c) 1996-2000: Ian Darwin");
        slide.append(2, "Copyright (c) 2000-now:");
        slide.append(2, "Gert Florijn en Sylvia Stuurman");
        slide.append(4, "JabberPoint aanroepen zonder bestandsnaam");
        slide.append(4, "laat deze presentatie zien");
        slide.append(1, "Navigeren:");
        slide.append(3, "Volgende slide: PgDn of Enter");
        slide.append(3, "Vorige slide: PgUp of up-arrow");
        slide.append(3, "Stoppen: q or Q");
        presentation.append(slide);

        slide = new Slide();
        slide.setTitle("Demonstratie van levels en stijlen");
        slide.append(1, "Level 1");
        slide.append(2, "Level 2");
        slide.append(1, "Nogmaals level 1");
        slide.append(1, "Level 1 heeft stijl nummer 1");
        slide.append(2, "Level 2 heeft stijl nummer 2");
        slide.append(3, "Zo ziet level 3 er uit");
        slide.append(4, "En dit is level 4");
        presentation.append(slide);

        slide = new Slide();
        slide.setTitle("De derde slide");
        slide.append(1, "Om een nieuwe presentatie te openen,");
        slide.append(2, "gebruik File->Open uit het menu.");
        slide.append(1, " ");
        slide.append(1, "Dit is het einde van de presentatie.");
        slide.append(new BitmapItem(1, "src/com/nhlstenden/resources/JabberPoint.jpg"));

        presentation.append(slide);
    }

    public void saveFile(Presentation presentation, String unusedFilename)
    {
        throw new IllegalStateException("Het opslaan van een demo-presentatie is niet toegestaan. Maak een nieuw bestand aan.");
    }
}
