
package com.mycompany.millionaire.controller.hint;

import com.mycompany.millionaire.data.entity.Question;
import com.mycompany.millionaire.model.component.builder.LabelBuilder;
import com.mycompany.millionaire.model.hint.AudienceVoteService;
import com.mycompany.millionaire.model.hint.FiftyToFiftyService;
import com.mycompany.millionaire.model.hint.FriendCallService;
import com.mycompany.millionaire.model.media.AudioManager;
import com.mycompany.millionaire.model.media.ImageManager;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        fiftyToFiftyHint = new LabelBuilder()
                .setImage(ImageManager.getImageIcon("fiftyhint"))
                .setBounds(30, 30)
                .build();
        
        fiftyToFiftyHint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Switch hint availability on false on click
                if (fiftyToFiftyAvailible) {
                    fiftyToFiftyAvailible = false;
                    AudioManager.handleAudioEvent("50-50");
                    // Initialize hint with current question
                    fiftyToFifty = new FiftyToFiftyService(currentQuestion);
                    // Run logic to remove wrong option from view
                    fiftyToFifty.removeWrongOptions();
                    // Build up new label with 'no active' image
                    fiftyToFiftyHint = new LabelBuilder(fiftyToFiftyHint)
                        .setImage(ImageManager.getImageIcon("fiftyexpired"))
                        .setBounds(30, 35)
                        .removeMouseListeners()
                        .build();
                }
            }
        });        
        
        friendCallHint = new LabelBuilder()
                .setImage(ImageManager.getImageIcon("callhint"))
                .setBounds(120, 30)
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
                    friendCallHint = new LabelBuilder(friendCallHint)
                        .setBounds(friendCallHint.getX(), friendCallHint.getY() + 8)
                        .setImage(ImageManager.getImageIcon("callexpired"))
                        .removeMouseListeners()
                        .build();
                }
            }
        });
        
        audienceHelpHint = new LabelBuilder()
                .setImage(ImageManager.getImageIcon("audiencehint"))
                .setBounds(210, 30)
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
                    audienceHelpHint = new LabelBuilder(audienceHelpHint)
                        .setBounds(audienceHelpHint.getX(), audienceHelpHint.getY() + 8)
                        .setImage(ImageManager.getImageIcon("audienceexpired"))
                        .removeMouseListeners()
                        .build();
                }
            }
        });

        // Elements to add image hover
        Map<JLabel, String> hints = new HashMap<>();
        hints.put(fiftyToFiftyHint, "fiftyhoverhint");
        hints.put(friendCallHint, "callhoverhint");
        hints.put(audienceHelpHint, "audiencehoverhint");

        // Process hint labels in hoverImage()
        hints.forEach(this::hoverImage);
    }
    
    public List<JLabel> getHints() {
        return List.of(fiftyToFiftyHint,friendCallHint, audienceHelpHint);
    }

    // Change image on hover, using image's name from parameter
    private void hoverImage(JLabel label, String imgPath) {
        Icon currentIcon = label.getIcon();
        int x = label.getX();
        int y = label.getY();
        int width = label.getWidth();
        int height = label.getHeight();
        label.setPreferredSize(new Dimension(width, height));
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label.setIcon(ImageManager.getImageIcon(imgPath));
                label.setBounds(x, y - 2, label.getPreferredSize().width, label.getPreferredSize().height);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Set back previous image
                label.setIcon(currentIcon);
                label.setBounds(x, y, label.getPreferredSize().width, label.getPreferredSize().height);
            }
        });
    }
}
