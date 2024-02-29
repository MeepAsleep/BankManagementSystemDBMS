import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ainfo {
    @FXML
    private TextField acnum;

    @FXML
    private TextField bal;

    @FXML
    private TextField dob;

    @FXML
    private TextField mail;

    @FXML
    private TextField name;

    @FXML
    private TextField phno;

    @FXML
    private TextField uid;

    @FXML
    private Label lab;

    @FXML
    private void go_back(ActionEvent event) throws Exception {
        App a = new App();
        a.changeScene("admin.fxml");
    }

    @FXML
    private void view(ActionEvent event) throws Exception,SQLException {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms","root","meep");
            Statement stm=con.createStatement();
            ResultSet rs = stm.executeQuery("select *from accounts where account_no="+acnum.getText().toString());
            if(rs.next()){
                lab.setText("");
                phno.setText(rs.getString(4));
                bal.setText(rs.getString(8));
                dob.setText(rs.getString(6));
                mail.setText(rs.getString(5));
                name.setText(rs.getString(3));
                uid.setText(rs.getString(1));
            }
            else{
                acnum.setText("");
                bal.setText("");
                dob.setText("");
                mail.setText("");
                name.setText("");
                uid.setText("");
                phno.setText("");
                lab.setText("account not found");
            }
            rs.close();
            stm.close();
            con.close();
        }
        catch(Exception e){
            lab.setText("invalid input");
        }
    }
}
