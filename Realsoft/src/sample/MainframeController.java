package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.xml.soap.Node;
import java.io.IOException;

public class MainframeController {

    public JFXButton mdbutton;
    public JFXButton logout;
    public JFXButton utibutton;
    public JFXButton repobutton;
    public JFXButton drbutton;
    public AnchorPane mainframepane;
    public AnchorPane sidepane;
    public AnchorPane masterdatapane;
    public AnchorPane utilitypane;
    public AnchorPane reportspane;
    public AnchorPane dailyroutinepane;
    public AnchorPane regipane;
    public JFXButton regibutton;
    public AnchorPane collegedatapane;
    public JFXButton collegedatabutton;
    public JFXButton expentrybutton;
    public JFXButton batch;
    public JFXButton studentstate;
    public JFXButton receiptbutton;
    public JFXButton daycolbutton;
    public JFXButton batchcollectionbutton;


    public void initialize()
    {
        setFalse(masterdatapane);

    }
    public void setFalse(AnchorPane pane)
    {
        reportspane.setVisible(false);
        masterdatapane.setVisible(false);
        dailyroutinepane.setVisible(false);
        utilitypane.setVisible(false);
        pane.setVisible(true);

    }
    public void actionButton(ActionEvent event) throws IOException //daily routine pane actions
    {
        JFXButton be=(JFXButton)event.getSource();
        if(be==logout)
        {
            Stage primaryStage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("Login");
            primaryStage.setScene(new Scene(root, 571, 305));
            primaryStage.show();
        }

        switch (be.getText())
        {
            case "Master Data":
                setFalse(masterdatapane);
                return;

            case "Daily Routine":
                setFalse(dailyroutinepane);
                return;
            case "Reports":
                setFalse(reportspane);
                return;
            case "Utilities":
                setFalse(utilitypane);
                return;
        }

    }


    public void openForm(ActionEvent event)throws IOException
    {
        JFXButton bbe=(JFXButton)event.getSource();
        if(bbe==regibutton)
        {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("regiform.fxml"));
            primaryStage.setTitle("Registration");
            primaryStage.setScene(new Scene(root, 757, 616));
            primaryStage.show();
        }
        if (bbe==collegedatabutton)
        {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("CollegeForm.fxml"));
            primaryStage.setTitle("College Data Entry");
            primaryStage.setScene(new Scene(root, 754, 596));
            primaryStage.show();
        }
        if (bbe==expentrybutton)
        {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("ExpencesEntry.fxml"));
            primaryStage.setTitle("College Data Entry");
            primaryStage.setScene(new Scene(root, 754, 596));
            primaryStage.show();

        }
        if (bbe==batch)
        {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Batch.fxml"));
            primaryStage.setTitle("Batch Information Form");
            primaryStage.setScene(new Scene(root, 754, 596));
            primaryStage.show();

        }
        if (bbe==studentstate)
        {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("StudState.fxml"));
            primaryStage.setTitle("Add State");
            primaryStage.setScene(new Scene(root, 754, 596));
            primaryStage.show();

        }
        if(bbe ==receiptbutton)
        {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("RecieptEntry.fxml"));
            primaryStage.setTitle("Fee Receipt Form");
            primaryStage.setScene(new Scene(root, 754, 596));
            primaryStage.show();

        }
        if (bbe==daycolbutton)
        {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("DailyCollection.fxml"));
            primaryStage.setTitle("Daily Collection");
            primaryStage.setScene(new Scene(root, 754, 496));
            primaryStage.show();

        }
        if (bbe==batchcollectionbutton)
        {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("BatchwiseCollection.fxml"));
            primaryStage.setTitle("BatchWise Collection");
            primaryStage.setScene(new Scene(root, 754, 496));
            primaryStage.show();

        }
    }

}
