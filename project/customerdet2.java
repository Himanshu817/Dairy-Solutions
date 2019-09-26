package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class customerdet2 {

	public customerdet2(){
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/milkmanassistant","root","jainhimanshu");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		pcd =new Text("Customer Detail");
		pcd.setFont(Font.font("Roman",FontWeight.BOLD,30));
		
		pg =new Text("Name");
		pg.setStyle("-fx-border-color:White");
		pl =new Text("Locality");
		
		cow =new RadioButton("Cow");
		buff =new RadioButton("Buffalo");
		both =new RadioButton("Both");
		
		google =new Button("Search");
		google.setPrefSize(60, 30);
		google.setTranslateX(-200);
		search1 =new Button("Search");
		search1.setPrefSize(60, 30);
		search1.setTranslateX(-200);
		search2 =new Button("Search");
		search2.setPrefSize(60, 30);
		search2.setTranslateX(-200);
		
		hbox =new HBox();
		hbox.setSpacing(10);
		hbox.getChildren().addAll(both,search1);
		
	    localty =new ComboBox<>();
	    localty.setPrefSize(90,30);
	    getlocalty();
	    tg =new ComboBox<>();
	    tg.setPrefSize(90, 30);
	    getnames();
	    
	    grp =new ToggleGroup();
	    cow.setToggleGroup(grp);
	    buff.setToggleGroup(grp);
	    both.setToggleGroup(grp);
	    
	    TableColumn<customerdet1, String> colname =new TableColumn<>("Customer Name");
	    colname.setCellValueFactory(new PropertyValueFactory<>("cname"));
	    colname.setMinWidth(100);
	    
	    TableColumn<customerdet1, String> colmobile =new TableColumn<>("Mobile No.");
	    colmobile.setCellValueFactory(new PropertyValueFactory<>("Mobile"));
	    colmobile.setMinWidth(100);
	    
	    TableColumn<customerdet1, String> coladdress =new TableColumn<>("Address");
	    coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
	    coladdress.setMinWidth(100);
	    
	    TableColumn<customerdet1, String> collocality =new TableColumn<>("Locality");
	    collocality.setCellValueFactory(new PropertyValueFactory<>("locality"));
	    collocality.setMinWidth(100);
	    
	    TableColumn<customerdet1, Float> colcowq =new TableColumn<>("Cow quan.");
	    colcowq.setCellValueFactory(new PropertyValueFactory<>("cowq"));
	    colcowq.setMinWidth(100);
	    
	    TableColumn<customerdet1, Float> colbuffq =new TableColumn<>("Buff quan.");
	    colbuffq.setCellValueFactory(new PropertyValueFactory<>("buffq"));
	    colbuffq.setMinWidth(100);
	    
	    TableColumn<customerdet1, String> coldos =new TableColumn<>("Date of Start");
	    coldos.setCellValueFactory(new PropertyValueFactory<>("dos"));
	    coldos.setMinWidth(100);
	    
	    tbl =new TableView<>();
	    tbl.getColumns().addAll(colname,colmobile,coladdress,collocality,colcowq,colbuffq,coldos);
	     
	    grd =new GridPane();
	    grd.setHgap(5);
	    grd.setVgap(5);
	    grd.setPadding(new Insets(40));
	    grd.setGridLinesVisible(false);
	    //grd.setId("cover");
	    
		GridPane.setConstraints(pcd, 0, 0, 4, 1, HPos.CENTER, VPos.CENTER, null, null,new Insets(5));
		grd.getChildren().add(pcd);
		GridPane.setConstraints(pg, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null,new Insets(5));
		grd.getChildren().add(pg);
		GridPane.setConstraints(tg, 2, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null,new Insets(5));
		grd.getChildren().add(tg);
		GridPane.setConstraints(google, 3, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null,new Insets(5));
		grd.getChildren().add(google);
		GridPane.setConstraints(cow, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null,new Insets(5));
		grd.getChildren().add(cow);
		GridPane.setConstraints(buff, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null,new Insets(5));
		grd.getChildren().add(buff);
		GridPane.setConstraints(both, 2, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null,new Insets(5));
		grd.getChildren().add(both);
		GridPane.setConstraints(search1, 3, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null,new Insets(5));
		grd.getChildren().add(search1);
		GridPane.setConstraints(pl, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER, null, null,new Insets(5));
		grd.getChildren().add(pl);
		GridPane.setConstraints(localty, 2, 3, 1, 1, HPos.CENTER, VPos.CENTER, null, null,new Insets(5));
		grd.getChildren().add(localty);
		GridPane.setConstraints(search2, 3, 3, 1, 1, HPos.CENTER, VPos.CENTER, null, null,new Insets(5));
		grd.getChildren().add(search2);
		GridPane.setConstraints(tbl, 0, 4, 4, 1, HPos.CENTER, VPos.CENTER, null, null,new Insets(5));
		grd.getChildren().add(tbl);
		
		google.setOnAction(e->{
			tbl.setItems(getdetailname());
		});
		
		search1.setOnAction(e->{
			tbl.setItems(getdetailq());
		});
		
		search2.setOnAction(e->{
			tbl.setItems(getdetailloc());
		});
		
		lst =FXCollections.observableArrayList();
		
		Scene scene =new Scene(grd,800,500);
		//scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
		Stage stage =new Stage();
		stage.setScene(scene);
		stage.show();
	}
	
	
	Text pcd,pg,pl;
	RadioButton cow,buff,both;
	ComboBox<String> localty,tg;
	Button google,search1,search2;
	TableView<customerdet1> tbl;
	GridPane grd;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	ToggleGroup grp;
	HBox hbox;
    ObservableList<customerdet1> lst;
	

	   ObservableList<customerdet1> getdetailname() {
		 
		   tbl.getItems().removeAll(tbl.getItems());
		try {
			pst =con.prepareStatement("select * from customer where cname=? ");
			pst.setString(1, tg.getSelectionModel().getSelectedItem());
			
			rs =pst.executeQuery();
			while(rs.next())
			{
				customerdet1 ref =new customerdet1(rs.getString("cname"),rs.getString("Mobile") ,rs.getString("address"),rs.getString("locality"),rs.getFloat("cowq"),rs.getFloat("buffq"),rs.getString("dos"));
			    lst.add(ref);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return lst;
	}
	   
	   ObservableList<customerdet1> getdetailloc() {
		
		   tbl.getItems().removeAll(tbl.getItems());
		   try {
			pst =con.prepareStatement("select * from customer where locality=?");
			pst.setString(1, localty.getSelectionModel().getSelectedItem());
			
			rs =pst.executeQuery();
			while(rs.next())
			{
				customerdet1 ref =new customerdet1(rs.getString("cname"),rs.getString("Mobile") ,rs.getString("address"),rs.getString("locality"),rs.getFloat("cowq"),rs.getFloat("buffq"),rs.getString("dos"));
			    lst.add(ref);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return lst;
	   }
			
	   
	   ObservableList<customerdet1> getdetailq() {
			 
		   tbl.getItems().removeAll(tbl.getItems());
		try {
			if(cow.isSelected())
			{
			pst =con.prepareStatement("select * from customer where buffq=0");
			//pst.setFloat(1, 0);
			
			rs =pst.executeQuery();
			while(rs.next())
			{
				customerdet1 ref =new customerdet1(rs.getString("cname"),rs.getString("Mobile") ,rs.getString("address"),rs.getString("locality"),rs.getFloat("cowq"),rs.getFloat("buffq"),rs.getString("dos"));
			    lst.add(ref);
			}
			}
			
			else if(buff.isSelected())
			{
			pst =con.prepareStatement("select * from customer where cowq=0");
			//pst.setFloat(1, 0);
			
			rs =pst.executeQuery();
			while(rs.next())
			{
				customerdet1 ref =new customerdet1(rs.getString("cname"),rs.getString("Mobile") ,rs.getString("address"),rs.getString("locality"),rs.getFloat("cowq"),rs.getFloat("buffq"),rs.getString("dos"));
			    lst.add(ref);
			}
			}
			
			else if(both.isSelected())
			{
			pst =con.prepareStatement("select * from customer where cowq>0 and buffq>0");
			//pst.setFloat(1, 0);
			
			rs =pst.executeQuery();
			while(rs.next())
			{
				customerdet1 ref =new customerdet1(rs.getString("cname"),rs.getString("Mobile") ,rs.getString("address"),rs.getString("locality"),rs.getFloat("cowq"),rs.getFloat("buffq"),rs.getString("dos"));
			    lst.add(ref);
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		return lst;
	}

	void getlocalty() {
		  try {
				pst =con.prepareStatement("select distinct locality from customer");
				 rs =pst.executeQuery();
				 while(rs.next())
				   {  
					  localty.getItems().add(rs.getString("locality")); 
				   }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}

	void getnames() {
		 try {
				pst =con.prepareStatement("select cname from customer");
				 rs =pst.executeQuery();
				 while(rs.next())
				   {  
					  tg.getItems().add(rs.getString("cname")); 
				   }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}

}
