![screenshots](https://github.com/hanes1233/Millionaire_Game/blob/main/screenshots/millionaire_menu.png)
#
![screenshots](https://github.com/hanes1233/Millionaire_Game/blob/main/screenshots/millionaire_game.png)
#
![screenshots](https://github.com/hanes1233/Millionaire_Game/blob/main/screenshots/question_tables.png)
#
![screenshots](https://github.com/hanes1233/Millionaire_Game/blob/main/screenshots/create_question.png)

# Who wants to be a Millionaire? Game 

## Description 

Java swing game based on popular TV show. 

### FIRST PART:
User invited to create game with his preferences, like game language, difficulty and quiz subject.
After setting up new game, Java fetches questions from MongoDB, shuffle it due to user's settings and displays questions in JFrame. 
Rules of game very copying rules of TV show (including music, logos, hints etc) so no need to describe it in details. 
When user lose or win the game, he's asked to write his username to save score into mongodb database.
Finally user can check for top 20 scores in JList from the main menu.

### SECOND PART:
By using React and Node.js created API to communicate with database to handle questions. There are CRUD operations for 2 tables of questions (in Czech and English languages),
very user-friendly filters with async search and limit results and Pagination available.  


## How to use

Famous quiz game with 15 questions, hints and score stats.

## Technologies used

+ NetBeans
+ Java
+ Lombok
+ Swing
+ AWT
+ Maven
+ React
+ MongoDB
+ JavaScript
+ Node.js
+ Postman
+ Bootstrap
