package project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class paymenthistory2{

	 public paymenthistory2(){
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con =DriverManager.getConnection("jdbc:mysql://localhost:3306/milkmanassistant","root","jainhimanshu");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			lst =FXCollections.observableArrayList();
			
			pph =new Text("Payment History");
			pph.setFont(Font.font("Roman",FontWeight.BLACK,30));
			
			pn =new Text("Name");
			pn.setTranslateX(100);
			pd=new Text("Date");
			pf =new Text("from");
			pt =new Text("to");
			
			sd =new DatePicker();
			ed =new DatePicker();
			
			fetch =new Button("Fetch");
			fetch.setPrefSize(60, 30);
			fetch.setTranslateX(-200);
			
			hbox =new HBox();
			hbox.setSpacing(20);
			hbox.getChildren().addAll(pt,ed);
			
			tn =new ComboBox<>();
			tn.setMinSize(175, 30);
			tn.setTranslateX(15);
			getnames();
			
			tbl =new TableView<>();
			TableColumn<paymenthistory1, String> colname =new TableColumn<>("Customer Name");
		    colname.setCellValueFactory(new PropertyValueFactory<>("cname"));
		    colname.setMinWidth(100);
		    TableColumn<paymenthistory1, Date> colsdate =new TableColumn<>("Starting Date");
		    colsdate.setCellValueFactory(new PropertyValueFactory<>("sdate"));
			colsdate.setMinWidth(100);
			TableColumn<paymenthistory1, Date> coledate =new TableColumn<>("Ending Date");
		    coledate.setCellValueFactory(new PropertyValueFactory<>("edate"));
		    coledate.setMinWidth(100);
		    TableColumn<paymenthistory1, Float> colcowq =new TableColumn<>("Cow quantity");
		    colcowq.setCellValueFactory(new PropertyValueFactory<>("cowq"));
		    colcowq.setMinWidth(100);
		    TableColumn<paymenthistory1, Float> colbuffq =new TableColumn<>("Buff quantity");
		    colbuffq.setCellValueFactory(new PropertyValueFactory<>("buffq"));
		    colbuffq.setMinWidth(100);
		    TableColumn<paymenthistory1, Float> colcowa =new TableColumn<>("Cow amount");
		    colcowa.setCellValueFactory(new PropertyValueFactory<>("cowamnt"));
		    colcowa.setMinWidth(100);
		    TableColumn<paymenthistory1, Float> colbuffa =new TableColumn<>("Buff amount");
		    colbuffa.setCellValueFactory(new PropertyValueFactory<>("buffamnt"));
		    colbuffa.setMinWidth(100);
		    TableColumn<paymenthistory1, String> coltotal =new TableColumn<>("Total amount");
		    coltotal.setCellValueFactory(new PropertyValueFactory<>("total"));
		    coltotal.setMinWidth(100);
		    TableColumn<paymenthistory1, Integer> colstatus =new TableColumn<>("Status");
		    colstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		    colstatus.setMinWidth(100);
		    TableColumn<paymenthistory1, Date> colpaydate =new TableColumn<>("Paydate");
		    colpaydate.setCellValueFactory(new PropertyValueFactory<>("paydate"));
		    colpaydate.setMinWidth(100);
		    
		    tbl.getColumns().addAll(colname,colsdate,coledate,colcowq,colbuffq,colcowa,colbuffa,coltotal,colstatus,colpaydate);
			
		    grd =new GridPane();
			grd.setHgap(5);
			grd.setVgap(5);
			grd.setPadding(new Insets(40));
			grd.setGridLinesVisible(false);
			//grd.setId("cover");
			
			GridPane.setConstraints(pph, 0, 0, 6, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5));
			grd.getChildren().add(pph);
			GridPane.setConstraints(pn, 2, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5));
			grd.getChildren().add(pn);
			GridPane.setConstraints(tn, 3, 1, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5));
			grd.getChildren().add(tn);
			GridPane.setConstraints(pd, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5));
			grd.getChildren().add(pd);
			GridPane.setConstraints(pf, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5));
			grd.getChildren().add(pf);
			GridPane.setConstraints(sd, 2, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5));
			grd.getChildren().add(sd);
			GridPane.setConstraints(hbox, 3, 2, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5));
			grd.getChildren().add(hbox);
			GridPane.setConstraints(fetch, 5, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5));
			grd.getChildren().add(fetch);
			GridPane.setConstraints(tbl, 0, 3, 6, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5));
			grd.getChildren().add(tbl);
			
			fetch.setOnAction(e->{
				tbl.setItems(getlist());
			});
			
			Scene scene =new Scene(grd,1100,500);
			//scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
			Stage stage =new Stage();
			stage.setScene(scene);
			stage.show();
		}
	
	
	DatePicker sd,ed;
	Text pn,pd,pf,pt,pph;
	HBox hbox;
	ComboBox<String> tn;
	TableView<paymenthistory1> tbl;
	Button fetch;
	GridPane grd;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	ObservableList<paymenthistory1> lst;

	

	ObservableList<paymenthistory1> getlist() {
		 
		 tbl.getItems().removeAll(tbl.getItems());
			try {
				pst =con.prepareStatement("select * from billgeneration where sdate>=? and edate<=? and cname=?");
				pst.setDate(1, Date.valueOf(sd.getValue()));
				pst.setDate(2, Date.valueOf(ed.getValue()));
				pst.setString(3, tn.getSelectionModel().getSelectedItem());
				
				rs =pst.executeQuery();
				
				while(rs.next())
				{
					paymenthistory1 ref =new paymenthistory1(rs.getString("cname"),rs.getDate("sdate") ,rs.getDate("edate"),rs.getFloat("cowq") ,rs.getFloat("buffq"), rs.getFloat("cowamnt"), rs.getFloat("buffamnt"), rs.getFloat("total"), rs.getInt("status"), rs.getDate("paydate"));
				    lst.add(ref);
				}
	           } catch (SQLException e) {
		               e.printStackTrace();
	            }
			return lst;
}
	void getnames() {
		try {
			pst =con.prepareStatement("select distinct cname from billgeneration");
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
