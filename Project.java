import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

import java.util.ArrayList;

import java.util.List;
/**
 * This class does certain operations on the tables in the database.
 *
 * @author jmartin
 */

public class Project {

	
	public static void createItemUsingPreparedStatement(String itemCode, String itemDescription, double price)
			throws SQLException {

		Connection connection = null;

		connection = MySqlDatabase.getDatabaseConnection();
		String sql = "insert into Item (itemCode, itemDescription, price) values (?, ?, ?);";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setString(1, itemCode);
		preparedStatement.setString(2, itemDescription);
		preparedStatement.setDouble(3, price);

		preparedStatement.execute();
		connection.close();

	}

	public static void attemptToCreateNewItem(String itemCode, String itemDescription, double price) {

		try {
			createItemUsingPreparedStatement(itemCode, itemDescription, price);


			System.out.println("You successfully created am item.");

		} catch (SQLException sqlException) {

			System.out.println("Failed to create item");

			System.out.println(sqlException.getMessage());

		}

	}

	public static void createPurchase(String itemCode, int quantity) throws SQLException {

		Connection connection = null;

		connection = MySqlDatabase.getDatabaseConnection();

		Statement sqlStatement = connection.createStatement();

		String sql = String.format(
				"Insert into Purchase(ItemID, Quantity) Select ID, '%s' from Item where itemCode = '%s'",

				quantity, itemCode);

		sqlStatement.executeUpdate(sql);

		connection.close();

	}

	public static void attemptToCreateNewPurchase(String itemCode, int quantity) {

		try {

			createPurchase(itemCode, quantity);
			System.out.println("You successfully created a purchase.");

		} catch (SQLException sqlException) {

			System.out.println("Failed to create purchase");

			System.out.println(sqlException.getMessage());

		}

	}

	public static void createShipment(String itemCode, int quantity, String date) throws SQLException {

		Connection connection = null;

		connection = MySqlDatabase.getDatabaseConnection();

		Statement sqlStatement = connection.createStatement();

		String sql = String.format(
				"Insert into Shipment(ItemID, Quantity,  ShipmentDate)  Select ID, '%s', '%s' from Item where ItemCode = '%s';",

				quantity, date, itemCode);

		sqlStatement.executeUpdate(sql);
		connection.close();

	}

	

	public static List<Item> getItems(String itemCode) throws SQLException {

		Connection connection = null;

		ResultSet resultSet;

		connection = MySqlDatabase.getDatabaseConnection();

		Statement sqlStatement = connection.createStatement();

		if (itemCode.equals("%")) {

			String sql = "SELECT * FROM Item;";

			resultSet = sqlStatement.executeQuery(sql);

		} else {

			String sql = String.format("Select  * FROM Item where ItemCode = '%s';", itemCode);

			resultSet = sqlStatement.executeQuery(sql);

		}

		List<Item> items = new ArrayList<Item>();

		while (resultSet.next()) {
			int iD = resultSet.getInt(1);
			String itemC = resultSet.getString(2);

			String itemDescription = resultSet.getString(3);

			double price = resultSet.getDouble(4);

			Item item = new Item(iD, itemC, itemDescription, price);

			items.add(item);

		}

		resultSet.close();

		connection.close();

		return items;

	}

	public static void attemptToListItems(String itemCode) {

		try {

			List<Item> items = getItems(itemCode);

			for (Item item : items) {

				System.out.println(item.toString());

			}

		} catch (SQLException sqlException) {

			System.out.println("Failed to get Items");

			System.out.println(sqlException.getMessage());

		}

	}

	public static List<Shipment> getShipments(String itemCode) throws SQLException {

		ResultSet resultSet;

		Connection connection = null;

		connection = MySqlDatabase.getDatabaseConnection();

		Statement sqlStatement = connection.createStatement();

		if (itemCode.equals("%")) {

			String sql = "SELECT * FROM Shipment;";

			resultSet = sqlStatement.executeQuery(sql);

		} else {

			String sql = String.format("Select * from Shipment s Join Item i on i.ItemCode = '%s' and i.ID= s.ItemID;",
					itemCode);

			resultSet = sqlStatement.executeQuery(sql);

		}

		List<Shipment> shipments = new ArrayList<Shipment>();

		while (resultSet.next()) {
			int Id = resultSet.getInt(1);
			String itemC = resultSet.getString(2);

			int quantity = resultSet.getInt(3);

			Date date = resultSet.getDate(4);

			Shipment shipment = new Shipment(Id, itemC, quantity, date);

			shipments.add(shipment);

		}

		resultSet.close();

		connection.close();

		return shipments;

	}

	public static void attemptTolistShipments(String itemCode) {

		try {

			List<Shipment> shipments = getShipments(itemCode);

			for (Shipment shipment : shipments) {

				System.out.println(shipment.toString());

			}

		} catch (SQLException sqlException) {

			System.out.println("Failed to get shipments");

			System.out.println(sqlException.getMessage());

		}

	}

	public static List<Purchase> getPurchases(String itemCode) throws SQLException {

		ResultSet resultSet;

		Connection connection = null;

		connection = MySqlDatabase.getDatabaseConnection();

		Statement sqlStatement = connection.createStatement();

		if (itemCode.equals("%")) {

			String sql = "SELECT * FROM Purchase;";

			resultSet = sqlStatement.executeQuery(sql);

		} else {

			String sql = String.format("Select * from Purchase p Join Item i on i.ItemCode = '%s' and i.ID= p.ItemID;",
					itemCode);

			resultSet = sqlStatement.executeQuery(sql);

		}

		List<Purchase> purchases = new ArrayList<Purchase>();

		while (resultSet.next()) {
			int Id = resultSet.getInt(1);
			String itemC = resultSet.getString(2);

			int quantity = resultSet.getInt(3);
			Date date = resultSet.getDate(4);

			Purchase purchase = new Purchase(Id, itemC, quantity, date);

			purchases.add(purchase);

		}

		resultSet.close();

		connection.close();

		return purchases;

	}

	public static void attemptTolistPurchases(String itemCode) {

		try {

			List<Purchase> purchases = getPurchases(itemCode);

			for (Purchase purchase : purchases) {

				System.out.println(purchase.toString());

			}

		} catch (SQLException sqlException) {

			System.out.println("Failed to get Purchases");

			System.out.println(sqlException.getMessage());

		}

	}

	public static void deleteItem(String itemCode) throws SQLException {

		Connection connection = null;

		connection = MySqlDatabase.getDatabaseConnection();

		Statement sqlStatement = connection.createStatement();

		String sql = String.format("DELETE FROM Item WHERE ItemCode = '%s';", itemCode);

		sqlStatement.executeUpdate(sql);

		connection.close();

	}

	public static void attemptToDeleteItem(String itemCode) {

		try {

			deleteItem(itemCode);
			System.out.println("Deletion was successful.");

		} catch (SQLException sqlException) {

			System.out.println("Failed to delete item");

			System.out.println(sqlException.getMessage());

		}

	}

	public static void deleteShipment(String itemCode) throws SQLException {

		Connection connection = null;

		connection = MySqlDatabase.getDatabaseConnection();

		Statement sqlStatement = connection.createStatement();

		String sql = String.format(
				"Delete from Shipment s  where s.ItemID = (Select ID  from Item where itemCode = '%s');", itemCode);

		sqlStatement.executeUpdate(sql);

		connection.close();

	}

	public static void attemptToDeleteShipment(String itemID) {

		try {

			deleteShipment(itemID);
			System.out.println("Deletion was of shipment successful.");

		} catch (SQLException sqlException) {

			System.out.println("Failed to delete Shipment");

			System.out.println(sqlException.getMessage());

		}

	}

	public static void deletePurchase(String itemCode) throws SQLException {

		Connection connection = null;

		connection = MySqlDatabase.getDatabaseConnection();

		Statement sqlStatement = connection.createStatement();

		String sql = String.format(
				"Delete from Purchase p  where p.ItemID = (Select ID  from Item where itemCode = '%s');", itemCode);

		sqlStatement.executeUpdate(sql);

		connection.close();

	}

	public static void attemptToDeletePurchase(String itemCode) {

		try {

			deletePurchase(itemCode);
			System.out.println("Deletion was purchase successful.");

		} catch (SQLException sqlException) {

			System.out.println("Failed to delete purchase");

			System.out.println(sqlException.getMessage());

		}

	}

	public static void updateItem(String itemCode, double price) throws SQLException {

		Connection connection = null;

		connection = MySqlDatabase.getDatabaseConnection();

		Statement sqlStatement = connection.createStatement();

		String sql = String.format("UPDATE Item SET Price = '%s' WHERE ItemCode = '%s';",

				price, itemCode);

		sqlStatement.executeUpdate(sql);

		connection.close();

	}

	public static void attemptToUpdateItem(String itemCode, double price) {

		try {

			updateItem(itemCode, price);
			System.out.println("Item was updated successfully.");

		} catch (SQLException sqlException) {

			System.out.println("Failed to update item");

			System.out.println(sqlException.getMessage());

		}

	}

	public static void main(String[] args) {

		if (args[0].equals("CreateItem")) {

			String itemCode = args[1];

			String itemDescription = args[2];

			double price = Double.parseDouble(args[3]);

			attemptToCreateNewItem(itemCode, itemDescription, price);

		} else if (args[0].equals("CreatePurchase")) {

			String itemID = args[1];

			int quantity = Integer.parseInt(args[2]);

			attemptToCreateNewPurchase(itemID, quantity);

		} else if (args[0].equals("CreateShipment")) {

			String itemCode = args[1];

			int quantity = Integer.parseInt(args[2]);

			String date = args[3];

			try {

				createShipment(itemCode, quantity, date);

				System.out.println("You successfully created a shipment.");

			} catch (SQLException sqlException) {

				System.out.println("Failed to create shipment");

				System.out.println(sqlException.getMessage());

			}

		} else if (args[0].equals("GetItems")) {

			String itemCode = args[1];

			attemptToListItems(itemCode);

		} else if (args[0].equals("GetShipments")) {

			String itemCode = args[1];

			attemptTolistShipments(itemCode);

		} else if (args[0].equals("GetPurchases")) {

			String itemCode = args[1];

			attemptTolistPurchases(itemCode);

		} else if (args[0].equals("DeleteItem")) {

			String itemID = args[1];

			attemptToDeleteItem(itemID);

		} else if (args[0].equals("DeletePurchase")) {

			String itemID = args[1];

			attemptToDeletePurchase(itemID);

		} else if (args[0].equals("DeleteShipment")) {

			String itemID = args[1];

			attemptToDeleteShipment(itemID);

		} else if (args[0].equals("UpdateItem")) {

			String itemID = args[1];

			double price = Double.parseDouble(args[2]);

			attemptToUpdateItem(itemID, price);

		}else {
			System.out.println(" Please use one of the following commands to manipulate the tables in the data base:"
					+ "CreateItem, CreatePurchase, CreateShipment, GetItems, GetPurchases, GetShipments, DeleteItem, DeletePurchase, DeleteShipment, and UpdateItem");
		}

	}

}
