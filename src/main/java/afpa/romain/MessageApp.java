package afpa.romain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageApp {
    private String from;
    private String to;
    private String sujet;
    private String contenu;
    private LocalDateTime date;

    public MessageApp(String to, String sujet, String contenu) {
        this.from = "777.romain@gmail.com";
        this.to = to;
        this.sujet = sujet;
        this.contenu = contenu;
        date = LocalDateTime.now();

    }

    /**
     * @return String
     */
    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy à HH.mm.ss");

        String formatedDateTime = date.format(formatter);
        return formatedDateTime;
    }

    /**
     * @return String
     */
    public String getFrom() {
        return from;
    }

    /**
     * @return String
     */
    public String getTo() {
        return to;
    }

    /**
     * @return String
     */
    public String getSujet() {
        return sujet;
    }

    /**
     * @return String
     */
    public String getContenu() {
        return contenu;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "L'email envoyé par " + from + " à " + to + " à pour objet : « " + sujet
                + " » et contient le message suivant : « " + contenu + " »";

    }

}
