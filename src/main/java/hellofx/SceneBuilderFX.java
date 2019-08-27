package hellofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneBuilderFX extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// Get the FXML document for the root
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("nex.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
