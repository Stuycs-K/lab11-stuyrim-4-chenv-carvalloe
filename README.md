[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/KprAwj1n)
# APCS - Stuyrim

## Features

Make a clear list of features that work/dont work

:white_check_mark: This feature works.

:question: This feature works partially.

:ballot_box_with_check: This extra (beyond the things that the lab was supposed to do) feature works.

:x: This required feature does not work.

:beetle: This is a bug that affects the game.

### Progress

:white_check_mark: Playable party of 3 adventurers (includes attack, support, special, etc.)

:white_check_mark: Battling party of 1-3 random opponents (includes attack, support, special, etc.)

:white_check_mark: Player fields work as intended, and death occur once HP <= 0

:question: Program ends when the user choosed to quit, or all enemies is defeated, or the entire party is defeated. (win/lose screen to be added)

:question: Displaying the results of the attack/special/support inside frame borders

:question: Using attack/special operations on your opponents

:question: Using support operations on your team

:question: Functional displays/game screen for optimal play

:question: Updating screen for every attack turn

:x: Optional addons for players

:ballot_box_with_check: Randomized adventurer opponent names for each round (except boss)

:ballot_box_with_check: Colorful and unique boundary and headers for game screen

:ballot_box_with_check: This extra (beyond the things that the lab was supposed to do) feature works.
## Adventurer Subclasses

| Specifications | Apprentice Chef (modified from CodeWarrior) |   Baker  | Charcutier | Boss  |
| :------------------- | :----------: | :----------: | :----------: | :--------: |
| Maximum HP             | 15      | 30       | 20| 40 |
| Special            | sauce      | leavener     | salami |  salt |
| Maximum Special | 1 | 15 | 20 | 25|
| Attack               | Prep Work: Deals a fast and furious flurry of prepped items at the opponent, dealing 2-3 HP points of damage. | Batter Bomb: A hot, fiery ball of batter is launched at the enemies, dealing 3 HP points of damage (with a 20% chance to deal 2 more HP points). | Smoked Strike: Unleashes a dark cloud of smoke to confuse the opponent. 50-50 chance to cause 50% damage/3 HP points of damage to yourself or selected opponent, whichever is greater. | Hot Oil Spill: Deals a total loss of 10 HP, distributed randomly between enemies and the party. |
| Ally Support | Last-Minute Fix: Heals 1-4 HP points on a selected team member. | Shortcake Shield: Blocks 50% of damage to one selected team member over opponent's next 2 turns. | Cured Edge: Heals x HP points and x/2 special points on a selected ally, where, if y=your selected number from 1-5, and z=random number from 1-5, x=(y)*(z/y+1), and integer division is used (up to maximums). Uses 5 sausage.| Beef Wellington: Ally food is reviewed by Gordon. For enemies, 50-50 chance that Gordon likes their dish. If he does, 5 HP points and 5 special are gained; otherwise, only 2 HP points and 2 special are gained.|
| Self Support | Stock Relief: Heals 2 HP points, up to a max of 15. | Sugar Rush: Provides 3 leaveners, up to a max of 15. | Marination: Randomly restores 20% of your special or your HP points (up to maximums). | Michelin Star: Celebrities reviewing Gordon's food will gift 7 salt and 5 HP points, or give nothing (each 50/50 chance).|
| Special Attack | Recipe Swap: Once a game, swaps HP with an opponent, up to the max HP. Consumes 1 sauce. | Rolling Pin: Reduces the opponent HP or special points by 40%, whichever is greater. Consumes 6 leavener. | Sausage Sling: Creates a barrage of sausages across the field, reducing Charcutier and selected enemy (opponent/ally) by x HP (where x is a random number from 1-7). Uses 4 sausage. | Idiot Sandwich: Consumes 12 salt to remove all special points from selected opponent. |
