package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entity.Client;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

public class ClientControl {
	private StringProperty name = new SimpleStringProperty("");
	private StringProperty phone = new SimpleStringProperty("");
	private StringProperty address = new SimpleStringProperty("");
	private StringProperty email = new SimpleStringProperty("");

	private ObservableList<Client> list = FXCollections.observableArrayList();
	public static final String JDBC_URL = "jdbc:mariadb://localhost:3306/project";
	public static final String JDBC_USER = "root";
	public static final String JDBC_PASS = "123456";
	private Connection con;

	public ClientControl() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void New() {
		name.set("");
		phone.set("");
		email.set("");
		address.set("");
	}
	
	public void newClient() {
		Client Client = getEntity();
		list.add(Client);
		String sql = 
				"INSERT INTO Client (name,phone,email,address)"
					+ "VALUES (?,?,?,?)";
		try {			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, Client.getName());
			stmt.setString(2, Client.getPhone());
			stmt.setString(3, Client.getEmail());
			stmt.setString(4, Client.getAddress());
			stmt.executeUpdate();
			//con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	public ObservableList<Client> searchClientByName(TextField txtName) {
		list.clear();
		String sql = 
				"SELECT * FROM Client" 
					+ " WHERE name LIKE ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + txtName.getText() + "%");
			ResultSet rs  = stmt.executeQuery();
			while(rs.next()) {
				Client a = new Client();
				a.setName(rs.getString("name"));
				a.setPhone(rs.getString("phone"));
				a.setAddress(rs.getString("address"));
				a.setEmail(rs.getString("email"));
				list.add(a);
			}
			//con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void setEntity(Client Client) {
		name.set(Client.getName());
		phone.set(Client.getPhone());
		address.set(Client.getAddress());
		email.set(Client.getEmail());
	}
	
	public Client getEntity() {
		Client Client = new Client();
		Client.setName(name.get());
		Client.setPhone(phone.get());
		Client.setEmail(email.get());
		Client.setAddress(address.get());
		return Client;
	}
	
	public StringProperty nameProperty() {
		return this.name;
	}
	
	public StringProperty emailProperty() {
		return this.email;
	}
	
	public StringProperty addressProperty() {
		return this.address;
	}
	
	public StringProperty phoneProperty() {
		return this.phone;
	}

	public ObservableList<Client> getList() {
		return this.list;
	}

	
	

}
