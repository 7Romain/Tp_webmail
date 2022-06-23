import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Vector;

public class Fichier {
    String nom;
    String sujet;
    String date;

    public Fichier(String nom, String sujet, String date) {
        this.nom = nom;
        this.sujet = sujet;
        this.date = date;

    }

    public void setContenu(String chaine) {

        String fileTitle = this.date + " à " + this.nom + ".txt";
        try {
            FileWriter myWriter = new FileWriter(fileTitle);
            myWriter.write(chaine);
            myWriter.close();
            System.out.println("message écrit");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("message sauvegarder");

    }

    public String getContenu(String fichierTxt) throws IOException {
        LineNumberReader lineNumberReader = null;

        FileReader fileReader = new FileReader(fichierTxt);

        lineNumberReader = new LineNumberReader(fileReader);
        String ligneTotal = "";
        String ligneLue = null;
        while ((ligneLue = lineNumberReader.readLine()) != null) {

            ligneTotal += ligneLue;

        }
        try {
            lineNumberReader.close();
        } catch (IOException ioe) {
            System.err.println("Impossible de fermer le fichier  ");

        } catch (NullPointerException npe) {
            System.err.println("Impossible d'ouvrir le fichier  ");

        }

        return ligneTotal;
    }

    public Vector getlignes() {
        Vector<String> vecFichier = new Vector<String>();

        return vecFichier;

    }

}
