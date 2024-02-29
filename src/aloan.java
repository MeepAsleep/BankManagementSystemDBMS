import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class aloan {

    @FXML
    private Label a_lid;

    @FXML
    private TextField ad_acno;

    @FXML
    private TextField ad_amt;

    @FXML
    private TextField ad_loanid;

    @FXML
    private TextField ad_status;

    @FXML
    private Label approval;

    @FXML
    private Label idstat;

    @FXML
    private void check(ActionEvent event) throws Exception,SQLException {
        Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms","root","meep");
		Statement stm=con.createStatement();
        ResultSet rs1 = stm.executeQuery("select *from loans where loan_id="+ad_loanid.getText().toString());
        rs1.next();
        if(rs1.getString(4).equals("Pending")){
            int d = stm.executeUpdate("update accounts set balance=balance+"+rs1.getString(3)+" where account_no='"+rs1.getString(2)+"'");
            int c = stm.executeUpdate("update loans set status='Approved' where loan_id="+ad_loanid.getText().toString());
            approval.setText("approved");
        }
        else if(rs1.getString(4).equals("Approved")){
            approval.setText("already approved");
        }
        rs1.close();
        stm.close();
        con.close();
    }

    @FXML
    private void view(ActionEvent event) throws Exception,SQLException {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms","root","meep");
            Statement stm=con.createStatement();
            ResultSet rs = stm.executeQuery("select *from loans where loan_id="+ad_loanid.getText().toString());
            if(rs.next()){
                ad_acno.setText(rs.getString(2));
                ad_amt.setText(rs.getString(3));
                ad_status.setText(rs.getString(4));
                idstat.setText("");
                approval.setText("");
            }
            else{
                idstat.setText("Loan id doesn't exist");
                approval.setText("");
                ad_acno.setText("");
                ad_amt.setText("");
                ad_status.setText("");
            }
            rs.close();
            stm.close();
            con.close();
        }
        catch(Exception e){
            idstat.setText("Loan id doesn't exist");
        }
    }

    @FXML
    private void go_back(ActionEvent event) throws Exception{
        App a = new App();
        a.changeScene("admin.fxml");
    }

}
