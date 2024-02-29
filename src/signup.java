import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.sql.*;
import java.util.Optional;
import java.util.Random;

public class signup {

    @FXML
    private Button bt_goBack;

    @FXML
    private TextField sup_user;

    @FXML
    private PasswordField sup_pass1;

    @FXML
    private PasswordField sup_pass2;

    @FXML
    private TextField sup_name;

    @FXML
    private TextField sup_phno;

    @FXML
    private TextField sup_mail;

    @FXML
    private DatePicker sup_date;

    @FXML
    private Button register;

    @FXML
    private Label sup_lab;


    @FXML
    private void goBack(ActionEvent ae) throws Exception {
        App a = new App();
        a.changeScene("MS.fxml");
    }

    @FXML
    private void reg(ActionEvent ae) throws Exception,SQLException {
        try{
            String username = sup_user.getText().toString();
            String pass1 = sup_pass1.getText().toString();
            String pass2 = sup_pass2.getText().toString();
            String name = sup_name.getText().toString();
            String phone = sup_phno.getText();
            String mail = sup_mail.getText().toString();
            String date = sup_date.getValue().toString();

            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms","root","meep");
            Statement stm=con.createStatement();
            ResultSet rs = stm.executeQuery("select *from login where user_id = '"+username+"'");
            int c = check(rs,pass1,pass2);
            if(c==0){
                Random r = new Random();
                int pin = r.nextInt(9000)+1000;
                int accno = r.nextInt(900000)+100000;
                int u1 = stm.executeUpdate("insert into login values('"+username+"','"+pass1+"','user')");
                int u2 = stm.executeUpdate("insert into accounts values('"+username+"',"+String.valueOf(accno)+",'"+name+"',"+phone+",'"+mail+"','"+date+"',"+String.valueOf(pin)+",50000)");
                App a = new App();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("IMPORTANT, DO NOT IGNORE!");
                alert.setContentText("YOUR PIN IS "+String.valueOf(pin));
                Optional<ButtonType> result = alert.showAndWait();
                FileWriter f = new FileWriter("id.txt");
                f.write(String.valueOf(accno));
                f.close();
                a.changeScene("user.fxml");
            }
            rs.close();
            stm.close();
            con.close();
        }
        catch(Exception e){
            sup_lab.setText("INVALID DETAILS");
        }
    }

    private int check(ResultSet rs,String p1,String p2) throws Exception {
        if(!p1.equals(p2)){
            sup_lab.setText("Passwords Do Not Match");
            return 1;
        }
        else if(rs.next()){
            sup_lab.setText("Username Already In Use");
            return 1;
        }
        return 0;
    }
}
