package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTimePicker;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Batch
{
    public JFXRadioButton cradio;
    public JFXRadioButton cppradio;
    public JFXRadioButton dsradio;
    public JFXRadioButton javaradio;
    public JFXTimePicker timepicker;
    public JFXButton batchsubmit;
    public PreparedStatement pst=null;
    public int rs;
    public Connection conn;
    public Label statLabel;
    String Language="";
    ToggleGroup rgroup=new ToggleGroup();

    public void initialize()
    {
        conn=SqlConnection.DbConnector();
        cradio.setToggleGroup(rgroup);
        cppradio.setToggleGroup(rgroup);
        dsradio.setToggleGroup(rgroup);
        javaradio.setToggleGroup(rgroup);
        cradio.setSelected(true);
        cppradio.setSelected(false);
        dsradio.setSelected(false);
        javaradio.setSelected(false);


    }

    public void batchSubmitAction(ActionEvent event)
    {
        JFXButton bbe=(JFXButton)event.getSource();
        if(bbe==batchsubmit)
        {
            try
            {
                timepicker.getValue().toString().isEmpty();
            }catch (Exception eee){
                if (!eee.toString().isEmpty())
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Failed");
                    alert.setHeaderText("All Field Are Compulsory");
                    alert.setContentText("Data Entry Failed");
                    alert.showAndWait();
                }
            }
            try
            {

                String query = "INSERT INTO BatchData (Language,Time) VALUES(?,?)";
                pst = conn.prepareStatement(query);
                pst.setString(1,getBatch());
                pst.setString(2,timepicker.getValue().toString());
                rs=pst.executeUpdate();
                if (rs>0)
                {
                    statLabel.setText("Record Added Successfully ");
                }
                else
                {
                    statLabel.setText("Record Not Added Nicely");
                }
                pst.close();
                conn.close();
            }catch (Exception e){System.out.println(e);}

        }

    }
    public String getBatch()
    {
        if (cradio.isSelected())
        {
           Language+=cradio.getText();
        }
        if (cppradio.isSelected())
        {
            Language+=cppradio.getText();
        }
        if (dsradio.isSelected())
        {
            Language+=dsradio.getText();
        }
        if (javaradio.isSelected())
        {
            Language+=javaradio.getText();
        }
        return Language;

    }

}
