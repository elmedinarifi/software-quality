package com.nhlstenden.jabberpoint;

import com.nhlstenden.factorypattern.Slide;
import com.nhlstenden.factorypattern.SlideViewerComponent;

import java.util.ArrayList;


/**
 * <p>com.nhlstenden.demo.Presentation houdt de slides in de presentatie bij.</p>
 * <p>Er is slechts een instantie van deze klasse aanwezig.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Presentation {
	private String showTitle; 
	private ArrayList<Slide> showList = null; 
	private int currentSlideNumber = 0; 
	private SlideViewerComponent slideViewComponent = null; 

	public Presentation() {
		this.slideViewComponent = null;
		this.clear();
	}

	public Presentation(SlideViewerComponent slideViewerComponent) {
		this.slideViewComponent = slideViewerComponent;
		this.clear();
	}

	public int getSize() {
		return this.showList.size();
	}

	public String getTitle() {
		return this.showTitle;
	}

	public void setTitle(String nt) {
		this.showTitle = nt;
	}

	public void setShowView(SlideViewerComponent slideViewerComponent) {
		this.slideViewComponent = slideViewerComponent;
	}

	public int getSlideNumber() {
		return this.currentSlideNumber;
	}

	public void setSlideNumber(int number) {
		this.currentSlideNumber = number;
		if (this.slideViewComponent != null) {
			this.slideViewComponent.update(this, getCurrentSlide());
		}
	}

	public void prevSlide() {
		if (this.currentSlideNumber > 0) {
			this.setSlideNumber(this.currentSlideNumber - 1);
	    }
	}

	public void nextSlide() {
		if (this.currentSlideNumber < (this.showList.size()-1)) {
			this.setSlideNumber(this.currentSlideNumber + 1);
		}
	}

	public void clear() {
		this.showList = new ArrayList<Slide>();
		this.setSlideNumber(-1);
	}

	public void append(Slide slide) {
		this.showList.add(slide);
	}

	public Slide getSlide(int number) {
		if (number < 0 || number >= this.getSize()){
			return null;
	    }
		return (Slide) this.showList.get(number);
	}

	public Slide getCurrentSlide() {
		return this.getSlide(this.currentSlideNumber);
	}

	public void exit(int n) {
		System.exit(n);
	}
}
