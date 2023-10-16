
public class Madlibs {
	public static void printWelcomeBanner(){
		System.out.println("*".repeat(65));
		System.out.println("*"+" ".repeat(23) + "Welcome to Madlib"+" ".repeat(23) + "*");
		System.out.println("*".repeat(65));
		System.out.println();
		System.out.println("This program generates random stories using wordlists you supply.");
	}
	
	public static void main(String[] args) {
		printWelcomeBanner();

	}
}
