package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Motorcycle;

public class MotorcycleDao {
//Shows statements inputed into MySQL
	
	private Connection connection;
	private ModelDao modelDao;
	private final String GET_MOTORCYLCES_QUERY = "SELECT * FROM motorcycles";
	private final String GET_MOTORCYCLE_BY_ID_QUERY = "SELECT * FROM motorcycles WHERE id = ?";
	private final String CREATE_NEW_MOTORCYCLE_QUERY = "INSERT INTO motorcycles(name) VALUES(?)";
	private final String DELETE_MOTORCYCLE_BY_ID_QUERY = "DELETE FROM motorcycles WHERE id = ?";
	
	public MotorcycleDao() {
		connection = DBConnection.getConnection();
		modelDao = new ModelDao();
	}
	
	public List<Motorcycle> getMotorcycle() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_MOTORCYLCES_QUERY).executeQuery();
		List<Motorcycle> motorcycles = new ArrayList<Motorcycle>();
		
		while (rs.next()) {
			motorcycles.add(populateMotorcycle(rs.getInt(1), rs.getString(2)));
		}
		return motorcycles;
	}
	public Motorcycle getMotorcycleById (int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_MOTORCYCLE_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateMotorcycle(rs.getInt(1), rs.getString(2));
	}
	public void createNewMotorcycle(String motorcycleName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_MOTORCYCLE_QUERY);
		ps.setString(1, motorcycleName);
		ps.executeUpdate();
	}
	
	public void deleteMotorcycleById(int id) throws SQLException {
		modelDao.deleteModelsByMotorcycleId(id);
		PreparedStatement ps = connection.prepareStatement(DELETE_MOTORCYCLE_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.execute();
	}
	
	
	
	private Motorcycle populateMotorcycle (int id, String name) throws SQLException {
		return new Motorcycle(id, name, modelDao.getModelsByMotorcycleId(id));
	}

	

	

	

}
