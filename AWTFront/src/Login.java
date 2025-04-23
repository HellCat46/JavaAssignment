import java.awt.*;
import java.awt.event.*;

class Login extends Frame {
    Login() {
        Label heading = new Label("Login Form");
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setBounds(100, 100, 200, 30);
        add(heading);

        Label emailLbl = new Label("Email:");
        emailLbl.setBounds(135, 160, 100, 20);
        add(emailLbl);
        TextField emailIn = new TextField();
        emailIn.setBounds(135, 190, 100, 20);
        add(emailIn);

        Label passLbl = new Label("Password:");
        passLbl.setBounds(135, 220, 100, 20);
        add(passLbl);
        TextField passIn = new TextField();
        passIn.setBounds(135, 250, 100, 20);
        add(passIn);

        Button regBtn = new Button("Login");
        regBtn.setBounds(135, 280, 100, 25);
        add(regBtn);

        Label resLabel = new Label();
        resLabel.setBounds(135, 310, 200, 25);
        add(resLabel);

        Label regLbl = new Label("Not Registered? Register Now");
        regLbl.setForeground(Color.blue);
        regLbl.setBounds(135, 330, 200, 20);
        add(regLbl);

        regLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Registration();
                dispose();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        regBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(emailIn.getText().isEmpty() || passIn.getText().isEmpty()){
                    resLabel.setText("All the fields needs to be filled");
                    resLabel.setForeground(Color.red);
                    return;
                }


                String res = Network.login(emailIn.getText(), passIn.getText());
                if(res.startsWith("Error: ")){
                    resLabel.setText(res);
                    resLabel.setForeground(Color.red);
                    return;
                }

                resLabel.setText("Successfully Logged In. \nRedirecting to Home.");
                resLabel.setForeground(Color.green);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                new Home(res);
                dispose();
            }
        });


        setLayout(null);
        setSize(500, 400);
        setVisible(true);
    }
}