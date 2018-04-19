package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class ExpencesEntry
{

    public JFXTextField itemname;
    public JFXTextField place;
    public JFXTextField price;
    public JFXButton expensesbutton;
    public PreparedStatement pst=null;
    public int rs;
    public Connection conn;
    public JFXDatePicker datepick;
    public Label statlabel;


    public void initialize()
    {
        conn=SqlConnection.DbConnector();
    }

    public void expsubmitAction(ActionEvent event)
    {
        JFXButton bbe=(JFXButton)event.getSource();
        if(bbe==expensesbutton)
        {
            if (itemname.getText().isEmpty())
            {
                if (place.getText().isEmpty())
                {
                    if (price.getText().isEmpty())
                    {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Failed");
                        alert.setHeaderText("All Fields Are Empty");
                        alert.setContentText("Entry Required");
                        alert.showAndWait();
                        return;

                    }
                }
            }
        }

            try
            {

                String query = "INSERT INTO Expenses (Item,Place,Price,Date) VALUES(?,?,?,?)";
                pst = conn.prepareStatement(query);
                pst.setString(1,itemname.getText());
                pst.setString(2,place.getText());
                pst.setString(3,price.getText());
                pst.setString(4,datepick.getValue().toString());
                rs=pst.executeUpdate();
                if (rs>0)
                {
                    statlabel.setText("Record Added Successfully ");
                    setNull();
                }
                else
                {
                    statlabel.setText("Record Not Added Nicely");
                }
                pst.close();
                conn.close();
            }catch (Exception e){System.out.println(e);}
        }
        public void setNull()
        {
            itemname.setText("");
            price.setText("");
            place.setText("");
        }
    }
