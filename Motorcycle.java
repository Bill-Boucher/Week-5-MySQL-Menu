package entity;

import java.util.List;


//Constructor with setters and getters
public class Motorcycle {

	private int motorcycleId;
	private String name;
	private List <Model> models;
	
	public Motorcycle(int motorcycleId, String name, List<Model> models) {
		this.setMotorcycleId(motorcycleId);
		this.setName(name);
		this.setModels(models);
	}

	public int getMotorcycleId() {
		return motorcycleId;
	}

	public void setMotorcycleId(int motorcycleId) {
		this.motorcycleId = motorcycleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List <Model> getModels() {
		return models;
	}

	public void setModels(List <Model> models) {
		this.models = models;
	}
}
