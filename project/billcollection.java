package project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class billcollection{

	public billcollection(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/milkmanassistant","root","jainhimanshu");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		update =new Button("Update");
		update.setPrefSize(60, 30);
		
		tpena =new TextField();
		tpena.setEditable(false);
		
		bilc =new Text("Bill Collection");
		bilc.setFont(Font.font("Italic", FontWeight.BLACK,30 ));
		bilc.setTranslateX(20);
		pn =new Text("Name");
		pn.setTranslateX(80);
		psd =new Text("Start Date");
		ped =new Text("End Date");
		pta =new Text("Total Amount");
		ppa =new Text("Payable Amount");
		
		tn =new ComboBox<String>();
		tn.setPrefSize(150, 30);
		tn.setEditable(true);
		getlist();
		
		sd =new ListView<Date>();
		sd.setPrefSize(170, 200);
		ed =new ListView<Date>();
		ed.setPrefSize(170, 200);
		
		ta =new ListView<Float>();
		ta.setPrefSize(170, 200);
		
		grd =new GridPane();
		grd.setGridLinesVisible(false);
		grd.setHgap(5);
		grd.setVgap(5);
		//grd.setId("cover");
		
		GridPane.setConstraints(bilc, 0, 0, 3, 1, HPos.CENTER, VPos.CENTER,null,null,new Insets(10));
		grd.getChildren().add(bilc);
		GridPane.setConstraints(pn, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(pn);
		GridPane.setConstraints(tn, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(tn);
		GridPane.setConstraints(psd, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(psd);
		GridPane.setConstraints(ped, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(ped);
		GridPane.setConstraints(pta, 2, 2, 1, 1, HPos.CENTER, VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(pta);
		GridPane.setConstraints(sd, 0, 3, 1, 1, HPos.CENTER, VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(sd);
		GridPane.setConstraints(ed, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(ed);
		GridPane.setConstraints(ta, 2, 3, 1, 1, HPos.CENTER, VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(ta);
		GridPane.setConstraints(ppa, 0, 4, 1, 1, HPos.CENTER, VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(ppa);
		GridPane.setConstraints(tpena, 1, 4, 1, 1, HPos.CENTER, VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(tpena);
		GridPane.setConstraints(update, 1, 5, 1, 1, HPos.CENTER, VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(update);
		
		ta.setOnMouseClicked(e->{
			if(e.getClickCount()==2)
			{
				tpena.setText(ta.getSelectionModel().getSelectedItem()+"");
			}
		});
		tn.setOnAction(e->{
			prntselected();
		});
		
		update.setOnAction(e->{
			doupdate();
		});
		
		Scene scene =new Scene(grd,600,500);
		//scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
		Stage stage =new Stage();
		stage.setScene(scene);
		stage.show();
	}
	GridPane grd;
	Text  bilc,pn,psd,ped,pta,ppa;
	ListView<Date> sd,ed;
	ListView<Float> ta;
	TextField tpena;
	ComboBox<String> tn;
	Button update;
	Connection con;
	PreparedStatement pst,pst1;
	ResultSet rs,rs1;
	

	   void doupdate() {
		
		   try {
			pst=con.prepareStatement("update billgeneration set status=1,paydate=curdate() where cname=? and sdate=?");
            pst.setString(1, tn.getSelectionModel().getSelectedItem());
            pst.setDate(2,sd.getSelectionModel().getSelectedItem());
            pst.executeUpdate();
            
            tn.getItems().removeAll(tn.getItems());
            sd.getItems().removeAll(sd.getItems());
  		    ed.getItems().removeAll(ed.getItems());
  		    ta.getItems().removeAll(ta.getItems());
  		    
  		    getlist();
            
            /*pst1 =con.prepareStatement("select distinct cname from billgeneration where status=?");
            pst1.setInt(1, 0);
			rs1 =pst1.executeQuery();
			while(rs1.next())
			{
				tn.getItems().add(rs1.getString("cname"));
			}*/ 
		   } catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void prntselected() {
		  
		  sd.getItems().removeAll(sd.getItems());
		  ed.getItems().removeAll(ed.getItems());
		  ta.getItems().removeAll(ta.getItems());
		try {
			
			
			pst =con.prepareStatement("select sdate,edate,total from billgeneration where cname=? and status=?");
			pst.setString(1, tn.getSelectionModel().getSelectedItem());
			pst.setInt(2, 0);
			rs =pst.executeQuery();
			while(rs.next())
			{
			sd.getItems().add(rs.getDate("sdate"));
			ed.getItems().add(rs.getDate("edate"));
			ta.getItems().add(rs.getFloat("total"));
			tpena.setText("0");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	void getlist() {
		try {
			pst =con.prepareStatement("select distinct cname from billgeneration where status=?");
			pst.setInt(1, 0);
			rs =pst.executeQuery();
			while(rs.next())
			{
				tn.getItems().add(rs.getString("cname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
