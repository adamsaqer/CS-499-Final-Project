import java.util.*;
import java.io.*;

/**
 * Driver class for the Rescue Animal Management System.
 * Provides a console menu interface for managing rescue dogs and monkeys.
 * Includes data persistence to a file and uses collections for efficient data management.
 */
public class Driver {
    // Lists to store Dog and Monkey objects
    private static List<Dog> dogList = new ArrayList<>();
    private static List<Monkey> monkeyList = new ArrayList<>();
    // Set to track all animal names (to ensure uniqueness)
    private static Set<String> animalNames = new HashSet<>();
    // Allowed monkey species for validation (per specification)
    private static final List<String> ALLOWED_MONKEY_SPECIES = Arrays.asList(
            "Capuchin", "Guenon", "Macaque", "Marmoset", "Squirrel monkey", "Tamarin"
    );
    // Data file for persistence
    private static final String DATA_FILE = "animals.txt";

    public static void main(String[] args) {
        // Load existing data from file (if present), or initialize with sample data
        loadData();

        // Scanner for user input in the menu
        Scanner scanner = new Scanner(System.in);
        boolean exitProgram = false;
        // Menu loop
        while (!exitProgram) {
            displayMenu();
            String choice = scanner.nextLine().trim();
            if (choice.isEmpty()) {
                continue;  // if no input, loop again
            }
            char selection = Character.toLowerCase(choice.charAt(0));  // take first char, case-insensitive for letters
            switch (selection) {
                case '1': // Intake a new dog
                    intakeNewDog(scanner);
                    break;
                case '2': // Intake a new monkey
                    intakeNewMonkey(scanner);
                    break;
                case '3': // Reserve an animal for service
                    reserveAnimal(scanner);
                    break;
                case '4': // Print all dogs
                    printAnimals("dog");
                    break;
                case '5': // Print all monkeys
                    printAnimals("monkey");
                    break;
                case '6': // Print all available (in-service & not reserved) animals
                    printAnimals("available");
                    break;
                case 'q': // Quit application
                    exitProgram = true;
                    // Save all current data to file before exiting
                    saveData();
                    System.out.println("Exiting program. All data saved.");
                    break;
                default:
                    // Invalid menu selection
                    System.out.println("Invalid selection, please enter a menu option (1-6 or q).");
            }
        }
        scanner.close();
    }

    /**
     * Displays the menu options to the console.
     */
    public static void displayMenu() {
        System.out.println("\n\nRescue Animal Management System Menu");
        System.out.println("[1] Intake a new dog");
        System.out.println("[2] Intake a new monkey");
        System.out.println("[3] Reserve an animal");
        System.out.println("[4] Print all dogs");
        System.out.println("[5] Print all monkeys");
        System.out.println("[6] Print all in-service animals that are available");
        System.out.println("[q] Quit application");
        System.out.print("Enter a menu selection: ");
    }

    /**
     * Handles intake (addition) of a new Dog.
     * Prompts the user for dog details, validates inputs, and adds the dog to the system.
     */
    public static void intakeNewDog(Scanner scanner) {
        System.out.print("Enter the dog's name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty. Aborting intake.");
            return;
        }
        // Check if this name already exists (in either list)
        if (animalNames.contains(name.toLowerCase())) {
            System.out.println("This animal name already exists in the system. Intake aborted.");
            return;
        }
        // Gather other attributes
        System.out.print("Enter the dog's breed: ");
        String breed = scanner.nextLine().trim();
        System.out.print("Enter the dog's gender: ");
        String gender = scanner.nextLine().trim();
        System.out.print("Enter the dog's age: ");
        String age = scanner.nextLine().trim();
        System.out.print("Enter the dog's weight: ");
        String weight = scanner.nextLine().trim();
        System.out.print("Enter the acquisition date (mm-dd-yyyy): ");
        String acquisitionDate = scanner.nextLine().trim();
        System.out.print("Enter the acquisition country: ");
        String acquisitionCountry = scanner.nextLine().trim();
        // Default new dog status and location
        String trainingStatus;
        System.out.print("Enter the dog's training status \n(e.g., intake, Phase I, Phase II, Phase III, Phase IV, Phase V, in service): ");
        trainingStatus = scanner.nextLine().trim();
        if (trainingStatus.isEmpty()) {
            trainingStatus = "intake"; // default to intake if not provided
        }
        boolean reserved = false;  // new dogs are not reserved initially
        String inServiceCountry;
        if (trainingStatus.equalsIgnoreCase("in service")) {
            // If the dog is already in service, ask for its service country
            System.out.print("Enter the country where the dog is in service: ");
            inServiceCountry = scanner.nextLine().trim();
            if (inServiceCountry.isEmpty()) {
                inServiceCountry = acquisitionCountry;
            }
        } else {
            // If not in service yet, use acquisition country as current location
            inServiceCountry = acquisitionCountry;
        }
        // Create new Dog object and add to list
        Dog newDog = new Dog(name, breed, gender, age, weight, acquisitionDate, acquisitionCountry, trainingStatus, reserved, inServiceCountry);
        dogList.add(newDog);
        animalNames.add(name.toLowerCase());  // record this name as used
        System.out.println("New dog added: " + newDog.getName() + " (" + newDog.getBreed() + ").");
    }

    /**
     * Handles intake (addition) of a new Monkey.
     * Prompts the user for monkey details, validates inputs (including species), and adds the monkey to the system.
     */
    public static void intakeNewMonkey(Scanner scanner) {
        System.out.print("Enter the monkey's name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty. Aborting intake.");
            return;
        }
        // Check if name is unique
        if (animalNames.contains(name.toLowerCase())) {
            System.out.println("This animal name already exists in the system. Intake aborted.");
            return;
        }
        // Gather monkey-specific data and common attributes
        System.out.print("Enter the monkey's tail length: ");
        String tailLength = scanner.nextLine().trim();
        System.out.print("Enter the monkey's height: ");
        String height = scanner.nextLine().trim();
        System.out.print("Enter the monkey's body length: ");
        String bodyLength = scanner.nextLine().trim();
        // Species with validation against allowed list
        System.out.print("Enter the monkey's species: ");
        String species = scanner.nextLine().trim();
        while (!ALLOWED_MONKEY_SPECIES.contains(species)) {
            // If species is invalid, prompt until a valid species is entered
            System.out.println("Invalid species. Eligible monkey species: " + ALLOWED_MONKEY_SPECIES);
            System.out.print("Enter an eligible monkey species: ");
            species = scanner.nextLine().trim();
        }
        System.out.print("Enter the monkey's gender: ");
        String gender = scanner.nextLine().trim();
        System.out.print("Enter the monkey's age: ");
        String age = scanner.nextLine().trim();
        System.out.print("Enter the monkey's weight: ");
        String weight = scanner.nextLine().trim();
        System.out.print("Enter the acquisition date (mm-dd-yyyy): ");
        String acquisitionDate = scanner.nextLine().trim();
        System.out.print("Enter the acquisition country: ");
        String acquisitionCountry = scanner.nextLine().trim();
        // Training status and in-service handling
        System.out.print("Enter the monkey's training status \n(e.g., intake, Phase I, Phase II, Phase III, Phase IV, Phase V, in service): ");
        String trainingStatus = scanner.nextLine().trim();
        if (trainingStatus.isEmpty()) {
            trainingStatus = "intake";
        }
        boolean reserved = false;
        String inServiceCountry;
        if (trainingStatus.equalsIgnoreCase("in service")) {
            System.out.print("Enter the country where the monkey is in service: ");
            inServiceCountry = scanner.nextLine().trim();
            if (inServiceCountry.isEmpty()) {
                inServiceCountry = acquisitionCountry;
            }
        } else {
            inServiceCountry = acquisitionCountry;
        }
        // Create new Monkey object and add it to the list
        Monkey newMonkey = new Monkey(name, tailLength, height, bodyLength, species, 
                                      gender, age, weight, acquisitionDate, acquisitionCountry, 
                                      trainingStatus, reserved, inServiceCountry);
        monkeyList.add(newMonkey);
        animalNames.add(name.toLowerCase());
        System.out.println("New monkey added: " + newMonkey.getName() + " (" + newMonkey.getSpecies() + ").");
    }

    /**
     * Reserves an animal (dog or monkey) for a rescue service, if available.
     * The user specifies the type and country, and the first matching available animal is marked as reserved.
     */
    public static void reserveAnimal(Scanner scanner) {
        System.out.print("Enter the type of animal to reserve (Dog or Monkey): ");
        String type = scanner.nextLine().trim();
        if (!(type.equalsIgnoreCase("dog") || type.equalsIgnoreCase("monkey"))) {
            System.out.println("Invalid animal type. Please enter 'Dog' or 'Monkey'.");
            return;
        }
        System.out.print("Enter the country in which you need the animal: ");
        String country = scanner.nextLine().trim();
        boolean found = false;
        if (type.equalsIgnoreCase("dog")) {
            for (Dog dog : dogList) {
                if (!dog.isReserved() 
                        && dog.getInServiceLocation().equalsIgnoreCase(country) 
                        && dog.getTrainingStatus().equalsIgnoreCase("in service")) {
                    // Found an available dog in the specified country
                    dog.setReserved(true);
                    found = true;
                    System.out.println("Dog " + dog.getName() + " (Breed: " + dog.getBreed() + ") has been reserved for service in " + country + ".");
                    break;
                }
            }
        } else if (type.equalsIgnoreCase("monkey")) {
            for (Monkey monkey : monkeyList) {
                if (!monkey.isReserved() 
                        && monkey.getInServiceLocation().equalsIgnoreCase(country) 
                        && monkey.getTrainingStatus().equalsIgnoreCase("in service")) {
                    monkey.setReserved(true);
                    found = true;
                    System.out.println("Monkey " + monkey.getName() + " (Species: " + monkey.getSpecies() + ") has been reserved for service in " + country + ".");
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("No available " + type.toLowerCase() + "s found in " + country + " that are ready for service.");
        }
    }

    /**
     * Prints a list of animals based on the specified category.
     * @param listType "dog" for all dogs, "monkey" for all monkeys, "available" for all not-reserved animals in service.
     */
    public static void printAnimals(String listType) {
        if (listType.equalsIgnoreCase("dog")) {
            System.out.println("\nList of all dogs:");
            if (dogList.isEmpty()) {
                System.out.println(" - None -");
            } else {
                for (Dog dog : dogList) {
                    // Print each dog's details using toString()
                    System.out.println(dog.toString());
                }
            }
        } 
        else if (listType.equalsIgnoreCase("monkey")) {
            System.out.println("\nList of all monkeys:");
            if (monkeyList.isEmpty()) {
                System.out.println(" - None -");
            } else {
                for (Monkey monkey : monkeyList) {
                    System.out.println(monkey.toString());
                }
            }
        } 
        else if (listType.equalsIgnoreCase("available")) {
            System.out.println("\nList of all available (not reserved) animals in service:");
            boolean anyFound = false;
            // List available dogs in service
            for (Dog dog : dogList) {
                if (!dog.isReserved() && dog.getTrainingStatus().equalsIgnoreCase("in service")) {
                    System.out.println("Dog - " + dog.toString());
                    anyFound = true;
                }
            }
            // List available monkeys in service
            for (Monkey monkey : monkeyList) {
                if (!monkey.isReserved() && monkey.getTrainingStatus().equalsIgnoreCase("in service")) {
                    System.out.println("Monkey - " + monkey.toString());
                    anyFound = true;
                }
            }
            if (!anyFound) {
                System.out.println(" - None -");
            }
        }
    }

    /**
     * Loads animal data from the persistent file if it exists.
     * If no file is found, initializes default test data.
     */
    private static void loadData() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            // No existing data file, initialize with some sample data for demonstration
            initializeDogList();
            initializeMonkeyList();
            System.out.println("No data file found. Initialized with default sample data.");
            return;
        }
        // File exists – read and parse data
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) continue;
                // Each line represents one animal record, comma-separated
                String[] data = line.split(",", -1); // -1 to include empty strings if any
                String type = data[0];
                if (type.equalsIgnoreCase("Dog") && data.length == 11) {
                    // Parse dog record
                    String name = data[1];
                    String breed = data[2];
                    String gender = data[3];
                    String age = data[4];
                    String weight = data[5];
                    String acquisitionDate = data[6];
                    String acquisitionCountry = data[7];
                    String trainingStatus = data[8];
                    boolean reserved = Boolean.parseBoolean(data[9]);
                    String inServiceCountry = data[10];
                    Dog dog = new Dog(name, breed, gender, age, weight, acquisitionDate, acquisitionCountry, trainingStatus, reserved, inServiceCountry);
                    dogList.add(dog);
                    animalNames.add(name.toLowerCase());
                } else if (type.equalsIgnoreCase("Monkey") && data.length == 14) {
                    // Parse monkey record
                    String name = data[1];
                    String species = data[2];
                    String tailLength = data[3];
                    String height = data[4];
                    String bodyLength = data[5];
                    String gender = data[6];
                    String age = data[7];
                    String weight = data[8];
                    String acquisitionDate = data[9];
                    String acquisitionCountry = data[10];
                    String trainingStatus = data[11];
                    boolean reserved = Boolean.parseBoolean(data[12]);
                    String inServiceCountry = data[13];
                    Monkey monkey = new Monkey(name, tailLength, height, bodyLength, species, gender, age, weight, acquisitionDate, acquisitionCountry, trainingStatus, reserved, inServiceCountry);
                    monkeyList.add(monkey);
                    animalNames.add(name.toLowerCase());
                }
            }
            System.out.println("Data loaded from " + DATA_FILE + ". " 
                               + dogList.size() + " dogs and " + monkeyList.size() + " monkeys in system.");
        } catch (FileNotFoundException e) {
            System.err.println("Data file not found: " + e.getMessage());
        }
    }

    /**
     * Saves all current animal data to the persistent file.
     * Data is written in a comma-separated format (CSV) with each animal on a new line.
     */
    private static void saveData() {
        try (PrintWriter writer = new PrintWriter(DATA_FILE)) {
            // Write all dogs
            for (Dog dog : dogList) {
                writer.printf("Dog,%s,%s,%s,%s,%s,%s,%s,%s,%b,%s%n",
                        dog.getName(), dog.getBreed(), dog.getGender(), dog.getAge(), dog.getWeight(),
                        dog.getAcquisitionDate(), dog.getAcquisitionLocation(), dog.getTrainingStatus(),
                        dog.isReserved(), dog.getInServiceLocation());
            }
            // Write all monkeys
            for (Monkey monkey : monkeyList) {
                writer.printf("Monkey,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%b,%s%n",
                        monkey.getName(), monkey.getSpecies(), monkey.getTailLength(), monkey.getHeight(), monkey.getBodyLength(),
                        monkey.getGender(), monkey.getAge(), monkey.getWeight(),
                        monkey.getAcquisitionDate(), monkey.getAcquisitionLocation(), monkey.getTrainingStatus(),
                        monkey.isReserved(), monkey.getInServiceLocation());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    /**
     * Initializes the dog list with some default dogs (for testing or first-run setup).
     * Also populates the animalNames set with these names.
     */
    private static void initializeDogList() {
        Dog dog1 = new Dog("Spot", "German Shepherd", "Male", "1", "25.6", "05-12-2019", "United States", "intake", true, "United States");
        Dog dog2 = new Dog("Rex", "Great Dane", "Male", "3", "35.2", "02-03-2020", "United States", "Phase I", false, "United States");
        Dog dog3 = new Dog("Bella", "Chihuahua", "Female", "4", "25.6", "12-12-2019", "Canada", "in service", false, "Canada");
        dogList.add(dog1);
        dogList.add(dog2);
        dogList.add(dog3);
        animalNames.add("spot");
        animalNames.add("rex");
        animalNames.add("bella");
    }

    /**
     * Initializes the monkey list with some default monkeys (for testing or first-run setup).
     * Also populates the animalNames set with these names.
     */
    private static void initializeMonkeyList() {
        Monkey monkey1 = new Monkey("Bob", "2", "24.2", "20.2", "Capuchin", "Male", "4", "30", "05-05-2021", "United States", "in service", true, "United States");
        Monkey monkey2 = new Monkey("Linda", "7", "25.7", "22.2", "Macaque", "Female", "5", "27", "09-03-2021", "United States", "intake", false, "United States");
        Monkey monkey3 = new Monkey("Louise", "4", "20.3", "19.1", "Tamarin", "Female", "3", "10", "07-21-2021", "United States", "in service", false, "United States");
        monkeyList.add(monkey1);
        monkeyList.add(monkey2);
        monkeyList.add(monkey3);
        animalNames.add("bob");
        animalNames.add("linda");
        animalNames.add("louise");
    }
}
