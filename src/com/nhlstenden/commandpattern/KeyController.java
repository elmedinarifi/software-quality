package com.nhlstenden.commandpattern;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class KeyController extends KeyAdapter
{
    private Map<Integer, Command> commandMap;

    public KeyController(Receiver receiver)
    {
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
    public void keyPressed(KeyEvent keyEvent)
    {
        Command command = commandMap.get(keyEvent.getKeyCode());
        if (command != null)
        {
            try
            {
                command.execute();
            }
            catch (IOException exception)
            {
            }
        }
    }
}

