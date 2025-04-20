/**
 * Base class representing a rescue animal with common attributes.
 * Provides getters and setters for each attribute.
 */
public class RescueAnimal {
    // Instance variables (common attributes for all rescue animals)
    protected String name;
    protected String animalType;
    protected String gender;
    protected String age;
    protected String weight;
    protected String acquisitionDate;
    protected String acquisitionCountry;
    protected String trainingStatus;
    protected boolean reserved;
    protected String inServiceCountry;

    // No-argument constructor
    public RescueAnimal() {
    }

    // Getters and setters for each field
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAnimalType() {
        return animalType;
    }
    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAcquisitionDate() {
        return acquisitionDate;
    }
    public void setAcquisitionDate(String acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String getAcquisitionLocation() {  // acquisition country getter
        return acquisitionCountry;
    }
    public void setAcquisitionLocation(String acquisitionCountry) {
        this.acquisitionCountry = acquisitionCountry;
    }

    public String getTrainingStatus() {
        return trainingStatus;
    }
    public void setTrainingStatus(String trainingStatus) {
        this.trainingStatus = trainingStatus;
    }

    public boolean isReserved() {
        return reserved;
    }
    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public String getInServiceLocation() {  // in-service country getter
        return inServiceCountry;
    }
    public void setInServiceCountry(String inServiceCountry) {
        this.inServiceCountry = inServiceCountry;
    }

    /**
     * Returns a formatted string with all core attributes of the animal.
     * Subclasses will append their specific details.
     */
    @Override
    public String toString() {
        return "Name: " + name 
            + ", Gender: " + gender 
            + ", Age: " + age 
            + ", Weight: " + weight 
            + ", Acquisition: " + acquisitionDate + " (" + acquisitionCountry + ")"
            + ", Training Status: " + trainingStatus 
            + ", Reserved: " + reserved 
            + ", In-Service Country: " + inServiceCountry;
    }
}
