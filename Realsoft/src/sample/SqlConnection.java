package sample;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class SqlConnection {

    public static Connection DbConnector()
    {
        try {
            Connection conn;
            Class.forName("org.sqlite.JDBC");
            conn= getConnection("JDBC:sqlite:C:\\Users\\Shivam Kagalkar\\IdeaProjects\\Realsoft\\src\\sample\\UserDatabase.db");
            return conn;
        }catch (ClassNotFoundException | SQLException e)
        {
            System.out.println(e);
        }
        return null;

    }
}

