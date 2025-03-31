package com.nhlstenden.demo;

import com.nhlstenden.commandpattern.Receiver;
import com.nhlstenden.factorypattern.SlideViewerFrame;
import com.nhlstenden.factorypattern.Style;

import javax.swing.JOptionPane;

import java.io.IOException;

/** com.nhlstenden.demo.JabberPoint Main Programma
 * <p>This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License).
 * Please read it. Your use of the software constitutes acceptance
 * of the terms in the COPYRIGHT.txt file.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class JabberPoint {
	private Presentation presentation;
	private Receiver receiver;

	public JabberPoint() {
		this.presentation = new Presentation();
		this.receiver = new Receiver(presentation, this);
	}

	public Presentation getPresentation() {
		return presentation;
	}

	public Receiver getReceiver() {
		return receiver;
	}

	public static void main(String argv[]) {
		Style.createStyles();
		JabberPoint jabberpoint = new JabberPoint();
		Receiver receiver = jabberpoint.getReceiver();
		new SlideViewerFrame("Jabberpoint 1.6 - OU", receiver);

		try {
			if (argv.length == 0) {
				Accessor.getDemoAccessor().loadFile(jabberpoint.getPresentation(), "");
			} else {
				new XMLAccessor().loadFile(jabberpoint.getPresentation(), argv[0]);
			}
			jabberpoint.getPresentation().setSlideNumber(0);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "IO Error: " + ex, "Jabberpoint Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}

