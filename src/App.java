import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    void exitApp(Stage stage) {

        if (!(Laurent.isAreaTextFull())) {
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

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Image premiereIcon = new Image("C:/Users/Oziri/Documents/CDA/Projets Java/Tp_fx_webmail/src/mail3.png");
        stage.getIcons().add(premiereIcon);
        stage.setTitle("Web Mail Fx");
        stage.show();
        stage.setMinHeight(250);
        stage.setMinWidth(600);
        stage.setOnCloseRequest(event -> {
            event.consume();
            exitApp(stage);
        });
        scene.getStylesheets().add(getClass().getResource("Style2.css").toExternalForm());

    }

}
