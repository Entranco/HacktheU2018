package server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShelterAnimalDatabase {
	private ArrayList<ShelterAnimal> animals = new ArrayList<ShelterAnimal>();
	
	public ShelterAnimalDatabase() {
		createFromText();
	}
	
	private void createFromText() {
		String placeholder = "";
		try(Scanner input = new Scanner(new FileInputStream("src/server/texts/animalinfo.txt"))) {
			while(input.hasNextLine()) {
				placeholder = input.nextLine();
				animals.add(new ShelterAnimal(placeholder));
			}
		}catch(FileNotFoundException ex) {	
		}
	}
	
	public ArrayList<ShelterAnimal> getAnimals() {
		return animals;
	}
	
}
