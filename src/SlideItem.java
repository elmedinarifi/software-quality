import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

/**
 * De abstracte klasse voor een item op een Slide.
 * Implementatie van de Template Method Pattern.
 */
public abstract class SlideItem {
	private int level;

	public SlideItem(int level) {
		this.level = level;
	}

	public SlideItem() {
		this(0);
	}

	public int getLevel() {
		return level;
	}

	public final void render(int x, int y, float scale, Graphics g, ImageObserver observer) {
		Style style = Style.getStyle(getLevel()); // 🟢 Get the correct style
		g.setColor(style.color);
		g.setFont(style.getFont(scale));
		draw(x, y, scale, g, style, observer); // 🔥 Calls subclass-specific drawing logic
	}

	protected abstract void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer);

	public abstract Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style);
}
