import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class user {
    
    @FXML
    private Button us_bal;

    @FXML
    private Button us_change;

    @FXML
    private Button us_with;

    @FXML
    private Button us_dep;

    @FXML
    private Button us_loan;
    
    @FXML
    private Button us_trans;

    @FXML
    private Button bt_goBack;

    @FXML
    private void goBack(ActionEvent ae) throws Exception {
        App a = new App();
        a.changeScene("MS.fxml");
    }

    @FXML
    private void op_bal(ActionEvent ae) throws Exception {
        App a = new App();
        a.changeScene("bal.fxml");
    }
    
    @FXML
    private void op_change(ActionEvent ae) throws Exception {
        App a = new App();
        a.changeScene("change.fxml");
    }

    @FXML
    private void op_with(ActionEvent ae) throws Exception {
        App a = new App();
        a.changeScene("with.fxml");
    }

    @FXML
    private void op_dep(ActionEvent ae) throws Exception {
        App a = new App();
        a.changeScene("dep.fxml");
    }

    @FXML
    private void op_loan(ActionEvent ae) throws Exception {
        App a = new App();
        a.changeScene("loan.fxml");
    }

    @FXML
    private void op_trans(ActionEvent ae) throws Exception {
        App a = new App();
        a.changeScene("transfer.fxml");
    }
}
