/**
 * Dog class extends RescueAnimal and adds the breed attribute.
 */
public class Dog extends RescueAnimal {
    // Instance variable unique to Dog
    private String breed;

    // Constructor to initialize all Dog attributes
    public Dog(String name, String breed, String gender, String age, String weight, 
               String acquisitionDate, String acquisitionCountry, String trainingStatus, 
               boolean reserved, String inServiceCountry) {
        // Set common fields using RescueAnimal's setters
        setName(name);
        setAnimalType("Dog");
        setGender(gender);
        setAge(age);
        setWeight(weight);
        setAcquisitionDate(acquisitionDate);
        setAcquisitionLocation(acquisitionCountry);
        setTrainingStatus(trainingStatus);
        setReserved(reserved);
        setInServiceCountry(inServiceCountry);
        // Set Dog-specific field
        setBreed(breed);
    }

    // Default no-arg constructor
    public Dog() {
        setAnimalType("Dog");
    }

    // Accessor and mutator for breed
    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }

    /**
     * Returns a formatted string of Dog details, including breed and all base attributes.
     */
    @Override
    public String toString() {
        return "Breed: " + breed + ", " + super.toString();
    }
}
