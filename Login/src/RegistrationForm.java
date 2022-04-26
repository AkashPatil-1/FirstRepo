import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class RegistrationForm extends JFrame {

    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel email;
    private JTextField textField4;
    private JPasswordField passwordField1;
    private JLabel nm;
    private JLabel login;
    private JPanel RegisterPage;
    private JButton Register;
    private JButton cancelButton;
    private JPasswordField passwordField2;
    private JLabel repass;

    JFrame frame;

    public RegistrationForm(){
        frame = new JFrame("Registration Page");
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(500,450));
        frame.pack();
        frame.setTitle("Registration");
        frame.add(RegisterPage);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                LoginForm loginForm = new LoginForm(null);
                loginForm.setVisible(true);
                loginForm.setLocationRelativeTo(null);
                loginForm.pack();
                loginForm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        Register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = textField1.getText();
                String email = textField2.getText();
                String phone = textField3.getText();
                String address = textField4.getText();
                String password = String.valueOf(passwordField1.getPassword());
                String repass = String.valueOf(passwordField2.getPassword());

                if(name.equals("")) {
                    JOptionPane.showMessageDialog(null, "Add User Name","Required",0);
                }
                else if(email.equals("")){
                    JOptionPane.showMessageDialog(null,"Email is required","Required",0);
                } else if (phone.equals("")) {
                    JOptionPane.showMessageDialog(null,"Add your contact number","Required",0);
                } else if(address.equals("")){
                    JOptionPane.showMessageDialog(null,"Enter Your Address Here","Required",0);
                }else if(password.equals("")){
                    JOptionPane.showMessageDialog(null,"Enter Your Password","Required",0);
                }else if(!password.equals(repass)) {
                    JOptionPane.showMessageDialog(null,"Password Doesn't Match","Try Again",0);
                }

                PreparedStatement statement;
                String sql = "INSERT INTO users(name,email,phone,address,password,repass)values(?,?,?,?,?,?)" ;

                try {
                    statement = myConnection.getConnection().prepareStatement(sql);
                    statement.setString(1,name);
                    statement.setString(2,email);
                    statement.setString(3,phone);
                    if(address != null) {
                        statement.setString(4, address);
                    }
                    if(password != null) {
                        statement.setString(5, password);
                    }
                    if(password.equals(repass)) {
                        statement.setString(6, repass);
                    }

                        if (statement.executeUpdate() > 0) {
                            JOptionPane.showMessageDialog(null, "User Added Successfully", "Done", JOptionPane.INFORMATION_MESSAGE);
                        }


                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }


            }
        });


        frame.setVisible(true);
    }


    public static void main(String[] args) {
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setVisible(true);
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new RegistrationForm().setVisible(true);
//            }
//        });
    }
}

