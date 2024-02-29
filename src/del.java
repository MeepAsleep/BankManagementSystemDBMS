import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class del {
    @FXML
    private TextField acnum;

    @FXML
    private Label lab;

    @FXML
    private void butclick(ActionEvent event) throws Exception,SQLException {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms","root","meep");
            Statement stm=con.createStatement();
            ResultSet rs = stm.executeQuery("select *from accounts where account_no="+acnum.getText().toString());
            if(rs.next()){
                String uid = rs.getString(1);
                int c1 = stm.executeUpdate("delete from accounts where account_no="+acnum.getText().toString());
                int c2 = stm.executeUpdate("delete from login where user_id='"+uid+"'");
                lab.setText("account deleted");
            }
            else{
                lab.setText("account does not exist");
            }
            rs.close();
            stm.close();
            con.close();
        }
        catch(Exception e){
            lab.setText("invalid input");
        }
    }

    @FXML
    private void go_back(ActionEvent event) throws Exception {
        App a = new App();
        a.changeScene("admin.fxml");
    }
}
