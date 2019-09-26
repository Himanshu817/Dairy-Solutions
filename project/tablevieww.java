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
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class tablevieww extends Application
{
public static void main(String[] args) 
{
// TODO Auto-generated method stub
launch(args);
}
Connection con;
PreparedStatement pst;
ResultSet rs;
public tablevieww()
{
try {
Class.forName("com.mysql.jdbc.Driver");
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/milkmanassistant","root","jainhimanshu");
} 
catch (Exception e)
{
e.printStackTrace();
}
}
TableView<billgenerate> tbl;

TextField txt=new TextField();
Button b=new Button("Search");
Button bb=new Button("Show All");
@Override
public void start(Stage stage) throws Exception
{
	
tbl=new TableView<>();
//bb.setPrefSize(90, 30);

TableColumn<billgenerate, String>colname=new TableColumn<>("Customer Name");
colname.setCellValueFactory(new PropertyValueFactory<>("cname"));
colname.setMinWidth(100);

TableColumn<billgenerate, Date>colSdate=new TableColumn<>("Start Date");
colSdate.setCellValueFactory(new PropertyValueFactory<>("sdate"));
colSdate.setMinWidth(100);

TableColumn<billgenerate, Date>coledate=new TableColumn<>("End date");
coledate.setCellValueFactory(new PropertyValueFactory<>("edate"));
coledate.setMinWidth(100);

TableColumn<billgenerate, Float>colcowq=new TableColumn<>("Cow Quantity");
colcowq.setCellValueFactory(new PropertyValueFactory<>("cowq"));
colcowq.setMinWidth(100);

TableColumn<billgenerate, Float>colbuffq=new TableColumn<>("Buffalo Quantity");
colbuffq.setCellValueFactory(new PropertyValueFactory<>("buffq"));
colbuffq.setMinWidth(100);

TableColumn<billgenerate, Float>coltotal=new TableColumn<>("Total");
coltotal.setCellValueFactory(new PropertyValueFactory<>("total"));
coltotal.setMinWidth(100);

TableColumn<billgenerate, Integer>colstatus=new TableColumn<>("Status");
colstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
colstatus.setMinWidth(100);

TableColumn<billgenerate, Date>colpaydate=new TableColumn<>("Pay Date");
colpaydate.setCellValueFactory(new PropertyValueFactory<>("paydate"));
colpaydate.setMinWidth(100);

tbl.getColumns().addAll(colname,colSdate,coledate,colcowq,colbuffq,coltotal,colstatus,colpaydate);

VBox vbox=new VBox();
b.setTranslateX(200);
b.setPadding(new Insets(10));
vbox.getChildren().addAll(txt,b,bb,tbl);

b.setOnAction(e->{
	tbl.setItems(getSomeRows());
	});

bb.setOnAction(e->{
	tbl.setItems(getRows());
	});

Scene scene=new Scene(vbox,800,500);
stage.setScene(scene);
stage.show();
}

ObservableList<billgenerate> getRows()
{
ObservableList<billgenerate> lst=FXCollections.observableArrayList();
try {
pst=con.prepareStatement("select * from billgeneration");
 rs=pst.executeQuery();
while(rs.next())
{
	billgenerate ref=new billgenerate(rs.getString("cname"),rs.getDate("sdate"),rs.getDate("edate"),rs.getFloat("cowq"),rs.getFloat("buffq"),rs.getFloat("total"),rs.getInt("status"),rs.getDate("paydate"));
    lst.add(ref);
}
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
return lst;
}


ObservableList<billgenerate> getSomeRows()
{
ObservableList<billgenerate> lst=FXCollections.observableArrayList();
try {
pst=con.prepareStatement("select * from billgeneration where cname like '%"+txt.getText()+"%'");
ResultSet rs=pst.executeQuery();
while(rs.next())
{
	billgenerate ref=new billgenerate(rs.getString("cname"),rs.getDate("sdate"),rs.getDate("edate"),rs.getFloat("cowq"),rs.getFloat("buffq"),rs.getFloat("total"),rs.getInt("status"),rs.getDate("paydate"));
    lst.add(ref);
}
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
return lst;
}
}
