import java.awt.*;

public class Home extends Frame {
    Home(String name){
        Label label = new Label("Hello "+ name);
        label.setFont(new Font("Arial", Font.BOLD, 50));
        add(label);

        setLayout(null);
        setVisible(true);
    }
}