package pis.hue1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



//Jede Javafx-Anwendung implementiert immer die Anwendungsschnittstelle

/**
 *
 * @author Ngnindjeu Sonfack Doriane Lovline
 * @version 14.0.1
 */
public class CodecGui  extends Application {
    private Codec c = new Caesar();
    private Codec w = new Wuerfel("THM");

    /**
     *
     * @param stage  represents a window in a JavaFX
     * @throws Exception bei ungeeignette schluessel die meldung wird geworfen
     */
    @Override
    public void start(Stage stage) throws Exception {

        Codec newcaesar ;
        Codec newwuerfel;
        Label lb = new Label("KlarText Eingeben:");
        TextArea klartext = new TextArea();//wenn man mehr Zeile schreiben will
        klartext.setPrefSize(200, 50); //Weight and height der Textarea

        Label lb2 = new Label("Loesungswort 1:");
        TextField lw1 = new TextField(); //Nur eine Zeile
        lw1.setPrefSize(100, 50);

        Label lb3 = new Label("Loesungswort 2:");
        TextField lw2 = new TextField();//Nur eine zeile
        lw2.setPrefSize(100, 50);

        Label lb4 = new Label("Geheimtext Eingeben:");
        TextArea geheimtext = new TextArea();// wenn man mehr zeile schreiben will
        geheimtext.setPrefSize(200, 50);//Weight and height der Textarea

        //Für die Gruppierung des Buttuns
        ToggleGroup group = new ToggleGroup();
        RadioButton caesar =new  RadioButton("caesar");
        RadioButton wuerfel =new  RadioButton("wuerfel");
        caesar.setToggleGroup(group);//fügt caesar in Group
        wuerfel.setToggleGroup(group);//fügt wuerfel in Group

        HBox hb1= new HBox(caesar, wuerfel);//Caesar und Wuerfel auf einer horizontalen Achse ausrichten
        hb1.setSpacing(10);//Abstand von 10 zwischen den 2 Tasten
        Button enc = new Button("Encode");
        Button dec = new Button("Decode");
        /* for encoding Action**/
        /**
         * Anonyme Klasse
         * Verantwortlich fuer das Button Encode
         * new EventHandler<ActionEvent>: Erzeugt ein neues Object von Typ ActionHandler in Generics ActionEvent,
         */
        enc.setOnAction(new EventHandler<ActionEvent>() {
            /**
             *
             * @param actionEvent Ein Ereignis, das eine Aktion darstellt
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                /*Wenn die RadioButton schon ausgewahlt ist*/
                if(caesar.isSelected()){
                   c.setzeLosung(lw1.getText());
                   geheimtext.setText(c.kodiere(klartext.getText()));

                }

                else if(wuerfel.isSelected()){
                    w.setzeLosung(lw1.getText());
                    geheimtext.setText(w.kodiere(klartext.getText()));
                    w.setzeLosung(lw2.getText());
                    geheimtext.setText(w.kodiere(geheimtext.getText()));

                }
                else;
            }
        });


        /* for decoding Action**/
        dec.setOnAction(new EventHandler<ActionEvent>() {
            /**
             *
             * @param actionEvent Ein Ereignis, das eine Aktion darstellt
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                if(caesar.isSelected()){
                c.setzeLosung(lw1.getText());
                klartext.setText(c.dekodiere(geheimtext.getText()));
                }
                else if(wuerfel.isSelected()){

                    w.setzeLosung(lw2.getText());
                   geheimtext.setText(w.dekodiere(geheimtext.getText()));
                   w.setzeLosung(lw1.getText());
                  klartext.setText(w.dekodiere(geheimtext.getText()));

                }
                else;
            }
        });

        HBox hb2= new HBox(enc, dec);
        hb2.setSpacing(10);
      //  VBox root = new VBox(hb2);
        /**
         * Ich benutze ein VBox als Root, um alle Elemente vertical zuzuordnen.
         */
      VBox root = new VBox(lb, klartext, lb2, lw1, lb3, lw2, lb4, geheimtext, hb1, hb2);//Es kann beliebig viele Panel in VBox-konstructor eingefügt werden
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);//
        stage.setTitle("CodecGui");// gibt der title der Guicode
        stage.show();//fenster aus


    }

    /**
     * mit Launch wird die Gui gestartet
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
