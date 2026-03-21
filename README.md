# Double Trouble

This game called "Double Trouble" consists of

- Three (3) green buttons
- Seven (7) yellow buttons
- Five (5) orange buttons

Two players take turns clicking (and therefore disabling) as many buttons of a single color as they wish.
The player who removes the last button wins!

## History

The game we call "Double Trouble" is really a particular instantiation of the
game [NIMS](https://en.wikipedia.org/wiki/Nim).
The original origins of the game is unknown but the modern interpretation of the game can be traced back to 16th century
in Europe.
The current name NIM was coined by Charles L. Bouton of Harvard University. Charles who also was the first to solve the
game in 1901

## Modern Appearances

The game of NIM reminds me of *Honeycomb Havoc* in Mario party 2. In the game players jump to hit a block that chooses 1
or 2 items (fruits/coins). Similar to the game NIM, you want to leave the game state balanced to avoid taking a
honeycomb (which gets you stung by bees). In HH, the optimal strategy is to leave items in multiples of three so you can
always take another item and not be forced to take the honeycomb.
![Honneycomb Havoc](./honeycomb-havoc.jpg)
## How To Run
**Requirements:** JDK 21+
```bash
git clone https://github.com/noahheller/DoubleTrouble.git
cd DoubleTrouble
```
### Mac/Linux
`./mvnw javafx:run`
### Windows
`mvnw.cmd javafx:run`

## How to Play 
Before the game starts all tiles will be black. This is when you can change the game options like the strategy used and who goes first. 

When you are ready to begin you may press "Start Game".

Once the game begins you can choose as many tiles of the same color as you want. When you are done with your selection press the "End Turn" button.
You will notice the computer will quickly take its turn and update the board. The game then continues until either you, or the computer, click the last button. 
Depending on who won there will be a winning message at the top of the screen. At this point you are free to change the game options and then click "Start Game" to play again.