// --== CS400 File Header Information ==--
// Name: Willow Pae
// Email: wpae@wisc.edu
// Team: Red
// Group: Group: AA
// TA: Mu Cai
// Lecturer: Gary
// Notes to Grader: N/A
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @version 1.0
 *
 * This is the front end of the project.
 */
public class Frontend {

	// Attributes..
	private BackendInterface backend;
	private Scanner scanner;
	private String[] buildingNames;

	/**
	 * Constructor to initialize the
	 * whole variables.
	 */
	public Frontend() {

		// IMPORTANT!!!
		// REPLACE THAT LINE WITH ORIGINAL BACKEND CLASS.
		try {
			backend = new Backend();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

		// input stream
		scanner = new Scanner(System.in);
		// Getting all places..
		buildingNames = backend.getAllPlaces();

	}

	/**
	 * It will start the execution of the program.
	 *
	 * You can use that method to run the program.
	 */
	public void startProgram() {

		System.out.println("Welcome at the Building Guide");
		System.out.println();

		String startingPoint = chooseBuilding("Choose Your Starting Point");
		String endingPoint = "";
		boolean exitCommand = false;
		do {

			// First mode..
			System.out.println("Starting Point: "+startingPoint);
			System.out.println("****** Menu ******");
			System.out.print("D: Distance/Directions for Another Building\n"
					+ "E: Find Closest Dining Hall\n"
					+ "L: Find Closest Library Hall\n"
					+ "Choose your option between (D,E,L): ");
			char mode = ' ';
			do {

				mode = scanner.next().charAt(0);
				if(!(mode == 'D' || mode == 'E' || mode == 'L')) {
					System.out.print("Try Again! Please select between (D,E,L): ");
				}

			} while(!(mode == 'D' || mode == 'E' || mode == 'L'));
			// Selecting another building..
			switch(mode) {
			case 'D':
				endingPoint = chooseBuilding("Choose Another Building");
				break;
			case 'E':
				endingPoint = backend.findNearestDining(startingPoint);
				break;
			case 'L':
				endingPoint = backend.findNearestLibrary(startingPoint);
				break;
			}
			if(mode == 'D') {
				System.out.println("Directions Between "+startingPoint+" and "+endingPoint);
				for(String direction: backend.getDirections(startingPoint, endingPoint)) {
					System.out.println("\t=> "+direction);
				}
			} else {
				// Second Mode..
				System.out.println("Ending Point: "+endingPoint);
				System.out.println("****** Menu ******");
				System.out.print("W: Fastest Time Walking\n"
						+ "B: Fastest Time on Bike\n"
						+ "Choose your option between (W,B): ");
				mode = ' ';
				do {

					mode = scanner.next().charAt(0);
					if(!(mode == 'W' || mode == 'B')) {
						System.out.print("Try Again! Please select between (W,B): ");
					}

				} while(!(mode == 'W' || mode == 'B'));
				int time = backend.getTimeBetween(startingPoint, endingPoint, mode == 'B');
				System.out.println("");
				System.out.println("Total Travel Time: "+time);
			}
			System.out.println("");
			System.out.print("Enter 'E' to Exit or enter any letter to continue: ");
			mode = scanner.next().charAt(0);
			if(mode == 'E') {
				exitCommand = true;
			} else {
				startingPoint = endingPoint;
			}

		} while(!exitCommand);
		System.out.println("Thank you for using our system.");

	}

	/**
	 * It will ask the user for building name to choose.
	 *
	 * @param msg starting message
	 * @return chosen building
	 */
	private String chooseBuilding(String msg) {

		System.out.println("Followings are all buildings.");
		for(int i = 0; i < buildingNames.length; i++) {

			System.out.println((i+1)+": "+buildingNames[i]);

		}
		System.out.print(msg+" (1-"+buildingNames.length+") : ");
		int option = 0;
		do {

			try {
				option = scanner.nextInt();
				if(option < 1 || option > buildingNames.length) {
					System.out.print("Try Again! Please select between (1-"+buildingNames.length+") : ");
				}
			} catch(InputMismatchException ime) {
				System.out.print("Try Again! Only enter the integer value.");
				scanner.nextLine();
			}

		} while(option < 1 || option > buildingNames.length);
		return buildingNames[option - 1];

	}

	/**
	 * MAIN METHOD TO RUN THE FRONT END.
	 *
	 * @param args arguments.
	 */
	public static void main(String[] args) {

		Frontend frontend = new Frontend();
		frontend.startProgram();

	}

}
