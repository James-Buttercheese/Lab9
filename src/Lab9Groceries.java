
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lab9Groceries {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scnr = new Scanner(System.in);

		// menu print loop (product regex to format price)
		// product as Array
		// price as Array

//			Tree map with prices as keys?  Sorted by vaule, makes highest and lowest easy
		String shopLoop;
		String userPurchase;
		Map<String, Double> menu = new HashMap<>();
		int counter = 0;
		double avg = 0;
		int most = 0;
		int least = 0;
		int userNum = 0;

		menu.put("Apple", .99);
		menu.put("Banana", .59);
		menu.put("Cauliflower", 1.59);
		menu.put("Dragonfruit", 2.19);
		menu.put("Elderberry", 1.79);
		menu.put("Fig", 2.09);
		menu.put("Grapefruit", 1.99);
		menu.put("Honeydew", 3.49);

		List<String> boughtItems = new ArrayList<>();
		List<Double> prices = new ArrayList<>();

		System.out.println("Hello, welcome to James' Market");
		System.out.println();
		printMenu(menu);
		System.out.println();
		System.out.println("To see this menu again, type \"menu\" at anu time.");
		System.out.println();
		System.out.println("Would you like to buy anything? (Yes/No)");

		shopLoop = scnr.nextLine();

		if (shopLoop.equalsIgnoreCase("menu")) {
			shopLoop = "yes";
			printMenu(menu);
		}

		while (shopLoop.equalsIgnoreCase("yes")) {

			System.out.println("What would you like to buy?");
			userPurchase = scnr.nextLine();
			if (doWeCarry(menu, userPurchase)) {

				boughtItems.add(whichItem(menu, userPurchase));
				prices.add(howMuch(menu, userPurchase));
				counter++;
				System.out.printf("%-14s   %10.2f\n", (whichItem(menu, userPurchase)), (howMuch(menu, userPurchase)));

			} else if (userPurchase.equalsIgnoreCase("menu")) {
				printMenu(menu);
			} else if (numbers(userPurchase)) {
				userNum = (userPurchase.charAt(0) - 48);
				boughtItems.add(whichItemNum(menu, userNum));
				prices.add(howMuchNum(menu, userNum));
				counter++;
				System.out.printf("%-14s   %10.2f\n", (whichItemNum(menu, userNum)), (howMuchNum(menu, userNum)));
			} else {
				System.out.println("We dont carry that.");
			}

			System.out.println("Would you like to buy anything else? (Yes/No)");
			shopLoop = scnr.nextLine();
			if (shopLoop.equalsIgnoreCase("menu")) {
				shopLoop = "yes";
				printMenu(menu);
			}
		}
		for (int i = 0; i < counter; i++) {
			System.out.printf("%-14s   %10.2f\n", boughtItems.get(i), prices.get(i));
			avg += prices.get(i);
		}
		averagePrice(avg, counter);
		least = lowestCost(prices, counter);
		most = highestCost(prices, counter);
		System.out.println("The cheapest item is " + boughtItems.get(least) + ", and it costs $" + prices.get(least));
		System.out
				.println("The most expensive item is " + boughtItems.get(most) + ", and it costs $" + prices.get(most));
		System.out.println("Goodbye");

	}

	public static void printMenu(Map menu) {

		Set<String> menuKeys = menu.keySet();

		System.out.printf("%-5s %-10s %16s\n", "num", "Item", "Price");
		System.out.printf("=====================================\n");
		int i = 1;
		for (String grocery : menuKeys) {
			System.out.printf("%-5s %-14s   %10.2f\n", i, grocery, menu.get(grocery));
			i++;

		}
	}

	public static boolean doWeCarry(Map menu, String shopList) {

		Set<String> menuKeys = menu.keySet();

		for (String grocery : menuKeys) {

			if (shopList.equalsIgnoreCase(grocery)) {
				return true;
			}

		}
		return false;
	}

	public static String whichItem(Map menu, String shopList) {

		Set<String> menuKeys = menu.keySet();

		for (String grocery : menuKeys) {

			if (shopList.equalsIgnoreCase(grocery)) {
				return grocery;
			}

		}
		return "";
	}

	public static Double howMuch(Map menu, String shopList) {

		Set<String> menuKeys = menu.keySet();

		for (String grocery : menuKeys) {

			if (shopList.equalsIgnoreCase(grocery)) {
				return (Double) menu.get(grocery);
			}

		}
		return .00;
	}

	public static String whichItemNum(Map menu, int shopNum) {

		Set<String> menuKeys = menu.keySet();
		int counterNum = 1;

		for (String grocery : menuKeys) {

			if (counterNum == shopNum) {
				return grocery;
			}
			counterNum++;
		}
		return "";
	}

	public static Double howMuchNum(Map menu, int shopNum) {

		Set<String> menuKeys = menu.keySet();
		int counterNum = 1;

		for (String grocery : menuKeys) {

			if (counterNum == shopNum) {
				return (Double) menu.get(grocery);
			}
			counterNum++;
		}
		return .00;
	}

	public static void averagePrice(Double avg, int counter) {

		avg = (avg / counter);

		System.out.printf("%-14s   %10.2f\n", "Average price", (avg));
	}

	public static int highestCost(List prices, int counter) {
		int highestIndex = 0;
		List<Double> sortedPrices = new ArrayList(prices);
		Collections.sort(sortedPrices);

		int length = sortedPrices.size();

		return (prices.indexOf(sortedPrices.get(length - 1)));

	}

	public static int lowestCost(List prices, int counter) {
		int lowestIndex = 0;
		List<Double> sortedPrices = new ArrayList(prices);
		Collections.sort(sortedPrices);

		return (prices.indexOf(sortedPrices.get(0)));

	}

	public static boolean numbers(String userResponse) {

		Pattern p = Pattern.compile("[1-8]");
		Matcher m = p.matcher(userResponse);
		boolean b = m.matches();

		return b;
	}
}
