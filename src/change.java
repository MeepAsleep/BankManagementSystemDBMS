import java.io.FileReader;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class change {
    
    @FXML
    private ToggleGroup gp;

    @FXML
    private RadioButton mail;

    @FXML
    private TextField newval;

    @FXML
    private RadioButton phone;

    @FXML
    private RadioButton pin;

    @FXML
    private Label rlab;

    @FXML
    private Button bt;

    @FXML
    private void go_back(ActionEvent event) throws Exception {
        App a = new App();
        a.changeScene("user.fxml");
    }

    @FXML
    private void update(ActionEvent event) throws Exception,SQLException {
        FileReader f = new FileReader("id.txt");
        Scanner fs = new Scanner(f);
        String acno = fs.next();
        fs.close();
        f.close();
        Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms","root","meep");
		Statement stm=con.createStatement();
        String changer="";
        if(pin.isSelected()){
            changer = "pin";
        }
        if(phone.isSelected()){
            changer = "phone";
        }
        if(mail.isSelected()){
            changer = "email";
        }
        try{
            int c = stm.executeUpdate("update accounts set "+changer+" = '"+newval.getText().toString()+"' where account_no = '"+acno+"'");
            if(c==0){
                rlab.setText("INVALID");
            }
            else{
                rlab.setText("UPDATED");
            }
        }
        catch(SQLException e){
            rlab.setText("INVALID INPUT");
        }
        
    }
}
