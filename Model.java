package entity;


//Constructor with setters and getters
public class Model {

	private int modelsId;
	private String modelName;
	
	public Model (int modelsId, String modelName) {
		this.setModelsId(modelsId);
		this.setModelName(modelName);
		
	}

	public int getModelsId() {
		return modelsId;
	}

	public void setModelsId(int modelsId) {
		this.modelsId = modelsId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
}
