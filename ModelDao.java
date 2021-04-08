package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Model;
//Shows statements inputed into MySQL
public class ModelDao {
	
	private Connection connection;
	private final String GET_MODELS_BY_MOTORCYCLE_ID_QUERY = "SELECT * FROM models WHERE motorcycle_id = ? ";
	private final String DELETE_MODELS_BY_MOTORCYCLE_ID_QUERY = "DELETE FROM models WHERE motorcycle_id = ?";
	private final String CREATE_NEW_MODEL_QUERY = "INSERT INTO models(model_name, motorcycle_id) VALUES(?,?)";
	private final String DELETE_MODEL_BY_ID_QUERY = "DELETE from models WHERE id = ?";
	
	
	public ModelDao() {
		connection = DBConnection.getConnection();
	}

	public List<Model> getModelsByMotorcycleId (int motorcycleid) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_MODELS_BY_MOTORCYCLE_ID_QUERY);
		ps.setInt(1, motorcycleid);
		ResultSet rs = ps.executeQuery();
		List<Model> models = new ArrayList<Model>();
		
		while (rs.next()) {
			models.add(new Model (rs.getInt(1), rs.getString(2)));
		}
		
		return models;
	}
	
	public void createNewModel(String modelName, int motorcycleId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_MODEL_QUERY);
		ps.setString(1, modelName);
		ps.setInt(2, motorcycleId);
		ps.executeUpdate();
	}
	
	public void deleteModelsByMotorcycleId (int motorcycleId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_MODELS_BY_MOTORCYCLE_ID_QUERY);
		ps.setInt(1, motorcycleId);
		ps.execute();
}
	
	public void deleteModelById (int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_MODEL_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
}