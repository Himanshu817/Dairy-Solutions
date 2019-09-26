package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class qwer extends Application{
	Connection con;
	PreparedStatement pst,pst1;
	public qwer(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/milkman","root","jainhimanshu");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Connected");
	}
	public static void main(String args[]){
		launch(args);
	}
	ListView<String> lst;
	GridPane grd;
	Button btnP,btnM,btnS;
	TextField txtC,txtB;
	Label lblC,lblB,lblL,lblH;
	
	@Override
	public void start(Stage stage) throws Exception {
		lst=new ListView<String>();
		fillCname();
		lst.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		lst.setPrefSize(200, 400);
		
		grd=new GridPane();
//		grd.setGridLinesVisible(true);
		grd.setPadding(new Insets(25));
		grd.setHgap(5);
		grd.setVgap(5);
		
		lblH=new Label("DAILY UPDATE");
		lblH.setFont(Font.font("Tw Cen MT Condensed Extra Bold", FontWeight.BOLD, 17));
		grd.setConstraints(lblH, 0, 0, 3, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		lblL=new Label("CUSTOMERS");
		grd.setConstraints(lblL, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null,new Insets(2));
		grd.setConstraints(lst, 0, 2, 1, 5, HPos.CENTER, VPos.CENTER, null, null,new Insets(10));
		btnP=new Button("PostEntry");
		grd.setConstraints(btnP, 1, 2, 2, 1, HPos.CENTER, VPos.CENTER, null, null,new Insets(10));
		btnM=new Button("Missed");
		grd.setConstraints(btnM, 1, 3, 2, 1, HPos.CENTER, VPos.CENTER, null, null,new Insets(10));
		lblC=new Label("Cow:");
		grd.setConstraints(lblC, 1, 4, 1, 1, HPos.CENTER, VPos.CENTER, null, null,new Insets(50,10,10,10));
		lblB=new Label("Buffalo:");
		grd.setConstraints(lblB, 1, 5, 1, 1, HPos.CENTER, VPos.CENTER, null, null,new Insets(10));
		txtC=new TextField();
		txtC.setPrefWidth(50);
		grd.setConstraints(txtC, 2, 4, 1, 1, HPos.CENTER, VPos.CENTER, null, null,new Insets(50,10,10,10));
		txtB=new TextField();
		txtB.setPrefWidth(50);
		grd.setConstraints(txtB, 2, 5, 1, 1, HPos.CENTER, VPos.CENTER, null, null,new Insets(10));
		btnS=new Button("Save");
		grd.setConstraints(btnS, 1, 6, 2, 1, HPos.CENTER, VPos.TOP, null, null,new Insets(2));
		grd.getChildren().addAll(lblH,lblL,lst,btnP,btnM,lblC,lblB,txtC,txtB,btnS);
		
		
		Scene scene=new Scene(grd,450,500);
		stage.setScene(scene);
		stage.show();
		
		// Events
		btnP.setOnAction(e->{
			
		ObservableList<Integer> ind=lst.getSelectionModel().getSelectedIndices();	
			try {
			for (Integer val : ind) {
				int index=val;
				pst=con.prepareStatement("insert into dailyupdate values(?,?,?,curdate())");
				pst.setString(1, lst.getItems().get(index));
				pst1=con.prepareStatement("select cqty,bqty from customers where cname=?");
				pst1.setString(1, lst.getItems().get(index));
				ResultSet rs=pst1.executeQuery();
				Float c=0f,b=0f;
				if(rs.next()){
					c=Float.parseFloat(rs.getString("cqty"));
					b=Float.parseFloat(rs.getString("bqty"));
				}
				pst.setFloat(2, c);
				pst.setFloat(3, b);
				pst.executeUpdate();
			}
			lst.getItems().removeAll(lst.getSelectionModel().getSelectedItems());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		});
		btnS.setOnAction(e->{
			ObservableList<Integer> ind=lst.getSelectionModel().getSelectedIndices();	
			try {
			for (Integer val : ind) {
				int index=val;
				pst=con.prepareStatement("insert into dailyupdate values(?,?,?,curdate())");
				pst.setString(1, lst.getItems().get(index));
				
				pst.setFloat(2, Float.parseFloat(txtC.getText()));
				pst.setFloat(3, Float.parseFloat(txtB.getText()));
				pst.executeUpdate();
			}
			lst.getItems().removeAll(lst.getSelectionModel().getSelectedItems());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		btnM.setOnAction(e->{
			lst.getItems().removeAll(lst.getSelectionModel().getSelectedItems());
			lst.getSelectionModel().clearSelection();
		});
	}
	void fillCname(){
		ArrayList<String> lst1=new ArrayList<String>();
		try {
			pst=con.prepareStatement("select distinct cname from customers");
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				lst1.add(rs.getString("cname"));
			}
			lst.getItems().addAll(lst1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}