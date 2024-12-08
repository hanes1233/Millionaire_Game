
package com.mycompany.millionaire.controller.hint;

import com.mycompany.millionaire.data.entity.Question;
import com.mycompany.millionaire.model.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.model.hint.AudienceVoteService;
import com.mycompany.millionaire.model.hint.FiftyToFiftyService;
import com.mycompany.millionaire.model.hint.FriendCallService;
import com.mycompany.millionaire.model.media.AudioManager;
import com.mycompany.millionaire.model.media.ImageManager;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Hint controller provides hints on the panel
 * @author pavel
 */
@Getter
@Setter
public class Hint { 
    
    // Declare labels
    private JLabel fiftyToFiftyHint;
    private JLabel friendCallHint;
    private JLabel audienceHelpHint;


    // Declare instances of every hint service
    private FiftyToFiftyService fiftyToFifty;
    private AudienceVoteService audienceVote;
    private FriendCallService friendCall;

    /**
     * Variables to check hints availability, needed to have control
     * under making every hint available just for one time per game
     */
    private boolean fiftyToFiftyAvailible;
    private boolean friendCallAvailible;
    private boolean audienceHelpAvailible;
    
    // Current question instance
    private Question currentQuestion;
    
    // Constructor
    public Hint() {
        // Set hints availability on true 
        this.fiftyToFiftyAvailible = true;
        this.friendCallAvailible = true;
        this.audienceHelpAvailible = true;
    }
    
    // Initialize components 
    public void initHints() {
        fiftyToFiftyHint = new LabelBuilderImpl()
                .image(ImageManager.getImageIcon("fiftyhint"))
                .bounds(30, 30)
                .onHover("fiftyhoverhint")
                .build();
        
        fiftyToFiftyHint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Switch hint availability on false on click
                if(fiftyToFiftyAvailible) {
                    fiftyToFiftyAvailible = false;
                    AudioManager.handleAudioEvent("50-50");
                    // Initialize hint with current question
                    fiftyToFifty = new FiftyToFiftyService(currentQuestion);
                    // Run logic to remove wrong option from view
                    fiftyToFifty.removeWrongOptions();
                    // Build up new label with 'no active' image
                    fiftyToFiftyHint = new LabelBuilderImpl(fiftyToFiftyHint)
                        .image(ImageManager.getImageIcon("fiftyexpired"))
                        .bounds(30, 35)
                        .removeMouseListeners()
                        .build();
                }
            }
        });        
        
        friendCallHint = new LabelBuilderImpl()
                .image(ImageManager.getImageIcon("callhint"))
                .bounds(120, 30)
                .onHover("callhoverhint")
                .build();
        
        friendCallHint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Switch hint availability on false on click
                if (friendCallAvailible) {
                    friendCallAvailible = false;
                    AudioManager.muteIntro();
                    AudioManager.handleAudioEvent("friendcall");
                    // Initialize hint with current question
                    friendCall = new FriendCallService(currentQuestion);
                    // Run call simulation
                    friendCall.callFriend();
                    // Build up new label with 'no active' image
                    friendCallHint = new LabelBuilderImpl(friendCallHint)
                        .setY(friendCallHint.getY() + 8)
                        .image(ImageManager.getImageIcon("callexpired"))
                        .removeMouseListeners()
                        .build();
                }
            }
        });
        
        audienceHelpHint = new LabelBuilderImpl()
                .image(ImageManager.getImageIcon("audiencehint"))
                .bounds(210, 30)
                .onHover("audiencehoverhint")
                .build();
        
        audienceHelpHint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Switch hint availability on false on click
                if (audienceHelpAvailible) {
                    audienceHelpAvailible = false;
                    // Initialize hint with current question
                    audienceVote = new AudienceVoteService(currentQuestion);
                    AudioManager.muteIntro();
                    AudioManager.handleAudioEvent("audience");
                    // Simulate audience vote
                    audienceVote.performAudienceVoting();
                    // Build up new label with 'no active' image
                    audienceHelpHint = new LabelBuilderImpl(audienceHelpHint)
                        .setY(audienceHelpHint.getY() + 8)
                        .image(ImageManager.getImageIcon("audienceexpired"))
                        .removeMouseListeners()
                        .build();
                }
            }
        });
    }
    
    public List<JLabel> getHints() {
        return List.of(fiftyToFiftyHint,friendCallHint, audienceHelpHint);
    }
}
