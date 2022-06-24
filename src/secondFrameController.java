import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class secondFrameController {

    @FXML
    private Button btnAnnuler;

    @FXML
    private Button btnOk;

    @FXML
    private TextField fieldMail;

    @FXML
    private TextField fieldNom;

    @FXML
    private TextField fieldPrenom;

    @FXML
    private VBox secondFrame;

    @FXML
    void actionBtnAnnuler(ActionEvent event) {
        Stage secondStage = (Stage) secondFrame.getScene().getWindow();
        secondStage.close();

    }

    @FXML
    void actionbtnOk(ActionEvent event) {
        String prenom = fieldPrenom.getText();
        String nom = fieldNom.getText();
        String mail = fieldMail.getText();
        if (isEmail(mail)) {
            ajoutCsv(prenom, nom, mail);
            Stage secondStage = (Stage) secondFrame.getScene().getWindow();
            secondStage.close();
        }

    }

    public void ajoutCsv(String prenom, String nom, String mail) {
        try {

            String content = String.format("%s %s, %s", prenom, nom, mail);

            File file = new File("src/adressesMails.csv");

            // créer le fichier s'il n'existe pas
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.newLine();
            bw.close();

            System.out.println("Modification terminée!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean isEmail(String texte) {

        try {
            Matcher m = Pattern.compile(("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+")).matcher(texte);

            while (m.find()) {
                return true;
            }

        } catch (Exception e) {
            fieldMail.setPromptText("Adresse email invalide !");

        }
        return false;

    }
}
