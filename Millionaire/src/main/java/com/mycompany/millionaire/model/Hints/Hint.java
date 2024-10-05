
package com.mycompany.millionaire.model.Hints;

import com.mycompany.millionaire.component.PanelTemplate;
import com.mycompany.millionaire.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.media.AudioManager;
import com.mycompany.millionaire.media.ImageManager;
import com.mycompany.millionaire.model.ComponentServiceImpl;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author pavel
 */
@Getter
@Setter
public class Hint { 
    
    private JLabel fiftyToFiftyHint;
    private JLabel friendCallHint;
    private JLabel audienceHelpHint;
    
    private FiftyToFifty fiftyToFifty;
    private AudienceVote audienceVote;
    private FriendCall friendCall;
    
    private boolean fiftyToFiftyAvailible;
    private boolean friendCallAvailible;
    private boolean audienceHelpAvailible;
    
    private final ComponentServiceImpl service;
    private final PanelTemplate panelConfig;
   
    
    public Hint() throws  IOException {
        fiftyToFiftyHint = new JLabel();
        friendCallHint = new JLabel();
        audienceHelpHint = new JLabel();
        
        this.service = new ComponentServiceImpl();
        this.panelConfig = new PanelTemplate();
        
        this.fiftyToFiftyAvailible = true;
        this.friendCallAvailible = true;
        this.audienceHelpAvailible = true;
        
        fiftyToFiftyHint = new LabelBuilderImpl()
                .image(ImageManager.getImageIcon("fiftyhint"))
                .bounds(30, 30)
                .hoverImage("fiftyhoverhint")
                .get();
        
        fiftyToFiftyHint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(fiftyToFiftyAvailible) {
                    fiftyToFiftyAvailible = false;
                    try {
                        AudioManager.handleAudioEvent("50-50");
                        fiftyToFifty = new FiftyToFifty();
                        fiftyToFifty.removeWrongOptions();
                    } catch (UnsupportedAudioFileException | 
                             IOException | 
                             LineUnavailableException ex) {
                        Logger.getLogger(Hint.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    fiftyToFiftyHint = new LabelBuilderImpl(fiftyToFiftyHint)
                        .setY(fiftyToFiftyHint.getY() + 2)
                        .image(ImageManager.getImageIcon("fiftyexpired"))
                        .bounds(30, 30)
                        .removeMouseListeners()
                        .get();
                }
            }
        });
        
        
        
        friendCallHint = new LabelBuilderImpl()
                .image(ImageManager.getImageIcon("callhint"))
                .bounds(120, 30)
                .hoverImage("callhoverhint")
                .get();
        
        friendCallHint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(friendCallAvailible) {
                    friendCallAvailible = false;
                    try {
                        AudioManager.muteIntro();
                        AudioManager.handleAudioEvent("friendcall");
                        friendCall = new FriendCall();
                        friendCall.performFriendCall();
                    } catch (UnsupportedAudioFileException | 
                             IOException | 
                             LineUnavailableException ex) {
                        Logger.getLogger(Hint.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    friendCallHint = new LabelBuilderImpl(friendCallHint)
                        .setY(friendCallHint.getY() + 15)
                        .image(ImageManager.getImageIcon("callexpired"))
                        .removeMouseListeners()
                        .get();
                }
            }
        });
        
        audienceHelpHint = new LabelBuilderImpl()
                .image(ImageManager.getImageIcon("audiencehint"))
                .bounds(210, 30)
                .hoverImage("audiencehoverhint")
                .get();
        
        audienceHelpHint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(audienceHelpAvailible) {
                    audienceHelpAvailible = false;
                    try {
                        audienceVote = new AudienceVote();
                        AudioManager.muteIntro();
                        AudioManager.handleAudioEvent("audience");
                        audienceVote.performAudienceVoting();
                    } catch (UnsupportedAudioFileException | 
                             IOException | 
                             LineUnavailableException ex) {
                        Logger.getLogger(Hint.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    audienceHelpHint = new LabelBuilderImpl(audienceHelpHint)
                        .setY(audienceHelpHint.getY())
                        .image(ImageManager.getImageIcon("audienceexpired"))
                        .removeMouseListeners()
                        .get();
                }
            }
        });
    }
    
    public void addHints(JPanel panel) {
        panel = service.addOnPanel(panel, friendCallHint, fiftyToFiftyHint, audienceHelpHint);
        panel.repaint();
    }
    
    public void addFiftyToFiftyHint(JPanel panel) {
        panel.add(fiftyToFiftyHint);
        panel.repaint();
    }
}
