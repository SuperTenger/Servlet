import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBHelper {
    private static final String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8";
    private static final String name = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String pwd = "uAiqwVwjJ8-i";

    private Connection connection = null;
    public PreparedStatement preparedStatement = null;

    public DBHelper(String sql){
        try{
            Class.forName(name);
            connection = DriverManager.getConnection(url,user,pwd);
            preparedStatement = connection.prepareStatement(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void close(){
        try{
            this.connection.close();
            this.preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
