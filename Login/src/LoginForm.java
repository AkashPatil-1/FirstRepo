import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JDialog{
    private JTextField userText;
    private JPasswordField passwordText;
    private JButton loginButton;
    private JButton cancelButton;
    private JPanel loginpanel;

    public LoginForm(JFrame parent){
        super(parent);
        setTitle("Login");
        setContentPane(loginpanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setMinimumSize(new Dimension(500, 490));
        setLocationRelativeTo(parent);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = userText.getText();
                String password = String.valueOf(passwordText.getPassword());
                user = getAuthenticatedUser(email, password);

                if(user != null){
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "Username or Password Invalid",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    public  User user;
    private User getAuthenticatedUser(String email, String password){
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost/loginform?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Connection connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM users WHERE email=? and password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.name = resultSet.getString("name");
                user.email = resultSet.getString("email");
                user.phone = resultSet.getString("phone");
                user.address = resultSet.getString("address");
                user.password = resultSet.getString("password");
            }
            statement.close();
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm(null);
        User user = loginForm.user;
        if(user != null){
            System.out.println("Successful Authentication of: "+ user.name);
            System.out.println("           Email: "+ user.email);
            System.out.println("           Phone: "+ user.phone);
            System.out.println("           address: "+ user.address);
        }
        else{
            System.out.println("Authentication Cancelled");
        }
    }
}
