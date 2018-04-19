package sample;

import javafx.beans.property.SimpleStringProperty;

public class TableData
{
    private final SimpleStringProperty ID;
    private final SimpleStringProperty Name;
    private final SimpleStringProperty Feepaid;

    public TableData(String id, String name, String feepaid) {
        this.ID=new SimpleStringProperty(id);
        this.Name=new SimpleStringProperty(name);
        this.Feepaid=new SimpleStringProperty(feepaid);
    }



    public String getID() {
        return ID.get();
    }

    public String getName() {
        return Name.get();
    }

    public String getFeepaid() {
        return Feepaid.get();
    }
    public void setID(String id)
    {
        ID.set(id);
    }
    public void setName(String nm)
    {
        Name.set(nm);
    }
    public void setFeepaid(String fee)
    {
        Feepaid.set(fee);
    }
}
