
package com.mycompany.millionaire.media;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author pavel
 */
public class ImageManager {
    
    public static final Image getImage(ImageIcon icon) {
        return new ImageIcon("/home/pavel/NetBeansProjects/Millionaire/src/main/resources/images/icon.png").getImage();
    }
    
    public static final ImageIcon getImageIcon(String image) {
        return new ImageIcon("/home/pavel/NetBeansProjects/Millionaire/src/main/resources/images/" + image + ".png");
    }
    
    public static final Image scaleImage(String path) {
        ImageIcon icon = ImageManager.getImageIcon(path);
        Image image = icon.getImage();
        return image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
    }
}
