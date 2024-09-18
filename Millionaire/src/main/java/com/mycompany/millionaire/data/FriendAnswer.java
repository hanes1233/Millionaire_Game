
package com.mycompany.millionaire.data;

import java.util.Random;
import javax.swing.DefaultListModel;
import lombok.Getter;

/**
 *
 * @author pavel
 */
@Getter
public class FriendAnswer {
    
    private final DefaultListModel<String> engFriends;
    private final DefaultListModel<String> czFriends;
    
    public FriendAnswer() {
        this.engFriends = new DefaultListModel<>();
        this.czFriends = new DefaultListModel<>();
        this.pushFriendsToList();
    }
    
    private void pushFriendsToList() {
         this.engFriends.addElement("Elon Musk");
         this.engFriends.addElement("Joe Biden");
         this.engFriends.addElement("Mike Tyson");
         this.engFriends.addElement("Rihanna");
         
         this.czFriends.addElement("Andrej Babiš");
         this.czFriends.addElement("David Pastrnák");
         this.czFriends.addElement("Zeman");
         this.czFriends.addElement("Pavel Nedved");
    }
    
    public String getRandomEngAnswer() {
        String[] answers = new String[15];
        answers[0] = "Hello, who is this? How did you get my number?? Whatever, the answer is ";
        answers[1] = "Hi bro, I'm pretty sure it's ";
        answers[2] = "Good afternoon, I'm not sure, but I guess it could be ";
        answers[3] = "Hi darling, how are you doing? Oh-oh, you have no time...sorry...I think answer is ";
        answers[4] = "Hello, sorry mate, I don't know...may be ";
        answers[5] = "Good evening. I have no idea, sorry. Let's say it's ";
        answers[6] = "Hey, oh...of course it's ";
        answers[7] = "No worries! I'm sure answer is ";
        answers[8] = "Good morning. This question looks hard at this early time... I would say ";
        answers[9] = "Who the hell is this?! Did you seen what's time?! Get your answer and do not call here anymore : ";
        answers[10] = "Wazzup! It's easy - ";
        answers[11] = "Hello, you did right you call me, answer is ";
        answers[12] = "This is crazy hard question...but if I'd seet there, I'd choose ";
        answers[13] = "It's again raining outside...sorry, correct will be ";
        answers[14] = "You're luck! We used to discuss same question with Lady Gaga, and she told me that answer is ";
        
        
        return answers[new Random().nextInt(answers.length-1)];
    }
    
    public String getRandomCzAnswer() {
        String[] answers = new String[15];
        answers[0] = "Dobrý den, odpověd' je ";
        answers[1] = "Zdravím, myslím si že spravně je ";
        answers[2] = "Ahoj, určitě ";
        answers[3] = "Čus, nevím....vsadil bych na ";
        answers[4] = "No nazdar! Jak je? jo, chápu, spravná odpověd' je ";
        answers[5] = "Čau, nejsem si jistý, ale zkusíl bych ";
        answers[6] = "Dobrý den, s kým prosím mluvím? A kde jste dostal na mě číslo? dobře...odpověd' je ";
        answers[7] = "Já to přece nemužu vědět! Zkus třeba ";
        answers[8] = "No to je teda otázka...no nevím, zkuste tam dát ";
        answers[9] = "Dobré ráno, zrovna o tom jsem před chvíli přečetl, takže správně je ";
        answers[10] = "Dobrý večer, nevím jestli Vám mužu s tímto pomoci...ale mám pocít, že to je ";
        answers[11] = "Čago, no to je rozhodně ";
        answers[12] = "Zdravím kamaráde, dej tam ";
        answers[13] = "Ahoj, možna by to bylo ";
        answers[14] = "Dobrý den, pokud si nepletu, je to ";
        
        return answers[new Random().nextInt(answers.length-1)];
    }
    
}
