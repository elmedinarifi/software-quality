package com.nhlstenden.jabberpoint.core;

import com.nhlstenden.factorypattern.Slide;

/**
 * Interface for presentation display operations.
 * 
 * SOLID Principles Applied:
 * - Single Responsibility Principle: Interface focuses only on view operations
 * - Interface Segregation Principle: Separate interface for view concerns
 * - Dependency Inversion Principle: View implementations depend on abstractions
 */
public interface PresentationView {
    void update(PresentationData presentation, Slide currentSlide);
    void setTitle(String title);
    void clear();
} 