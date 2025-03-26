import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.TextLayout;
import java.awt.font.TextAttribute;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class TextItem extends SlideItem {
	private String text;
	private static final String EMPTYTEXT = "No Text Given";

	public TextItem(int level, String text) {
		super(level);
		this.text = (text != null) ? text : EMPTYTEXT;
	}

	public TextItem() {
		this(0, EMPTYTEXT);
	}

	public String getText() {
		return text;
	}

	/** 🟡 Implementing the abstract draw method */
	@Override
	protected void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer) {
		if (text.isEmpty()) return;

		List<TextLayout> layouts = getLayouts(g, style, scale);
		Point pen = new Point(x + (int)(style.indent * scale), y + (int) (style.leading * scale));
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(style.color);

		for (TextLayout layout : layouts) {
			pen.y += layout.getAscent();
			layout.draw(g2d, pen.x, pen.y);
			pen.y += layout.getDescent();
		}
	}

	@Override
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style) {
		List<TextLayout> layouts = getLayouts(g, style, scale);
		int xsize = 0, ysize = (int) (style.leading * scale);

		for (TextLayout layout : layouts) {
			Rectangle2D bounds = layout.getBounds();
			xsize = Math.max(xsize, (int) bounds.getWidth());
			ysize += bounds.getHeight() + layout.getLeading() + layout.getDescent();
		}

		return new Rectangle((int) (style.indent * scale), 0, xsize, ysize);
	}

	private List<TextLayout> getLayouts(Graphics g, Style style, float scale) {
		List<TextLayout> layouts = new ArrayList<>();
		AttributedString attrStr = getAttributedString(style, scale);
		Graphics2D g2d = (Graphics2D) g;
		FontRenderContext frc = g2d.getFontRenderContext();
		LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
		float wrappingWidth = (Slide.WIDTH - style.indent) * scale;

		while (measurer.getPosition() < text.length()) {
			layouts.add(measurer.nextLayout(wrappingWidth));
		}

		return layouts;
	}

	private AttributedString getAttributedString(Style style, float scale) {
		AttributedString attrStr = new AttributedString(text);
		attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, text.length());
		return attrStr;
	}

	@Override
	public String toString() {
		return "TextItem[" + getLevel() + "," + getText() + "]";
	}
}
