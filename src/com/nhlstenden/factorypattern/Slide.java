package com.nhlstenden.factorypattern;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Vector;

/** <p>Een slide. Deze klasse heeft tekenfunctionaliteit.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Slide {
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 800;
	protected String title;
	protected Vector<SlideItem> items;

	public Slide() {
		this.items = new Vector<SlideItem>();
	}

	public void append(SlideItem anItem) {
		this.items.addElement(anItem);
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String newTitle) {
		this.title = newTitle;
	}

	public void append(int level, String message) {
		SlideItemFactory factory = new SlideItemFactory();
		SlideItem item = factory.createSlideItem(SlideItemTypes.TEXT_ITEM, level, message);
		this.append(item);
	}

	public SlideItem getSlideItem(int number) {
		return (SlideItem) this.items.elementAt(number);
	}

	public Vector<SlideItem> getSlideItems() {
		return this.items;
	}

	public int getSize() {
		return this.items.size();
	}

	public void draw(Graphics g, Rectangle area, ImageObserver view) {
		float scale = this.getScale(area);
	    int y = area.y;
		SlideItemFactory factory = new SlideItemFactory();
		SlideItem slideItem = factory.createSlideItem(SlideItemTypes.TEXT_ITEM, 0, this.getTitle());
		Style style = Style.getStyle(slideItem.getLevel());
	    slideItem.draw(area.x, y, scale, g, style, view);
	    y += slideItem.getBoundingBox(g, view, scale, style).height;
	    for (int number=0; number<this.getSize(); number++) {
	      slideItem = (SlideItem) this.getSlideItems().elementAt(number);
	      style = Style.getStyle(slideItem.getLevel());
	      slideItem.draw(area.x, y, scale, g, style, view);
	      y += slideItem.getBoundingBox(g, view, scale, style).height;
	    }
	}

	private float getScale(Rectangle area) {
		return Math.min(((float)area.width) / ((float)WIDTH), ((float)area.height) / ((float)HEIGHT));
	}
}
