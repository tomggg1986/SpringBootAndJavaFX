package ptoku.com.scheduler;

import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

@Component
public class PaneController {
	@FXML
	private Label fileName;
	private Stage stage;
	
	@FXML
	public void initialize() {
	//	this.fileName = new Label(); 
		this.fileName.setText("Waiting for file");
		
	}
	public void  setStage(Stage stage) {
		this.stage = stage;
	}
	public void setFileName(String text) {
		this.fileName.setText(text);
	}
	public void hideScene(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	public void show() {
		Platform.runLater(new Runnable() {
			public void run() {
				stage.show();
			}
		});		
	}
	public void minimalize(boolean nim) {
		Platform.runLater(()-> stage.setIconified(nim));
	}
	

}
