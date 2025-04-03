package com.nhlstenden.factorypattern;

import com.nhlstenden.commandpattern.KeyController;
import com.nhlstenden.commandpattern.MenuController;
import com.nhlstenden.commandpattern.Receiver;
import com.nhlstenden.jabberpoint.Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * <p>Het applicatiewindow voor een slideviewcomponent</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class SlideViewerFrame extends JFrame
{
    private static final long serialVersionUID = 3227L;

    private static final String JABTITLE = "Jabberpoint 1.6 - OU";
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;

    public SlideViewerFrame(String title, Receiver receiver)
    {
        super(title);
        Presentation presentation = receiver.getPresentation();
        SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, this);
        presentation.setShowView(slideViewerComponent);
        this.setupWindow(slideViewerComponent, receiver);
    }

    public void setupWindow(SlideViewerComponent slideViewerComponent, Receiver receiver)
    {
        this.setTitle(JABTITLE);
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
        this.getContentPane().add(slideViewerComponent);
        this.addKeyListener(new KeyController(receiver));
        this.setMenuBar(new MenuController(this, receiver));
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setVisible(true);
    }

}
