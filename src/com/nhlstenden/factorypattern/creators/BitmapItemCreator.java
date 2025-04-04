package com.nhlstenden.factorypattern.creators;

import com.nhlstenden.factorypattern.BitmapItem;
import com.nhlstenden.factorypattern.SlideItem;
import com.nhlstenden.factorypattern.core.SlideItemCreator;

public class BitmapItemCreator implements SlideItemCreator
{
    @Override
    public SlideItem createItem(int level, String content)
    {
        return new BitmapItem(level, content);
    }

    @Override
    public boolean canCreateType(String type)
    {
        return "bitmap".equalsIgnoreCase(type);
    }
} 