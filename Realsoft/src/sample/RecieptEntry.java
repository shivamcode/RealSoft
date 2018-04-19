package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RecieptEntry
{

    public JFXTextField studentuid;
    public JFXTextField stuname;
    public Label courses;
    public Label fees;
    public JFXTextField payingnow;
    public JFXButton feesubmit;

        public PreparedStatement pst=null;
        public ResultSet rs=null;
    public Connection conn;
    public String query="";
    public Label feeramain;
    public int feess;
    public int remain;
    public JFXButton findbutton;
    public JFXDatePicker datepick;
    public JFXComboBox combobox;

    public void initialize()
    {
        conn=SqlConnection.DbConnector();

    }


    public void feesubmitAction(ActionEvent event)
    {
        JFXButton bbe=(JFXButton)event.getSource();
        if (bbe==feesubmit)
        {

            try {
                query="UPDATE FeeData SET Remain=? WHERE ID=?";
                pst=conn.prepareStatement(query);
                int now= Integer.parseInt(payingnow.getText());
                remain=feess-now;
                pst.setInt(1,remain);
                pst.setString(2,studentuid.getText());
               int rs=pst.executeUpdate();
                if (rs>0)
                {
                    feeramain.setText("Remain :"+String.valueOf(remain));
                }
                pst.close();
            }catch (Exception ee){System.out.println(ee);}
            try {
                query="UPDATE FeeData SET Date=? WHERE ID=?";
                pst=conn.prepareStatement(query);
                pst.setString(1,datepick.getValue().toString());
                pst.setString(2,studentuid.getText());
                int rs=pst.executeUpdate();
                if (rs>0)
                {
                    feeramain.setText("Remaining Fee :"+String.valueOf(remain));
                }
                pst.close();
            }catch (Exception eeee){System.out.println(eeee);}
            try {
                    query="";
                    query="INSERT INTO FeeDumpData (id,Paid,Date,Batch) VALUES(?,?,?,?)";
                    pst=conn.prepareStatement(query);
                    pst.setString(1,studentuid.getText());
                    pst.setInt(2, Integer.parseInt(payingnow.getText()));
                    pst.setString(3,datepick.getValue().toString());
                    pst.setString(4,combobox.getValue().toString());
                    int rs=pst.executeUpdate();
                    if (rs>0)
                    {}
            }catch (Exception err){System.out.println(err);}


        }
    }

    public void findputAction(ActionEvent event)
    {
        JFXButton bbe=(JFXButton)event.getSource();
        if (bbe==findbutton)
        {
            try {

                query="SELECT * FROM StudentTable WHERE id=?";
                pst=conn.prepareStatement(query);
                pst.setString(1,studentuid.getText());
                rs=pst.executeQuery();
                if (rs.next())
                {
                    String name=rs.getString("Name");
                    stuname.setText(name);
                    String course=rs.getString("Course");
                    courses.setText(course);
                }
                pst.close();
                rs.close();
                query="";
            }catch (Exception e){System.out.println(e);}
            try {
                query="SELECT * FROM FeeData WHERE ID=?";
                pst=conn.prepareStatement(query);
                pst.setString(1,studentuid.getText());
                rs=pst.executeQuery();
                if (rs.next())
                {
                    feess=rs.getInt("Remain");
                    fees.setText(String.valueOf(feess));
                }
                pst.close();
                rs.close();
            }catch (Exception ee){System.out.println(ee);}
            try
            {
                query="";
                query="SELECT * FROM BatchData";
                pst=conn.prepareStatement(query);
                rs=pst.executeQuery();
                while (rs.next())
                {
                    combobox.getItems().add(rs.getString("Language")+" Time :"+rs.getString("Time"));
                }
                pst.close();
                rs.close();
            }catch (Exception errr){System.out.println(errr);}

        }
    }
}
