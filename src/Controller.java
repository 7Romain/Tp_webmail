import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Controller implements Initializable {

    @FXML
    private Button btnEnvoyer;

    @FXML
    void infoBtnNouveau(MouseEvent event) {
        lblStatus.setText("Créer un nouveau message.");

    }

    @FXML
    private VBox mainFrame;

    @FXML
    void infoBtnOuvrir(MouseEvent event) {
        lblStatus.setText("Ouvre un message qui a déjà été envoyé");

    }

    @FXML
    void infoBtnEnvoyer(MouseEvent event) {
        lblStatus.setText("Envoyer l'email.");
    }

    @FXML
    private Label lblStatus;

    @FXML
    private Button btnNouveau;

    @FXML
    private Button btnOuvrir;

    @FXML
    void actionNouveau(ActionEvent event) {
        if (!(textArea.getText().equals(""))) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Recommencer ?");
            alert.setHeaderText("Un message est en cours de rédaction !");
            alert.setContentText(
                    "Êtes-vous sur de vouloir commencer un nouveau message alors que celui en cours n'a pas été envoyé ?");

            if (alert.showAndWait().get() == ButtonType.OK) {

                textArea.setText("");
                fieldSujet.setText("");
                cbMails.setValue("");
            }
        }

    }

    @FXML
    private MenuItem menuNouveau;

    @FXML
    private MenuItem menuOuvrir;

    @FXML
    void clearStatus(MouseEvent event) {

        lblStatus.setText("");
    }

    @FXML
    private TextArea textArea;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Font x3;

    @FXML
    private MenuItem btnQuitter;

    @FXML
    private ComboBox<String> cbMails;
    LineNumberReader lineNumberReader = null;

    @FXML
    public void exitApp(ActionEvent event) {
        if (!(textArea.getText().equals(""))) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Quitter ?");
            alert.setHeaderText("Un message est en cours de rédaction !");
            alert.setContentText(
                    "Êtes-vous sur de vouloir quitter l'application alors que le message en cours n'a pas été envoyé ?");

            if (alert.showAndWait().get() == ButtonType.OK) {

                stage = (Stage) mainFrame.getScene().getWindow();
                System.out.println("succes");
                stage.close();
            }
        } else {
            // Platform.exit();
            stage = (Stage) mainFrame.getScene().getWindow();
            System.out.println("succes");
            stage.close();
        }
    }

    @FXML
    private MenuItem btnAbout;

    @FXML
    void enableEnvoyer(KeyEvent event) {

        if (!(textArea.getText().equals("")) && !(fieldSujet.getText().equals(""))
                && !(cbMails.getSelectionModel().getSelectedItem().equals(""))) {
            btnEnvoyer.setDisable(false);
            menuEnvoyer.setDisable(false);
            System.out.println("btn montré");
        } else {
            btnEnvoyer.setDisable(true);
            menuEnvoyer.setDisable(true);
            System.out.println("btn caché");
        }

    }

    @FXML
    void actionOuvrir(ActionEvent event) {

        System.out.println(cbMails.getValue());
        System.out.println(cbMails.getSelectionModel().getSelectedItem());

    }

    @FXML
    private MenuItem menuEnvoyer;

    @FXML
    private MenuItem menuParam;

    @FXML
    private TextField fieldSujet;

    @FXML
    void ouvrirAbout(ActionEvent event) throws MalformedURLException {

        String message1 = String.format("Envoi de mails à un destinataire   %n%n(c) 7Romain 2022  %n%nVersion 1.0",
                null);

        ImageIcon icon = new ImageIcon(
                "C:/Users/Oziri/Documents/CDA/Projets Java/Tp_fx_webmail/src/mail3.png");

        JOptionPane.showMessageDialog(null, message1, "À propos de Web Mail", JOptionPane.INFORMATION_MESSAGE, icon);
    }

    Stage stage;

    public void logout(ActionEvent event) {
        stage = (Stage) mainFrame.getScene().getWindow();
        System.out.println("succes");
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image imageNouveau = new Image("nouveau.png");
        ImageView btnNouveauI = new ImageView();
        btnNouveauI.setImage(imageNouveau);
        btnNouveauI.setPreserveRatio(true);
        btnNouveauI.setSmooth(true);
        btnNouveau.setGraphic(btnNouveauI);

        Image imageOuvrir = new Image("ouvrir.png");
        ImageView btnOuvrirI = new ImageView();
        btnOuvrirI.setImage(imageOuvrir);
        btnOuvrirI.setPreserveRatio(true);
        btnOuvrirI.setSmooth(true);
        btnOuvrir.setGraphic(btnOuvrirI);

        Image imageEnvoyer = new Image("envoyer.png");
        ImageView btnEnvoyerI = new ImageView();
        btnEnvoyerI.setImage(imageEnvoyer);
        btnEnvoyerI.setPreserveRatio(true);
        btnEnvoyerI.setSmooth(true);
        btnEnvoyer.setGraphic(btnEnvoyerI);

        FileReader fileReader;
        fileReader = null;
        try {
            fileReader = new FileReader(
                    "C:/Users/Oziri/Documents/CDA/Projets Java/Tp_fx_webmail/src/adressesMails.csv");
        } catch (FileNotFoundException e) {
            System.err.println("fichier introuvable");
        }

        lineNumberReader = new LineNumberReader(fileReader);
        ArrayList<String> liste = new ArrayList<String>();

        String ligneLue = null;
        try {
            while ((ligneLue = lineNumberReader.readLine()) != null) {
                String ligneLueNet = ligneLue.replaceAll(String.valueOf((char) 44), "  :  ");
                liste.add(ligneLueNet);

            }
        } catch (IOException e) {
            System.err.println("fichier introuvable");
        }
        cbMails.getItems().addAll(liste);

        btnEnvoyer.setDisable(true);
        menuEnvoyer.setDisable(true);
        cbMails.setValue("");
        textArea.setText("");
        fieldSujet.setText("");

        menuEnvoyer.setAccelerator(KeyCombination.keyCombination("F2"));
        menuEnvoyer.setMnemonicParsing(true);
        menuOuvrir.setMnemonicParsing(true);
        btnQuitter.setMnemonicParsing(true);
        menuNouveau.setMnemonicParsing(true);
        menuParam.setMnemonicParsing(true);
        btnAbout.setMnemonicParsing(true);

    }

    public String getAreaText() {
        return textArea.getText();
    }

}
