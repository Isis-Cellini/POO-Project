package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Product;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

public class ProductControl {
	private StringProperty name = new SimpleStringProperty("");
	private SimpleDoubleProperty price = new SimpleDoubleProperty(0.0);
	private SimpleStringProperty description = new SimpleStringProperty("");
	private IntegerProperty quantity = new SimpleIntegerProperty(0);
	private ObservableList<Product> list = FXCollections.observableArrayList();
	public static final String JDBC_URL = "jdbc:mariadb://localhost:3306/project";
	public static final String JDBC_USER = "root";
	public static final String JDBC_PASS = "123456";
	private Connection con;

	public ProductControl() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void New() {
		name.set("");
		price.set(0.0);
		description.set("");
		quantity.set(0);
	}
	
	public void newProduct() {
		Product product = getEntity();
		list.add(product);
		String sql = 
				"INSERT INTO Product (name,price,description,quantity)"
					+ "VALUES (?,?,?,?)";
		try {			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, product.getName());
			stmt.setDouble(2, product.getPrice());
			stmt.setString(3, product.getDescription());
			stmt.setInt(4, product.getQuantity());
			stmt.executeUpdate();
			//con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	public ObservableList<Product> searchProductByName(TextField txtName) {
		list.clear();
		String sql = 
				"SELECT * FROM product" 
					+ " WHERE name LIKE ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + txtName.getText() + "%");
			ResultSet rs  = stmt.executeQuery();
			while(rs.next()) {
				Product a = new Product();
				a.setName(rs.getString("name"));
				a.setPrice(rs.getDouble("price"));
				a.setDescription(rs.getString("description"));
				a.setQuantity(rs.getInt("quantity"));
				list.add(a);
			}
			//con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void setEntity(Product product) {
		name.set(product.getName());
		price.set(product.getPrice());
		description.set(product.getDescription());
		quantity.set(product.getQuantity());
	}
	
	public Product getEntity() {
		Product product = new Product();
		product.setName(name.get());
		product.setPrice(price.get());
		product.setDescription(description.get());
		product.setQuantity(quantity.get());
		return product;
	}
	
	public StringProperty nameProperty() {
		return this.name;
	}

	public DoubleProperty priceProperty() {
		return this.price;
	}

	public StringProperty descriptionProperty() {
		return this.description;
	}

	public IntegerProperty quantityProperty() {
		return this.quantity;
	}

	public ObservableList<Product> getList() {
		return this.list;
	}

	
	

}
