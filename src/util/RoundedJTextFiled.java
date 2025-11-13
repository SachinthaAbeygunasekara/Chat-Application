package util;

import javax.swing.*;
import java.awt.*;

public class RoundedJTextFiled {
    public static void makeTextFieldRounded(JTextField textField, int radius, Color bgColor, Color borderColor) {
    textField.setOpaque(false);          // remove default background
    textField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // padding inside
    textField.setBackground(bgColor);
    textField.setForeground(Color.BLACK);

    textField.setUI(new javax.swing.plaf.basic.BasicTextFieldUI() {
        @Override
        protected void paintSafely(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = textField.getWidth();
            int h = textField.getHeight();

            // Fill background rounded
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, w, h, radius, radius);

            // Optional border
            if (borderColor != null) {
                g2.setColor(borderColor);
                g2.drawRoundRect(0, 0, w - 1, h - 1, radius, radius);
            }

            g2.dispose();

            // Paint text
            super.paintSafely(g);
        }
    });
}

}
