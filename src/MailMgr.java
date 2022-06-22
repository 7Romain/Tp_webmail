public class MailMgr {

    private String server;
    public static String monEmail = "moi@gmail.com";

    public MailMgr() {

    }

    public void envoyerMsg(Message message) {
        System.out.println("message envoyé !");
        System.out.println(message);

    }

}
