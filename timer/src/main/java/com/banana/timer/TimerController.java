package com.banana.timer;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.banana.request.model.DataResponse;
import com.banana.request.service.DataRequestService;

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

	DataResponse dataResponse = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configureButton();
		configureImageView();
	}


	private void configureButton() {
		button.setOnAction(event -> {
			if (status == Status.CHECKIN.getId()) {
				handleCheckin();
				return;
			}
			if (status == Status.CHECKOUT.getId()) {
				handleCheckout();
				return;
			}

		});
	}

	private void handleCheckin() {
		try {
			dataResponse = DataRequestService.createInstance().checkIn();
		} catch (Exception e) {
			buildAlert("Checkin failed !!!", "Got the technical error");
			e.printStackTrace();
		}
		if (dataResponse.getStatus() == Status.CHECKIN.getId()) {
			buildAlert("Checkin successfully !!!", dataResponse.getMessage());
			status = Status.CHECKOUT.getId();
			handleButtonAndScreen(Status.CHECKOUT);
		}
	}

	private void handleCheckout() {
		try {
			dataResponse = DataRequestService.createInstance().checkOut();
		} catch (Exception e) {
			buildAlert("Checkout failed !!!", "Got the technical error");
			e.printStackTrace();
		}
		if (dataResponse.getStatus() == Status.CHECKOUT.getId()) {
			buildAlert("Checkout successfully", dataResponse.getMessage());
			handleButtonAndScreen(Status.CHECKOUT);
		}
	}

	private void handleButtonAndScreen(Status status) {
		button.setText(status.getName());
		imageViews.get(status.getId() - 1).setVisible(true);
	}



	private void configureImageView() {
		imageViews = Arrays.asList(checkinImageView, processImageView, checkoutImageView);
		checkinImageView.setVisible(true);
	}

	private void buildAlert(String header, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Timer system");
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
	}
}
