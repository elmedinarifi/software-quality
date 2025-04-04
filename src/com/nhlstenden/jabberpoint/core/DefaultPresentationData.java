package com.nhlstenden.jabberpoint.core;

import java.util.ArrayList;
import java.util.List;

import com.nhlstenden.factorypattern.Slide;
import com.nhlstenden.factorypattern.SlideViewerComponent;
import com.nhlstenden.jabberpoint.Presentation;

/**
 * Default implementation of PresentationData that extends the original Presentation class.
 * 
 * SOLID Principles Applied:
 * - Single Responsibility Principle: Handles only presentation data management
 * - Liskov Substitution Principle: Properly implements all Presentation methods
 * - Open/Closed Principle: Can be extended for different data management strategies
 */
public class DefaultPresentationData extends Presentation implements PresentationData {
    
    public DefaultPresentationData() {
        super();
    }
    
    public DefaultPresentationData(SlideViewerComponent slideViewerComponent) {
        super(slideViewerComponent);
    }
    
    @Override
    public void setCurrentSlideNumber(int number) {
        this.setSlideNumber(number);
    }
    
    @Override
    public int getCurrentSlideNumber() {
        return this.getSlideNumber();
    }
    
    @Override
    public List<Slide> getSlides() {
        List<Slide> slides = new ArrayList<>();
        for (int i = 0; i < getSize(); i++) {
            slides.add(getSlide(i));
        }
        return slides;
    }
    
    @Override
    public void addSlide(Slide slide) {
        this.append(slide);
    }
    
    @Override
    public void removeSlide(int index) {
        if (index >= 0 && index < this.getSize()) {
            // We need to handle this differently since we can't access showList directly
            List<Slide> currentSlides = getSlides();
            this.clear();
            for (int i = 0; i < currentSlides.size(); i++) {
                if (i != index) {
                    this.append(currentSlides.get(i));
                }
            }
            if (this.getCurrentSlideNumber() >= this.getSize()) {
                this.setCurrentSlideNumber(this.getSize() - 1);
            }
        }
    }
    
    @Override
    public String getTitle() {
        return super.getTitle();
    }
    
    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }
    
    @Override
    public Slide getCurrentSlide() {
        return super.getCurrentSlide();
    }
    
    @Override
    public void clear() {
        super.clear();
    }
    
    @Override
    public int getSize() {
        return super.getSize();
    }
} 