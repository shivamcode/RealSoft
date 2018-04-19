package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Controller {

    public JFXButton login;
    public JFXTextField username;
    public JFXPasswordField password;
    public PreparedStatement pst=null;
    public ResultSet rs=null;
    public Connection conn;

    public void actionClicked(javafx.event.ActionEvent event)throws IOException
    {
        JFXButton be=(JFXButton) event.getSource();
        if(be==login)
        {

            conn=SqlConnection.DbConnector();
            if(username.getText().isEmpty() && password.getText().isEmpty()) //username not entered
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Failed");
                alert.setHeaderText("Enter Username and Password");
                alert.setContentText("Log In Failed");
                alert.showAndWait();
                return;
            }
            if(password.getText().isEmpty())
            {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Failed");
                    alert.setHeaderText("Enter Password");
                    alert.setContentText("Log In Failed");
                    alert.showAndWait();
                    return;
            }
            if (username.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Failed");
                alert.setHeaderText("Enter Username");
                alert.setContentText("Log In Failed");
                alert.showAndWait();
                return;
            }
            if(conn==null)
            {
                System.out.println("DataBase Not Connected");
            }
            try
            {
                String query = "select * from UserDatabase where Username=? and Password=?";
                pst=conn.prepareStatement(query);
                pst.setString(1,username.getText());
                pst.setString(2,password.getText());
                rs=pst.executeQuery();
                if (rs.next())//mainframe window will open
                {
                    Parent postlog = FXMLLoader.load(getClass().getResource("mainframe.fxml"));
                    Scene postloginScene = new Scene(postlog);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setTitle("RealSoft");
                   // window.getIcons().add(new Image("https://image.ibb.co/nQMiEn/Realsoft2.png"));
                    window.setScene(postloginScene);
                    window.show();
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Failed");
                    alert.setHeaderText("Wrong Username Or Password");
                    alert.setContentText("Log In Failed");
                    alert.showAndWait();

                }
                pst.close();
                conn.close();
            }catch (Exception e1){System.out.println(e1);}


        }
    }
}
