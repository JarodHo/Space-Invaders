# Space Invaders

## Prompt: Recreate a videogame using Eclipse Workspace
After browsing a couple of simple games like Space Race, Tic-Tac-Toe, Snake, Crossy Road, etc. I decided to recreate Space Invaders in Java for my AP Computer Science A project. The game was simple enough that I could recreate the key concepts using the coding skills that I was taught earlier in the year, but difficult enough that I would have to come up with creative ways to solve problems - this would be a good game for my skill level. The program is completely finished, including barriers which I initially thought I would not have the time to add. 

### Running the game
The game is completely ready to be run using the Frame.java file after cloning this project.

### Controls
You control a spaceship at the bottom of the screen and is only able to move left and right, and tries to shoot every enemy and the UFO which appears on screen occasionally. However, once the spaceship you control is hit three times **you lose**.

Move left by using the "A" or left arrow key.

Move right by using the "D" or right arrow key.

Shoot the enemies using the space key.

### Gameplay
![](spaceinv.gif)

### Game Code Explanation
I created a class for each object listed below. Most of these classes were copies of the same class which called to different images and different scales for the image. For example, I had a different class for each enemy.

##### Class List:
Background, Barrier, Button (for restart buttons), Enemy, Enemy2, Enemy3, EnemyLazer, Lazer, Lives, Player, UFO

## Game Mechanics / Code
Here is an explanation of the most important/complicated mechanics in the game.
### Hit Detection

![hitdetect](https://user-images.githubusercontent.com/70664893/151582702-f52216c5-bb2a-4101-ad9d-11f29d7ed04b.PNG)

This code basically checks if the player's lazer has hit one of the enemies. If it does, it will reset the lazer, increase the score, and set a 2D boolean array to true which is in the same spot as the enemy hit - this basically keeps track of which enemies were hit and need to stop being painted.
### Enemy Movement

![movement](https://user-images.githubusercontent.com/70664893/151582672-ef4f9a2b-9578-48af-9f0a-1aa57fdececc.PNG)

This code checks where the left and right most aliens are, if they hit one of the edges they will all move down. If they hit a certain y-level (the barriers) the player will also lose.
### Enemy Fire

![enemyfire](https://user-images.githubusercontent.com/70664893/151582540-d0c168b8-b47b-4ca5-b065-0500568ba263.PNG)

This code first checks which enemies can fire (those without other enemies in front of them) and generates random numbers, if a "2" is generated then that enemy will fire.

### Credits:
I created this game independently in class.


