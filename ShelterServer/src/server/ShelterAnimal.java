package server;

public class ShelterAnimal {
	// fields
	private String name;
	private int age;
	private String breed;
	private String shelter;
	private String description;
	
	// constructor
	public ShelterAnimal(String _name, int _age, String _breed, String _shelter, String _description) {
		this.name = _name;
		this.age = _age;
		this.breed = _breed;
		this.shelter = _shelter;
		this.description = _description;
	}
	
	// methods
	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getBreed() {
		return breed;
	}

	public String getShelter() {
		return shelter;
	}

	public String getDescription() {
		return description;
	}
	
	public String toString() {
		return "";
	}
}
