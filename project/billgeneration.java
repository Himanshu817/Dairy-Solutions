package project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class billgeneration {
	
	public billgeneration()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/milkmanassistant","root","jainhimanshu");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		pbg =new Text("Bill Generation");
		pbg.setFont(Font.font("Roman",FontWeight.BLACK,30));
		pbg.setTranslateX(40);
		
		psd =new Text("Start Date");
		ped =new Text("End Date");
		pn =new Text("Name");
		pmn =new Text("Mobile no.");
		pcq =new Text("Cow quan.");
		pbq =new Text("buf quan.");
		pca =new Text("Cow Amnt");
		pba =new Text("Buff Amnt");
		ptb =new Text("Total Bill");
		ppc =new Text("Cow price");
		ppb =new Text("Buff price");
		
		sd =new DatePicker();
		ed =new DatePicker();
		
		tn =new ComboBox<String>();
		tn.setPrefSize(170, 30);
		tn.setEditable(true);
		insert();

		tmn =new TextField();
		tmn.setPrefSize(60,20);
		tcq =new TextField("0");
		tcq.setPrefSize(110, 30);
		tbq =new TextField("0");
		tbq.setPrefSize(125, 30);
		tca =new TextField();
		tca.setPrefSize(110, 30);
		tba =new TextField();
		tba.setPrefSize(125, 30);
		ttb =new TextField();
		ttb.setPrefSize(60, 20);
		tpc =new TextField("35");
		tpc.setPrefSize(110, 30);
		tpb =new TextField("40");
		tpb.setPrefSize(125, 30);
		
		hbox =new HBox();
		hbox.setSpacing(10);
		hbox.getChildren().addAll(pcq,tcq);
		hbox1 =new HBox();
		hbox1.setSpacing(10);
		hbox1.getChildren().addAll(pbq,tbq);
		hbox2 =new HBox();
		hbox2.setSpacing(10);
		hbox2.getChildren().addAll(pca,tca);
		hbox3 =new HBox();
		hbox3.setSpacing(10);
		hbox3.getChildren().addAll(pba,tba);
		hbox4 =new HBox();
		hbox4.setSpacing(10);
		hbox4.getChildren().addAll(ppc,tpc);
		hbox5 =new HBox();
		hbox5.setSpacing(10);
		hbox5.getChildren().addAll(ppb,tpb);
		
		total =new Button("Total quantity");
		total.setPrefSize(200, 30);
		total.setAlignment(Pos.CENTER);
		Bill =new Button("Bill According to Quantity");
		Bill.setPrefSize(200, 30);
		Bill.setAlignment(Pos.CENTER);
		SMS =new Button("Send SMS");
		SMS.setPrefSize(80, 30);
		SMS.setAlignment(Pos.CENTER);
		save =new Button("Save");
		save.setPrefSize(80, 30);
		save.setAlignment(Pos.CENTER);
		
		billg =new TextArea();
		billg.setText("Name:"+tn.getSelectionModel().getSelectedItem()+"\n Cow quan:"+tcq.getText()
		+"     Buff. quan:"+tbq.getText()+"\n Cow Price:"+tpc.getText()+"     Buff Price:"+tpb.getText()
		+"\n Cow Milk Amount:"+tca.getText()+"      Buff Milk Amount"+tba.getText()+"\n Toal Bill"+ttb.getText());
		
		grd =new GridPane();
		grd.setVgap(5);
		grd.setHgap(5);
		grd.setGridLinesVisible(false);
		grd.setPadding(new Insets(20));
		//grd.setId("cover");
		
		
		GridPane.setConstraints(pbg, 0, 0, 2, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(pbg);
		GridPane.setConstraints(psd, 0, 1, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(psd);
		GridPane.setConstraints(sd, 1, 1, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(sd);
		GridPane.setConstraints(ped, 0, 2, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(ped);
		GridPane.setConstraints(ed, 1, 2, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(ed);
		GridPane.setConstraints(pn, 0, 3, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(pn);
		GridPane.setConstraints(tn, 1, 3, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(tn);
		GridPane.setConstraints(pmn, 0, 4, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(pmn);
		GridPane.setConstraints(tmn, 1, 4, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(tmn);
		GridPane.setConstraints(total, 0, 5, 2, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(total);
		GridPane.setConstraints(hbox, 0, 6, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(hbox);
		GridPane.setConstraints(hbox1, 1, 6, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(hbox1);
		GridPane.setConstraints(hbox4, 0, 7, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(hbox4);
		GridPane.setConstraints(hbox5, 1, 7, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(hbox5);
		GridPane.setConstraints(Bill, 0, 8, 2, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(Bill);
		GridPane.setConstraints(hbox2, 0, 9, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(hbox2);
		GridPane.setConstraints(hbox3, 1, 9, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(hbox3);
		GridPane.setConstraints(ptb, 0, 10, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(ptb);
		GridPane.setConstraints(ttb, 1, 10, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(ttb);
		GridPane.setConstraints(SMS, 0, 11, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(SMS);
		GridPane.setConstraints(save, 1, 11, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(5));
		grd.getChildren().add(save);
		
		total.setOnAction(e->{
			  calcu();
			  tcq.setText(sumc+"");
			  tbq.setText(sumb+"");
			  
			 
		});
		
		  tn.setOnAction(e->{
			  getmobile();
		  });
		
		Bill.setOnAction(e->{
			tpricec =sumc*Float.parseFloat(tpc.getText());
			tpriceb =sumb*Float.parseFloat(tpb.getText());
			totalprice =tpricec+tpriceb;
			tca.setText(tpricec+"");
			tba.setText(tpriceb+"");
			ttb.setText(totalprice+"");
		});
		
		SMS.setOnAction(e->{
			SST_SMS.bceSunSoftSend("7017208231",billg.getText());
		});
		save.setOnAction(e->{
			dosave();
		});
		
		Scene scene =new Scene(grd,500,600);
		//scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.show();

	}

	Text psd,ped,pn,pmn,pcq,pbq,pca,pba,ptb,ppc,ppb,pbg;
	DatePicker sd,ed;
	TextField tmn,tcq,tbq,tca,tba,ttb,tpc,tpb;
	ComboBox<String> tn;
	Button Bill,total,SMS,save;
	GridPane grd;
	HBox hbox,hbox1,hbox2,hbox3,hbox4,hbox5;
	TextArea billg;
	//String std,endd;
	//int start,end;
	Connection con;
	PreparedStatement pst;
	ResultSet rs,rs1,rs2;
	float sumc =0,sumb =0;
	float tpricec,tpriceb,totalprice;
	

	     void dosave() {
		
	    	 try {
				pst =con.prepareStatement("insert into billgeneration values (?,?,?,?,?,?,?,?,?,?)");
				pst.setString(1, tn.getSelectionModel().getSelectedItem());
				pst.setDate(2, Date.valueOf(sd.getValue()));
				pst.setDate(3, Date.valueOf(ed.getValue()));
				pst.setFloat(4, Float.parseFloat(tcq.getText()));
				pst.setFloat(5, Float.parseFloat(tbq.getText()));
				pst.setFloat(6, Float.parseFloat(tca.getText()));
				pst.setFloat(7, Float.parseFloat(tba.getText()));
				pst.setFloat(8, Float.parseFloat(ttb.getText()));
				pst.setFloat(9, 0);
				pst.setDate(10, null);
				
				pst.executeUpdate();
				
				sd.setValue(null);
				ed.setValue(null);
			    tn.getSelectionModel().select(-1);
			    tmn.setText(null);
			    tcq.setText("0");
			    tbq.setText("0");
			    tpc.setText("0");
			    tpb.setText("0");
			    tca.setText(null);
			    tba.setText(null);
			    ttb.setText(null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

		void getmobile() {
          try {
			pst =con.prepareStatement("select Mobile from customer where cname=?");
			pst.setString(1, tn.getSelectionModel().getSelectedItem());
			rs1 =pst.executeQuery();
			if(rs1.next())
			tmn.setText(rs1.getString("Mobile"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	void insert() {
		try {
			pst =con.prepareStatement("select cname from customer");
			rs2=pst.executeQuery();
			while(rs2.next())
			tn.getItems().add(rs2.getString("cname"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	void calcu() {
		
		        sumc =0;
		        sumb =0;
				try {
					pst =con.prepareStatement("select buffq,cowq from dailyupdate where cname=? and date>=? and date<=?");
					pst.setString(1, tn.getSelectionModel().getSelectedItem());
					pst.setDate(2,Date.valueOf(sd.getValue()));
					pst.setDate(3,Date.valueOf(ed.getValue()));
					rs =pst.executeQuery();
					
					while(rs.next())
					{   //System.out.println(rs.getString("cowq"));
						sumc =sumc+Float.parseFloat(rs.getString("cowq"));
						sumb =sumb+Float.parseFloat(rs.getString("buffq"));
					}
					tcq.setText(sumc+"");
					tbq.setText(sumb+"");
				} catch (SQLException e) {
					e.printStackTrace();
				}
	}

}
