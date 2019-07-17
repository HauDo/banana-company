package com.axonactive.center;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

@SpringBootApplication
public class CenterApplication extends Application {

	private ConfigurableApplicationContext context;
	private Parent rootNode;

	public static void main(final String[] args) {
		launch(CenterApplication.class, args);
	}
	
	@Override
	public void init() throws Exception {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(CenterApplication.class);
		context = builder.run(getParameters().getRaw().toArray(new String[0]));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CenterApplication.fxml"));
		loader.setControllerFactory(context::getBean);
		rootNode = loader.load();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
		double width = visualBounds.getWidth();
		double height = visualBounds.getHeight();
		primaryStage.setScene(new Scene(rootNode, width, height));
		primaryStage.centerOnScreen();
		primaryStage.show();
	}

	@Override
	public void stop() throws Exception {
		context.close();
	}

}
