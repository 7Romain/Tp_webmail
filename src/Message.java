import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    public Message(String to, String sujet, String contenu) {
        this.from = MailMgr.monEmail;
        this.to = to;
        this.sujet = sujet;
        this.contenu = contenu;
        date = LocalDateTime.now();

    }

    private String from;
    private String to;
    private String sujet;
    private String contenu;
    private LocalDateTime date;

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy à HH.mm.ss");

        String formatedDateTime = date.format(formatter);
        return formatedDateTime;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getSujet() {
        return sujet;
    }

    public String getContenu() {
        return contenu;
    }

    @Override
    public String toString() {
        return "L'email envoyé par " + from + " à " + to + " à pour objet : « " + sujet
                + " » et contient le message suivant : « " + contenu + " »";

    }

}
