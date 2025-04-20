/**
 * Monkey class extends RescueAnimal and adds monkey-specific attributes.
 */
public class Monkey extends RescueAnimal {
    // Instance variables unique to Monkey
    private String species;
    private String tailLength;
    private String height;
    private String bodyLength;

    // Constructor to initialize all Monkey attributes
    public Monkey(String name, String tailLength, String height, String bodyLength, String species, 
                  String gender, String age, String weight, String acquisitionDate, String acquisitionCountry, 
                  String trainingStatus, boolean reserved, String inServiceCountry) {
        // Set common fields via RescueAnimal
        setName(name);
        setAnimalType("Monkey");
        setGender(gender);
        setAge(age);
        setWeight(weight);
        setAcquisitionDate(acquisitionDate);
        setAcquisitionLocation(acquisitionCountry);
        setTrainingStatus(trainingStatus);
        setReserved(reserved);
        setInServiceCountry(inServiceCountry);
        // Set Monkey-specific fields
        setTailLength(tailLength);
        setHeight(height);
        setBodyLength(bodyLength);
        setSpecies(species);
    }

    // Default no-arg constructor
    public Monkey() {
        setAnimalType("Monkey");
    }

    // Accessors and mutators for monkey-specific attributes
    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }

    public String getTailLength() {
        return tailLength;
    }
    public void setTailLength(String tailLength) {
        this.tailLength = tailLength;
    }

    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
    }

    public String getBodyLength() {
        return bodyLength;
    }
    public void setBodyLength(String bodyLength) {
        this.bodyLength = bodyLength;
    }

    /**
     * Returns a formatted string of Monkey details, including species and measurements plus base info.
     */
    @Override
    public String toString() {
        return "Species: " + species 
            + ", Tail Length: " + tailLength 
            + ", Height: " + height 
            + ", Body Length: " + bodyLength 
            + ", " + super.toString();
    }
}
