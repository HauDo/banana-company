package com.banana.timer;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class TimerController implements Initializable {

	@FXML private AnchorPane treeAnchorPane;
	
	@FXML private ImageView checkinImageView;

	@FXML private ImageView checkoutImageView;

	@FXML private ImageView processImageView;
	
	@FXML private Button button;

	private int status = 1;
	
	private List<ImageView> imageViews;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configureButton();
		configureImageView();
	}


	private void configureButton() {
		button.setText(Status.getById(status).getName());
		button.setOnAction(event -> {
			status++;
			button.setText(Status.getById(status).getName());
			imageViews.forEach(imageView -> imageView.setVisible(false));
			imageViews.get(status - 1).setVisible(true);
			if (status == 3) {
				status = 0;
			}
		});
	}
	
	private void configureImageView() {
		imageViews = Arrays.asList(checkinImageView, processImageView, checkoutImageView);
		checkinImageView.setVisible(true);
	}
}
