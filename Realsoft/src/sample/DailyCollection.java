package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class DailyCollection {
    public JFXDatePicker datepick;
    public JFXButton getinfobutton;
    public PreparedStatement pst=null;
    public ResultSet rs=null;
    public Connection conn;
    public String query="";
    public Label datalable;

    public int addoffee;

    public void initialize()
    {
        conn= SqlConnection.DbConnector();

    }

    public void getinfoAction(ActionEvent event) {
        JFXButton bbe=(JFXButton)event.getSource();
        if (bbe == getinfobutton)
        {
            System.out.println("Button Vlicked");
            try {
                query = "SELECT * FROM FeeDumpData WHERE Date=?";
                pst = conn.prepareStatement(query);
                pst.setString(1,datepick.getValue().toString());
                rs=pst.executeQuery();
                while(rs.next())
                {
                    addoffee+=rs.getInt("Paid");
                }
                datalable.setText("-------------------\n"+datepick.getValue().toString()+"\nDate's Collection : "+addoffee+"\n-------------------");
                addoffee=0;
                pst.close();
                rs.close();
            }catch (Exception e){System.out.println(e);}

        }
    }
}
