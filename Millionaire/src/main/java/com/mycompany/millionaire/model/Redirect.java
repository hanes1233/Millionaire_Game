
package com.mycompany.millionaire.model;

import com.mycompany.millionaire.form.AboutAuthor;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pavel
 */
public class Redirect {
    
    public static void redirectOnWeb(String address) {
                try {
                    URI uri = new URI(address);
                    Desktop.getDesktop().browse(uri);
                } catch (URISyntaxException | IOException ex) {
                    Logger.getLogger(AboutAuthor.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    public static void redirectToMail(String address) {
                try {
                    URI uri = new URI("mailto:" + address);
                    Desktop.getDesktop().mail(uri);
                } catch (URISyntaxException | IOException ex) {
                    Logger.getLogger(AboutAuthor.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
}
