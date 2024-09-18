
package com.mycompany.millionaire.media;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author pavel
 * Class for handling images
 */
public class ImageManager {
    
    /**
     * Get image icon
     * @return image
     */
    public static final Image getImage() {
        return new ImageIcon("/home/pavel/NetBeansProjects/Millionaire/src/main/resources/images/icon.png").getImage();
    }
    
    /**
     * Get icon from provided path
     * @param image - name of image we want to fetch
     * @return icon
     */
    public static final ImageIcon getImageIcon(String image) {
        return new ImageIcon("/home/pavel/NetBeansProjects/Millionaire/src/main/resources/images/" + image + ".png");
    }
    
    /**
     * Scale image
     * @param path - fetch image from path name
     * @return scaled image
     */
    public static final Image scaleImage(String path) {
        ImageIcon icon = ImageManager.getImageIcon(path);
        Image image = icon.getImage();
        return image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
    }
}
