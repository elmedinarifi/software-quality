//package com.nhlstenden;
//
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyAdapter;
//
///** <p>This is the com.nhlstenden.commandpattern.KeyController (KeyListener)</p>
// * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
// * @version 1.1 2002/12/17 Gert Florijn
// * @version 1.2 2003/11/19 Sylvia Stuurman
// * @version 1.3 2004/08/17 Sylvia Stuurman
// * @version 1.4 2007/07/16 Sylvia Stuurman
// * @version 1.5 2010/03/03 Sylvia Stuurman
// * @version 1.6 2014/05/16 Sylvia Stuurman
//*/
//
//public class KeyController extends KeyAdapter {
//	private Presentation presentation;
//
//	public KeyController(Presentation p) {
//		this.presentation = p;
//	}
//
//	public void keyPressed(KeyEvent keyEvent) {
//		switch(keyEvent.getKeyCode()) {
//			case KeyEvent.VK_PAGE_DOWN:
//			case KeyEvent.VK_DOWN:
//			case KeyEvent.VK_ENTER:
//			case '+':
//				this.presentation.nextSlide();
//				break;
//			case KeyEvent.VK_PAGE_UP:
//			case KeyEvent.VK_UP:
//			case '-':
//				this.presentation.prevSlide();
//				break;
//			case 'q':
//			case 'Q':
//				System.exit(0);
//				break;
//			default:
//				break;
//		}
//	}
//}

package com.nhlstenden.commandpattern;

import com.nhlstenden.commandpattern.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class KeyController extends KeyAdapter {
	private Map<Integer, Command> commandMap;

	public KeyController(Receiver receiver) {
		commandMap = new HashMap<>();

		commandMap.put(KeyEvent.VK_PAGE_DOWN, new NextSlideCommand(receiver));
		commandMap.put(KeyEvent.VK_DOWN, new NextSlideCommand(receiver));
		commandMap.put(KeyEvent.VK_ENTER, new NextSlideCommand(receiver));
		commandMap.put((int) '+', new NextSlideCommand(receiver));

		commandMap.put(KeyEvent.VK_PAGE_UP, new PrevSlideCommand(receiver));
		commandMap.put(KeyEvent.VK_UP, new PrevSlideCommand(receiver));
		commandMap.put((int) '-', new PrevSlideCommand(receiver));

		commandMap.put((int) 'q', new ExitCommand(receiver));
		commandMap.put((int) 'Q', new ExitCommand(receiver));
	}

	@Override
	public void keyPressed(KeyEvent keyEvent){
		Command command = commandMap.get(keyEvent.getKeyCode());
		if (command != null) {
            try {
                command.execute();
            } catch (IOException exception) {
                System.err.println("Command error" + exception);
            }
        }
	}
}

