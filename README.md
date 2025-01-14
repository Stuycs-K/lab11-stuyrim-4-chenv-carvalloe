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

:x: Adding + testing adventurers

:x: Creating displays/game screen

:x: Creating opponents/special boss

:x: Optional addons for players

## Adventurer Subclasses

| Specifications | Apprentice Chef (modified from CodeWarrior) |   Baker  | Charcutier | Boss  |
| :------------------- | :----------: | :----------: | :----------: | :--------: |
| Maximum HP             | 15      | 30       | 20| 40 |
| Special            | sauce      | leavener     | salami |  salt |
| Maximum Special | 1 | 15 | 20 | 25|
| Attack               | Prep Work: Deals a fast and furious flurry of prepped items at the opponent, dealing 2-3 HP points of damage. | Batter Bomb: A hot, fiery ball of batter is launched at the enemies, dealing 3 HP points of damage (with a 20% chance to deal 2 more HP points). | Smoked Strike: Unleashes a dark cloud of smoke to confuse the opponent. 50-50 chance to cause 50% damage/3 HP points of damage to yourself or selected opponent, whichever is greater. | Hot oil spill: total 10 lost, but randomly how much other will lose and how much you will lose. |
| Ally Support | Last-Minute Fix: Heals 1-4 HP points on a selected team member. | Shortcake Shield: Blocks 50% of damage to one selected team member over opponent's next 2 turns. | Cured Edge: Heals x HP points and x/2 special points on a selected ally, where, if y=your selected number from 1-5, and z=random number from 1-5, x=(y)*(z/y+1), and integer division is used (up to maximums). Uses 5 sausage.| BEEF WELLINGTON: 50-50 chance that Gordon likes your dish. If he does you get 5 HP poings and 5 special. If not, you get 2 HP and 2 special |
| Self Support | Stock Relief: Heals 2 HP points, up to a max of 15. | Sugar Rush: Provides 3 leaveners, up to a max of 15. | Marination: Randomly restores 20% of your special or your HP points (up to maximums). | Michelin Star: celebrity gifts you 7 special and 5 HP points by 50/50 chance if they like you |
| Special Attack | Recipe Swap: Once a game, swaps HP with an opponent, up to the max HP. Consumes 1 sauce. | Rolling Pin: Reduces the opponent HP or special points by 40%, whichever is greater. Consumes 6 leavener. | Sausage Sling: Creates a barrage of sausages across the field, reducing Charcutier and selected enemy (opponent/ally) by x HP (where x is a random number from 1-7). Uses 4 sausage. | Idiot Sandwich: if has 20 special, can completely eliminate special of opponent |
