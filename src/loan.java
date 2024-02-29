import java.io.FileReader;
import java.sql.*;
import java.util.Random;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class loan {

    @FXML
    private TextField pin;

    @FXML
    private TextField amt;

    @FXML
    private Label lobel;

    @FXML
    private void butclick(ActionEvent event) throws Exception,SQLException {
        FileReader f = new FileReader("id.txt");
        Scanner fs = new Scanner(f);
        String acno = fs.next();
        String pn = pin.getText().toString();
        String amount = amt.getText().toString();
        fs.close();
        f.close();
        Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms","root","meep");
		Statement stm=con.createStatement();

        ResultSet rs = stm.executeQuery("select *from accounts where account_no="+acno+" and pin="+pn);
        if(rs.next())
        {
            Random r = new Random();
            int loanid = r.nextInt(90000000)+10000000;
            int ron = stm.executeUpdate("insert into loans values("+String.valueOf(loanid)+",'"+acno+"','"+amount+"','Pending')");
            lobel.setText("LOAN REQUESTED");
        }
        else{
            lobel.setText("INCORRECT PIN");
        }
    }

    @FXML
    private void go_back(ActionEvent event) throws Exception {
        App a = new App();
        a.changeScene("user.fxml");
    }
}
