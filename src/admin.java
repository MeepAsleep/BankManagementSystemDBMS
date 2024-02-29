import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class admin {
    
    @FXML
    private void go_back(ActionEvent ae) throws Exception {
        App a = new App();
        a.changeScene("MS.fxml");
    }

    @FXML
    private void ad_trans(ActionEvent ae) throws Exception {
        App a = new App();
        a.changeScene("trans.fxml");
    }
    
    @FXML
    private void ad_loan(ActionEvent ae) throws Exception {
        App a = new App();
        a.changeScene("aloan.fxml");
    }

    @FXML
    private void ad_accts(ActionEvent ae) throws Exception {
        App a = new App();
        a.changeScene("ainfo.fxml");
    }

    @FXML
    private void ad_del(ActionEvent ae) throws Exception {
        App a = new App();
        a.changeScene("del.fxml");
    }
}
