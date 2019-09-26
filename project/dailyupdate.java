package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class dailyupdate{
	
	 public dailyupdate(){
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/milkmanassistant","root","jainhimanshu");
			} catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
				
			}
			
			pdu =new Text("Daily Update");
			pdu.setFont(Font.font("Roman",FontWeight.BLACK,30));
			
			lst =new ArrayList<String>();
			lst1 =new ArrayList<String>();
			
			postfull =new Button("Post Full Entry");
			postfull.setPrefSize(110, 30);
			post =new Button("Post");
			post.setPrefSize(60, 30);
			Missed =new Button("Missed");
			Missed.setPrefSize(60, 30);
			
			tcq =new TextField("0");
			tcq.setPrefSize(60, 30);
			tbq =new TextField("0");
			tbq.setPrefSize(60, 30);
			hbox =new HBox();
			hbox.setSpacing(20);
			hbox.getChildren().addAll(tcq,tbq);
			
			names =new ListView<String>();
			getnames();
			names.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			
			postfull.setOnAction(e->{
				dofull();
				names.getItems().removeAll(lst);
				postfull.setDisable(true);
			});
			
			Missed.setOnAction(e->{
				lst.addAll(names.getSelectionModel().getSelectedItems());
				names.getItems().removeAll(lst);
				Missed.setDisable(true);
			}); 
			
			post.setOnAction(e->{
			    dopartial();
			    names.getItems().remove(names.getSelectionModel().getSelectedItem());
			});
			
			grd =new GridPane();
			grd.setHgap(5);
			grd.setVgap(5);
			grd.setGridLinesVisible(false);
			//grd.setId("cover");
			
			GridPane.setConstraints(pdu, 0, 0, 3, 1, HPos.CENTER,VPos.CENTER,null,null,new Insets(10));
			grd.getChildren().add(pdu);
			GridPane.setConstraints(names, 0, 1, 2, 3, HPos.CENTER,VPos.CENTER,null,null,new Insets(10));
			grd.getChildren().add(names);
			GridPane.setConstraints(postfull, 2, 1, 1, 1, HPos.CENTER,VPos.CENTER,null,null,new Insets(20));
			grd.getChildren().add(postfull);
			GridPane.setConstraints(Missed, 2, 2, 1, 1, HPos.CENTER,VPos.CENTER,null,null,new Insets(20));
			grd.getChildren().add(Missed);
			GridPane.setConstraints(hbox, 2, 3, 1, 1, HPos.CENTER,VPos.CENTER,null,null,new Insets(20));
			grd.getChildren().add(hbox);
			GridPane.setConstraints(post, 2, 3, 1, 1, HPos.CENTER,VPos.CENTER,null,null,new Insets(5));
			grd.getChildren().add(post);
			
			Scene scene =new Scene(grd,500,500);
			//scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
			Stage stage =new Stage();
			stage.setScene(scene);
			stage.show();
		}

	Button postfull,post,Missed;
	TextField tcq,tbq;
	float cqq,bqq;
	ListView<String> names;
	Connection con;
	PreparedStatement pst,pst1,pst2;
	ResultSet rs,rs1;
	GridPane grd;
	ObservableList<Integer> indices;
	HBox hbox;
	int count=0;
	ArrayList<String> lst,lst1;
    Text pdu;
    
    void dopartial() {
		  
		   try {
				pst2 =con.prepareStatement("insert into dailyupdate values(?,?,?,curdate())");
				pst2.setString(1, names.getSelectionModel().getSelectedItem());
				pst2.setFloat(2, Float.parseFloat(tbq.getText()));
				pst2.setFloat(3, Float.parseFloat(tcq.getText()));
				
				pst2.executeUpdate();
				
			}
		    
		    catch (Exception e1) {
				e1.printStackTrace();
			}
		    
		
	}

	void dofull() {
		  
		  for(int i=0 ; i < count ;i++)
		     {  
			   if(names.getSelectionModel().isSelected(i)==false)
			   {      
				   lst.add(names.getItems().get(i));
				   try {
					    pst=con.prepareStatement("insert into dailyupdate values(?,?,?,curdate())");
						pst.setString(1, names.getItems().get(i));
						pst1=con.prepareStatement("select cowq,buffq from customer where cname=?");
						pst1.setString(1, names.getItems().get(i));
					    rs1=pst1.executeQuery();
						if(rs1.next()){
							cqq=Float.parseFloat(rs1.getString("buffq"));
							bqq=Float.parseFloat(rs1.getString("cowq"));
						pst.setFloat(2, cqq);
						pst.setFloat(3, bqq);
						pst.executeUpdate();
						}
				   
				   } catch (SQLException e) {
					e.printStackTrace();
				}
				   
        }		   
	  }
}

	void getnames() {
		try {
			pst =con.prepareStatement("select cname from customer");
			 rs =pst.executeQuery();
			 while(rs.next())
			   {  
				  count++;
				  names.getItems().add(rs.getString("cname")); 
			   }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}  
}
