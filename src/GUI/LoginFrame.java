package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoginFrame extends JFrame implements ActionListener {

    // TODO: Implement encryption for users

    private JTextField loginTextField;
    private JPasswordField  passwdTextField;
    private JLabel  loginLabel, passwdLabel;
    private JButton loginButton;
    private final String[] userGroups = {"Administrator", "User"};
    private JComboBox chooseGroup;

    private  void  _setupMetaData() {
        this.setDefaultCloseOperation(LoginFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Login Screen");
        this.setSize(230,130);
        this.setLayout(new FlowLayout());
        this.setVisible(true);
    }

    private  void _initialiseObjects() {
        loginTextField = new JTextField();
        loginTextField.setColumns(12);
        passwdTextField = new JPasswordField();
        passwdTextField.setColumns(12);

        loginLabel = new JLabel("Login: ");
        passwdLabel = new JLabel("Password: ");

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        chooseGroup = new JComboBox(userGroups);
        chooseGroup.setSelectedIndex(1);
        chooseGroup.addActionListener(this);
    }

    private byte _getLoginId(String login) {
        byte i = 1;
        try {
            FileReader fr = new FileReader("assets/accounts");
            BufferedReader br = new BufferedReader(fr);
            String helper;

            while ((helper = br.readLine()) != null) {
                if(i % 2 != 0 && helper.equals(login))
                        break;
                i++;
            }
            br.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        return  i;
    }

    private boolean _checkPassword(String passwd, byte loginId) {
        boolean isThePasswdCorrect = false;
        byte parsedLoginId = 1, i = 1;

        try {
            FileReader fr = new FileReader("assets/accounts");
            BufferedReader br = new BufferedReader(fr);
            String helper;

            while ((helper = br.readLine()) != null) {
                if(i%2 == 0 && loginId == parsedLoginId && helper.equals(passwd))
                    isThePasswdCorrect = true;

                if ( i%2 == 0)
                    parsedLoginId++;
                i++;
            }
            br.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        return  isThePasswdCorrect;
    }

    private  boolean _isTheUserAnAdmin(String login) {
        boolean areTheyAdmin = false;

        try {
            FileReader fr = new FileReader("assets/administrators");
            BufferedReader br = new BufferedReader(fr);
            String helper;

            while ((helper = br.readLine()) != null) {
                if(login.equals(helper))
                    areTheyAdmin = true;
            }
            br.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        return areTheyAdmin;
    }

    private void _login() {
        String enteredLogin = loginTextField.getText();
        String enteredPasswd = passwdTextField.getText();
        byte loginId = _getLoginId(enteredLogin);

        if (loginId > 0) {
            if(_checkPassword(enteredPasswd, loginId)) {
                if(chooseGroup.getSelectedIndex() == 0 && _isTheUserAnAdmin(enteredLogin)) {
                    new AdminMainFrame();
                    this.dispose();
                } else if (chooseGroup.getSelectedIndex() == 0 &&  !_isTheUserAnAdmin(enteredLogin))
                    JOptionPane.showMessageDialog(null, "The user is not an administrator!");
            } else
                JOptionPane.showMessageDialog(null, "Incorrect password entered");
        } else
            JOptionPane.showMessageDialog(null, "Incorrect login entered");


    }

    private void _addObjects() {
        add(loginLabel);
        add(loginTextField);
        add(passwdLabel);
        add(passwdTextField);
        add(loginButton);
        add(chooseGroup);
    }

    public LoginFrame() {
        _setupMetaData();
        _initialiseObjects();
        _addObjects();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == loginButton) {
            _login();
        }
    }
}
