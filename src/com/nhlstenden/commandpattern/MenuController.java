package com.nhlstenden.commandpattern;

import com.nhlstenden.jabberpoint.AboutBox;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class MenuController extends MenuBar {
	private Frame parent;
	private Receiver receiver;
	private Map<String, Command> commandMap;

	private static final long serialVersionUID = 227L;

	private static final String ABOUT = "About";
	private static final String FILE = "File";
	private static final String EXIT = "Exit";
	private static final String GOTO = "Go to";
	private static final String HELP = "Help";
	private static final String NEW = "New";
	private static final String NEXT = "Next";
	private static final String OPEN = "Open";
	private static final String PAGENR = "Page number?";
	private static final String PREV = "Prev";
	private static final String SAVE = "Save";
	private static final String VIEW = "View";

	private static final String TESTFILE = "test.xml";
	private static final String SAVEFILE = "dump.xml";

	private static final String IOEX = "IO Exception: ";
	private static final String LOADERR = "Load Error";
	private static final String SAVEERR = "Save Error";

	public MenuController(Frame frame, Receiver receiver) {
		this.parent = frame;
		this.receiver = receiver;
		this.commandMap = new HashMap<>();

		this.commandMap.put(OPEN, new OpenFileCommand(receiver, TESTFILE));
		this.commandMap.put(NEW, new NewPageCommand(receiver));
		this.commandMap.put(SAVE, new SaveFileCommand(receiver, SAVEFILE));
		this.commandMap.put(EXIT, new ExitCommand(receiver));
		this.commandMap.put(NEXT, new NextSlideCommand(receiver));
		this.commandMap.put(PREV, new PrevSlideCommand(receiver));

		setupMenus();
	}

	private void setupMenus() {
		Menu fileMenu = new Menu(FILE);
		this.addMenuItem(fileMenu, OPEN);
		this.addMenuItem(fileMenu, NEW);
		this.addMenuItem(fileMenu, SAVE);
		fileMenu.addSeparator();
		this.addMenuItem(fileMenu, EXIT);
		this.add(fileMenu);

		Menu viewMenu = new Menu(VIEW);
		this.addMenuItem(viewMenu, NEXT);
		this.addMenuItem(viewMenu, PREV);
		this.addGoToMenuItem(viewMenu);
		this.add(viewMenu);

		Menu helpMenu = new Menu(HELP);
		this.addAboutMenuItem(helpMenu);
		this.setHelpMenu(helpMenu);
	}

	private void addMenuItem(Menu menu, String commandKey) {
		MenuItem menuItem = mkMenuItem(commandKey);
		menuItem.addActionListener(e -> executeCommand(commandKey));
		menu.add(menuItem);
	}

	private void addGoToMenuItem(Menu menu) {
		MenuItem menuItem = mkMenuItem(GOTO);
		menuItem.addActionListener(e -> {
			String pageNumberStr = JOptionPane.showInputDialog(parent, PAGENR);
			try {
				int pageNumber = Integer.parseInt(pageNumberStr);
				new GoToCommand(this.receiver, pageNumber - 1).execute();
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(parent, "Voer een geldig nummer in.", "Fout", JOptionPane.ERROR_MESSAGE);
			}
		});
		menu.add(menuItem);
	}

	private void addAboutMenuItem(Menu menu) {
		MenuItem menuItem = mkMenuItem(ABOUT);
		menuItem.addActionListener(e -> AboutBox.show(parent));
		menu.add(menuItem);
	}

	private void executeCommand(String commandKey) {
		Command command = this.commandMap.get(commandKey);
		if (command != null) {
			try {
				command.execute();
			} catch (IOException exception) {
				String errorMessage = IOEX + exception;
				String errorTitle = commandKey.equals(SAVE) ? SAVEERR : LOADERR;
				JOptionPane.showMessageDialog(parent, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
			}
		}
	}


	public MenuItem mkMenuItem(String name) {
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}
}

