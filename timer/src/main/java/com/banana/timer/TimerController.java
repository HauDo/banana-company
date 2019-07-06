package com.banana.timer;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.banana.datarequest.DataRequestService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class TimerController implements Initializable {

	@FXML private AnchorPane treeAnchorPane;

	@FXML private ImageView checkinImageView;

	@FXML private ImageView checkoutImageView;

	@FXML private ImageView processImageView;

	@FXML private Button button;

	@FXML private Tooltip checkinMessage;

	private int status = 1;

	private List<ImageView> imageViews;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configureButton();
		configureImageView();
	}


	private void configureButton() {
		button.setText("Checkin");
		button.setOnAction(event -> {
			status = Status.PROCESS.getId();
			button.setText(Status.getById(status).getName());
			imageViews.get(2).setVisible(true);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DataResponse dataResponse = DataRequestService.createInstance().checkIn();
			if (dataResponse.getStatus() == Status.CHECKIN.getId()) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("test");
				alert.setHeaderText("1111");
				alert.setContentText("222222222");
				alert.show();
				status = Status.CHECKOUT.getId();
			}
			button.setText(Status.getById(status).getName());
			imageViews.forEach(imageView -> imageView.setVisible(false));
			imageViews.get(status - 1).setVisible(true);
			if (status == 3) {
				status = 0;
			}
		});
	}

	private void configureImageView() {
		imageViews = Arrays.asList(checkinImageView, checkoutImageView, processImageView);
		checkinImageView.setVisible(true);
	}
}
