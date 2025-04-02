package com.nhlstenden.demo;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;


class AboutBoxTest {

    @Test
    void testShow_ShouldShowMessageDialog() {
        Frame frame = new Frame();

        JOptionPane.showMessageDialog(frame,
                "com.nhlstenden.demo.JabberPoint is a primitive slide-show program in Java(tm). It\n" +
                        "is freely copyable as long as you keep this notice and\n" +
                        "the splash screen intact.\n" +
                        "Copyright (c) 1995-1997 by Ian F. Darwin, ian@darwinsys.com.\n" +
                        "Adapted by Gert Florijn (version 1.1) and " +
                        "Sylvia Stuurman (version 1.2 and higher) for the Open" +
                        "University of the Netherlands, 2002 -- now." +
                        "Author's version available from http://www.darwinsys.com/",
                "About com.nhlstenden.demo.JabberPoint",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
