import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    private static ArrayList<Dog> dogList = new ArrayList<Dog>();
    private static ArrayList<Monkey> monkeyList = new ArrayList<Monkey>();
    // Instance variables (if needed)

    public static void main(String[] args) {

    	Scanner scnr =  new Scanner(System.in);
    	char userChoice = ' ';
        initializeDogList();
        initializeMonkeyList();

        // Add a loop that displays the menu, accepts the users input
        // and takes the appropriate action.
        while (userChoice != 'q') {
        	displayMenu();
        	userChoice = scnr.next().charAt(0);
        	// input validation loop
        	while (!(userChoice == 'q' || (userChoice > 48 && userChoice <= 54))) {
        		System.out.println("That is not a valid menu choice.");
        		System.out.println();
        		System.out.println("Enter a menu selection");
        		userChoice = scnr.next().charAt(0);
     
        	}
        	switch (userChoice) {
        		case '1':
        			intakeNewDog(scnr);
        			break;
        		case '2':
        			intakeNewMonkey(scnr);
        			break;
        		case '3':
        			reserveAnimal(scnr);
        			break;
        		case '4' :
        			printAnimals("dog");
        			break;
        		case '5' :
        			printAnimals("monkey");
        			break;
        		case '6' :
        			printAnimals("available");
        			break;
        		default :
        			break;
        	}
        }
	// For the project submission you must also include input validation
        // and appropriate feedback to the user.
        // Hint: create a Scanner and pass it to the necessary
        // methods 
	// Hint: Menu options 4, 5, and 6 should all connect to the printAnimals() method.

    }

    // This method prints the menu options
    public static void displayMenu() {
        System.out.println("\n\n");
        System.out.println("\t\t\t\tRescue Animal System Menu");
        System.out.println("[1] Intake a new dog");
        System.out.println("[2] Intake a new monkey");
        System.out.println("[3] Reserve an animal");
        System.out.println("[4] Print a list of all dogs");
        System.out.println("[5] Print a list of all monkeys");
        System.out.println("[6] Print a list of all animals that are not reserved");
        System.out.println("[q] Quit application");
        System.out.println();
        System.out.println("Enter a menu selection");
    }


    // Adds dogs to a list for testing
    public static void initializeDogList() {
        Dog dog1 = new Dog("Spot", "German Shepherd", "male", "1", "25.6", "05-12-2019", "United States", "intake", false, "United States");
        Dog dog2 = new Dog("Rex", "Great Dane", "male", "3", "35.2", "02-03-2020", "United States", "Phase I", false, "United States");
        Dog dog3 = new Dog("Bella", "Chihuahua", "female", "4", "25.6", "12-12-2019", "Canada", "in service", true, "Canada");

        dogList.add(dog1);
        dogList.add(dog2);
        dogList.add(dog3);
    }


    // Adds monkeys to a list for testing
    //Optional for testing
    public static void initializeMonkeyList() {
    	Monkey monkey1 = new Monkey();
    	Monkey monkey2 = new Monkey("Koko", "female", "46", "175", "07-31-2022", "United States", "in service", false, "United States", "0", "59cm", "59cm", "Gorilla");
    	
    	monkeyList.add(monkey1);
    	monkeyList.add(monkey2);
    }


    // Complete the intakeNewDog method
    // The input validation to check that the dog is not already in the list
    // is done for you
    public static void intakeNewDog(Scanner scanner) {
    	// Removes any extra data left in the scanner
    	scanner.nextLine();
    	// Prompt the user for input for all dog parameters, storing them in variables
        System.out.println("What is the dog's name?");
        String name = scanner.nextLine();
        for(Dog dog: dogList) {
            if(dog.getName().equalsIgnoreCase(name)) {
                System.out.println("\n\nThis dog is already in our system\n\n");
                return; //returns to menu
            }
        }
        System.out.println("What is the dog's breed?");
        String breed = scanner.nextLine();
        System.out.println("What is the dog's gender?");
        String gender = scanner.nextLine();
        System.out.println("What is the dog's age?");
        String age = scanner.nextLine();
        System.out.println("What is the dog's weight?");
        String weight = scanner.nextLine();
        System.out.println("When was the dog acquired?");
        String acquisitionDate = scanner.nextLine();
        System.out.println("In what country was the dog acquired?");
        String acquisitionCountry = scanner.nextLine();
        System.out.println("What is the dog's training status?");
        String trainingStatus = scanner.nextLine();
        // A string variable to get input on whether the dog is reserved and a boolean to set the reserved status
        String reservedInput = " ";
        boolean reserved;
        // A loop to validate if the input for reserved was yes or no
        while(!(reservedInput.equals("yes") || reservedInput.equals("no"))) {
        	 System.out.println("Is the dog reserved?");
        	 reservedInput = scanner.nextLine();
        	 // If the input is invalid, the user is prompted with appropriate choices
        	 if(!(reservedInput.equals("yes") || reservedInput.equals("no"))) {
        		 System.out.println("Please enter \"yes\" or \"no\"");
        	 }
        }
        if(reservedInput.equals("no"))
        {
        	reserved = false;
        }
        else{
        	reserved = true;
        }
        System.out.println("What country is the dog is service in?");
        String serviceCountry = scanner.nextLine();
        // Add the code to instantiate a new dog and add it to the appropriate list
        // instantiate the new dog using the user input variables
        Dog intakeDog = new Dog(name, breed, gender, age, weight, acquisitionDate, acquisitionCountry, trainingStatus, reserved, serviceCountry);
        dogList.add(intakeDog);
        System.out.println(name + " has been added to the list.");
    }


        // Complete intakeNewMonkey
	//Instantiate and add the new monkey to the appropriate list
        // For the project submission you must also  validate the input
	// to make sure the monkey doesn't already exist and the species type is allowed
        public static void intakeNewMonkey(Scanner scanner) {
        	// Removes any whitespace left in the scanner
        	scanner.nextLine();
        	// List of monkey species eligible for training
           String monkeySpecies[] = {"Capuchin", "Guenon", "Macaque", "Marmoset", "Squirrel Monkey", "Tamarin"};
           // boolean to check if the species is eligible for training
           boolean validSpecies = false;
           System.out.println("What is the monkey's name?");
           String name = scanner.nextLine();
           // Check if there is already a monkey by that name
           for (Monkey monkey: monkeyList) {
        	   if(monkey.getName().equalsIgnoreCase(name)) {
        		   System.out.println("\n\nThis monkey is already in our system\n\n");
        		   return;
        	   }
           }
           System.out.println("What is the monkey's species?");
           String species = scanner.nextLine();
           // Loop to validate that the species is eligible for training
           for (int i = 0; i < monkeySpecies.length; ++i) {
        	   if (species.equals(monkeySpecies[i])) {
        		   validSpecies = true;
        		   break;
        	   }
           }
           // Return to the menu if the species is not eligible for training
           if (!validSpecies) {
        	   System.out.println("\n\nThis species is not eligible for training\n\n");
        	   return;
           }
           // instantiate the new monkey 
           Monkey intakeMonkey = new Monkey();
           intakeMonkey.setName(name);
           intakeMonkey.setSpecies(species);
           System.out.println("What is the monkey's age?");
           String age = scanner.nextLine();
           intakeMonkey.setAge(age);
           System.out.println("What is the monkey's gender?");
           String gender = scanner.nextLine();
           intakeMonkey.setGender(gender);
           System.out.println("What is the monkey's acquisition date?");
           String acquisitionDate = scanner.nextLine();
           intakeMonkey.setAcquisitionDate(acquisitionDate);
           System.out.println("What country was the monkey acquired in?");
           String acquistionCountry = scanner.nextLine();
           intakeMonkey.setAcquisitionLocation(acquistionCountry);
           System.out.println("What is the monkey's training status?");
           String trainingStatus = scanner.nextLine();
           intakeMonkey.setTrainingStatus(trainingStatus);
           System.out.println("Is the monkey reserved?");
           String reserved = scanner.nextLine();
           // Input validation for reserved status
           while (!(reserved.equals("yes") || reserved.equals("no"))) {
        	   System.out.println("Please enter either \"yes\" or \"no\". Is the monkey reserved?");
        	   reserved = scanner.nextLine();
           }
           if (reserved.equals("yes")){
        	   intakeMonkey.setReserved(true);
           }
           else {
        	   intakeMonkey.setReserved(false);
           }
           System.out.println("What country is the monkey in service in?");
           String serviceCountry = scanner.nextLine();
           intakeMonkey.setInServiceCountry(serviceCountry);
           System.out.println("How long is the monkey's tail?");
           String tailLength = scanner.nextLine();
           intakeMonkey.setTailLength(tailLength);
           System.out.println("What is the monkey's height?");
           String height = scanner.nextLine();
           intakeMonkey.setHeight(height);
           System.out.println("What is the monkey's body length?");
           String bodyLength = scanner.nextLine();
           intakeMonkey.setBodyLength(bodyLength);
           
           // Add the intake monkey to the list of monkeys
           monkeyList.add(intakeMonkey);
           System.out.println(name + " has been added to the list.");
           
        }

        // Complete reserveAnimal
        // You will need to find the animal by animal type and in service country
        public static void reserveAnimal(Scanner scanner) {
        	// Get rid of any extra input in the scanner
        	scanner.nextLine();
        	String animalType = " ";
        	boolean matchingAnimal = false;
        	// input validation loop
        	while (!(animalType.equals("monkey") || animalType.equals("dog"))) {
        		System.out.println("Please enter the desired animal type: ");
        		animalType = scanner.nextLine();
        		// If the entered type is not a dog or monkey, prompt the user for the correct type
        		if(!(animalType.equals("monkey") || animalType.equals("dog"))) {
        			System.out.println("The animal type must be either \"dog\" or \"monkey\".");
        		}
        	}
        	System.out.println("Please enter the desired service country: ");
            String country = scanner.nextLine();
            // Search the dog list for available dog
            if (animalType.equals("dog")) {
            	for(Dog dog : dogList) {
            		// Check if the dog is available and in the selected country
            		if (!dog.getReserved() && dog.getInServiceLocation().equals(country)) {
            			// set matchingAnimal and the dogs reserved status to true
            			matchingAnimal = true;
            			dog.setReserved(true);
            			// Inform the user that the dog has been found and reserved
            			System.out.println(dog.getName() + " in " + country + " has been updated to reserved.");
            			// end the for loop once a matching animal has been found
            			break;
            		}
            	}
            }
            // Search the monkey list for available monkey
            else if (animalType.equals("monkey")) {
            	for(Monkey monkey : monkeyList) {
            		// Check if the monkey is available and in the selected country
            		if (!monkey.getReserved() && monkey.getInServiceLocation().equals(country)) {
            			// set matchingAnimal and the monkey's reserved status to true
            			matchingAnimal = true;
            			monkey.setReserved(true);
            			// Inform the user that the dog has been found and reserved
            			System.out.println(monkey.getName() + " in " + country + " has been updated to reserved.");
            			// end the for loop once a matching animal has been found
            			break;
            		}
            	}
            	
            }
            // Error message prints if the animal type is neither dog or monkey. Input validation loop should ensure this doesn't happen.
            else {
            	System.out.println("Something went wrong");
            }
            // Inform the user if no matching animal could be found
            if (!matchingAnimal) {
            	System.out.println("There is no available " + animalType + " in " + country + ".");
            }

        }

        // Complete printAnimals
        // Include the animal name, status, acquisition country and if the animal is reserved.
	// Remember that this method connects to three different menu items.
        // The printAnimals() method has three different outputs
        // based on the listType parameter
        // dog - prints the list of dogs
        // monkey - prints the list of monkeys
        // available - prints a combined list of all animals that are
        // fully trained ("in service") but not reserved 
	// Remember that you only have to fully implement ONE of these lists. 
	// The other lists can have a print statement saying "This option needs to be implemented".
	// To score "exemplary" you must correctly implement the "available" list.
        public static void printAnimals(String menuChoice) {
        	// The parameter menuChoice is the menu option the user selected. 
        	// Prints the header for the table
        	System.out.println("Name           Status         Acq County     Reserved");
        	System.out.println("_____________________________________________________");
        	// Prints the list of dogs
            if (menuChoice.equals("dog")) {
            	// Iterate through the list of dogs
            	for(Dog dog : dogList) {
            		System.out.printf("%-15s", dog.getName());
            		System.out.printf("%-15s", dog.getTrainingStatus());
            		System.out.printf("%-15s", dog.getAcquisitionLocation());
            		// Determine if the dog is reserved or not
            		if(dog.getReserved()) {
            			System.out.println("Reserved");
            		}
            		else {
            			System.out.println("Available");
            		}
            	}
            }
            // Prints the list of monkeys
            else if (menuChoice.equals("monkey")) {
            	// Iterate through the list of monkeys
            	for(Monkey monkey : monkeyList) {
            		System.out.printf("%-15s", monkey.getName());
            		System.out.printf("%-15s", monkey.getTrainingStatus());
            		System.out.printf("%-15s", monkey.getAcquisitionLocation());
            		// Determine if the monkey is reserved or not
            		if(monkey.getReserved()) {
            			System.out.println("Reserved");
            		}
            		else {
            			System.out.println("Available");
            		}
            	}
            	
            }
            // Prints the list of all available animals
            else if (menuChoice.equals("available")) {
            	// iterate through the dog list
            	for(Dog dog : dogList) {
            		// If the dog is available and in service, print the info
            		if(dog.getTrainingStatus().equals("in service") && !dog.getReserved()) {
            			System.out.printf("%-15s", dog.getName());
                		System.out.printf("%-15s", dog.getTrainingStatus());
                		System.out.printf("%-15s", dog.getAcquisitionLocation());
                		System.out.println("Available");
            		}
            	}
            	// iterate through the monkey list
            	for(Monkey monkey : monkeyList) {
            		// If the monkey is available and in service, print the info
            		if(monkey.getTrainingStatus().equals("in service") && !monkey.getReserved()) {
            			System.out.printf("%-15s", monkey.getName());
                		System.out.printf("%-15s", monkey.getTrainingStatus());
                		System.out.printf("%-15s", monkey.getAcquisitionLocation());
                		System.out.println("Available");
            		}
            	}
            	
            }
            else {
            	System.out.println("Something went wrong.");
            }

        }
}

