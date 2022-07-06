/**
 * 05/07/2022.
 *
 * @author romain.Laurent Lamiral
 */module Tp.webmail {

    requires transitive javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.desktop;
    requires javax.mail.api;

    exports afpa.romain;

    opens afpa.romain;
}