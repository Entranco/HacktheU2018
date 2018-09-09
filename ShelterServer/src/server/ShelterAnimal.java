package server;

public class ShelterAnimal {
	private String name;
	private int age;
	private String animal;
	private String breed;
	private String description;
	private String imgURL;
	
	public ShelterAnimal(String _imgURL) {
		this.imgURL = _imgURL;
	}
	
	public String getimgURL() {
		return imgURL;
	}
}
