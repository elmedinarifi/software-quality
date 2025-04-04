package com.nhlstenden.factorypattern.creators;

import com.nhlstenden.factorypattern.SlideItem;
import com.nhlstenden.factorypattern.TextItem;
import com.nhlstenden.factorypattern.core.SlideItemCreator;

/**
 * Creator for text slide items.
 * <p>
 * SOLID Principles Applied:
 * - Single Responsibility Principle: Creates only text items
 * - Liskov Substitution Principle: Properly implements SlideItemCreator
 * - Interface Segregation Principle: Implements only necessary methods
 */
public class TextItemCreator implements SlideItemCreator
{
    @Override
    public SlideItem createItem(int level, String content)
    {
        return new TextItem(level, content);
    }

    @Override
    public boolean canCreateType(String type)
    {
        return "text".equalsIgnoreCase(type);
    }
} 