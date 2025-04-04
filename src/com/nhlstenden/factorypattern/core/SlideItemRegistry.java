package com.nhlstenden.factorypattern.core;

import java.util.ArrayList;
import java.util.List;

import com.nhlstenden.factorypattern.SlideItem;

/**
 * Registry for slide item creators.
 * 
 * SOLID Principles Applied:
 * - Single Responsibility Principle: Manages registration and creation of slide items
 * - Open/Closed Principle: New creators can be added without modifying this class
 * - Dependency Inversion Principle: Depends on SlideItemCreator interface
 */
public class SlideItemRegistry {
    private final List<SlideItemCreator> creators = new ArrayList<>();
    
    public void registerCreator(SlideItemCreator creator) {
        creators.add(creator);
    }
    
    public SlideItem createItem(String type, int level, String content) {
        for (SlideItemCreator creator : creators) {
            if (creator.canCreateType(type)) {
                return creator.createItem(level, content);
            }
        }
        throw new IllegalArgumentException("No creator found for type: " + type);
    }
} 