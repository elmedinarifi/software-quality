package com.nhlstenden.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

class AccessorTest {

    @Test
    void testGetDemoAccessor_ShouldReturnDemoPresentation() {
        Accessor accessor = Accessor.getDemoAccessor();

        assertTrue(accessor instanceof DemoPresentation, "De accessor moet een instance van DemoPresentation zijn");
    }

    @Test
    void testLoadFile_ShouldLoadPresentation() throws IOException {
        Presentation presentation = new Presentation();
        Accessor accessor = new DemoPresentation();

        accessor.loadFile(presentation, "demo.xml");

        assertDoesNotThrow(() -> accessor.loadFile(presentation, "demo.xml"));

        assertEquals("Demo Presentation", presentation.getTitle());
    }

    @Test
    void testSaveFile_ShouldThrowException() {
        Presentation presentation = new Presentation();
        Accessor accessor = new DemoPresentation();

        assertThrows(IllegalStateException.class, () -> accessor.saveFile(presentation, "demo.xml"));
    }
}
