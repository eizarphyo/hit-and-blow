package games;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class HitAndBlow {
	private int hits = 0;
	private int blows = 0;
	private int chances = 3;

	private Scanner sc = new Scanner(System.in);

	void start() {
		Vector<String> rdNums = generateRandomNum();

		while (chances > 0 && hits != 4) {
			String gNums = getValidatedInput();
			checkHitOrBlow(rdNums, gNums);

		}

		if (hits == 4 && chances >= 0) {
			System.out.println("Yaay!! You win!");
		} else {
			System.out.println("Uh-oh.. You lose..\nHere is the 4 numbers: " + rdNums.toString());
		}

		restart();
	}

	private Vector<String> generateRandomNum() {
		Random rd = new Random();

		Vector<String> v = new Vector<String>();

		for (int i = 0; v.size() < 4; i++) {
			String num = rd.nextInt(9) + 1 + "";
			for (int j = 0; j < i; j++) {
				while (v.contains(num)) {
					num = rd.nextInt(9) + 1 + "";
				}
			}
			v.add(num);
		}

		System.out.println(v);
		return v;
	}

	private String getValidatedInput() {

		boolean ok = false;
		String str = "";

		System.out.print("Guess a number: ");
		while (!ok) {
			str = sc.next();

			if (str.length() != 4) {
				System.out.print("Invalid input.\n\nPlease enter 4 numbers: ");
				ok = false;
			} else if (str.contains("0") || str.contains(" ") || str.contains(",")) {
				System.out.print("Invalid input.\n\nPlease re-guess without 0, space and commas (,): ");
				ok = false;
			} else if (hasDup(str)) {
				System.out.print("The numbers do not include duplicates.\n\nPlease re-guess: ");
				ok = false;
			} else {
				ok = true;
			}
		}
		return str;
	}

	private boolean hasDup(String str) {
		for (int i = 0; i < str.length(); i++) {
			for (int j = i + 1; j < str.length(); j++) {
				if (str.charAt(i) == str.charAt(j)) {
					return true;
				}
			}
		}
		return false;
	}

	private void checkHitOrBlow(Vector<String> v, String str) {
		hits = 0;
		blows = 0;
		for (int i = 0; i < v.size(); i++) {

			String ch = str.charAt(i) + "";
			int index = v.indexOf(ch);

			if (v.contains(ch) && index == i) {
				hits++;
			} else if (v.contains(ch)) {
				blows++;
			}
		}
		chances--;
		System.out.println(hits + " hit " + blows + " blow\nYou have " + chances + " chances left.\n");
	}

	private void restart() {
		System.out.print("\nRestart?\nEnter y to restart. Enter anything to end: ");
		char isRestart = sc.next().charAt(0);

		if (isRestart == 'y') {
			chances = 3;
			start();
		}
	}
}
