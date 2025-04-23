import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Home extends Frame {
    Home(String name){
        Label label = new Label("Hello "+ name);
        label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setBounds(0,0, 500, 500);
        add(label);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setLayout(null);
        setSize(500, 500);
        setVisible(true);
    }
}