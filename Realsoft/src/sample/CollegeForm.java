package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CollegeForm
{
    public Connection conn;
    public PreparedStatement pst=null;
    public int rs;
    public JFXButton collegedatasubmit;
    public JFXTextField collegename;
    public JFXTextField collegeaddress;
    public JFXTextField collegephone;
    public Label collegefromlable;

    public void initialize()
    {
        conn=SqlConnection.DbConnector();
    }
    public void submitAction(javafx.event.ActionEvent event)
    {
        JFXButton bbe=(JFXButton)event.getSource();
        if (bbe==collegedatasubmit)
        {
            if(collegename.getText().isEmpty() || collegeaddress.getText().isEmpty() || collegephone.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Failed");
                alert.setHeaderText("All Field Are Compulsory");
                alert.setContentText("Data Entry Failed");
                alert.showAndWait();
                return;
            }
            try
            {

                String query = "INSERT INTO CollegeData (ID,Name,Address,Phone) VALUES(?,?,?,?)";
                pst=conn.prepareStatement(query);
                pst.setString(1,"3");
                pst.setString(2,collegename.getText());
                pst.setString(3,collegeaddress.getText());
                pst.setString(4,collegephone.getText());
                rs = pst.executeUpdate();
                System.out.println("After execute update");
                query="";
                if(rs>0)
                {
                    collegefromlable.setText("Record Added Successfully");
                }
                else
                {
                    collegefromlable.setText("Record Not Added Finely");
                }
                pst.close();
                conn.close();

            }catch (Exception e){}
        }
    }
}
