import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader body = req.getReader();
        String[] email = body.readLine().split("=",2);
        String[] password = body.readLine().split("=",2);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaassign", "root", "Harshit5582");

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM users WHERE email = '"+email[1]+"' and password = '"+password[1]+"';");

            if(!resultSet.next()){
                resp.setStatus(404);
                statement.close();
                resultSet.close();
                conn.close();
                return;
            }
            String name = resultSet.getString("name");
            resp.setStatus(200);
            resp.getWriter().println(name);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            body.close();
        }


    }

}
