package com.myblog.components.table.cellrenderereditor.colorRenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

public class ColorIcon implements Icon {
	static final int BOX = 10;
	private Color color;

	public ColorIcon(Color c) {
		color = c;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		Color old = g.getColor();
		g.setColor(color);
		g.fillRect(x, y, BOX, BOX);
		g.setColor(Color.black);
		g.drawRect(x, y, BOX, BOX);
		g.setColor(old);
	}

	public int getIconWidth() {
		return BOX;
	}

	public int getIconHeight() {
		return BOX;
	}

//    public final static Color WHITE = white;
//    public final static Color LIGHT_GRAY = lightGray;
//    public final static Color GRAY = gray;
//    public final static Color DARK_GRAY = darkGray;
//    public final static Color BLACK = black;
//    public final static Color RED = red;
//    public final static Color PINK = pink;
//    public final static Color ORANGE = orange;
//    public final static Color YELLOW = yellow;
//    public final static Color GREEN = green;
//    public final static Color MAGENTA = magenta;
//    public final static Color CYAN = cyan;
//    public final static Color BLUE = blue;

	public static String getColorName(Color c) {
		if (c.equals(Color.RED)) {
			return "Color.RED";
		} else if (c.equals(Color.ORANGE)) {
				return "Color.ORANGE";
		} else if (c.equals(Color.WHITE)) {
			return "Color.WHITE";
		} else {
			return "ColorIcon";
		}
	}
}