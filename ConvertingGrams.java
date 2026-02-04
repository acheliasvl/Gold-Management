import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ConvertingGrams {

    public Scene getScene(Stage primaryStage, Scene previousScene) {
    	MenuBar menuBar = new MenuBar(); 
		
		Menu mainMenu = new Menu("Ana Sayfa");
        MenuItem homeItem = new MenuItem("Yatırımlarım");

		
		Menu tidbitsMenu = new Menu("Kısa Notlar"); 
		MenuItem tidbitsItem = new MenuItem("Bilinmesi Gerekenler"); 
		
		Menu exitMenu = new Menu("Çıkış");
		MenuItem exitItem = new MenuItem("Hoşçakal!");
		
		exitItem.setOnAction(event-> {
			primaryStage.close(); 
		});
		
		homeItem.setOnAction(event->{
			primaryStage.setScene(previousScene); 
		});
		
		Menu graphicMenu = new Menu("Grafikler"); 
		Menu goalsMenu = new Menu("Hedeflerim"); 
		MenuItem setGoalsItem = new MenuItem("Hedef Belirle");
		
		Menu calculatorMenu = new Menu("Hesap Makinesi"); 
        
		mainMenu.getItems().add(homeItem);
		goalsMenu.getItems().add(setGoalsItem); 
		exitMenu.getItems().add(exitItem); 
		tidbitsMenu.getItems().add(tidbitsItem); 
		menuBar.getMenus().addAll(mainMenu, goalsMenu, graphicMenu, calculatorMenu, tidbitsMenu, exitMenu); 
		
        BorderPane borderpane = new BorderPane(); 
        borderpane.setTop(menuBar);
        Scene scene = new Scene(borderpane, 800, 600); 
        scene.getStylesheets().add("stylesheet.css"); 
        return scene;
    }
}
