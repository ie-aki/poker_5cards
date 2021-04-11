package poker_5cards;
import java.util.*;

//Bonus homework for AB01010 by 109AEN002

public class poker {
	static String[] deckDiamond = { "A♦", "2♦", "3♦", "4♦", "5♦", "6♦", "7♦", "8♦", "9♦", "10♦", "J♦", "Q♦", "K♦" };
	static String[] deckSpade = { "A♣", "2♣", "3♣", "4♣", "5♣", "6♣", "7♣", "8♣", "9♣", "10♣", "J♣", "Q♣", "K♣" };
	static String[] deckHeart = { "A♥", "2♥", "3♥", "4♥", "5♥", "6♥", "7♥", "8♥", "9♥", "10♥", "J♥", "Q♥", "K♥" };
	static String[] deckClub = { "A♠", "2♠", "3♠", "4♠", "5♠", "6♠", "7♠", "8♠", "9♠", "10♠", "J♠", "Q♠", "K♠" };

	public static Integer[] shuffleCardArray() {
		Integer[] cardArray = new Integer[52];

		for (int i = 0; i < 52; i++) {
			cardArray[i] = i;
		}

		List<Integer> cardList = Arrays.asList(cardArray);
		Collections.shuffle(cardList);

		cardList.toArray(cardArray);

		return cardArray;
	}

	public static int getNumber(int cardID) {
		int number = cardID % 13;
		return number;
	}

	public static int getSuit(int cardID) {
		int suit = cardID / 13;
		return suit;
	}

	public static boolean checkPair(int[] numberArray) {
		int[] count = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
//	    count duplicate card number by looping through finalNumberArray[]
		for (int i = 0; i < 5; i++) {
			count[numberArray[i]]++;
		}

		for (int i = 0; i < 13; i++) {
			if (count[i] == 2) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkThreeOfAKind(int[] numberArray) {
		int[] count = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
//	    count duplicate card number by looping through finalNumberArray[]
		for (int i = 0; i < 5; i++) {
			count[numberArray[i]]++;
		}

		for (int i = 0; i < 13; i++) {
			if (count[i] == 3) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkFourOfAKind(int[] numberArray) {
		int[] count = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
//	    count duplicate card number by looping through finalNumberArray[]
		for (int i = 0; i < 5; i++) {
			count[numberArray[i]]++;
		}

		for (int i = 0; i < 13; i++) {
			if (count[i] == 4) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkTwoPair(int[] numberArray) {
		int[] count = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
//	    count duplicate card number by looping through finalNumberArray[]
		for (int i = 0; i < 5; i++) {
			count[numberArray[i]]++;
		}
		
//		check if 2 number appeared 2 times
		int pairTimes = 0;
		for (int i = 0; i < 13; i++) {
			if (count[i] == 2) {
				pairTimes++;
			}
		}

		if (pairTimes == 2) {
			return true;
		}
		return false;
	}

	public static boolean checkFullHouse(int[] numberArray) {
		int[] count = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
//    count duplicate card number by looping through finalNumberArray[]
		for (int i = 0; i < 5; i++) {
			count[numberArray[i]]++;
		}
		
		int pairTimes = 0;
		int threeOfAKindTimes = 0;
		
		for (int i = 0; i < 13; i++) {
			switch (count[i]) {
			case 3:
				threeOfAKindTimes++;
				break;
			case 2:
				pairTimes++;
				break;
			}
		}
		
		if (pairTimes == 1 && threeOfAKindTimes == 1) {
			return true;
		}
		return false;
	}

	public static boolean checkFlush(int[] suitArray) {
		int temp = suitArray[0];
		for (int i = 0; i < 5; i++) {
			if (temp != suitArray[i]) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkStraight(int[] numberArray) {
//		Sort numbers in numberArray and check if number of card n is 1+n
		Arrays.sort(numberArray);
		int temp = numberArray[0];
		for (int i = 1; i < 5; i++) {
			if (numberArray[i] != temp + i) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkStraightFlush(int[] suitArray, int[] numberArray) {
		if (checkFlush(suitArray) && checkStraight(numberArray)) {
			return true;
		}
		return false;
	}

	public static void checkSpecialHands(int[] suitArray, int[] numberArray) {
		if (checkStraightFlush(suitArray, numberArray)) {
			System.out.println("Special Hand: Straight Flush");
		} else if (checkFullHouse(numberArray)) {
			System.out.println("Special Hand: Full House");
		} else if (checkFlush(suitArray)) {
			System.out.println("Special Hand: Flush");
		} else if (checkStraight(numberArray)) {
			System.out.println("Special Hand: Straight");
		} else if (checkFourOfAKind(numberArray)) {
			System.out.println("Special Hand: Four of a kind");
		} else if (checkThreeOfAKind(numberArray)) {
			System.out.println("Special Hand: Three of a kind");
		} else if (checkTwoPair(numberArray)) {
			System.out.println("Special Hand: Two Pair");
		} else if (checkPair(numberArray)) {
			System.out.println("Special Hand: Pair");
		} else {
			System.out.println("Special Hand: None");
		}
	}


	public static void main(String[] args) {
//		Cheating array for debugging
//		Integer[] newCardList = { 17, 32, 31, 27, 23 };

		Integer[] newCardList = shuffleCardArray();
		String[] finalCard = new String[5];
		int[] finalSuitArray = new int[5];
		int[] finalNumberArray = new int[5];

//		Get Card w/ cardID
		for (int i = 0; i < 5; i++) {
			finalSuitArray[i] = getSuit(newCardList[i]);
			finalNumberArray[i] = getNumber(newCardList[i]);

			switch (finalSuitArray[i]) {
			case 3:
				finalCard[i] = deckClub[finalNumberArray[i]];
				break;
			case 2:
				finalCard[i] = deckHeart[finalNumberArray[i]];
				break;
			case 1:
				finalCard[i] = deckSpade[finalNumberArray[i]];
				break;
			case 0:
				finalCard[i] = deckDiamond[finalNumberArray[i]];
				break;
			}
			System.out.println("Card " + (i + 1) + ": " + finalCard[i]);
//			Print card ID For debugging
//			System.out.println(newCardList[i]);
		}
		checkSpecialHands(finalSuitArray, finalNumberArray);
	}
}
