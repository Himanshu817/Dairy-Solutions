package project;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class about {
	 about() {
		
		cir1 =new Circle(250,200,80);
		cir1.setStroke(Color.SEAGREEN);
		cir1.setFill(new ImagePattern(new Image(this.getClass().getResourceAsStream("me.JPG"))));
		cir2 =new Circle(250,200,80);
		cir2.setStroke(Color.SEAGREEN);
		cir2.setFill(new ImagePattern(new Image(this.getClass().getResourceAsStream("sir.JPG"))));
		pa =new Text("About Us");
		pa.setFont(Font.font("Candara Bold",FontWeight.BLACK,30));
		pa.setTranslateX(120);
		pdb =new Text("Developed by ");
		pdb.setFont(Font.font("Candara Bold",FontWeight.BLACK,20));
		me =new Text("Himanshu Jain \nCollege: UPES,Dheradun");
		me.setFont(Font.font("Candara Bold",FontWeight.BLACK,15));
		me.setTranslateX(20);
		pug =new Text("Under The Guidance Of\nMr. Rajesh Bansal\nBanglore Computer Education");
		pug.setFont(Font.font("Candara Bold",FontWeight.BLACK,18));
		pug.setTranslateX(0);
		
		
		
		grd =new GridPane();
		grd.setHgap(5);
		grd.setVgap(5);
		grd.setPadding(new Insets(0,80,80,80));
		grd.setGridLinesVisible(false);
		
		
		GridPane.setConstraints(pa, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER,null,null,new Insets(10));
		grd.getChildren().add(pa);
		GridPane.setConstraints(cir1, 0, 1, 1, 2, HPos.CENTER, VPos.CENTER,null,null,new Insets(10));
		grd.getChildren().add(cir1);
		GridPane.setConstraints(pdb, 2, 1, 1, 1, HPos.CENTER, VPos.CENTER,null,null,new Insets(20,0,0,0));
		grd.getChildren().add(pdb);
		GridPane.setConstraints(me, 2, 2, 1, 1, HPos.CENTER, VPos.CENTER,null,null,new Insets(0));
		grd.getChildren().add(me);
		GridPane.setConstraints(pug, 0, 4, 1, 1, HPos.CENTER, VPos.CENTER,null,null,new Insets(50,0,0,0));
		grd.getChildren().add(pug);
		GridPane.setConstraints(cir2, 2, 4, 1, 1, HPos.CENTER, VPos.CENTER,null,null,new Insets(50,0,0,0));
		grd.getChildren().add(cir2);
		
		
		
		Scene scene =new Scene(grd,700,500);
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.show();
	}
	Text pdb,me,sir,pa,pug;
	ImageView img,img1;
	GridPane grd;
	Circle cir1,cir2;


}
