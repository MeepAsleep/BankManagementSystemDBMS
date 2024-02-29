import java.io.FileReader;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class with {
    @FXML
    private TextField amount;

    @FXML
    private Button button;

    @FXML
    private Label lab;

    @FXML
    private PasswordField pin;

    @FXML
    private void go_back(ActionEvent event) throws Exception{
        App a = new App();
        a.changeScene("user.fxml");
    }

    @FXML
    private void withdraw(ActionEvent event) throws Exception,SQLException {
        FileReader f = new FileReader("id.txt");
        Scanner fs = new Scanner(f);
        String acno = fs.next();
        String pn = pin.getText().toString();
        fs.close();
        f.close();
        Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms","root","meep");
		Statement stm=con.createStatement();
        ResultSet rs = stm.executeQuery("select *from accounts where account_no = '"+acno+"' and pin = '"+pn+"'");
        if(rs.next()){
            int money = Integer.parseInt(rs.getString(8));
            if((money-Integer.parseInt(amount.getText().toString()))<0){
                lab.setText("INSUFFICIENT BALANCE");
            }
            else{
                int c = stm.executeUpdate("update accounts set balance = balance - "+amount.getText().toString()+" where account_no = '"+acno+"'");
                lab.setText("WITHDRAW SUCCESSFUL");
            }
        }
        else{
            lab.setText("WRONG PIN");
        }

    }
}
