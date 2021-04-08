package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.ModelDao;
import dao.MotorcycleDao;
import entity.Model;
import entity.Motorcycle;

//Menu for application

public class Menu {
	
	private MotorcycleDao motorcycleDao = new MotorcycleDao();
	private ModelDao modelDao = new ModelDao();
	private Scanner scanner = new Scanner (System.in);
	private List<String> options = Arrays.asList(
			"Display Motorcylces", 
			"Display Motorcylce",
			"Create Motorcycle",
			"Delete Motorcycle",
			"Create Motorcycle Model",
			"Delete Motorcycle Model");
	
	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			
			try {
				if (selection.equals("1")) {
					displayMotorcycles();
				}else if (selection.equals("2")) {
					displayMotorcycle();
				}else if (selection.equals("3")) {
					createMotorcycle();
				}else if (selection.equals("4")) {
					deleteMotorcycle();
				}else if (selection.equals("5")) {
					createModel();
				}else if (selection.equals("6")) {
					deleteModel();
				}
				else System.out.println("Invalid selection, try again.");
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
	
	
			System.out.println("Press enter to continue...");
			scanner.nextLine();
		} while (!selection.equals("-1"));
}
	//Prints menu options
	private void printMenu() {
		System.out.println("Select an option:\n-----------------------");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ") " + options.get(i));
		}
	}
	// Displays all makes of motorcycles
	private void displayMotorcycles() throws SQLException {
		List<Motorcycle> motorcycles = motorcycleDao.getMotorcycle();
		for (Motorcycle motorcycle : motorcycles) {
			System.out.println(motorcycle.getMotorcycleId() + ": " + motorcycle.getName());
		}
			
	}
	//Displays a certain motorcycle with all associated models
	private void displayMotorcycle() throws SQLException {
		System.out.print("Enter motorcycle id: ");
		int id = Integer.parseInt(scanner.nextLine());
		try {
			Motorcycle motorcycle = motorcycleDao.getMotorcycleById(id);
			System.out.println(motorcycle.getMotorcycleId()+ ": " + motorcycle.getName());
			for (Model model : motorcycle.getModels()) {
				System.out.println("ModelId: " + model.getModelsId() + "- Name: " + model.getModelName());
			}
		} catch (SQLException e) {
			System.out.println("No motorcycle with that id");
//			e.printStackTrace();
		}
	}
	//Create a new motorcycle
	private void createMotorcycle() throws SQLException {
		System.out.print("Enter new Motorcycle name: ");
		String motorcycleName = scanner.nextLine();
		motorcycleDao.createNewMotorcycle(motorcycleName);
	}
	//Delete a motorcycle
	private void deleteMotorcycle() throws SQLException {
		System.out.print("Enter motorcycle id to delete: ");
		int id = Integer.parseInt(scanner.nextLine());
		motorcycleDao.deleteMotorcycleById(id);
	}
	//Create a new motorcycle model
	private void createModel() throws SQLException {
		System.out.print("Enter name of new model: ");
		String modelName = scanner.nextLine();
		System.out.print("Enter motorcycle ID of new model: ");
		int motorcycleId = Integer.parseInt(scanner.nextLine());
		modelDao.createNewModel(modelName, motorcycleId);
	}
	//Delete a motorcycle model
	private void deleteModel() throws SQLException {
		System.out.print("Enter model Id to delete: ");
		int id = Integer.parseInt(scanner.nextLine());
		modelDao.deleteModelById(id);
	}
}
