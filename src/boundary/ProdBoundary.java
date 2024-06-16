package boundary;

import entity.Product;
import control.ProductControl;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.converter.NumberStringConverter;

public class ProdBoundary{
    TextField txtName = new TextField();
	TextField txtPrice = new TextField();
	TextField txtDescription = new TextField();
    TextField txtQuantity = new TextField();
    ProductControl control = new ProductControl();
    TableView<Product> table = new TableView<>();
	Button btnNew = new Button("Cadastrar");
	Button btnSearch = new Button("Pesquisar");
	BorderPane mainPane = new BorderPane();
	
	
	public BorderPane render() { 
		btnNew.setOnAction( e -> control.newProduct() );
		btnSearch.setOnAction( e -> control.searchProductByName(txtName) );
		
		GridPane paneForm = new GridPane();
		paneForm.add(new Label("Nome:"), 0, 0);
		paneForm.add(txtName, 1, 0);
		paneForm.add(new Label("Preço:"), 0, 1);
		paneForm.add(txtPrice, 1, 1);
		paneForm.add(new Label("Descrição:"), 0, 2);
		paneForm.add(txtDescription, 1, 2);
		paneForm.add(new Label("Estoque:"), 0, 3);
		paneForm.add(txtQuantity, 1, 3);
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
		TableColumn<Product, String> colName = new TableColumn<>("Nome");
		colName.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getName()));
		
		TableColumn<Product, Double> colPrice = new TableColumn<>("Preço");
		colPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
		
		TableColumn<Product, String> colDescription = new TableColumn<>("Descrição");
		colDescription.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getDescription()));
			
		TableColumn<Product, Integer> colQuantity = new TableColumn<>("Quantidade");
		colQuantity.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
		
		table.getColumns().addAll(colName, colPrice, colDescription, colQuantity);
		
	}
	private void binding() {
		Bindings.bindBidirectional(txtName.textProperty(), control.nameProperty());
		Bindings.bindBidirectional(txtPrice.textProperty(), control.priceProperty(), new NumberStringConverter("#,###"));
		Bindings.bindBidirectional(txtDescription.textProperty(), control.descriptionProperty());
		Bindings.bindBidirectional(txtQuantity.textProperty(), control.quantityProperty(), new NumberStringConverter());
	}
	
}
