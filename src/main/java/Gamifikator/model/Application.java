package main.java.Gamifikator.model;

public class Application {

	private String name;
	private String creator;

	public Application(String name, String creator) {
		this.name = name;
		this.creator = creator;
	}

	public String getName() {
		return name;
	}

	public String getCreator() {
		return creator;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
}
