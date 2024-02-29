import java.io.FileReader;
import java.util.Scanner;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.util.Random;

public class transfer {
    @FXML
    private TextField acnum;

    @FXML
    private TextField amt;

    @FXML
    private PasswordField pin;

    @FXML
    private Label lab;

    @FXML
    private void confirm(ActionEvent event) throws Exception,SQLException {
        FileReader f = new FileReader("id.txt");
        Scanner fs = new Scanner(f);
        String acno = fs.next();
        String amount = amt.getText().toString();
        fs.close();
        f.close();
        Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms","root","meep");
		Statement stm=con.createStatement();
        ResultSet rs = stm.executeQuery("select *from accounts where account_no="+acno+" and pin="+pin.getText().toString());
        if(rs.next()){
            int curbal = Integer.parseInt(rs.getString(8));
            rs.close();
            ResultSet rs1 = stm.executeQuery("select *from accounts where account_no="+acnum.getText().toString());
            if(rs1.next()){
                if(curbal>=Integer.parseInt(amt.getText().toString())){
                    Random r = new Random();
                    int loanid = r.nextInt(900)+100;
                    int s1 = stm.executeUpdate("update accounts set balance=balance-"+amt.getText().toString()+" where account_no="+acno);
                    int s2 = stm.executeUpdate("update accounts set balance=balance+"+amt.getText().toString()+" where account_no="+acnum.getText().toString());
                    int s3 = stm.executeUpdate("insert into transactions values("+String.valueOf(loanid)+","+acno+","+acnum.getText().toString()+","+amt.getText().toString()+",CURRENT_TIMESTAMP)");
                    lab.setText("Transfer Successful");
                }
                else{
                    lab.setText("Insufficient balance");
                }
            }
            else{
                lab.setText("Invalid Account");
            }
            rs1.close();
        }
        else{
            lab.setText("Invalid PIN");
        }
        stm.close();
        con.close();
    }

    @FXML
    private void go_back(ActionEvent event) throws Exception {
        App a = new App();
        a.changeScene("user.fxml");
    }
}
