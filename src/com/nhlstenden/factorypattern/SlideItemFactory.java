package com.nhlstenden.factorypattern;

public class SlideItemFactory {
    public SlideItem createSlideItem(SlideItemTypes slideItemTypes, int level, String content) {
        switch (slideItemTypes) {
            case TEXT_ITEM :
                return new TextItem(level, content);
            case BITMAP_ITEM :
                return new BitmapItem(level, content);
            default:
                System.err.println("Unknown Element type");
        }

        return null;
    }
}
