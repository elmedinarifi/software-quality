package com.nhlstenden.commandpattern;

import com.nhlstenden.demo.AboutBox;
import com.nhlstenden.demo.Accessor;
import com.nhlstenden.demo.Presentation;
import com.nhlstenden.demo.XMLAccessor;

import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

/** <p>De controller voor het menu</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar {
	
	private Frame parent;
	private Presentation presentation;
	private Receiver receiver;
	
	private static final long serialVersionUID = 227L;
	
	protected static final String ABOUT = "About";
	protected static final String FILE = "File";
	protected static final String EXIT = "Exit";
	protected static final String GOTO = "Go to";
	protected static final String HELP = "Help";
	protected static final String NEW = "New";
	protected static final String NEXT = "Next";
	protected static final String OPEN = "Open";
	protected static final String PAGENR = "Page number?";
	protected static final String PREV = "Prev";
	protected static final String SAVE = "Save";
	protected static final String VIEW = "View";
	
	protected static final String TESTFILE = "test.xml";
	protected static final String SAVEFILE = "dump.xml";
	
	protected static final String IOEX = "IO Exception: ";
	protected static final String LOADERR = "Load Error";
	protected static final String SAVEERR = "Save Error";

	public MenuController(Frame frame, Presentation pres, Receiver receiver) {
		this.parent = frame;
		this.presentation = pres;
		this.receiver = receiver;
		MenuItem menuItem;
		Menu fileMenu = new Menu(FILE);
		fileMenu.add(menuItem = this.mkMenuItem(OPEN));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.clear();
				Accessor xmlAccessor = new XMLAccessor();
				try {
					new OpenFileCommand(receiver, TESTFILE).execute();
					presentation.setSlideNumber(0);
				} catch (IOException exc) {
					JOptionPane.showMessageDialog(parent, IOEX + exc,
         			LOADERR, JOptionPane.ERROR_MESSAGE);
				}
				parent.repaint();
			}
		} );
		fileMenu.add(menuItem = this.mkMenuItem(NEW));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				new NewPageCommand(receiver).execute();
				parent.repaint();
			}
		});
		fileMenu.add(menuItem = this.mkMenuItem(SAVE));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Accessor xmlAccessor = new XMLAccessor();
				try {
					new SaveFileCommand(receiver, SAVEFILE).execute();
				} catch (IOException exc) {
					JOptionPane.showMessageDialog(parent, IOEX + exc,
							SAVEERR, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		fileMenu.addSeparator();
		fileMenu.add(menuItem = this.mkMenuItem(EXIT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				new ExitCommand(receiver).execute();
			}
		});
		this.add(fileMenu);
		Menu viewMenu = new Menu(VIEW);
		viewMenu.add(menuItem = this.mkMenuItem(NEXT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				new NextSlideCommand(receiver).execute();
			}
		});
		viewMenu.add(menuItem = this.mkMenuItem(PREV));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				new PrevSlideCommand(receiver).execute();
			}
		});
		viewMenu.add(menuItem = this.mkMenuItem(GOTO));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String pageNumberStr = JOptionPane.showInputDialog((Object) PAGENR);
				try {
					int pageNumber = Integer.parseInt(pageNumberStr);
					new GoToCommand(receiver, pageNumber - 1).execute();
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(parent, "Voer een geldig nummer in.", "Fout", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

//		viewMenu.add(menuItem = this.mkMenuItem(GOTO));
//		menuItem.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent actionEvent) {
//				String pageNumberStr = JOptionPane.showInputDialog((Object)PAGENR);
//				int pageNumber = Integer.parseInt(pageNumberStr);
//				presentation.setSlideNumber(pageNumber - 1);
//			}
//		});
		this.add(viewMenu);
		Menu helpMenu = new Menu(HELP);
		helpMenu.add(menuItem = this.mkMenuItem(ABOUT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AboutBox.show(parent);
			}
		});
		this.setHelpMenu(helpMenu);
	}

	public MenuItem mkMenuItem(String name) {
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}
}
