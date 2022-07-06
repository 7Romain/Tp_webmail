package afpa.romain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller implements Initializable {

    LineNumberReader lineNumberReader = null;
    Stage stage;
    @FXML
    private Button btnEnvoyer;
    @FXML
    private VBox mainFrame;
    @FXML
    private MenuItem menuAjoutContact;
    @FXML
    private Label lblStatus;
    @FXML
    private Button btnNouveau;
    @FXML
    private Label lblErreur;
    @FXML
    private Button btnOuvrir;
    @FXML
    private MenuItem menuNouveau;
    @FXML
    private MenuItem menuOuvrir;
    @FXML
    private TextArea textArea;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Font x3;
    @FXML
    private MenuItem btnQuitter;
    @FXML
    private ComboBox<String> cbMails;
    @FXML
    private MenuItem btnAbout;
    @FXML
    private MenuItem menuEnvoyer;
    @FXML
    private MenuItem menuParam;
    @FXML
    private TextField fieldSujet;

    /**
     * @param event
     */
    @FXML
    void infoBtnNouveau(MouseEvent event) {
        lblStatus.setText("Créer un nouveau message.");

    }

    /**
     * @param event
     * @throws IOException
     */
    @FXML
    void entrerRepertoire(ActionEvent event) throws IOException {

        newWindow();
    }

    /**
     * Fenêtre d'ajout de contact
     */
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

    /**
     * @param event
     */
    @FXML
    void infoBtnOuvrir(MouseEvent event) {
        lblStatus.setText("Ouvre un message qui a déjà été envoyé");

    }

    /**
     * @param event
     */
    @FXML
    void infoBtnEnvoyer(MouseEvent event) {
        lblStatus.setText("Envoyer l'email.");
    }

    /**
     * @param event
     * @throws Exception Envoie l'email et le sauvegarde
     */
    @FXML
    void actionEnvoyer(ActionEvent event) throws Exception {
        String to1 = getEmail(cbMails.getValue());
        String sujet11 = fieldSujet.getText();
        String contenu1 = textArea.getText();
        MessageApp message = new MessageApp(to1, sujet11, contenu1);

        MailSender mailSender = new MailSender(message);

        mailSender.goMail();

        sauvegarderMail(message);

        textArea.setText("");
        fieldSujet.setText("");
        cbMails.setValue("");

    }

    /**
     * @param event Efface les zones de textes
     */
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

    /**
     * @param event actualise la list de la choiceBox
     */
    @FXML
    void actionMajList(MouseEvent event) {
        actualisationCbMailsBox();

    }

    /**
     * @param event
     */
    @FXML
    void clearStatus(MouseEvent event) {

        lblStatus.setText("");
    }

    /**
     * @param event
     */
    @FXML
    void actionToucheEnter(KeyEvent event) {

        switch (event.getCode()) {
            case ENTER:
                newWindow();

        }

    }

    /**
     * @param event Quitter appli via menu
     */
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

    /**
     * @param event Ouvrir un email déja envoyé
     */
    @FXML
    void actionOuvrir(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File defaultDir = new File("C:/Users/Oziri/Documents/CDA/ProjetsJava/Tp_fx_webmail");
        fileChooser.setInitialDirectory(defaultDir);
        Stage direct = (Stage) mainFrame.getScene().getWindow();
        File file = fileChooser.showOpenDialog(direct);
        textArea.setText(lireFichier(file));

    }

    /**
     * @param event
     * @throws MalformedURLException Boite de dialogue "à propos"
     */
    @FXML
    void ouvrirAbout(ActionEvent event) throws MalformedURLException {

        String message1 = String.format(
                "Envoi de mails à un destinataire   %n%n(c) 7Romain 2022  %n%nVersion 1.0 %n%nicons8.com",
                null);

        ImageIcon icon = new ImageIcon(
                "/src/mail3.png");

        JOptionPane.showMessageDialog(null, message1, "À propos de Web Mail", JOptionPane.INFORMATION_MESSAGE, icon);
    }

    /**
     * @param event
     */
    public void logout(ActionEvent event) {
        stage = (Stage) mainFrame.getScene().getWindow();
        System.out.println("Quitter");
        stage.close();
    }

    /**
     * @param location
     * @param resources
     */
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

    }

    /**
     * actualiser la liste de la combo box
     */
    private void actualisationCbMailsBox() {
        FileReader fileReader;
        fileReader = null;
        try {
            fileReader = new FileReader(
                    getClass().getResource("/adressesMails.csv").getPath());
        } catch (FileNotFoundException e) {
            lblErreur.setText("fichier introuvable");

            ;
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

    /**
     *
     */
    public void montrerBtnEnvoyer() { /* active le bouton si les champs sont remplis */
        if (champsRemplis()) {
            btnEnvoyer.setDisable(false);
            menuEnvoyer.setDisable(false);

        } else {
            btnEnvoyer.setDisable(true);
            menuEnvoyer.setDisable(true);

        }

    }

    /**
     * @return boolean
     * Test si les champs sont vides
     */
    public boolean champsRemplis() {
        return (Laurent.isAreaTextFull() && Laurent.isFieldTextFull()
                && Laurent.isComboBoxTextStatusFull());

    }

    /**
     * @param event sauvegarde l'email en cours de frappe
     */
    @FXML
    void actionSaveMail(KeyEvent event) {
        Laurent.setTaxi(cbMails.getValue());

    }

    /**
     * test si le champ de texte de la combo box contient quelque chose
     */
    public void upDateComboBoxTextStatus() {

        Laurent.setComboBoxTextStatusFull(!(cbMails.getEditor().getText().isBlank()));
        montrerBtnEnvoyer();

    }

    /**
     * test si le champ de texte contient quelque chose
     */
    public void upDateFieldStatus() {
        Laurent.setFieldTextFull(!(fieldSujet.getText().isBlank()));
        montrerBtnEnvoyer();
    }

    /**
     * test si on a selectionné un item de la combobox
     */
    public void upDateComboBoxStatus() {

        Laurent.setComboBoxFull(!(cbMails.getValue().isBlank()));

        montrerBtnEnvoyer();
    }

    /**
     * test si la zone de texte contient quelque chose
     */
    public void upDateTextStatus() {

        Laurent.setAreaTextFull(!(textArea.getText().isBlank()));
        montrerBtnEnvoyer();

    }

    /**
     * @param texte
     * @return String
     * trouve l'email parmis le nom, le prénom, le mail dans la liste de
     * contact
     */
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

    /**
     * @param message garde une copie du mail
     */
    public void sauvegarderMail(MessageApp message) {
        Fichier fichier = new Fichier(message.getTo(), message.getSujet(), message.getDate());
        fichier.setContenu(message.getContenu());

    }

    /**
     * @param file
     * @return String
     * affiche le mail déja sauvegardé dans la zone de texte
     */
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
