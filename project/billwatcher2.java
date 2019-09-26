package project;

import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.control.DatePicker;
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

public class billwatcher2 {
	
     public billwatcher2(){
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/milkmanassistant","root","jainhimanshu");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		lst =FXCollections.observableArrayList();
		
		pbw =new Text("Bill Watcher");
		//pbw.setTranslateX(90);
		pbw.setFont(Font.font("Roman",FontWeight.BLACK,30));
		
		pd =new Text("Date");
		pf =new Text("from");
		pt =new Text("to");
		
		fullpaid =new RadioButton("Full paid");
		fullpaid.setTranslateX(20);
		pending =new RadioButton("Pending");
		pending.setTranslateX(20);
		
		grp =new ToggleGroup();
		fullpaid.setToggleGroup(grp);
		pending.setToggleGroup(grp);
		
		sd =new DatePicker();
		ed =new DatePicker();
		
		hbox =new HBox();
		hbox.setSpacing(10);
        hbox.getChildren().addAll(pt,ed);
		
		fetch =new Button("Fetch");
	    fetch.setPrefSize(60, 30);
	    
	    
	    tbl =new TableView<billwatcher1>();
	    tbl.setTranslateX(20);
	    
	    TableColumn<billwatcher1, String> colname =new TableColumn<>("Customer Name");
	    colname.setCellValueFactory(new PropertyValueFactory<>("cname"));
	    colname.setMinWidth(100);
	    TableColumn<billwatcher1, Date> colsdate =new TableColumn<>("Starting Date");
	    colsdate.setCellValueFactory(new PropertyValueFactory<>("sdate"));
		colsdate.setMinWidth(100);
		TableColumn<billwatcher1, Date> coledate =new TableColumn<>("Ending Date");
	    coledate.setCellValueFactory(new PropertyValueFactory<>("edate"));
	    coledate.setMinWidth(100);
	    TableColumn<billwatcher1, Float> colcowq =new TableColumn<>("Cow quantity");
	    colcowq.setCellValueFactory(new PropertyValueFactory<>("cowq"));
	    colcowq.setMinWidth(100);
	    TableColumn<billwatcher1, Float> colbuffq =new TableColumn<>("Buff guantity");
	    colbuffq.setCellValueFactory(new PropertyValueFactory<>("buffq"));
	    colbuffq.setMinWidth(100);
	    TableColumn<billwatcher1, Float> colcowa =new TableColumn<>("Cow amount");
	    colcowa.setCellValueFactory(new PropertyValueFactory<>("cowamnt"));
	    colcowa.setMinWidth(100);
	    TableColumn<billwatcher1, Float> colbuffa =new TableColumn<>("Buff amount");
	    colbuffa.setCellValueFactory(new PropertyValueFactory<>("buffamnt"));
	    colbuffa.setMinWidth(100);
	    TableColumn<billwatcher1, String> coltotal =new TableColumn<>("Total amount");
	    coltotal.setCellValueFactory(new PropertyValueFactory<>("total"));
	    coltotal.setMinWidth(100);
	    TableColumn<billwatcher1, Integer> colstatus =new TableColumn<>("Status");
	    colstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
	    colstatus.setMinWidth(100);
	    TableColumn<billwatcher1, Date> colpaydate =new TableColumn<>("Paydate");
	    colpaydate.setCellValueFactory(new PropertyValueFactory<>("paydate"));
	    colpaydate.setMinWidth(100);
	    
	    tbl.getColumns().addAll(colname,colsdate,coledate,colcowq,colbuffq,colcowa,colbuffa,coltotal,colstatus,colpaydate);
		
	    grd =new GridPane();
	    grd.setVgap(5);
	    grd.setHgap(5);
	    grd.setPadding(new Insets(30));
	    grd.setGridLinesVisible(false);
	    //grd.setId("cover");
	    
	    GridPane.setConstraints(pbw, 0, 0, 5, 1, HPos.CENTER,VPos.CENTER, null, null, new Insets(5));
	    grd.getChildren().add(pbw);
	    GridPane.setConstraints(pd, 1, 1, 1, 1, HPos.CENTER,VPos.CENTER, null, null, new Insets(5));
	    grd.getChildren().add(pd);
	    GridPane.setConstraints(pf, 2, 1, 1, 1, HPos.CENTER,VPos.CENTER, null, null, new Insets(5));
	    grd.getChildren().add(pf);
	    GridPane.setConstraints(sd, 3, 1, 1, 1, HPos.CENTER,VPos.CENTER, null, null, new Insets(5));
	    grd.getChildren().add(sd);
	    GridPane.setConstraints(hbox, 4, 1, 2, 1, HPos.CENTER,VPos.CENTER, null, null, new Insets(5));
	    grd.getChildren().add(hbox);
	    GridPane.setConstraints(fullpaid, 1, 2, 1, 1, HPos.CENTER,VPos.CENTER, null, null, new Insets(5));
	    grd.getChildren().add(fullpaid);
	    GridPane.setConstraints(pending, 2, 2, 1, 1, HPos.CENTER,VPos.CENTER, null, null, new Insets(5));
	    grd.getChildren().add(pending);
	    GridPane.setConstraints(fetch, 3, 2, 1, 1, HPos.CENTER,VPos.CENTER, null, null, new Insets(5));
	    grd.getChildren().add(fetch);
	    GridPane.setConstraints(tbl, 0, 3, 5, 1, HPos.CENTER,VPos.CENTER, null, null, new Insets(5));
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


    Text pbw,pd,pt,pf;
    RadioButton fullpaid,pending;
    Button fetch;
    DatePicker sd,ed;
    TableView<billwatcher1> tbl;
    GridPane grd;
    HBox hbox;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    ObservableList<billwatcher1> lst;
    ToggleGroup grp;
    
	


	 ObservableList<billwatcher1> getlist() {
		 
		 tbl.getItems().removeAll(tbl.getItems());
			try {
				if(fullpaid.isSelected()){
				pst =con.prepareStatement("select * from billgeneration where sdate>=? and edate<=? and status=1");
				pst.setDate(1, Date.valueOf(sd.getValue()));
				pst.setDate(2, Date.valueOf(ed.getValue()));
				
				rs =pst.executeQuery();
				
				while(rs.next())
				{
					billwatcher1 ref =new billwatcher1(rs.getString("cname"),rs.getDate("sdate") ,rs.getDate("edate"),rs.getFloat("cowq") ,rs.getFloat("buffq"), rs.getFloat("cowamnt"), rs.getFloat("buffamnt"), rs.getFloat("total"), rs.getInt("status"), rs.getDate("paydate"));
				    lst.add(ref);
				}
				}
				
				else if(pending.isSelected()){
					pst =con.prepareStatement("select * from billgeneration where sdate>=? and edate<=? and status=0");
					pst.setDate(1, Date.valueOf(sd.getValue()));
					pst.setDate(2, Date.valueOf(ed.getValue()));
					
					rs =pst.executeQuery();
					
					while(rs.next())
					{
						billwatcher1 ref =new billwatcher1(rs.getString("cname"),rs.getDate("sdate") ,rs.getDate("edate"),rs.getFloat("cowq") ,rs.getFloat("buffq"), rs.getFloat("cowamnt"), rs.getFloat("buffamnt"), rs.getFloat("total"), rs.getInt("status"), rs.getDate("paydate"));
					    lst.add(ref);
					}
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			return lst;
	}

}
