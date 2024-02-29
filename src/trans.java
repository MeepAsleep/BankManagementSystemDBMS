import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class trans {
    @FXML
    private Button go_back;

    @FXML
    private TableView<t_table> trans_table;

    @FXML
    private TableColumn<t_table, Integer> trans_id;

    @FXML
    private TableColumn<t_table, Integer> send_id;

    @FXML
    private TableColumn<t_table, Integer> rec_id;

    @FXML
    private TableColumn<t_table, Integer> amount;

    @FXML
    private TableColumn<t_table, String> date;
    
    @FXML
    private void go_back(ActionEvent event) throws Exception{
        App a = new App();
        a.changeScene("admin.fxml");
    }

    @FXML
    private void show(ActionEvent event) throws Exception,SQLException{
        ObservableList<t_table> rowlist = FXCollections.observableArrayList();
        Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms","root","meep");
		Statement stm=con.createStatement();
        ResultSet rs = stm.executeQuery("select *from transactions");
        while(rs.next()){
            t_table row=new t_table(Integer.parseInt(rs.getString(1)),Integer.parseInt(rs.getString(2)),Integer.parseInt(rs.getString(3)),Integer.parseInt(rs.getString(4)),rs.getString(5));
            rowlist.add(row);
        }

        trans_id.setCellValueFactory(cellData -> cellData.getValue().transIdProperty().asObject());
        send_id.setCellValueFactory(cellData -> cellData.getValue().sendIdProperty().asObject());
        rec_id.setCellValueFactory(cellData -> cellData.getValue().recIdProperty().asObject());
        amount.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());
        date.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());

        trans_table.setItems(rowlist);
        rs.close();
        stm.close();
        con.close();
    }

}