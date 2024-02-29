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

public class dep {
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
    private void deposit(ActionEvent event) throws Exception,SQLException {
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
            int c = stm.executeUpdate("update accounts set balance = balance + "+amount.getText().toString()+" where account_no = '"+acno+"'");
            lab.setText("DEPOSIT SUCCESSFUL");
        }
        else{
            lab.setText("WRONG PIN");
        }

    }
}
