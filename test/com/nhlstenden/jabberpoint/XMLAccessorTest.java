package com.nhlstenden.jabberpoint;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nhlstenden.factorypattern.Slide;
import com.nhlstenden.factorypattern.SlideItemFactory;
import com.nhlstenden.factorypattern.TextItem;

class XMLAccessorTest {

    private Presentation presentation;
    private XMLAccessor xmlAccessor;
    private SlideItemFactory slideItemFactory;

    @BeforeEach
    void setup() {
        this.presentation = new Presentation();
        this.slideItemFactory = new SlideItemFactory();
        this.xmlAccessor = new XMLAccessor(this.slideItemFactory);
    }

    @Test
    void testLoadFile_ShouldLoadValidXML() throws IOException {
        File file = new File("test_presentation.xml");

        String xmlContent = """
                <?xml version="1.0"?>
                <presentation>
                    <showtitle>Test Presentation</showtitle>
                    <slide>
                        <title>Slide 1</title>
                        <item kind="text" level="1">This is a text item</item>
                    </slide>
                </presentation>
                """;

        this.xmlAccessor.loadFile(this.presentation, file.getAbsolutePath());

        assertEquals("Test Presentation", this.presentation.getTitle());
        assertEquals(1, this.presentation.getSize());

        assertEquals("Slide 1", this.presentation.getSlide(0).getTitle());
        assertEquals(1, this.presentation.getSlide(0).getSlideItems().size());
        assertTrue(this.presentation.getSlide(0).getSlideItems().getFirst() instanceof TextItem);
    }

    @Test
    void testSaveFile_ShouldSaveValidXML() throws IOException {
        this.presentation.setTitle("Test Presentation");
        Slide slide = new Slide();
        slide.setTitle("Slide 1");
        slide.append(new TextItem(1, "Text item"));
        this.presentation.append(slide);

        File file = new File("saved_presentation.xml");
        this.xmlAccessor.saveFile(this.presentation, file.getAbsolutePath());

        assertTrue(file.exists());

        Presentation loadedPresentation = new Presentation();
        this.xmlAccessor.loadFile(loadedPresentation, file.getAbsolutePath());

        assertEquals("Test Presentation", loadedPresentation.getTitle());
        assertEquals(1, loadedPresentation.getSize());
        assertEquals("Slide 1", loadedPresentation.getSlide(0).getTitle());
        assertTrue(loadedPresentation.getSlide(0).getSlideItems().get(0) instanceof TextItem);
    }
}