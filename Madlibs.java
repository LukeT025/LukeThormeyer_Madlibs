/*
 * This program will generate a randomly generated story from the user 
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;

public class Madlibs {
	private static Random rnd = new Random();
	private static ArrayList<String> singNouns, singVerbs, pluNouns, pluVerb, pastVerb, adj, adv;
	
	/**
	 * This makes a welcome banner when the user loads up the program
	 * @param 
	 * @return
	 */
	public static void printWelcomeBanner(){
		System.out.println("*".repeat(65));
		System.out.println("*"+" ".repeat(23) + "Welcome to Madlib"+" ".repeat(23) + "*");
		System.out.println("*".repeat(65));
		System.out.println();
		System.out.println("This program generates random stories using wordlists you supply.");
		System.out.println();
	}
	/**
	 * This new string of an array list of the nouns, verbs, ajds, and adv,
	 * @param 
	 * @return
	 */
	public static void createWordList() {
		singNouns = new ArrayList<String>();
		singVerbs = new ArrayList<String>();
		pluNouns = new ArrayList<String>();
		pluVerb = new ArrayList<String>();
		pastVerb = new ArrayList<String>();
		adj = new ArrayList<String>();
		adv = new ArrayList<String>();
		
	}
	/**
	 * This does
	 * a) will scan through the folder for each noun, verb, adj, and adv .txt
	 * b)while its goes through each line it will add the words in each line until there isn't anything there
	 * c) and of course have to close the scanner and return the whole thing true
	 * d) if it can't find the folder or items in the folder or in the .txt then will return false
	 * @param String folder
	 * @return
	 */
	public static boolean loadWordList (String folder) {
		createWordList();
		try {
			Scanner fsc = new Scanner(new File(folder, "singnoun.txt"));
			while (fsc.hasNextLine()) {
				singNouns.add(fsc.nextLine());
			}
			fsc.close();
			Scanner fsc2 = new Scanner(new File(folder, "singverb.txt"));
			while (fsc2.hasNextLine()) {
				singVerbs.add(fsc2.nextLine());
			}	
			fsc2.close();
			Scanner fsc3 = new Scanner(new File(folder, "plunoun.txt"));
			while (fsc3.hasNextLine()) {
				pluNouns.add(fsc3.nextLine());
			}	
			fsc3.close();
			Scanner fsc4 = new Scanner(new File(folder, "pluverb.txt"));
			while (fsc4.hasNextLine()) {
				pluVerb.add(fsc4.nextLine());
			}
			fsc4.close();
			Scanner fsc5 = new Scanner(new File(folder, "pastverb.txt"));
			while (fsc5.hasNextLine()) {
				pastVerb.add(fsc5.nextLine());
			}
			fsc5.close();
			Scanner fsc6 = new Scanner(new File(folder, "adj.txt"));
			while (fsc6.hasNextLine()) {
				adj.add(fsc6.nextLine());
			}
			fsc6.close();
			Scanner fsc7 = new Scanner(new File(folder, "adv.txt"));
			while (fsc7.hasNextLine()) {
				adv.add(fsc7.nextLine());
			}
			fsc7.close();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	/**
	 * This does 
	 * a) will look at in the folder and scan for the story number that the user put in
	 * b) once it does that will scan every line and add that to the array list and then close the scanner
	 * c) if it can't find that story or anything in the story it will return null
	 * @param String folder, int storyNum
	 * @return
	 */
	public static ArrayList<String> readStory(String folder, int storyNum) {
		ArrayList<String> result = new ArrayList<String>();
		try{
			Scanner fsc = new Scanner(new File(folder, "story" + storyNum + ".txt"));
			while(fsc.hasNextLine()) {
				result.add(fsc.nextLine());
			}
			fsc.close();
			return result;
		} catch (Exception ex) {
			return null;
		}
	}
	/**
	 * This does does it will look at each line of what the user use story is 
	 * a) has a string "story" to keep the new story in
	 * b)for each line of the user story they will replace all the <> and put a new random word in that that relates to what it is 
	 * c)then it will add that random word it gets into the story
	 * d)and finally print out the the story 
	 * @param ArrayList<String> userStory
	 * @return
	 */
	public static String TellStory (ArrayList<String> userStory) {
		String story = " ";
		for (String line : userStory ) {
			line = line.replaceAll("<singnoun>", singNouns.get(rnd.nextInt(singNouns.size())));
			line = line.replaceAll("<singverb>", singVerbs.get(rnd.nextInt(singVerbs.size())));
			line = line.replaceAll("<plunoun>", pluNouns.get(rnd.nextInt(pluNouns.size())));
			line = line.replaceAll("<pluverb>", pluVerb.get(rnd.nextInt(pluVerb.size())));
			line = line.replaceAll("<pastverb>", pastVerb.get(rnd.nextInt(pastVerb.size())));
			line = line.replaceAll("<adj>", adj.get(rnd.nextInt(adj.size())));
			line = line.replaceAll("<adv>", adv.get(rnd.nextInt(adv.size())));
			story += line + "\n";
		}	
		System.out.println(story);
		return story;
	}
	/**
	 * This will
	 * a) ask the user for the folder that has the files in them
	 * b) if it can't find folder then will yell at them until they get the directory right and will continuous if it find the directory folder is found
	 * c) then i will ask the user to put in the story and if cant find that will yell at them to put in one that does exist and if they did pout in a correct on then it will print out the story
	 * d) will quit once pressed q if not will ask the user over and over to put in stories until they do.
	 * @param 
	 * @return
	 */
	public static void main(String[] args) {
		printWelcomeBanner();
		Scanner sc = new Scanner(System.in);
		String folder;
		boolean words;
		do {
			System.out.println("Enter the name of the folder where the stories and wordlists are or just press Enter to accept the default location: ");
			folder = sc.nextLine();
			words = loadWordList(folder);
			if (words == false){
				System.out.println("Unable to read the folder. Make sure its the directory as well.");
			}
		} while (words == false);
		
		String userChoice;
		do {
			System.out.println("Enter a story number or q to quit: ");
			userChoice = sc.nextLine();
			if (!userChoice.equals("q")) {
				int storyNum = Integer.parseInt(userChoice);
				ArrayList<String> userStory = readStory(folder, storyNum);
					if (userStory != null) {
						TellStory(userStory);
					} else {
						System.out.println("That story does not exist. Choose again.");
					}
			} else {
				System.out.println("Thank you for using this program.");
			}	
		} while (!userChoice.equals("q"));
	}
}
