package boundary;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{
	private ProdBoundary productBoundary = new ProdBoundary();
	private ClientBoundary clientBoundary = new ClientBoundary();
	private BorderPane mainPane = new BorderPane();

	public void start(Stage stage) throws Exception {
		Scene scn = new Scene(mainPane, 800, 600);
		MenuBar mainMenu = new MenuBar();
		Menu menuProduct = new Menu("Produtos");
		Menu menuClient = new Menu("Clientes");
		MenuItem menuItemNewProduct = new MenuItem("Cadastrar/Pesquisar");
		MenuItem menuItemExitProduct = new MenuItem("Sair");
		MenuItem menuItemNewClient = new MenuItem("Cadastrar/Pesquisar");
		MenuItem menuItemExitClient = new MenuItem("Sair");
		
		mainMenu.getMenus().addAll(menuProduct, menuClient);
		
		menuProduct.getItems().addAll(menuItemNewProduct);
		menuProduct.getItems().add(menuItemExitProduct);
		menuClient.getItems().addAll(menuItemNewClient);
		menuClient.getItems().add(menuItemExitClient);

		menuItemNewProduct.setOnAction ( e-> { mainPane.setCenter(productBoundary.render()); });
		menuItemExitProduct.setOnAction ( e-> {Platform.exit();
		System.exit(0);; });

		menuItemNewClient.setOnAction ( e-> { mainPane.setCenter(clientBoundary.render()); });
		menuItemExitClient.setOnAction ( e-> {Platform.exit();
		System.exit(0);; });
		
		stage.setScene(scn);
		stage.setTitle("Gestão Loja");
		stage.show();                                                               
		
		mainPane.setTop(mainMenu);
		mainPane.setCenter(null);
	}

	public static void main(String[] args) {
		Application.launch(Main.class, args);
	}
	
	
}
