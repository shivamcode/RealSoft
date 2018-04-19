package sample;

import com.intellij.ide.ui.EditorOptionsTopHitProvider;
import com.intellij.openapi.vfs.newvfs.persistent.FSRecords;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BatchwiseCollection
{

    public JFXComboBox combobox;
    public Label datalable;
    public String query;
    public Connection conn;
    public PreparedStatement pst=null;
    public ResultSet rs=null;

    public JFXButton findbutton;
    public int addoffee;

    public void initialize()
    {
        conn=SqlConnection.DbConnector();
        try {
            query = "SELECT * FROM BatchData";
            pst = conn.prepareStatement(query);
            rs=pst.executeQuery();
            while (rs.next())
            {
                combobox.getItems().add(rs.getString("Language")+" Time :"+rs.getString("Time"));
            }
            rs.close();
            pst.close();
        }catch (Exception e){System.out.println(e);}
    }


    public void findAction(ActionEvent event) {
        JFXButton bbe=(JFXButton)event.getSource();
        if (bbe==findbutton)
        {
            try
            {
                query="";
                query="SELECT * FROM FeeDumpData WHERE Batch=?";
                pst=conn.prepareStatement(query);
                pst.setString(1,combobox.getValue().toString());
                rs=pst.executeQuery();
                while (rs.next())
                {
                    addoffee+=rs.getInt("Paid");
                }
                datalable.setText("--------------------\nBatch :"+combobox.getValue().toString()+"\nTotal Batch Collection :"+addoffee+"\n--------------------");
                addoffee=0;
            }catch (Exception ee){System.out.println(ee);}

        }
    }
}
