import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class App extends Application {

    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root;
        stg = primaryStage;
        primaryStage.setResizable(false);
        root = FXMLLoader.load(getClass().getResource("MS.fxml"));
        Scene loginscene = new Scene(root);
        primaryStage.setTitle("BANK MANAGEMENT SYSTEM");
        primaryStage.setScene(loginscene);
        primaryStage.show();
    }

    public void changeScene(String fxml) throws Exception {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

public static void main(String[] args) {
        launch(args);
    }
}