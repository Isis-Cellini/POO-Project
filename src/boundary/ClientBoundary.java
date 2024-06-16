package boundary;

import entity.Client;
import control.ClientControl;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class ClientBoundary{
    TextField txtName = new TextField();
	TextField txtEmail = new TextField();
	TextField txtPhone = new TextField();
    TextField txtAddress = new TextField();
    ClientControl control = new ClientControl();
    TableView<Client> table = new TableView<>();
	Button btnNew = new Button("Cadastrar");
	Button btnSearch = new Button("Pesquisar");
	BorderPane mainPane = new BorderPane();
	
	
	public BorderPane render() { 
		btnNew.setOnAction( e -> control.newClient() );
		btnSearch.setOnAction( e -> control.searchClientByName(txtName) );
		
		GridPane paneForm = new GridPane();
		paneForm.add(new Label("Nome:"), 0, 0);
		paneForm.add(txtName, 1, 0);
		paneForm.add(new Label("Email:"), 0, 1);
		paneForm.add(txtEmail, 1, 1);
		paneForm.add(new Label("Telefone:"), 0, 2);
		paneForm.add(txtPhone, 1, 2);
		paneForm.add(new Label("Endereço:"), 0, 3);
		paneForm.add(txtAddress, 1, 3);
		paneForm.add(btnNew, 0, 4);
		paneForm.add(btnSearch, 1, 4);
		
		mainPane.setTop(paneForm);
		mainPane.setCenter(table);
		
		binding();
		createColumns();
		
		return mainPane;
	
	}
	private void createColumns() {
		table.setItems(control.getList());
		TableColumn<Client, String> colName = new TableColumn<>("Nome");
		colName.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getName()));
		
		TableColumn<Client, String> colEmail = new TableColumn<>("Email");
		colEmail.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getEmail()));
		
		TableColumn<Client, String> colPhone = new TableColumn<>("Telefone");
		colPhone.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getPhone()));
			
		TableColumn<Client, String> colAddress = new TableColumn<>("Endereço");
		colAddress.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getAddress	()));
		
		table.getColumns().addAll(colName, colEmail, colPhone, colAddress);
		
	}
	private void binding() {
		Bindings.bindBidirectional(txtName.textProperty(), control.nameProperty());
		Bindings.bindBidirectional(txtEmail.textProperty(), control.emailProperty());
		Bindings.bindBidirectional(txtPhone.textProperty(), control.phoneProperty());
		Bindings.bindBidirectional(txtAddress.textProperty(), control.addressProperty());
	}
	
}
