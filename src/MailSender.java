import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

    public MailSender(MessageApp message) {
        this.TO = message.getTo();
        this.SUBJECT = message.getSujet();
        this.BODY = message.getContenu();
    }

    // Replace sender@example.com with your "From" address.
    // This address must be verified.
    private String FROM = "777.romain@gmail.com";

    public String getFROM() {
        return FROM;
    }

    private String FROMNAME = "7Romain";

    // Replace recipient@example.com with a "To" address. If your account
    // is still in the sandbox, this address must be verified.
    private String TO;

    // Replace smtp_username with your Amazon SES SMTP user name.
    private String SMTP_USERNAME = "777.romain@gmail.com";

    // Replace smtp_password with your Amazon SES SMTP password.
    private String SMTP_PASSWORD = "xajnehkqfvlaqdol";

    // The name of the Configuration Set to use for this message.
    // If you comment out or remove this variable, you will also need to
    // comment out or remove the header below.
    private String CONFIGSET = "ConfigSet";

    // Amazon SES SMTP host name. This example uses the US West (Oregon) region.
    // See
    // https://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html#region-endpoints
    // for more information.
    private String HOST = "smtp.gmail.com";

    // The port you will connect to on the Amazon SES SMTP endpoint.
    private int PORT = 587;

    private String SUBJECT;

    private String BODY;

    public void goMail() throws Exception {

        // Create a Properties object to contain connection configuration information.
        Properties props = System.getProperties();
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        // props.put("mail.smtp.ssl.trust", "*");

        // Create a Session object to represent a mail session with the specified
        // properties.
        Session session = Session.getDefaultInstance(props);
        session.getProperties().put("mail.smtp.starttls.enable", "true");
        session.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // Create a message with the specified information.
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM, FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject(SUBJECT);
        msg.setContent(BODY, "text/html");

        // Add a configuration set header. Comment or delete the
        // next line if you are not using a configuration set
        msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);

        // Create a transport.
        Transport transport = session.getTransport();

        // Send the message.
        try {
            System.out.println("Sending...");

            // Connect to Amazon SES using the SMTP username and password you specified
            // above.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
        } catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        } finally {
            // Close and terminate the connection.
            transport.close();
        }
    }
}
