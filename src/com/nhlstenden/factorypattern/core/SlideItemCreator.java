package com.nhlstenden.factorypattern.core;

import com.nhlstenden.factorypattern.SlideItem;

/**
 * Interface for creating slide items.
 * 
 * SOLID Principles Applied:
 * - Single Responsibility Principle: Each creator is responsible for creating one type of item
 * - Open/Closed Principle: New item types can be added by implementing this interface
 * - Liskov Substitution Principle: All creators must follow the same contract
 */
public interface SlideItemCreator {
    SlideItem createItem(int level, String content);
    boolean canCreateType(String type);
} 