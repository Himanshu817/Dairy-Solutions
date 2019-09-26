package project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Enrollcustomer {

	public Enrollcustomer() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/milkmanassistant", "root", "jainhimanshu");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		cal = Calendar.getInstance();
		pec = new Label("Enroll Customer");
		pec.setFont(Font.font("Roman", FontWeight.BLACK, 30));
		pec.setTranslateX(20);

		pn = new Label("Name");
		pmn = new Label("mobile no.");
		pa = new Label("Address");
		pl = new Label("locality");
		pdos = new Label("Date Of start");

		date = new DatePicker();
		date.setValue(LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE)));

		tn = new TextField();
		tn.setPromptText("Name please...");
		tmn = new TextField();
		tmn.setPromptText("Mobile no. please...");
		tl = new TextField();
		tl.setPromptText("Loaclity please...");
		tcq = new TextField("0");
		tcq.setPromptText("Cow Qty");
		tbq = new TextField("0");
		tbq.setPromptText("Buffalo Qty");
		Addrs = new TextArea();
		Addrs.setPrefSize(150, 100);
		/*
		 * tdos =new TextField(); tdos.setPromptText("Enter Date");
		 */

		New = new Button("New");
		New.setPrefSize(60, 30);
		Delete = new Button("Delete");
		Delete.setPrefSize(60, 30);
		save = new Button("Save");
		save.setPrefSize(60, 30);
		update = new Button("Update");
		update.setPrefSize(60, 30);

		hbox = new HBox();
		hbox.setSpacing(10);
		hbox.getChildren().addAll(New, save, Delete, update);

		cow = new CheckBox("Cow");
		buffalo = new CheckBox("Buffalo");

		save.setOnAction(e -> {

			String mobile = "^[7896]\\d{9}$";
			if (tn.getText().isEmpty())
				tn.setStyle("-fx-border-color:red");
			if (Addrs.getText().isEmpty()) {
				Addrs.setStyle("-fx-border-color:red");
			}
			if (tmn.getText().isEmpty()) {
				tmn.setStyle("-fx-border-color:red");
			}
			if (tl.getText().isEmpty()) {
				tl.setStyle("-fx-border-color:red");
			}
			if (date.getValue() == null)
				date.setStyle("-fx-border-color:red");
			if (!tmn.getText().matches(mobile)) {
				tmn.setStyle("-fx-border-color:red");
			}
			else {
					dosave();
			}
		});

		Delete.setOnAction(e -> {
			dodelete();
		});

		update.setOnAction(e -> {
			doupdate();
		});

		New.setOnAction(e -> {
			tn.setStyle("-fx-border-color:default");
			tmn.setStyle("-fx-border-color:default");
			Addrs.setStyle("-fx-border-color:default");
			tl.setStyle("-fx-border-color:default");
			tn.clear();
			tmn.clear();
			Addrs.clear();
			tl.clear();
			tcq.setText("0");
			tbq.setText("0");
		});

		/*
		 * dd =new ComboBox<String>(); mm =new ComboBox<String>(); yyyy =new
		 * ComboBox<String>();
		 */

		grd = new GridPane();
		grd.setHgap(5);
		grd.setVgap(5);
		grd.setPadding(new Insets(10, 30, 10, 70));
		grd.setGridLinesVisible(false);
		// grd.setId("cover");

		GridPane.setConstraints(pec, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		GridPane.setConstraints(pn, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		GridPane.setConstraints(pmn, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		GridPane.setConstraints(pa, 0, 3, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		GridPane.setConstraints(pl, 0, 5, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		GridPane.setConstraints(cow, 0, 6, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		GridPane.setConstraints(buffalo, 0, 7, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		GridPane.setConstraints(pdos, 0, 8, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		GridPane.setConstraints(tn, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		GridPane.setConstraints(tmn, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		GridPane.setConstraints(Addrs, 1, 3, 1, 2, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		GridPane.setConstraints(tl, 1, 5, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		GridPane.setConstraints(tcq, 1, 6, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		GridPane.setConstraints(tbq, 1, 7, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		GridPane.setConstraints(date, 1, 8, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		GridPane.setConstraints(hbox, 0, 9, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));

		grd.getChildren().addAll(pec, pn, pmn, pa, pl, cow, buffalo, pdos, tn, tmn, Addrs, tl, tcq, tbq, date, hbox);

		Scene scene = new Scene(grd, 500, 500);
		// scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}

	GridPane grd;
	Label pn, pmn, pa, pl, pdos, pec;
	TextField tn, tmn, tl, tcq, tbq;
	CheckBox cow, buffalo;
	Button New, Delete, save, update;
	TextArea Addrs;
	HBox hbox, hbox1;
	ComboBox<String> dd, mm, yyyy;
	Connection con;
	PreparedStatement pst;
	DatePicker date;
	Calendar cal;

	void dosave() {
		try {

			pst = con.prepareStatement("insert into customer values (?,?,?,?,?,?,?)");
			pst.setString(1, tn.getText());
			pst.setString(2, tmn.getText());
			pst.setString(3, Addrs.getText());
			pst.setString(4, tl.getText());
			pst.setFloat(5, Integer.parseInt(tcq.getText()));
			pst.setFloat(6, Integer.parseInt(tbq.getText()));
			pst.setDate(7, Date.valueOf(date.getValue()));

			pst.executeUpdate();

			tn.clear();
			tmn.clear();
			Addrs.clear();
			tl.clear();
			tcq.setText(0 + "");
			tbq.setText(0 + "");
			date.setValue(LocalDate.of(cal.get(Calendar.DATE), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR)));

		} catch (SQLException e) {

		}

	}

	void dodelete() {
		try {

			pst = con.prepareStatement("delete from customer where cname=?");
			pst.setString(1, tn.getText());
			int kitne = pst.executeUpdate();

			if (kitne == 0)
				System.out.println("Invalid Record");
			else
				System.out.println("Record Deleted");
		} catch (SQLException e) {

		}
	}

	void doupdate() {
		try {

			pst = con.prepareStatement("update customer set Mobile=?,lcality=?");
			pst.setString(1, tmn.getText());
			pst.setString(2, tl.getText());
			int kitne = pst.executeUpdate();

			if (kitne == 0)
				System.out.println("Invalid Record");
			else
				System.out.println("Record Updated");
		} catch (SQLException e) {

		}
	}
}
