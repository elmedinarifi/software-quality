package com.nhlstenden.jabberpoint.core;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nhlstenden.factorypattern.Slide;

class DefaultPresentationDataTest {
    private DefaultPresentationData presentationData;

    @BeforeEach
    void setup() {
        this.presentationData = new DefaultPresentationData();
    }

    @Test
    void testAddAndGetSlides() {
        Slide slide1 = new Slide();
        Slide slide2 = new Slide();
        slide1.setTitle("Slide 1");
        slide2.setTitle("Slide 2");

        presentationData.addSlide(slide1);
        presentationData.addSlide(slide2);

        List<Slide> slides = presentationData.getSlides();
        assertEquals(2, slides.size());
        assertEquals("Slide 1", slides.get(0).getTitle());
        assertEquals("Slide 2", slides.get(1).getTitle());
    }

    @Test
    void testRemoveSlide() {
        Slide slide1 = new Slide();
        Slide slide2 = new Slide();
        slide1.setTitle("Slide 1");
        slide2.setTitle("Slide 2");

        presentationData.addSlide(slide1);
        presentationData.addSlide(slide2);
        presentationData.removeSlide(0);

        List<Slide> slides = presentationData.getSlides();
        assertEquals(1, slides.size());
        assertEquals("Slide 2", slides.get(0).getTitle());
    }

    @Test
    void testClear() {
        Slide slide = new Slide();
        presentationData.addSlide(slide);
        presentationData.clear();

        assertEquals(0, presentationData.getSize());
        assertEquals(-1, presentationData.getCurrentSlideNumber());
    }

    @Test
    void testSetAndGetTitle() {
        String title = "Test Presentation";
        presentationData.setTitle(title);
        assertEquals(title, presentationData.getTitle());
    }

    @Test
    void testGetCurrentSlide() {
        Slide slide1 = new Slide();
        Slide slide2 = new Slide();
        slide1.setTitle("Slide 1");
        slide2.setTitle("Slide 2");

        presentationData.addSlide(slide1);
        presentationData.addSlide(slide2);
        presentationData.setCurrentSlideNumber(1);

        assertEquals(slide2, presentationData.getCurrentSlide());
    }

    @Test
    void testGetCurrentSlideWithInvalidIndex() {
        assertNull(presentationData.getCurrentSlide());
    }
} 