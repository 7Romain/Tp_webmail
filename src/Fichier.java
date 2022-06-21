import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Vector;

public class Fichier {
    String nom;

    public Fichier(String nom) {
        this.nom = nom;
    }

    public void setContenu(String chaine) {

        String fileTitle = this.nom + ".txt";
        try {
            FileWriter myWriter = new FileWriter(fileTitle);
            myWriter.write(chaine);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public String getContenu(String fichierTxt) throws FileNotFoundException {
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
