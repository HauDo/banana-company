package com.banana.timer;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.banana.dataresponse.StatusDataResponse;
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

	private List<ImageView> imageViews;
	private DataResponse dataResponse = null;

	private static final String CHECK_IN_FAILED = "Checkin failed !!!";
	private static final String CHECK_IN_SUCCESSFUL = "Checkin successful !!!";
	private static final String CHECK_OUT_SUCCESSFUL = "Checkout successful !!!";
	private static final String CHECK_OUT_FAILED = "Checkout failed !!!";
	private static final String CAN_NOT_CONNECT_SERVER = "Can't connect to server";
	private int status = 1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configureImageView();
		checkStatus();
		configureButton();
	}

	private void checkStatus() {
		imageViews.forEach(imageView -> imageView.setVisible(false));
		StatusDataResponse statusDataResponse = DataRequestService.createInstance().checkStatus();
		if (statusDataResponse.getStatus() == Status.CHECKIN.getId()
				|| statusDataResponse.getStatus() == Status.CHECKOUT.getId()) {
			status = statusDataResponse.getStatus();
			handleButtonAndScreen(Status.CHECKOUT);
		} else {
			handleButtonAndScreen(Status.CHECKIN);
		}
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
			buildAlert(CHECK_IN_FAILED, CAN_NOT_CONNECT_SERVER);
			e.printStackTrace();
			return;
		}
		if (dataResponse.getStatus() == Status.CHECKIN.getId()) {
			buildAlert(CHECK_IN_SUCCESSFUL, dataResponse.getMessage());
			status = Status.CHECKOUT.getId();
			handleButtonAndScreen(Status.CHECKOUT);
		}
	}

	private void handleCheckout() {
		try {
			dataResponse = DataRequestService.createInstance().checkOut();
		} catch (Exception e) {
			buildAlert(CHECK_OUT_FAILED, CAN_NOT_CONNECT_SERVER);
			e.printStackTrace();
			return;
		}
		if (dataResponse.getStatus() == Status.CHECKOUT.getId()) {
			buildAlert(CHECK_OUT_SUCCESSFUL, dataResponse.getMessage());
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
