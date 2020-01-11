package com.views;

import java.awt.GraphicsConfiguration;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class FrameLocationSetter {
    static Rectangle getMaxWindowBounds(JFrame frame) {
        GraphicsConfiguration config = frame.getGraphicsConfiguration();
        Rectangle bounds = config.getBounds();
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(config);
        bounds.x += insets.left;
        bounds.y += insets.top;
        bounds.width -= insets.left + insets.right;
        bounds.height -= insets.top + insets.bottom;
        return bounds;
    }
    
    static void setLocationToTop(JFrame frame) {
        frame.setLocation(frame.getX(), getMaxWindowBounds(frame).y);
    }
    
    static void setLocationToRight(JFrame frame) {
        Rectangle bounds = getMaxWindowBounds(frame);
        frame.setLocation(bounds.x + bounds.width - frame.getWidth(), frame.getY());
    }
	
    static void setLocationToLeft(JFrame frame) {
        frame.setLocation(getMaxWindowBounds(frame).x, frame.getY());
    }
}
