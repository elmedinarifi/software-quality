package com.nhlstenden.jabberpoint.io;

import java.io.IOException;

import com.nhlstenden.jabberpoint.core.PresentationData;

public interface PresentationSaver {
    void savePresentation(PresentationData presentation, String destination) throws IOException;
} 