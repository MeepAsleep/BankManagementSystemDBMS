import java.util.Scanner;
import java.io.FileReader;
import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class bal {
    @FXML
    private TableColumn<b_table, Integer> amount;

    @FXML
    private Button bt_goBack;

    @FXML
    private TableColumn<b_table, String> date;

    @FXML
    private Button go;

    @FXML
    private PasswordField pin;

    @FXML
    private TableColumn<b_table, Integer> rec_id;

    @FXML
    private TableView<b_table> table;

    @FXML
    private TableColumn<b_table, Integer> trans_id;

    @FXML
    private Label viewbal;

    @FXML
    private void goBack(ActionEvent event) throws Exception{
        App a = new App();
        a.changeScene("user.fxml");
    }

    @FXML
    private void printbal(ActionEvent event) throws Exception,SQLException {
        FileReader f = new FileReader("id.txt");
        Scanner fs = new Scanner(f);
        String acno = fs.next();
        String pn = pin.getText().toString();
        fs.close();
        f.close();
        ObservableList<b_table> rowlist = FXCollections.observableArrayList();
        Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms","root","meep");
		Statement stm=con.createStatement();
        ResultSet rs = stm.executeQuery("select *from accounts where account_no="+acno+" and pin="+pn);
        if(rs.next()){
            viewbal.setText("ACCOUNT BALANCE: ₹"+rs.getString(8));
        }
        else{
            viewbal.setText("INVALID PIN");
        }
        rs.close();
        ResultSet rs1 = stm.executeQuery("select *from transactions where sender_no="+acno);
        while(rs1.next()){
            b_table row=new b_table(Integer.parseInt(rs1.getString(1)),Integer.parseInt(rs1.getString(3)),Integer.parseInt(rs1.getString(4)), rs1.getString(5));
            rowlist.add(row);
        }

        trans_id.setCellValueFactory(cellData -> cellData.getValue().transIdProperty().asObject());
        rec_id.setCellValueFactory(cellData -> cellData.getValue().recIdProperty().asObject());
        amount.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());
        date.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());

        table.setItems(rowlist);
        rs1.close();
        stm.close();
        con.close();
    }

}
