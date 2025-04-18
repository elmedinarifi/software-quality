package com.nhlstenden.factorypattern;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * <p>De abstracte klasse voor een item op een com.nhlstenden.factorypattern.Slide<p>
 * <p>Alle SlideItems hebben tekenfunctionaliteit.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public abstract class SlideItem
{
    private int level = 0;

    public SlideItem(int lev)
    {
        this.level = lev;
    }

    public SlideItem()
    {
        this(0);
    }

    public int getLevel()
    {
        return this.level;
    }

    public final void render(int x, int y, float scale, Graphics g, ImageObserver observer)
    {
        Style style = Style.getStyle(getLevel());
        g.setColor(style.color);
        g.setFont(style.getFont(scale));
        draw(x, y, scale, g, style, observer);
    }

    public abstract Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style);

    public abstract void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer);
}
