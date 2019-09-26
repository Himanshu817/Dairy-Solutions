package project;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class dashboard{
	
	dashboard(){
		img =new ImageView(new Image(this.getClass().getResourceAsStream("Enrollcustomer.png")));
		img.setFitHeight(120);
		img.setFitWidth(120);
		img1 =new ImageView(new Image(this.getClass().getResourceAsStream("daily.png")));
		img1.setFitHeight(120);
		img1.setFitWidth(120);
		img2 =new ImageView(new Image(this.getClass().getResourceAsStream("billcollector.png")));
		img2.setFitHeight(120);
		img2.setFitWidth(120);
		
		
		img3 =new ImageView(new Image(this.getClass().getResourceAsStream("billgeneration.png")));
		img3.setFitHeight(120);
		img3.setFitWidth(120);
		img3.setTranslateY(30);
		img4 =new ImageView(new Image(this.getClass().getResourceAsStream("customer.png")));
		img4.setFitHeight(120);
		img4.setFitWidth(120);
		img4.setTranslateY(30);
		img5 =new ImageView(new Image(this.getClass().getResourceAsStream("payment.png")));
		img5.setFitHeight(120);
		img5.setFitWidth(120);
		img5.setTranslateY(30);
		
		img6 =new ImageView(new Image(this.getClass().getResourceAsStream("billcollector.png")));
		img6.setFitHeight(120);
		img6.setFitWidth(120);
		img6.setTranslateX(60);
		img6.setTranslateY(60);
		img7 =new ImageView(new Image(this.getClass().getResourceAsStream("about.png")));
		img7.setFitHeight(120);
		img7.setFitWidth(120);
		img7.setTranslateX(60);
		img7.setTranslateY(60);
		
		img.setOnMouseEntered(e->{
			scene.setCursor(Cursor.HAND);
		});
		img.setOnMouseExited(e->{
			scene.setCursor(Cursor.DEFAULT);
		});
		
		img1.setOnMouseEntered(e->{
			scene.setCursor(Cursor.HAND);
		});
		img1.setOnMouseExited(e->{
			scene.setCursor(Cursor.DEFAULT);
		});
		
		img2.setOnMouseEntered(e->{
			scene.setCursor(Cursor.HAND);
		});
		img2.setOnMouseExited(e->{
			scene.setCursor(Cursor.DEFAULT);
		});
		
		img3.setOnMouseEntered(e->{
			scene.setCursor(Cursor.HAND);
		});
		img3.setOnMouseExited(e->{
			scene.setCursor(Cursor.DEFAULT);
		});
		
		img4.setOnMouseEntered(e->{
			scene.setCursor(Cursor.HAND);
		});
		img4.setOnMouseExited(e->{
			scene.setCursor(Cursor.DEFAULT);
		});
		
		img5.setOnMouseEntered(e->{
			scene.setCursor(Cursor.HAND);
		});
		img5.setOnMouseExited(e->{
			scene.setCursor(Cursor.DEFAULT);
		});
		
		img6.setOnMouseEntered(e->{
			scene.setCursor(Cursor.HAND);
		});
		img6.setOnMouseExited(e->{
			scene.setCursor(Cursor.DEFAULT);
		});
		
		img7.setOnMouseEntered(e->{
			scene.setCursor(Cursor.HAND);
		});
		img7.setOnMouseExited(e->{
			scene.setCursor(Cursor.DEFAULT);
		});
		
		img.setOnMouseClicked(e->{
			new Enrollcustomer();
		});
		img1.setOnMouseClicked(e->{
			new dailyupdate();
		});
		img2.setOnMouseClicked(e->{
			new billgeneration();
		});
		img3.setOnMouseClicked(e->{
			new billcollection();
		});
		img4.setOnMouseClicked(e->{
			new customerdet2();
		});
		img5.setOnMouseClicked(e->{
			new paymenthistory2();
		});
		img6.setOnMouseClicked(e->{
			new billwatcher2();
		});
		img7.setOnMouseClicked(e->{
			new about();
		});
		
		
		
		
		
		
		grd=new GridPane();
		grd.setHgap(5);
		grd.setVgap(5);
		grd.setPadding(new Insets(130,0,0,300));
		grd.setGridLinesVisible(false);
		grd.setId("dash");
		
		GridPane.setConstraints(img, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, null,null,new Insets(5));
		grd.getChildren().add(img);
		GridPane.setConstraints(img1, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER, null,null,new Insets(5,0,0,30));
		grd.getChildren().add(img1);
		GridPane.setConstraints(img2, 2, 0, 1, 1, HPos.CENTER, VPos.CENTER, null,null,new Insets(5,0,0,30));
		grd.getChildren().add(img2);

		GridPane.setConstraints(img3, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, null,null,new Insets(5));
		grd.getChildren().add(img3);
		GridPane.setConstraints(img4, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, null,null,new Insets(5,0,0,30));
		grd.getChildren().add(img4);
		GridPane.setConstraints(img5, 2, 1, 1, 1, HPos.CENTER, VPos.CENTER, null,null,new Insets(5,0,0,30));
		grd.getChildren().add(img5);
		
		GridPane.setConstraints(img6, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER, null,null,new Insets(5,0,0,30));
		grd.getChildren().add(img6);
		GridPane.setConstraints(img7, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER, null,null,new Insets(5,0,0,30));
		grd.getChildren().add(img7);
		
		
			    scene=new Scene(grd,850,650);
				scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
				Stage stage =new Stage();
				stage.setScene(scene);
				stage.show();
	}
	
	GridPane grd;
	ImageView img,img1,img2,img3,img4,img5,img6,img7;
	Scene scene;

}
