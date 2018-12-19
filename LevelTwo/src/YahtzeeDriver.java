import java.util.Scanner;

public class YahtzeeDriver
{
	/*
	1. Creates a new instance of the YahtzeeGame class
	2. Calls the playGame method on the Yahtzee object
	3. Asks user if they would like to play again
	4. When the user is done playing, prints the number of games played, min, max, and average score
	*/
	public static void main(String [] args)
	{
			//decs
			int score; //score of the game
			Scanner s = new Scanner(System.in);
			char play; //Checks if user wants to keep going
			Boolean keepPlaying = true; //bool for while
			int minScore = -999;
			int maxScore = -999;
			int numRuns = 0;
			int sumTotal = 0;


			while (keepPlaying == true) { //while loop

				//initialize and play game
				YahtzeeGame myGame = new YahtzeeGame();
				System.out.println("Welcome to Cooper's APCSA Yahtzee Game!");
				score = myGame.playGame();

				//set value
				if (minScore > score || minScore == -999) {
					minScore = score;
				}
				if (maxScore < score || maxScore == -999) {
					maxScore = score;
				}
				numRuns++;
				sumTotal = sumTotal + 1;


				//Play Again Feature
				System.out.println("Your Score: " + score);
				System.out.println("Play Again? Y/N");
				play = s.nextLine().charAt(0);
				if (play == 'Y' || play == 'y') {
					System.out.println("Playing again...");
					System.out.println(" ");
					System.out.println(" ");
					System.out.println(" ");

				} else if (play == 'N' || play == 'n') {
					System.out.println("Thanks for Playing!");
					keepPlaying = false;
				} else {
					System.out.println("Invalid input. Stopping...");
					keepPlaying = false;
				}
			}
			System.out.println("Low Score: " + minScore);
			System.out.println("High Score: " + maxScore);
			System.out.println("Average Score: " + sumTotal*1.0/numRuns*1.0);


	}
}
