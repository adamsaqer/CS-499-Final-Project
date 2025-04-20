import java.io.*;
import java.util.*;

public class AnimalFileHandler {
    private static final String FILE_NAME = "animals.txt";

    public static void saveAnimals(List<Dog> dogs, List<Monkey> monkeys) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Dog dog : dogs) {
                writer.printf("Dog,%s,%s,%s,%s,%s,%s,%s,%s,%b,%s%n",
                        dog.getName(), dog.getBreed(), dog.getGender(), dog.getAge(), dog.getWeight(),
                        dog.getAcquisitionDate(), dog.getAcquisitionLocation(), dog.getTrainingStatus(),
                        dog.isReserved(), dog.getInServiceLocation());
            }

            for (Monkey monkey : monkeys) {
                writer.printf("Monkey,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%b,%s%n",
                        monkey.getName(), monkey.getSpecies(), monkey.getTailLength(), monkey.getHeight(), monkey.getBodyLength(),
                        monkey.getGender(), monkey.getAge(), monkey.getWeight(),
                        monkey.getAcquisitionDate(), monkey.getAcquisitionLocation(), monkey.getTrainingStatus(),
                        monkey.isReserved(), monkey.getInServiceLocation());
            }
        } catch (IOException e) {
            System.out.println("Error saving animals: " + e.getMessage());
        }
    }

    public static void loadAnimals(List<Dog> dogs, List<Monkey> monkeys, Set<String> animalNames) {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No existing data file found. Initializing with sample data.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",", -1);
                if (parts[0].equalsIgnoreCase("Dog") && parts.length == 11) {
                    Dog dog = new Dog(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6],
                            parts[7], parts[8], Boolean.parseBoolean(parts[9]), parts[10]);
                    dogs.add(dog);
                    animalNames.add(parts[1].toLowerCase());
                } else if (parts[0].equalsIgnoreCase("Monkey") && parts.length == 14) {
                    Monkey monkey = new Monkey(parts[1], parts[3], parts[4], parts[5], parts[2], parts[6],
                            parts[7], parts[8], parts[9], parts[10], parts[11],
                            Boolean.parseBoolean(parts[12]), parts[13]);
                    monkeys.add(monkey);
                    animalNames.add(parts[1].toLowerCase());
                }
            }
            System.out.println("Animal data loaded from file.");
        } catch (FileNotFoundException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}
