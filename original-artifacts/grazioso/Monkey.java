

public class Monkey extends RescueAnimal {
	
	private String tailLength;
	private String height;
	private String bodyLength;
	private String species;
	
	
	// Default Constructor
	public Monkey() {
		setName("Unknown");
        setGender("Unknown");
        setAge("Unknown");
        setWeight("Unknown");
        setAcquisitionDate("Unknown");
        setAcquisitionLocation("Unknown");
        setTrainingStatus("Unknown");
        setReserved(false);
        setInServiceCountry("Unknown");
        setTailLength("Unknown");
        setHeight("Unknown");
        setBodyLength("Unknown");
        setSpecies("Unknown");
	}
	
	
	// Constructor that takes arguments for each of the variables
	public Monkey(String name, String gender, String age,
	String weight, String acquisitionDate, String acquisitionCountry,
	String trainingStatus, boolean reserved, String inServiceCountry, 
	String tailLength, String height, String bodyLength, String species) {
		        setName(name);
		        setGender(gender);
		        setAge(age);
		        setWeight(weight);
		        setAcquisitionDate(acquisitionDate);
		        setAcquisitionLocation(acquisitionCountry);
		        setTrainingStatus(trainingStatus);
		        setReserved(reserved);
		        setInServiceCountry(inServiceCountry);
		        setTailLength(tailLength);
		        setHeight(height);
		        setBodyLength(bodyLength);
		        setSpecies(species);
		    }
	
	
	// Accessor for tail length variable
	public String getTailLength() {
		return tailLength;
	}
	
	// Mutator for tail length variable
	public void setTailLength(String tailLength) {
		this.tailLength = tailLength;
	}
	
	// Accessor for height variable
	public String getHeight() {
		return height;
	}
		
	// Mutator for height variable
	public void setHeight(String height) {
		this.height = height;
	}
	
	// Accessor for body length variable
	public String getBodyLength() {
		return bodyLength;
	}
		
	// Mutator for body length variable
	public void setBodyLength(String bodyLength) {
		this.bodyLength = bodyLength;
	}
	
	// Accessor for species variable
	public String getSpecies() {
		return species;
	}
	
	// Mutator for species variable
	public void setSpecies(String species) {
		this.species = species;
	}
	
	
}
