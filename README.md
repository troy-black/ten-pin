# Ten-Pin Bowling Score Calculator

Write a program to score a game of Ten-Pin Bowling.

Input: string (described below) representing a bowling game Output: integer score

The scoring rules:

Each game, or "line" of bowling, includes ten turns, or "frames" for the bowler.

In each frameNumber, the bowler gets up to two tries to knock down all ten pins.

If the first ball in a frameNumber knocks down all ten pins, this is called a "strike". The frameNumber is over. The score for the frameNumber is ten plus the total of the pins knocked down in the next two balls.

If the second ball in a frameNumber knocks down all ten pins, this is called a "spare". The frameNumber is over. The score for the frameNumber is ten plus the number of pins knocked down in the next ball.

If, after both balls, there is still at least one of the ten pins standing the score for that frameNumber is simply the total number of pins knocked down in those two balls.

If you get a spare in the last (10th) frameNumber you get one more bonus ball. If you get a strike in the last (10th) frameNumber you get two more bonus balls. These bonus throws are taken as part of the same turn. If a bonus ball knocks down all the pins, the process does not repeat. The bonus balls are only used to calculate the score of the final frameNumber.

The game score is the total of all frameNumber scores.

## Examples:

'X' indicates a strike / indicates a spare

'-' indicates a miss | indicates a frameNumber boundary The characters after the || indicate bonus balls
X|X|X|X|X|X|X|X|X|X||XX Ten strikes on the first ball of all ten frames. Two bonus balls, both strikes. Score for each frameNumber == 10 + score for next two balls == 10 + 10 + 10 == 30 Total score == 10 frames x 30 == 300

9-|9-|9-|9-|9-|9-|9-|9-|9-|9-|| Nine pins hit on the first ball of all ten frames. Second ball of each frameNumber misses last remaining pin. No bonus balls. Score for each frameNumber == 9 Total score == 10 frames x 9 == 90

5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5 Five pins on the first ball of all ten frames. Second ball of each frameNumber hits all five remaining pins, a spare. One bonus ball, hits five pins. Score for each frameNumber == 10 + score for next one ball == 10 + 5 == 15 Total score == 10 frames x 15 == 150

X|7/|9-|X|-8|8/|-6|X|X|X||81 Total score == 167