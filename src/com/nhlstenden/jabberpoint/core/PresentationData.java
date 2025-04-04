package com.nhlstenden.jabberpoint.core;

import java.util.List;

import com.nhlstenden.factorypattern.Slide;

/**
 * Interface for presentation data management.
 * 
 * SOLID Principles Applied:
 * - Single Responsibility Principle: Interface focuses only on data management operations
 * - Interface Segregation Principle: Separates data operations from view operations
 * - Dependency Inversion Principle: High-level modules can depend on this abstraction
 */
public interface PresentationData {
    String getTitle();
    void setTitle(String title);
    int getCurrentSlideNumber();
    void setCurrentSlideNumber(int number);
    Slide getCurrentSlide();
    List<Slide> getSlides();
    void addSlide(Slide slide);
    void removeSlide(int index);
    void clear();
    int getSize();
} 