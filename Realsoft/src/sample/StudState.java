package sample;

import com.intellij.ide.ui.EditorOptionsTopHitProvider;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudState
{

    public JFXButton statesubmit;
    public PreparedStatement pst=null;
    public ResultSet rs;
    public Connection conn;
    public JFXTextField stuid;
    public JFXButton find;
    public JFXTextField stuname;
    public JFXToggleButton togbutton;
    public Label status;

   public String stustat="";

    public void initialize()
    {
        conn=SqlConnection.DbConnector();
        togbutton.setSelected(true);
        status.setText("Active");

    }

    public void stateSubmitAction(ActionEvent event)
    {
        JFXButton bbe=(JFXButton)event.getSource();
        if (bbe==find)
        {
            try
            {

                String query = "SELECT * FROM StudentTable WHERE id=?";
                pst = conn.prepareStatement(query);
                pst.setString(1,stuid.getText());
                rs=pst.executeQuery();

                if (rs.next())
                {
                    stuname.setText(rs.getString("Name"));
                    stustat=rs.getString("State");
                    getState();
                    setState();

                }
                rs.close();
                pst.close();
            }catch (Exception e){System.out.println(e);}

        }
        if (bbe==statesubmit)
        {
            try
            {
              String query = "UPDATE StudentTable SET State=? Where ID=?";
              pst=conn.prepareStatement(query);
              setState();
              pst.setString(1,getStustat());
              pst.setString(2,stuid.getText());
              int rs=pst.executeUpdate();
                if (rs>0)
                {


                }
                pst.close();
                conn.close();
            }catch (Exception ee){System.out.println(ee);}
        }
    }
    public void getState()
    {
        if(stustat.equals("true"))
        {
            togbutton.setSelected(true);
        }
        if (stustat.equals("false"))
        {
            togbutton.setSelected(false);
        }
    }
    public void setState()
    {
        if (togbutton.isSelected())
        {
            stustat="True";
            status.setText("Active");

        }
        else
        {
            stustat="false";
            status.setText("Left");
        }
    }
    public String getStustat()
    {
        if(togbutton.isSelected())
            return "true";
        else
            return "false";
    }
}
