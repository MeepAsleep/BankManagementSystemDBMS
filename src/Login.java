import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class Login {

    @FXML
    private Button bt_login;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Hyperlink create;

    @FXML
    private Label err1;

    @FXML
    private RadioButton type1,type2;

    @FXML
    public void loginclk(ActionEvent ae) throws Exception {
        checkLogin();
    }

    public void signup(ActionEvent ae) throws Exception {
        App a1 = new App();
        a1.changeScene("signup.fxml");
    }

    @FXML
    private void checkLogin() throws Exception,SQLException,IOException {
        App a2 = new App();
        App a3 = new App();
        Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms","root","meep");
		Statement stm=con.createStatement();

        String un = username.getText().toString();
        String pa = password.getText().toString();
        String type = "";

        if(type1.isSelected()){
            type = "user";
        }
        if(type2.isSelected()){
            type = "admin";
        }

        String query = "select * from login where user_id = '"+un+"' and password = '"+pa+"' and type = '"+type+"'";
        ResultSet rs = stm.executeQuery(query);

        if(rs.next()){
            if(type=="admin"){
                rs.close();
                stm.close();
                con.close();
                a3.changeScene("admin.fxml");
            }
            else{
                ResultSet rs1 = stm.executeQuery("select * from accounts where user_id = '"+un+"'");
                rs1.next();
                String acno = rs1.getString(2);
                rs1.close();
                rs.close();
                stm.close();
                con.close();
                FileWriter f = new FileWriter("id.txt");
                f.write(acno);
                f.close();
                a2.changeScene("user.fxml");
            }
        }
        else{
            err1.setText("INVALID CREDENTIALS");
        }
    }
}
