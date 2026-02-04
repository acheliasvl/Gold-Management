import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage; 
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane; 
import javafx.scene.control.TextField; 
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType; 
import java.util.ArrayList;
import java.util.List;

public class GoldCalculator extends Application{
	public static void main(String[] args) {
		launch(args); 
	}
	
	public void start(Stage primaryStage) {
		MenuBar menuBar = new MenuBar(); 
		
		Menu mainMenu = new Menu("Ana Sayfa");
        MenuItem homeItem = new MenuItem("Yatırımlarım");

		Menu tidbitsMenu = new Menu("Kısa Notlar"); 
		MenuItem tidbitsItem = new MenuItem("Bilinmesi Gerekenler"); 
		
		Menu exitMenu = new Menu("Çıkış");
		MenuItem exitItem = new MenuItem("Hoşçakal!");

		Menu graphicMenu = new Menu("Grafikler"); 
		Menu goalsMenu = new Menu("Hedeflerim"); 
		MenuItem setGoalsItem = new MenuItem("Hedef Belirle");
		Menu calculatorMenu = new Menu("Hesap Makinesi"); 
		
		mainMenu.getItems().add(homeItem);
		goalsMenu.getItems().add(setGoalsItem); 
		exitMenu.getItems().add(exitItem); 
		tidbitsMenu.getItems().add(tidbitsItem); 
		menuBar.getMenus().addAll(mainMenu, goalsMenu, graphicMenu, calculatorMenu, tidbitsMenu, exitMenu); 
		
		Label goldRateLabel = new Label("Gram Altın: yükleniyor...");
		goldRateLabel.setStyle("-fx-font-size: 9px;");

		exitItem.setOnAction(event-> {
			primaryStage.close(); 
		});
		
		tidbitsItem.setOnAction(event -> {
		    ConvertingGrams convertingGrams = new ConvertingGrams();
		    Scene convertingScene = convertingGrams.getScene(primaryStage, primaryStage.getScene()); 
		    primaryStage.setScene(convertingScene); 
		});
		
		Label totalMoney = new Label("Toplam Yatırımlarım");
		totalMoney.setStyle("-fx-font-size: 20; -fx-font-weight: bold");
		
		ComboBox<String> currency = new ComboBox<>(); 
		currency.getItems().addAll("₺", "$", "€" ); 
		currency.setVisibleRowCount(3);
		
		HBox hbox = new HBox(10, totalMoney, currency); 
		
		TextField textfield = new TextField(); 
		VBox vbox = new VBox(10, hbox, textfield); 
		vbox.setLayoutX(280);
		vbox.setLayoutY(50);
		
		Label label = new Label ("Henüz hiç yatırımın yok!"); 
		Label label2 = new Label ("Şimdi başla..."); 
		Button addGold = new Button ("+");

		VBox vbox2 = new VBox(10, label, label2); 
		
		loadInvestmentsFromDatabase(vbox2);
		
        vbox2.setLayoutX(50);
		vbox2.setLayoutY(200);
		
		addGold.setLayoutX(250); 
		addGold.setLayoutY(200);
		
		double initialAddGoldY = addGold.getLayoutY();

		Button okbtn = new Button("OK"); 

		TextField newGoldAmount = new TextField();
		Label label3 = new Label("gram"); 
		TextField spentMoney = new TextField();
		ComboBox<String> currency2 = new ComboBox<>(); 

		
		addGold.setOnAction(event->{

		    	addGold.setLayoutY(initialAddGoldY);

				vbox2.getChildren().clear(); 
				newGoldAmount.setPromptText("Altın miktarını giriniz"); 
				
				HBox hbox3 = new HBox(5, newGoldAmount, label3); 
				
				spentMoney.setPromptText("Altın bedeli");
			    
				currency2.getItems().clear();
				currency2.getItems().addAll("₺", "$", "€" ); 
				currency2.setVisibleRowCount(3);
				
				HBox hbox4 = new HBox(5, spentMoney, currency2); 
				vbox2.getChildren().addAll(hbox3, hbox4, okbtn); 
				
				
				okbtn.setOnAction(okevent->{
				    	String goldAmount = newGoldAmount.getText(); 
				    	String spentAmount = spentMoney.getText(); 
				    	String selectedCurrency = currency2.getValue(); 
					  
				    	if(goldAmount.isEmpty() || spentAmount.isEmpty() || selectedCurrency == null || 
			                    !isValidDouble(goldAmount) || !isValidDouble(spentAmount)) {
		                    Alert alert = new Alert(AlertType.ERROR); 
		                    alert.setHeaderText("HATA!"); 
		                    alert.setContentText("Geçerli Değerler Girin"); 
		                    Optional<ButtonType> result = alert.showAndWait(); 
		                    if(result.isPresent() && result.get() == ButtonType.OK) {
		                    	alert.close(); 
		                    }                      
		                    return; 
					   }
				        double goldGram = Double.parseDouble(goldAmount);
				        double goldWorth = Double.parseDouble(spentAmount);
				    	
				        // 🔥 DATABASE INSERT
				        InvestmentDAO.insertInvestment(
				                goldWorth,   
				                goldGram,
				                goldWorth,
				                selectedCurrency
				        );
				    	
	                	Label amountLabel = new Label("Altın gramajı: " + goldAmount + " gram"); 
	                    Label spentLabel = new Label("Bedeli: " + spentAmount + " " + selectedCurrency); 
	                    
	                    vbox2.getChildren().clear();
	                    vbox2.getChildren().addAll(amountLabel, spentLabel); 
	                    
	                    loadInvestmentsFromDatabase(vbox2); // Reload database information
	                    
	                    addGold.setLayoutY(addGold.getLayoutY() + 26); 
					
				});

		});
				
		HBox bottomRightBox = new HBox(goldRateLabel);
		bottomRightBox.setAlignment(Pos.CENTER_RIGHT);
		bottomRightBox.setPadding(new Insets(5, 10, 5, 10));


		Pane pane = new Pane(); 
		pane.getChildren().addAll(vbox, vbox2, addGold);
		
		
		BorderPane borderpane = new BorderPane(); 
		borderpane.setTop(menuBar); 
		borderpane.setCenter(pane);
		borderpane.setBottom(bottomRightBox); 

		Scene scene = new Scene(borderpane, 800, 600);
		scene.getStylesheets().add("stylesheet.css"); 
		primaryStage.setScene(scene); 
		primaryStage.show(); 
		
		Task<String> goldTask = new Task<>() {
		    @Override
		    protected String call() {
		        return GoldRateService.getCurrentGramGold();
		    }
		};

		goldTask.setOnSucceeded(e -> {
		    goldRateLabel.setText("Gram Altın: $" + goldTask.getValue());
		});


		new Thread(goldTask).start();

	}
	
	private boolean isValidDouble(String value) {
	    try {
	        Double.parseDouble(value);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	private void loadInvestmentsFromDatabase(VBox container) {

	    List<Investment> investments = InvestmentDAO.getAllInvestments(); 

	    if (!investments.isEmpty()) {
	        container.getChildren().clear(); 
	        for (Investment inv : investments) {
	            Label label = new Label("Altın: " + inv.getGram() + " gr | Bedel: " + inv.getWorth() + " " + inv.getCurrency());
	            container.getChildren().add(label);
	        }
	    }
	}
	
}
