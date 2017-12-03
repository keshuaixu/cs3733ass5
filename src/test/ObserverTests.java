package test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import controllers.ObserverController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * Created by Cory on 4/17/17.
 * Modified by Binam on 11/22/17 (Thanks Cory for great testing framework!)
 */
@RunWith( JfxTestRunner.class )
public class ObserverTests{

    DatePicker ESTDate;
    TextField txtESTHours, txtESTMinutes, txtESTSeconds;
    Label labelJan1970EST, labelBeforeDecade, labelPST;
    ObserverController c;

    @Before
    public void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = loader.load(getClass().getResource("../boundary/observer.fxml").openStream());
            Scene scene = new Scene(root);
            scene.getStylesheets().add("../boundary/styles.css");

            c = loader.getController();

            GridPane gridPane = (GridPane) root.getChildren().get(0);

            VBox ESTVBox = (VBox) gridPane.getChildren().get(0);

            ESTDate = (DatePicker) (ESTVBox.getChildren().get(1));

            txtESTHours = (TextField) ((HBox) ESTVBox.getChildren().get(2)).getChildren().get(0);
            txtESTMinutes = (TextField) ((HBox) ESTVBox.getChildren().get(2)).getChildren().get(2);
            txtESTSeconds = (TextField) ((HBox) ESTVBox.getChildren().get(2)).getChildren().get(4);

            labelJan1970EST = ((Label) ((VBox) gridPane.getChildren().get(1)).getChildren().get(1));
            labelBeforeDecade = ((Label) ((VBox) gridPane.getChildren().get(2)).getChildren().get(1));
            labelPST = ((Label) ((VBox) gridPane.getChildren().get(3)).getChildren().get(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void initialTest1PST() {
        ESTDate.setValue(LocalDate.of(2017, 1, 1));
        txtESTHours.setText("4");
        txtESTMinutes.setText("34");
        txtESTSeconds.setText("42");

        c.updateTime();

        assertEquals("01:34:42 PST", labelPST.getText());
    }

    @Test
    public void initialTest2PST() {
        ESTDate.setValue(LocalDate.of(2017, 1, 1));
        txtESTHours.setText("23");
        txtESTMinutes.setText("23");
        txtESTSeconds.setText("00");

        c.updateTime();

        assertEquals("20:23:00 PST", labelPST.getText());
    }

    @Test
    public  void initialTest3PST() {
        ESTDate.setValue(LocalDate.of(2017, 1, 1));
        txtESTHours.setText("1");
        txtESTMinutes.setText("43");
        txtESTSeconds.setText("26");

        c.updateTime();

        assertEquals("22:43:26 PST", labelPST.getText());
    }

    @Test
    public void initialTest1BD() {
        ESTDate.setValue(LocalDate.of(2017, 1, 1));
        txtESTHours.setText("1");
        txtESTMinutes.setText("1");
        txtESTSeconds.setText("1");

        c.updateTime();

        assertEquals("1094 BD", labelBeforeDecade.getText());
    }

    @Test
    public void initialTest2BD() {
        ESTDate.setValue(LocalDate.of(2029, 11, 30));
        txtESTHours.setText("1");
        txtESTMinutes.setText("1");
        txtESTSeconds.setText("1");

        c.updateTime();

        assertEquals("31 BD", labelBeforeDecade.getText());
    }

    @Test
    public void initialTest3BD() {
        ESTDate.setValue(LocalDate.of(2018, 6, 15));
        txtESTHours.setText("1");
        txtESTMinutes.setText("1");
        txtESTSeconds.setText("1");

        c.updateTime();

        assertEquals("564 BD", labelBeforeDecade.getText());
    }

    @Test
    public void initialTest1Jan1970EST() {
        ESTDate.setValue(LocalDate.of(2017, 1, 1));
        txtESTHours.setText("0");
        txtESTMinutes.setText("0");
        txtESTSeconds.setText("0");

        c.updateTime();

        // Manually calculated Local
        assertEquals("1483246800 Jan1970EST", labelJan1970EST.getText());
    }

    @Test
    public void initialTest2Jan1970EST() {
        ESTDate.setValue(LocalDate.of(2029, 11, 30));
        txtESTHours.setText("23");
        txtESTMinutes.setText("23");
        txtESTSeconds.setText("0");

        c.updateTime();

        // Manually calculated GMT
        assertEquals("1890793380 Jan1970EST", labelJan1970EST.getText());
    }

    @Test
    public void initialTest3Jan1970EST() {
        ESTDate.setValue(LocalDate.of(2018, 6, 15));
        txtESTHours.setText("1");
        txtESTMinutes.setText("43");
        txtESTSeconds.setText("26");

        c.updateTime();

        // Automatically GMT
        assertEquals("1529041406 Jan1970EST", labelJan1970EST.getText());
    }

    @Test
    public void testOriginJan1970EST() {
        ESTDate.setValue(LocalDate.of(1970, 1, 1));
        txtESTHours.setText("0");
        txtESTMinutes.setText("0");
        txtESTSeconds.setText("0");

        c.updateTime();

        // Manually calculated Local
        assertEquals("0 Jan1970EST", labelJan1970EST.getText());
    }
}
