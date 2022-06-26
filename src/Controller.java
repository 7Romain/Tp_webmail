import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller implements Initializable {

    @FXML
    private Button btnEnvoyer;

    @FXML
    void infoBtnNouveau(MouseEvent event) {
        lblStatus.setText("Créer un nouveau message.");

    }

    @FXML
    void entrerRepertoire(ActionEvent event) throws IOException {

        newWindow();
    }

    private void newWindow() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("frame2.fxml"));

            Scene scene2 = new Scene(fxmlLoader.load());
            Stage stage2 = new Stage();
            stage2.setTitle("Ajouter dans le répertoire");
            stage2.setScene(scene2);
            stage2.initModality(Modality.WINDOW_MODAL);
            stage2.initOwner(stage);

            stage2.show();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
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
    private MenuItem menuAjoutContact;

    @FXML
    void actionEnvoyer(ActionEvent event) throws Exception {
        String to1 = getEmail(cbMails.getValue());
        String sujet11 = fieldSujet.getText();
        String contenu1 = textArea.getText();
        Message message = new Message(to1, sujet11, contenu1);

        MailSender mailSender = new MailSender(to1, sujet11, contenu1);

        mailSender.goMail();

        sauvegarderMail(message);

        textArea.setText("");
        fieldSujet.setText("");
        cbMails.setValue("");

    }

    @FXML
    private Label lblStatus;

    @FXML
    private Button btnNouveau;

    @FXML
    private Label lblErreur;

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
    void actionMajList(MouseEvent event) {
        actualisationCbMailsBox();

    }

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
    private MenuBar menuBar;

    @FXML
    void actionToucheEnter(KeyEvent event) {

        switch (event.getCode()) {
            case ENTER:
                newWindow();

        }
        // if ((event.getCode().equals(KeyCode.ENTER)) &&
        // (!(cbMails.getValue().isBlank()))) {
        // newWindow();
        // }

    }

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

                stage.close();
            }
        } else {

            stage = (Stage) mainFrame.getScene().getWindow();

            stage.close();
        }
    }

    @FXML
    private MenuItem btnAbout;

    @FXML
    void actionOuvrir(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File defaultDir = new File("C:/Users/Oziri/Documents/CDA/ProjetsJava/Tp_fx_webmail");
        fileChooser.setInitialDirectory(defaultDir);
        Stage direct = (Stage) mainFrame.getScene().getWindow();
        File file = fileChooser.showOpenDialog(direct);
        textArea.setText(lireFichier(file));

    }

    @FXML
    private MenuItem menuEnvoyer;

    @FXML
    private MenuItem menuParam;

    @FXML
    private TextField fieldSujet;

    @FXML
    void ouvrirAbout(ActionEvent event) throws MalformedURLException {

        String message1 = String.format(
                "Envoi de mails à un destinataire   %n%n(c) 7Romain 2022  %n%nVersion 1.0 %n%nicons8.com",
                null);

        ImageIcon icon = new ImageIcon(
                "/src/mail3.png");

        JOptionPane.showMessageDialog(null, message1, "À propos de Web Mail", JOptionPane.INFORMATION_MESSAGE, icon);
    }

    Stage stage;

    public void logout(ActionEvent event) {
        stage = (Stage) mainFrame.getScene().getWindow();
        System.out.println("Quitter");
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

        actualisationCbMailsBox();

        lblErreur.setVisible(false);
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
        menuAjoutContact.setMnemonicParsing(true);

        textArea.textProperty().addListener(e -> upDateTextStatus());
        fieldSujet.textProperty().addListener(e -> upDateFieldStatus());
        cbMails.valueProperty().addListener(e -> upDateComboBoxStatus());
        cbMails.getEditor().textProperty().addListener(e -> upDateComboBoxTextStatus());
        // cbMails.onShowingProperty().addListener(e -> actualisationCbMailsBox());

    }

    private void actualisationCbMailsBox() {
        FileReader fileReader;
        fileReader = null;
        try {
            fileReader = new FileReader(
                    "src/adressesMails.csv");
        } catch (FileNotFoundException e) {
            lblErreur.setText("fichier introuvable");
            lblErreur.setVisible(true);
        }

        lineNumberReader = new LineNumberReader(fileReader);

        String ligneLue = null;
        cbMails.getItems().clear();
        try {
            while ((ligneLue = lineNumberReader.readLine()) != null) {
                String ligneLueNet = ligneLue.replaceAll(String.valueOf((char) 44), "  :  ");

                cbMails.getItems().add(ligneLueNet);
                System.out.println("actualiser");

            }
        } catch (IOException e) {
            lblErreur.setText("fichier introuvable");
            lblErreur.setVisible(true);
        }
    }

    public void montrerBtnEnvoyer() {
        if (champsRemplis()) {
            btnEnvoyer.setDisable(false);
            menuEnvoyer.setDisable(false);

        } else {
            btnEnvoyer.setDisable(true);
            menuEnvoyer.setDisable(true);

        }

    }

    public boolean champsRemplis() {
        return (Laurent.isAreaTextFull() && Laurent.isFieldTextFull()
                && Laurent.isComboBoxTextStatusFull());

    }

    @FXML
    void actionSaveMail(KeyEvent event) {
        Laurent.setTaxi(cbMails.getValue());

    }

    public void upDateComboBoxTextStatus() {

        // actualisationCbMailsBox();
        Laurent.setComboBoxTextStatusFull(!(cbMails.getEditor().getText().isBlank()));
        montrerBtnEnvoyer();

    }

    public void upDateFieldStatus() {
        Laurent.setFieldTextFull(!(fieldSujet.getText().isBlank()));
        montrerBtnEnvoyer();
    }

    public void upDateComboBoxStatus() {

        Laurent.setComboBoxFull(!(cbMails.getValue().isBlank()));
        // actualisationCbMailsBox();
        montrerBtnEnvoyer();
    }

    public void upDateTextStatus() {

        Laurent.setAreaTextFull(!(textArea.getText().isBlank()));
        montrerBtnEnvoyer();

    }

    public String getEmail(String texte) {

        try {
            Matcher m = Pattern.compile(("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+")).matcher(texte);

            while (m.find()) {
                return m.group();
            }
            return m.group();
        } catch (Exception e) {
            lblErreur.setText("Adresse email invalide !");
            lblErreur.setVisible(true);
            return "Mail invalide : " + texte;

        }
    }

    public void sauvegarderMail(Message message) {
        Fichier fichier = new Fichier(message.getTo(), message.getSujet(), message.getDate());
        fichier.setContenu(message.getContenu());

    }

    public String lireFichier(File file) {
        FileReader fileReader;
        fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            lblErreur.setText("fichier introuvable");
            lblErreur.setVisible(true);
        }

        lineNumberReader = new LineNumberReader(fileReader);

        String totale = "";
        String ligneLue = null;
        try {
            while ((ligneLue = lineNumberReader.readLine()) != null) {
                totale = totale + ligneLue + "\n";

            }
        } catch (IOException e) {
            lblErreur.setText("fichier introuvable");
            lblErreur.setVisible(true);
        }

        return totale;

    }
}
