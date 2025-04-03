package com.nhlstenden.jabberpoint;

import com.nhlstenden.commandpattern.Receiver;
import com.nhlstenden.factorypattern.SlideItemFactory;
import com.nhlstenden.factorypattern.SlideViewerFrame;
import com.nhlstenden.factorypattern.Style;

import javax.swing.*;
import java.io.IOException;

/**
 * com.nhlstenden.demo.JabberPoint Main Programma
 * <p>This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License).
 * Please read it. Your use of the software constitutes acceptance
 * of the terms in the COPYRIGHT.txt file.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class JabberPoint
{
    private Presentation presentation;
    private Receiver receiver;

    public JabberPoint()
    {
        this.presentation = new Presentation();
        this.receiver = new Receiver(presentation, this);
    }

    public Presentation getPresentation()
    {
        return presentation;
    }

    public Receiver getReceiver()
    {
        return receiver;
    }

    public static void main(String argv[])
    {
        Style.createStyles();
        JabberPoint jabberpoint = new JabberPoint();
        Receiver receiver = jabberpoint.getReceiver();
        new SlideViewerFrame("Jabberpoint 1.6 - OU", receiver);

        try
        {
            if (argv.length == 0)
            {
                Accessor.getDemoAccessor().loadFile(jabberpoint.getPresentation(), "");
            }
            else
            {
                SlideItemFactory slideItemFactory = new SlideItemFactory();

                new XMLAccessor(slideItemFactory).loadFile(jabberpoint.getPresentation(), argv[0]);
            }
            jabberpoint.getPresentation().setSlideNumber(0);
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "IO Error: " + ex, "Jabberpoint Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

