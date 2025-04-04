package com.nhlstenden.jabberpoint.io;

import java.io.IOException;

import com.nhlstenden.jabberpoint.core.PresentationData;

public interface PresentationLoader {
    void loadPresentation(PresentationData presentation, String source) throws IOException;
} 