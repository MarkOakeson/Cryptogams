import java.util.Scanner;

public class CryptogramTextView {
	public void playGame() {
		Scanner scanner = new Scanner(System.in);
		CryptogramController control = new CryptogramController(new CryptogramModel());
		while (!control.isGameOver() ) {
			System.out.println(control.getUsersProgress());
			System.out.println("Enter a command (type help to see commands):");
			String[] line = scanner.nextLine().split(" ");
			if(line[0].toUpperCase().equals("REPLACE")) {
				control.makeReplacement(line[1], line[3]);
			}
			else if(line.length == 3) {
				control.makeReplacement(line[0],line[2]);
			}
			else if(line[0].toUpperCase().equals("FREQ") ) {
				System.out.println(control.getFreq());
			}
			else if(line[0].toUpperCase().equals("HINT")) {
				control.giveHint();
			}
			else if(line[0].toUpperCase().equals("EXIT")) {
				break;
			}
			else if(line[0].toUpperCase().equals("HELP") ) {
				control.giveHelp();
			}
			else {
				System.out.print("Invalid Command! Try again");
			}

		}
		if(control.isGameOver()) {
			System.out.println("You got it!");
		}
		else {
			System.out.println("The game has ended early.");
			System.exit(0);
		}
	}
//	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//		CryptogramController control = new CryptogramController(new CryptogramModel());
//		while (!control.isGameOver() ) {
//			System.out.println(control.getUsersProgress());
//			System.out.println("Enter a command (type help to see commands):");
//			String[] line = scanner.nextLine().split(" ");
//			if(line[0].toUpperCase().equals("REPLACE")) {
//				control.makeReplacement(line[1], line[3]);
//			}
//			else if(line.length == 3) {
//				control.makeReplacement(line[0],line[2]);
//			}
//			else if(line[0].toUpperCase().equals("FREQ") ) {
//				control.getFreq();
//			}
//			else if(line[0].toUpperCase().equals("HINT")) {
//				control.giveHint();
//			}
//			else if(line[0].toUpperCase().equals("EXIT")) {
//				break;
//			}
//			else if(line[0].toUpperCase().equals("HELP") ) {
//				control.giveHelp();
//			}
//			else {
//				System.out.print("Invalid Command! Try again");
//			}
//
//		}
//		if(control.isGameOver()) {
//			System.out.println("You got it!");
//		}
//		else {
//			System.out.println("The game has ended early.");
//			System.exit(0);
//		}
//	}
}
