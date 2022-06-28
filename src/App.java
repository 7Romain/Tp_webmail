import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author 7Romain
 * @version 0.9
 */

public class App extends Application {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    /**
     * @param stage
     */
    void exitApp(Stage stage) {

        if (Laurent.isAreaTextFull()) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Quitter ?");
            alert.setHeaderText("Un message est en cours de rédaction !");
            alert.setContentText(
                    "Êtes-vous sur de vouloir quitter l'application alors que le message en cours n'a pas été envoyé ?");

            if (alert.showAndWait().get() == ButtonType.OK) {

                System.out.println("Quitter");
                System.out.println(Laurent.isAreaTextFull());
                stage.close();

            }
        } else {
            System.out.println("Quitter");
            System.out.println(Laurent.isAreaTextFull());
            stage.close();
        }
    }

    /**
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Image premiereIcon = new Image("mail3.png");
        primaryStage.getIcons().add(premiereIcon);
        primaryStage.setTitle("Web Mail Fx");
        primaryStage.show();
        primaryStage.setMinHeight(250);
        primaryStage.setMinWidth(600);
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            exitApp(primaryStage);
        });
        scene.getStylesheets().add(getClass().getResource("Style2.css").toExternalForm());

    }

}
