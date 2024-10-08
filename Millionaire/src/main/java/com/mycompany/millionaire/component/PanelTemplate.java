
package com.mycompany.millionaire.component;

//region imports
import com.mycompany.millionaire.component.builder.ButtonBuilderImpl;
import com.mycompany.millionaire.form.FormFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
//endregion

/**
 *
 * @author pavel
 */
public class PanelTemplate {
    
    private static JPanel panel;
    private JButton backButton;
    private final FormFactory FACTORY;
    
    
    public PanelTemplate() {
        this.FACTORY = new FormFactory();
    }
    
    
    public static JPanel getPanel() throws IOException {
        Image backgroundImage = ImageIO.read(new File("/home/pavel/NetBeansProjects/Millionaire/src/main/resources/images/wallpaper.jpg"));
        panel = new JPanel(new BorderLayout()) {         
                    @Override 
                        public void paintComponent(Graphics g) {
                            g.drawImage(backgroundImage, 0, 0, null);
                        }
        };
        panel.setLayout(null);
        return panel;
    }
    
    public static JPanel getPanelWithRectangle() throws IOException {
        Image backgroundImage = ImageIO.read(new File("/home/pavel/NetBeansProjects/Millionaire/src/main/resources/images/wallpaper.jpg"));
        panel = new JPanel(new BorderLayout()) {         
                    @Override 
                        public void paintComponent(Graphics g) {
                            g.drawImage(backgroundImage, 0, 0, null);
                            g.setColor(Color.BLACK);
                            g.fillRect(0,250,320,205);
                            g.drawRect(320,250,320,205);
                        }
        };
        panel.setLayout(null);
        return panel;
    }
    
    //REFACTOR...
    public void addBackButton(JFrame form) {
        this.backButton = new ButtonBuilderImpl()
                .text("<")
                .font(new Font("Serif", Font.BOLD, 16))
                .foreground(Color.WHITE)
                .background(new Color(0, 38, 75))
                .size(50,25)
                .bounds(15,15)
                .selectedItemHover()
                .get();
        
        backButton.addActionListener((ActionListener) -> {
            try {
                FACTORY.createForm(true);
                form.dispose();
            } catch (IOException |
                    NullPointerException |
                    ClassNotFoundException |
                    InstantiationException |
                    LineUnavailableException |
                    IllegalAccessException | 
                    UnsupportedLookAndFeelException |
                    UnsupportedAudioFileException e ) {
                throw new RuntimeException("Error catch trying to play intro(MainComponent.class): " + e);
            }
        });
        panel.add(this.backButton);
    } 
}
