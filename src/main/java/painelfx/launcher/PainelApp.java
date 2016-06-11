package painelfx.launcher;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PainelApp extends Application {

	private static Stage stage;
	private Scene scene;

	@Override
	public void start(Stage stage) {
		try {
			PainelApp.stage = stage;
			iniciarLayout();
			adicionarEventoFinalizarAplicacao();
			PainelApp.stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void iniciarLayout() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(PainelApp.class.getResource("../view/Painel.fxml"));
        VBox raiz = null;

		try {
			raiz = (VBox) loader.load();

			scene = new Scene(raiz);
			scene.getStylesheets().add(getClass().getResource("../../css/styles.css").toExternalForm());

			stage.setScene(scene);
			stage.setFullScreen(true);
			stage.centerOnScreen();
			stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
			stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	private void adicionarEventoFinalizarAplicacao() {
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Platform.exit();
				System.exit(0);
			}
		});
	}

}
