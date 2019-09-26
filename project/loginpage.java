package project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class loginpage extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	Text ph, pu, pp;
	TextField tu;
	PasswordField tp;
	GridPane grd;
	Button login, sendSMS;
	HBox hbox;

	@Override
	public void start(Stage stage) throws Exception {

		ph = new Text("Login");
		ph.setFont(Font.font("Italic", FontWeight.BLACK, 30));

		pu = new Text("Admin ID");
		pp = new Text("Password");

		tu = new TextField();
		tu.setPromptText("Admin ID please...");

		tp = new PasswordField();
		tp.setPromptText("Password please...");

		login = new Button("Login");
		login.setPrefSize(70, 30);
		sendSMS = new Button("Send SMS");
		sendSMS.setPrefSize(85, 30);

		hbox = new HBox();
		hbox.setSpacing(20);
		hbox.getChildren().addAll(login, sendSMS);
		hbox.setTranslateX(30);
		ImageView logo4 = new ImageView(new Image(loginpage.class.getResourceAsStream("lgoinsymbol.png")));
		logo4.setFitWidth(100);
		logo4.setFitHeight(100);

		grd = new GridPane();
		grd.setPadding(new Insets(20, 20, 10, 70));
		grd.setGridLinesVisible(false);
		grd.setId("login");

		GridPane.setConstraints(ph, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(20));
		grd.getChildren().add(ph);
		GridPane.setConstraints(pu, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5));
		grd.getChildren().add(pu);
		GridPane.setConstraints(tu, 0, 2, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5));
		grd.getChildren().add(tu);
		GridPane.setConstraints(pp, 0, 3, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5));
		grd.getChildren().add(pp);
		GridPane.setConstraints(tp, 0, 4, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5));
		grd.getChildren().add(tp);
		GridPane.setConstraints(logo4, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(logo4);
		GridPane.setConstraints(hbox, 0, 5, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(hbox);

		sendSMS.setOnAction(e -> {
			System.out.println("Sent");
			SST_SMS.bceSunSoftSend("7017208231", "Admin ID: admin\n Password: admin");
		});

		login.setOnAction(new EventHandler<ActionEvent>() {
			String s = new String("admin");
			String p = new String("admin");

			@Override
			public void handle(ActionEvent event) {
				if (s.equals(tu.getText()) && p.equals(tp.getText())) {
					new dashboard();
				}

				else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Wrong Info");
					alert.setContentText("Wrong ID and password combination");
					alert.show();
					tp.clear();
				}

			}
		});

		Scene scene = new Scene(grd, 400, 400);
		scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
		stage.setScene(scene);
		stage.show();

	}

}
