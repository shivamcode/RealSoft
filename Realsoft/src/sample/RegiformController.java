package sample;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class RegiformController
{

    public ChoiceBox choicebox;
    public ChoiceBox choiceboxyr;
    public Connection conn;
    public PreparedStatement pst=null;
    public int rs;
    public JFXTextField studname;
    public JFXTextField fathername;
    public JFXTextField collegename;
    public JFXCheckBox ccheck;
    public JFXCheckBox cppcheck;
    public JFXCheckBox dscheck;
    public JFXCheckBox jcheck;
    public JFXButton submit;
    public  String Course="";
    public String query=null;
    public Label status;
    public JFXTextField regiautoid;
    public Random random=new Random();
    public JFXButton printbutton;
    int randno=0;
   public Document doc=new Document();
    public String filename;
    public int fees=0;
    public int count=0;
    public void initialize()throws Exception
    {
        choicebox.getItems().addAll("Choose","CSE","ENTC","Mechanical","IT","Electrical","BCA");
        choiceboxyr.getItems().addAll("Chose","1","2","3","4");
        choicebox.getSelectionModel().selectFirst();
        choiceboxyr.getSelectionModel().selectFirst();
        status.setText("");
        conn=SqlConnection.DbConnector();
        randno=random.nextInt(100000)+1;
        regiautoid.setText("#"+randno);
        filename=studname.getText();
        filename="";


    }
    public void printReceipt()throws Exception
    {
        doc.open();
        doc.add(new Paragraph("                                  Receipt"));
        doc.add(new Paragraph("Realsoft Computers Receipt ID : "+regiautoid.getText()));
        doc.add(new Paragraph("Student Name :"+studname.getText()));
        doc.add(new Paragraph("Fathers Name :"+fathername.getText()));
        doc.add(new Paragraph("Course Selected :"+getState()));
        doc.add(new Paragraph("Total Fees :"+fees));
        doc.add(new Paragraph("Paid :"));
        doc.add(new Paragraph("Remaining Fees :"));
        doc.add(new Paragraph("\n"));
        doc.add(new Paragraph("\n"));
        doc.add(new Paragraph("\n"));
        doc.add(new Paragraph("\n"));
        doc.add(new Paragraph("\n"));
        doc.add(new Paragraph("\n"));
        doc.add(new Paragraph("\n"));
        doc.add(new Paragraph("                                                                                     Thank You....!"));
        doc.add(new Paragraph("                                                                                     __Realsoft Computers"));
        doc.close();
    }

    public void dataEnterdAction(ActionEvent event)
    {
        JFXButton bbe=(JFXButton)event.getSource();
        if(bbe==submit)
        {
            submitt();
        }
        if (bbe==printbutton)
        {
            getState();
            filename=studname.getText()+".pdf";
            try {
                PdfWriter.getInstance(doc,new FileOutputStream(filename));
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            filename=studname.getText()+".pdf";
            try {
                printReceipt();
            } catch (Exception e) {
                e.printStackTrace();
            }
            submitt();
        }
    }
    public void submitt()
    {
        try {
            query = "INSERT INTO StudentTable (ID,Name,Father,College,Trade,Year,Course,State) VALUES(?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(query);
            pst.setString(1,regiautoid.getText());
            pst.setString(2, studname.getText());
            pst.setString(3, fathername.getText());
            pst.setString(4, collegename.getText());
            String chschs = (String) choicebox.getSelectionModel().getSelectedItem();
            pst.setString(5, chschs);
            String chsyr = (String) choiceboxyr.getSelectionModel().getSelectedItem();
            pst.setString(6, chsyr);
            pst.setString(7, getState());
            pst.setString(8,"true");
            rs = pst.executeUpdate();
            if (rs>0) {
                status.setText("Record Is Added Successfully");
                setNull();
            } else {
                status.setText("Problem !");
            }
            pst.close();
            rs=0;
        }catch (Exception ee){System.out.println(ee);}
        try
        {
            query="INSERT INTO FeeData (ID,Fees,Remain) VALUES(?,?,?)";
            pst = conn.prepareStatement(query);
            pst.setString(1,regiautoid.getText());
            pst.setInt(2,fees);
            pst.setInt(3,fees);
            rs=pst.executeUpdate();
        }catch (Exception eee){System.out.println(eee);}
    }
    public String getState()
    {
        count++;
        if (count>1)
            return Course;
        if(ccheck.isSelected())
        {
            Course+="C";
            fees+=5000;
        }
        if (cppcheck.isSelected()){
            Course+=" C++";
            fees+=5000;
        }
        if (dscheck.isSelected())
        {
            Course+=" DS";
            fees+=5000;
        }
        if (jcheck.isSelected()){
            Course+=" Java";
            fees+=5000;
        }
        return Course;
    }
    public void setNull()
    {
        Course="";
        studname.setText("");
        collegename.setText("");
        fathername.setText("");
        ccheck.setSelected(false);
        cppcheck.setSelected(false);
        dscheck.setSelected(false);
        jcheck.setSelected(false);
        choicebox.getSelectionModel().selectFirst();
        choiceboxyr.getSelectionModel().selectFirst();
    }
}
