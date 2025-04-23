import java.awt.*;
import java.awt.event.*;

class Registration extends Frame {
    Registration() {
        Label heading = new Label("Registration Form");
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setBounds(100, 100, 200, 30);
        add(heading);

        Label nameLbl = new Label("Name:");
        nameLbl.setBounds(135, 160, 100, 20);
        add(nameLbl);
        TextField nameIn = new TextField();
        nameIn.setBounds(135, 190, 100, 20);
        add(nameIn);

        Label emailLbl = new Label("Email:");
        emailLbl.setBounds(135, 220, 100, 20);
        add(emailLbl);
        TextField emailIn = new TextField();
        emailIn.setBounds(135, 250, 100, 20);
        add(emailIn);

        Label passLbl = new Label("Password:");
        passLbl.setBounds(135, 280, 100, 20);
        add(passLbl);
        TextField passIn = new TextField();
        passIn.setBounds(135, 310, 100, 20);
        add(passIn);

        Label conPassLbl = new Label("Confirm Password:");
        conPassLbl.setBounds(135, 340, 100, 20);
        add(conPassLbl);
        TextField conPassIn = new TextField();
        conPassIn.setBounds(135, 370, 100, 20);
        add(conPassIn);

        Button regBtn = new Button("Register");
        regBtn.setBounds(135, 400, 100, 25);
        add(regBtn);

        Label resLabel = new Label();
        resLabel.setBounds(135, 440, 200, 25);
        add(resLabel);

        Label loginLbl = new Label("Already Registered? Login");
        loginLbl.setForeground(Color.blue);
        loginLbl.setBounds(135, 470, 200, 20);
        add(loginLbl);

        loginLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Login();
                dispose();
            }

        });

        regBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameIn.getText().isEmpty() || emailIn.getText().isEmpty()
                        || passIn.getText().isEmpty() || conPassIn.getText().isEmpty()){
                    resLabel.setText("All the fields needs to be filled");
                    resLabel.setForeground(Color.red);
                    return;
                }

                if(!passIn.getText().equals(conPassIn.getText())){
                    resLabel.setText("Passwords doesn't match in both fields.");
                    resLabel.setForeground(Color.red);
                    return;
                }

                String res = Network.register(nameIn.getText(), emailIn.getText(), passIn.getText());
                if(res != null){
                    resLabel.setText(res);
                    resLabel.setForeground(Color.red);
                    return;
                }

                resLabel.setText("Successfully Registered. \nRedirecting to Login.");
                resLabel.setForeground(Color.green);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                new Login();
                dispose();
            }
        });



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