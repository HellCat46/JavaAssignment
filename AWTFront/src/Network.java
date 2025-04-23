import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Network {
    private static String baseUrl = "http://localhost:8080/";
    public static String register(String name, String email, String pass){
        try {
            URL url = new URL(baseUrl + "auth/register");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.write(("name="+name).getBytes(StandardCharsets.UTF_8));
            out.write(("\nemail="+email).getBytes(StandardCharsets.UTF_8));
            out.write(("\npassword="+pass).getBytes(StandardCharsets.UTF_8));
            out.close();


            return switch (conn.getResponseCode()) {
                case 200 -> null;
                case 500 -> "Unexpected Error Occurred.";
                default -> "Status Unknown";
            };

        }catch (Exception ex){
            ex.printStackTrace();
            return ex.getMessage();
        }
    }

    public static String login(String email, String pass){
        try {
            URL url = new URL(baseUrl + "auth/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.write(("email="+email).getBytes(StandardCharsets.UTF_8));
            out.write(("\npassword="+pass).getBytes(StandardCharsets.UTF_8));
            out.close();

            switch (conn.getResponseCode()) {
                case 200 : {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    return in.readLine();
                }
                case 404 : return "Error: User Not Found";
                case 500 : return "Error: Unexpected Error Occurred.";
                default : return "Error: Status Unknown";
            }

        }catch (Exception ex){
            ex.printStackTrace();
            return "Error: "+ex.getMessage();
        }
    }

}
