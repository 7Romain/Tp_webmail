package afpa.romain;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;

public class Fichier {
    String nom;
    String sujet;
    String date;

    public Fichier(String nom, String sujet, String date) {
        this.nom = nom;
        this.sujet = sujet;
        this.date = date;

    }

    /**
     * @param chaine
     */
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

    /**
     * @param fichierTxt
     * @return String
     * @throws IOException
     */
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

}
