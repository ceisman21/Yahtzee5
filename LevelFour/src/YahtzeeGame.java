import java.util.*;

public class YahtzeeGame
{
	private static final int NUM_SIDES = 6;
	YahtzeeDie one,two,three,four,five;
	YahtzeeScorecard score;
	Scanner s;
	String tempStr;
	int x;
	int score1;
	boolean isDone;
	String tempStr2;
	int timesRan;





	/* initializes the dice and scoreboard */
	public YahtzeeGame()
	{
		one = new YahtzeeDie(NUM_SIDES);
		two = new YahtzeeDie(NUM_SIDES);
		three = new YahtzeeDie(NUM_SIDES);
		four = new YahtzeeDie(NUM_SIDES);
		five = new YahtzeeDie(NUM_SIDES);
		score = new YahtzeeScorecard();
		s = new Scanner(System.in);
		tempStr = "";
		x = 0;
		isDone = false;
		score1 = 0;
		timesRan = 1;
	}

	/* check to see if the game is over, and while the game is not over call the method takeTurn()
	once the game ends (hint: there are 13 turns in a game of Yahtzee), the method should print a
	final scorecard and return the grand total */
	public int playGame() {
		for (int x = 0; x < 13; x++) {
			takeTurn();
		}
		return score1;
	}
	private void takeTurn()
	{
		//reset
		isDone = false;
		x = 0;
		//1. call rollDice()
		rollDice();
		//2. call printDice()
		printDice();

		x = 0;
		while (isDone == false && x < 2) {
			//3. Ask the user if they are satisfied with their roll, or if they would like to roll again.
			System.out.println("Are you satisfied with your roll(Y), or do you want to roll again(N)");
			// If they are not satisfied continue, otherwise call markScore()
			//3. Ask the user if they are satisfied with their roll, or if they would like to roll again.
			// If they are not satisfied continue, otherwise call markScore()
			tempStr = "Bad";
				while (tempStr.equals("Bad")) {
					tempStr = s.nextLine();
				}


			if (tempStr.equals("Y") || tempStr.equals("y")) {
				isDone = true;
			} else if (tempStr.equals("N") || tempStr.equals("n")) {
				//4. call chooseFrozen()`
				chooseFrozen();
				//5. call rollDice()
				rollDice();
				//6. call printDice()
				printDice();
				x++;
			} else {
				System.out.println("Invalid input, Rolling again");
				//4. call chooseFrozen()`
				chooseFrozen();
				//5. call rollDice()
				rollDice();
				//6. call printDice()
				printDice();
				x++;
			}
		}
		//If they are not satisfied continue,otherwise call markScore()
		makeScore();
		markScore();
		//11. call markScore() ^
		timesRan++;
	}

	/* Rolls all unfrozen dice.  Also resets all dice to the unfrozen state after the roll */
	private void rollDice()
	{
		one.rollDie();
		two.rollDie();
		three.rollDie();
		four.rollDie();
		five.rollDie();
		//unfrozen
		one.unfreezeDie();
		two.unfreezeDie();
		three.unfreezeDie();
		four.unfreezeDie();
		five.unfreezeDie();

	}

	/* Asks the user which dice should be frozen 1-5 (should be read in in one line) */
	private void chooseFrozen()
	{
		System.out.println("Choose dice to freeze");
		tempStr = s.nextLine();
		StringTokenizer st = new StringTokenizer(tempStr);
		while (st.hasMoreTokens()) {
			tempStr2 = st.nextToken();
			if (tempStr2.equals("1")) {
				one.freezeDie();
			} else if (tempStr2.equals("2")) {
				two.freezeDie();
			} else if (tempStr2.equals("3")) {
				three.freezeDie();
			} else if (tempStr2.equals("4")) {
				four.freezeDie();
			} else if (tempStr2.equals("5")) {
				five.freezeDie();
			} else {
				System.out.println("Invalid input '" + tempStr2 + "'");
			}
		}
	}

	/* Should print the value of all five dice separated by a tab (\t) to the console */
	private void printDice()
	{
		System.out.println(one.getValue() + "\t" + two.getValue() + "\t" + three.getValue() + "\t" + four.getValue() + "\t" + five.getValue());
	}


	private void markScore()
	{
		//Make Values Relevant
		score.setValues(one.getValue(), two.getValue(), three.getValue(), four.getValue(), five.getValue());
		//1. Print a scoreboard
		score.printScoreCard();
		//2. Ask the user where they would like to mark their score.
		System.out.println("How would you like to mark your score?");
		System.out.println("1. Ones          7.  3 of Kind");
		System.out.println("2. Twos          8.  4 of Kind");
		System.out.println("3. Threes        9.  Full House");
		System.out.println("4. Fours         10. Sm Straight");
		System.out.println("5. Fives         11. Lg Straight");
		System.out.println("6. Sixes         12. Yahtzee");
		System.out.println("       13. Chance");
		System.out.println();
		int tempInt = s.nextInt();
		//3. Call appropriate function
		if (tempInt == 1) {
			score1 += score.ones;
		} else if (tempInt == 2) {
			score1 += score.twos;
		} else if (tempInt == 3) {
			score1 += score.threes;
		} else if (tempInt == 4) {
			score1 += score.fours;
		} else if (tempInt == 5) {
			score1 += score.fives;
		} else if (tempInt == 6) {
			score1 += score.sixes;
		} else if (tempInt == 7) {
			score1 += score.threeKind;
		} else if (tempInt == 8) {
			score1 += score.fourKind;
		} else if (tempInt == 9) {
			score1 += score.fullHouse;
		} else if (tempInt == 10) {
			score1 += score.smStraight;
		} else if (tempInt == 11) {
			score1 += score.lgStraight;
		} else if (tempInt == 12) {
			score1 += score.fiveKind;
		} else if (tempInt == 13) {
			score1 += score.chance;
		}
		//4. If false is returned the user entered an invalid number, so ask the user to try again
	}
	private void makeScore() {
		score.markOnes(one.getValue(), two.getValue(), three.getValue(), four.getValue(), five.getValue());
		score.markTwos(one.getValue(), two.getValue(), three.getValue(), four.getValue(), five.getValue());
		score.markThrees(one.getValue(), two.getValue(), three.getValue(), four.getValue(), five.getValue());
		score.markFours(one.getValue(), two.getValue(), three.getValue(), four.getValue(), five.getValue());
		score.markFives(one.getValue(), two.getValue(), three.getValue(), four.getValue(), five.getValue());
		score.markSixes(one.getValue(), two.getValue(), three.getValue(), four.getValue(), five.getValue());
	}
}