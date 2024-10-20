
package com.mycompany.millionaire.controller.hint;

import com.mycompany.millionaire.model.hint.FiftyToFiftyService;
import com.mycompany.millionaire.model.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.data.CurrentQuestion;
import com.mycompany.millionaire.model.hint.AudienceVoteService;
import com.mycompany.millionaire.model.hint.FriendCallService;
import com.mycompany.millionaire.model.media.AudioManager;
import com.mycompany.millionaire.model.media.ImageManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;
import lombok.Getter;
import lombok.Setter;

/**
 * Hint controller
 * @author pavel
 */
@Getter
@Setter
public class Hint { 
    
    // Declare labels
    private JLabel fiftyToFiftyHint;
    private JLabel friendCallHint;
    private JLabel audienceHelpHint;
    
    // Declare hints instances
    private FiftyToFiftyService fiftyToFifty;
    private AudienceVoteService audienceVote;
    private FriendCallService friendCall;
    
    // Values to check is hints available
    private boolean fiftyToFiftyAvailible;
    private boolean friendCallAvailible;
    private boolean audienceHelpAvailible;
    
    // Current question instance
    private CurrentQuestion currentQuestion;
    
    // Constructor
    public Hint() throws  IOException {
        // Set hints availability on true 
        this.fiftyToFiftyAvailible = true;
        this.friendCallAvailible = true;
        this.audienceHelpAvailible = true;
    }
    
    // Initialize components 
    public void initHints() {
        fiftyToFiftyHint = new LabelBuilderImpl()
                .setImage(ImageManager.getImageIcon("fiftyhint"))
                .setBounds(30, 30)
                .onHover("fiftyhoverhint")
                .get();
        
        fiftyToFiftyHint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Switch hint availability on false on click
                if(fiftyToFiftyAvailible) {
                    fiftyToFiftyAvailible = false;
                    try {
                        AudioManager.handleAudioEvent("50-50");
                        // Initialize hint with current question
                        fiftyToFifty = new FiftyToFiftyService(currentQuestion);
                        // Run logic to remove wrong option from view
                        fiftyToFifty.removeWrongOptions();
                    } catch (UnsupportedAudioFileException | 
                             IOException | 
                             LineUnavailableException ex) {
                        Logger.getLogger(Hint.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    // Build up new label with 'no active' image
                    fiftyToFiftyHint = new LabelBuilderImpl(fiftyToFiftyHint)
                        .setImage(ImageManager.getImageIcon("fiftyexpired"))
                        .setBounds(30, 35)
                        .removeMouseListeners()
                        .get();
                }
            }
        });        
        
        friendCallHint = new LabelBuilderImpl()
                .setImage(ImageManager.getImageIcon("callhint"))
                .setBounds(120, 30)
                .onHover("callhoverhint")
                .get();
        
        friendCallHint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Switch hint availability on false on click
                if(friendCallAvailible) {
                    friendCallAvailible = false;
                    try {
                        AudioManager.muteIntro();
                        AudioManager.handleAudioEvent("friendcall");
                        // Initialize hint with current question
                        friendCall = new FriendCallService(currentQuestion);
                        // Run call simulation
                        friendCall.callFriend();
                    } catch (UnsupportedAudioFileException | 
                             IOException | 
                             LineUnavailableException ex) {
                        Logger.getLogger(Hint.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    // Build up new label with 'no active' image
                    friendCallHint = new LabelBuilderImpl(friendCallHint)
                        .setY(friendCallHint.getY() + 8)
                        .setImage(ImageManager.getImageIcon("callexpired"))
                        .removeMouseListeners()
                        .get();
                }
            }
        });
        
        audienceHelpHint = new LabelBuilderImpl()
                .setImage(ImageManager.getImageIcon("audiencehint"))
                .setBounds(210, 30)
                .onHover("audiencehoverhint")
                .get();
        
        audienceHelpHint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Switch hint availability on false on click
                if(audienceHelpAvailible) {
                    audienceHelpAvailible = false;
                    try {
                        // Initialize hint with current question
                        audienceVote = new AudienceVoteService(currentQuestion);
                        AudioManager.muteIntro();
                        AudioManager.handleAudioEvent("audience");
                        // Simulate audience vote
                        audienceVote.performAudienceVoting();
                    } catch (UnsupportedAudioFileException | 
                             IOException | 
                             LineUnavailableException ex) {
                        Logger.getLogger(Hint.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    // Build up new label with 'no active' image
                    audienceHelpHint = new LabelBuilderImpl(audienceHelpHint)
                        .setY(audienceHelpHint.getY() + 8)
                        .setImage(ImageManager.getImageIcon("audienceexpired"))
                        .removeMouseListeners()
                        .get();
                }
            }
        });
        
    }
    
    public List<JLabel> getHints() {
        return List.of(fiftyToFiftyHint,friendCallHint, audienceHelpHint);
    }
}
