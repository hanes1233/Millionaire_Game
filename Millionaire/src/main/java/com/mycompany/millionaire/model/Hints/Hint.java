
package com.mycompany.millionaire.model.Hints;

import com.mycompany.millionaire.component.PanelConfiguration;
import com.mycompany.millionaire.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.data.Question;
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
import javax.swing.JButton;
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
    
    private String language;
    
    private JButton optionA;
    private JButton optionB;
    private JButton optionC;
    private JButton optionD; 
    
    private JPanel panel;
    private Question currentQuestion;
    
    private FiftyToFifty fiftyToFifty;
    private AudienceVote audienceVote;
    private FriendCall friendCall;
    
    private boolean fiftyToFiftyAvailible;
    private boolean friendCallAvailible;
    private boolean audienceHelpAvailible;
    
    private final ComponentServiceImpl service;
    private final PanelConfiguration panelConfig;
   
    
    public Hint() throws  IOException {
        fiftyToFiftyHint = new JLabel();
        friendCallHint = new JLabel();
        audienceHelpHint = new JLabel();
        
        this.service = new ComponentServiceImpl();
        this.panelConfig = new PanelConfiguration();
        
        this.fiftyToFiftyAvailible = true;
        this.friendCallAvailible = true;
        this.audienceHelpAvailible = true;
        
        fiftyToFiftyHint = new LabelBuilderImpl(fiftyToFiftyHint)
                .image(ImageManager.getImageIcon("fiftyhint"))
                .bounds(30, 30, service.getWidth(fiftyToFiftyHint), service.getHeight(fiftyToFiftyHint))
                .hoverImage("fiftyhoverhint")
                .get();
        
        fiftyToFiftyHint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(fiftyToFiftyAvailible) {
                    fiftyToFiftyAvailible = false;
                    try {
                        AudioManager.handleAudioEvent("50-50");
                        fiftyToFifty = new FiftyToFifty(currentQuestion, panel);
                        fiftyToFifty.removeWrongOptions(optionA,optionB,optionC,optionD);
                    } catch (UnsupportedAudioFileException | 
                             IOException | 
                             LineUnavailableException ex) {
                        Logger.getLogger(Hint.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    fiftyToFiftyHint = new LabelBuilderImpl(fiftyToFiftyHint)
                        .setY(fiftyToFiftyHint.getY() + 9)
                        .image(ImageManager.getImageIcon("fiftyexpired"))
                        .removeMouseListeners()
                        .get();
                }
            }
        });
        
        
        
        friendCallHint = new LabelBuilderImpl(friendCallHint)
                .image(ImageManager.getImageIcon("callhint"))
                .bounds(120, 30, service.getWidth(friendCallHint), service.getHeight(friendCallHint))
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
                        friendCall = new FriendCall(panel, language);
                        friendCall.defineFriendList();
                    } catch (UnsupportedAudioFileException | 
                             IOException | 
                             LineUnavailableException ex) {
                        Logger.getLogger(Hint.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    friendCallHint = new LabelBuilderImpl(friendCallHint)
                        .setY(friendCallHint.getY() + 9)
                        .image(ImageManager.getImageIcon("callexpired"))
                        .removeMouseListeners()
                        .get();
                }
            }
        });
        
        audienceHelpHint = new LabelBuilderImpl(audienceHelpHint)
                .image(ImageManager.getImageIcon("audiencehint"))
                .bounds(210, 30, service.getWidth(audienceHelpHint), service.getHeight(audienceHelpHint))
                .hoverImage("audiencehoverhint")
                .get();
        
        audienceHelpHint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(audienceHelpAvailible) {
                    audienceHelpAvailible = false;
                    try {
                        audienceVote = new AudienceVote(panel,currentQuestion,optionA,optionB,optionC,optionD);
                        AudioManager.muteIntro();
                        AudioManager.handleAudioEvent("audience");
                        audienceVote.performAudienceVoting();
                    } catch (UnsupportedAudioFileException | 
                             IOException | 
                             LineUnavailableException ex) {
                        Logger.getLogger(Hint.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    audienceHelpHint = new LabelBuilderImpl(audienceHelpHint)
                        .setY(audienceHelpHint.getY() + 9)
                        .image(ImageManager.getImageIcon("audienceexpired"))
                        .removeMouseListeners()
                        .get();
                }
            }
        });
    }
    
    public void addHints(JPanel panel) {
        panel = panelConfig.addOnPanel(panel, friendCallHint, fiftyToFiftyHint, audienceHelpHint);
        panel.repaint();
    }
    
    public void addFiftyToFiftyHint(JPanel panel) {
        panel.add(fiftyToFiftyHint);
        panel.repaint();
    }
}
