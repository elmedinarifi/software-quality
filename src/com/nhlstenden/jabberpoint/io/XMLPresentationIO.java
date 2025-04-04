package com.nhlstenden.jabberpoint.io;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.nhlstenden.factorypattern.Slide;
import com.nhlstenden.factorypattern.core.SlideItemRegistry;
import com.nhlstenden.jabberpoint.core.PresentationData;

/**
 * XML implementation of presentation I/O.
 * 
 * SOLID Principles Applied:
 * - Single Responsibility Principle: Handles only XML file operations
 * - Dependency Inversion Principle: Depends on PresentationData interface and SlideItemRegistry
 * - Open/Closed Principle: New file formats can be added without modifying this class
 * - Interface Segregation Principle: Implements separate interfaces for loading and saving
 */
public class XMLPresentationIO implements PresentationLoader, PresentationSaver {
    private final SlideItemRegistry itemRegistry;
    
    // Constructor injection demonstrates Dependency Inversion Principle
    public XMLPresentationIO(SlideItemRegistry itemRegistry) {
        this.itemRegistry = itemRegistry;
    }
    
    @Override
    public void loadPresentation(PresentationData presentation, String source) throws IOException {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new File(source));
            Element root = document.getDocumentElement();
            
            presentation.setTitle(getTitle(root));
            loadSlides(presentation, root);
        } catch (Exception e) {
            throw new IOException("Error loading presentation", e);
        }
    }
    
    @Override
    public void savePresentation(PresentationData presentation, String destination) throws IOException {
        // Implementation for saving to XML
    }
    
    private void loadSlides(PresentationData presentation, Element root) {
        NodeList slideNodes = root.getElementsByTagName("slide");
        for (int i = 0; i < slideNodes.getLength(); i++) {
            Element slideElement = (Element) slideNodes.item(i);
            Slide slide = createSlideFromElement(slideElement);
            presentation.addSlide(slide);
        }
    }
    
    private Slide createSlideFromElement(Element slideElement) {
        Slide slide = new Slide();
        slide.setTitle(getTitle(slideElement));
        loadSlideItems(slide, slideElement);
        return slide;
    }
    
    private void loadSlideItems(Slide slide, Element slideElement) {
        NodeList itemNodes = slideElement.getElementsByTagName("item");
        for (int i = 0; i < itemNodes.getLength(); i++) {
            Element itemElement = (Element) itemNodes.item(i);
            String type = itemElement.getAttribute("type");
            int level = Integer.parseInt(itemElement.getAttribute("level"));
            String content = itemElement.getTextContent();
            
            slide.append(itemRegistry.createItem(type, level, content));
        }
    }
    
    private String getTitle(Element element) {
        NodeList titleNodes = element.getElementsByTagName("title");
        return titleNodes.item(0).getTextContent();
    }
} 