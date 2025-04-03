package com.nhlstenden.factorypattern;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Vector;

/**
 * <p>Een slide. Deze klasse heeft tekenfunctionaliteit.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Slide
{
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;
    protected String title;
    protected Vector<SlideItem> items;

    public Slide()
    {
        this.items = new Vector<SlideItem>();
    }

    public void append(SlideItem anItem)
    {
        this.items.addElement(anItem);
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String newTitle)
    {
        this.title = newTitle;
    }

    public void append(int level, String message)
    {
        SlideItemFactory factory = new SlideItemFactory();
        SlideItem item = factory.createSlideItem(SlideItemTypes.TEXT_ITEM, level, message);
        this.append(item);
    }

    public SlideItem getSlideItem(int number)
    {
        return (SlideItem) this.items.elementAt(number);
    }

    public Vector<SlideItem> getSlideItems()
    {
        return this.items;
    }

    public int getSize()
    {
        return this.items.size();
    }

    public void draw(Graphics g, Rectangle area, ImageObserver view)
    {
        float scale = this.getScale(area);
        int y = area.y;
        SlideItemFactory factory = new SlideItemFactory();
        SlideItem slideItem = factory.createSlideItem(SlideItemTypes.TEXT_ITEM, 0, this.getTitle());
        Style style = Style.getStyle(slideItem.getLevel());
        slideItem.draw(area.x, y, scale, g, style, view);
        y += slideItem.getBoundingBox(g, view, scale, style).height;
        for (int number = 0; number < this.getSize(); number++)
        {
            slideItem = (SlideItem) this.getSlideItems().elementAt(number);
            style = Style.getStyle(slideItem.getLevel());
            slideItem.draw(area.x, y, scale, g, style, view);
            y += slideItem.getBoundingBox(g, view, scale, style).height;
        }
    }

    private float getScale(Rectangle area)
    {
        return Math.min(((float) area.width) / ((float) WIDTH), ((float) area.height) / ((float) HEIGHT));
    }
}
