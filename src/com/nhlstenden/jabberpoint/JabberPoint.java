package com.nhlstenden.jabberpoint;

import javax.swing.JOptionPane;

import com.nhlstenden.commandpattern.Receiver;
import com.nhlstenden.factorypattern.SlideViewerFrame;
import com.nhlstenden.factorypattern.Style;
import com.nhlstenden.factorypattern.core.SlideItemRegistry;
import com.nhlstenden.factorypattern.creators.BitmapItemCreator;
import com.nhlstenden.factorypattern.creators.TextItemCreator;
import com.nhlstenden.jabberpoint.core.DefaultPresentationData;
import com.nhlstenden.jabberpoint.io.XMLPresentationIO;

/**
 * Main application class demonstrating SOLID principles integration.
 * <p>
 * SOLID Principles Applied:
 * - Single Responsibility Principle: Coordinates components but delegates specific responsibilities
 * - Open/Closed Principle: New functionality can be added through new implementations of interfaces
 * - Liskov Substitution Principle: Uses interfaces instead of concrete classes
 * - Interface Segregation Principle: Depends on specific interfaces for specific needs
 * - Dependency Inversion Principle: Dependencies are injected and based on abstractions
 */
public class JabberPoint
{
    private final DefaultPresentationData presentationData;
    private final SlideItemRegistry itemRegistry;
    private final XMLPresentationIO presentationIO;
    private Receiver receiver;

    public JabberPoint()
    {
        Style.createStyles();  // Initialize styles
        this.presentationData = new DefaultPresentationData();
        this.itemRegistry = createSlideItemRegistry();
        this.presentationIO = new XMLPresentationIO(itemRegistry);
        this.receiver = new Receiver(presentationData, this);
    }

    /**
     * Creates and configures the SlideItemRegistry.
     * Demonstrates Open/Closed Principle: New creators can be added without modification
     */
    private SlideItemRegistry createSlideItemRegistry()
    {
        SlideItemRegistry registry = new SlideItemRegistry();
        registry.registerCreator(new TextItemCreator());
        registry.registerCreator(new BitmapItemCreator());

        return registry;
    }

    public static void main(String argv[])
    {
        JabberPoint jabberPoint = new JabberPoint();
        jabberPoint.start(argv);
    }

    private void start(String[] argv)
    {
        try
        {
            // Create the main window first
            new SlideViewerFrame("Jabberpoint 1.6 - OU", receiver);

            if (argv.length == 0)
            {
                loadDemoPresentation();
            }
            else
            {
                presentationIO.loadPresentation(presentationData, argv[0]);
            }
            showPresentation();
        }
        catch (Exception e)
        {
            handleError(e);
        }
    }

    private void loadDemoPresentation()
    {
        try
        {
            // Use the DemoPresentation class to load the demo
            DemoPresentation demoPresentation = new DemoPresentation();
            demoPresentation.loadFile(presentationData, "");
        }
        catch (Exception e)
        {
            handleError(e);
        }
    }

    private void showPresentation()
    {
        // Set the initial slide
        presentationData.setSlideNumber(0);
    }

    private void handleError(Exception e)
    {
        JOptionPane.showMessageDialog(null,
            "Error: " + e.getMessage(),
            "Jabberpoint Error",
            JOptionPane.ERROR_MESSAGE);
    }

    public Presentation getPresentation()
    {
        return presentationData;
    }

    public Receiver getReceiver()
    {
        return receiver;
    }
}

