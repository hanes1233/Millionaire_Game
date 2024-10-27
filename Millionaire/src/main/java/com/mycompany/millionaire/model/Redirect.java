
package com.mycompany.millionaire.model;

import com.mycompany.millionaire.controller.AboutAuthor;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class provides utils to redirect user
 * @author pavel
 */
public class Redirect {
    
    /**
     * Redirect user to provided address 
     * @param address contains URL we want redirect user to
     */
    public static void redirectOnWeb(String address) {
                try {
                    URI uri = new URI(address);
                    Desktop.getDesktop().browse(uri);
                } catch (URISyntaxException | IOException ex) {
                    Logger.getLogger(AboutAuthor.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    /**
     * Redirect user to provided email address
     * @param email we want redirect user to
     */
    public static void redirectToMail(String email) {
                try {
                    URI uri = new URI("mailto:" + email);
                    Desktop.getDesktop().mail(uri);
                } catch (URISyntaxException | IOException ex) {
                    Logger.getLogger(AboutAuthor.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
}
