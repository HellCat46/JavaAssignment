import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/auth/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader body = req.getReader();
        String[] name = body.readLine().split("=",2);
        String[] email = body.readLine().split("=",2);
        String[] password = body.readLine().split("=",2);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaassign", "root", "Harshit5582");

            Statement statement = conn.createStatement();
            int rows = statement.executeUpdate("INSERT INTO users(name, email, password) VALUES('"+name[1]+"', '"+email[1]+"', '"+password[1]+"')");
            if(rows > 0){
                resp.setStatus(200);
                statement.close();
                conn.close();
                return;
            }

            resp.setStatus(500);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            body.close();
        }
    }
}
