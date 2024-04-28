
package com.example.shapesliderapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ShapeSliderApplication extends Application {
    private Pane shapePane;
    private int currentShapeIndex = 0;
    private String[] shapeTypes = {"triangle", "rectangle", "circle"};
    private Color[] backgroundColors = {Color.LIGHTGRAY, Color.LIGHTBLUE, Color.LIGHTGREEN};

    @Override
    public void start(Stage primaryStage) {
        shapePane = new Pane();
        Button previousButton = new Button("Previous");
        previousButton.setOnAction(e -> showPreviousShape());

        Button nextButton = new Button("Next");
        nextButton.setOnAction(e -> showNextShape());

        Button changeBackgroundButton = new Button("Change Background");
        changeBackgroundButton.setOnAction(e -> changeBackgroundColor());

        VBox buttonBox = new VBox(10, previousButton, nextButton, changeBackgroundButton);

        // Event handler to enable dragging shapes
        shapePane.setOnMousePressed(e -> {
            for (int i = 0; i < shapePane.getChildren().size(); i++) {
                int finalI = i;
                shapePane.getChildren().get(i).setOnMouseDragged(event -> {
                    shapePane.getChildren().get(finalI).setLayoutX(event.getSceneX());
                    shapePane.getChildren().get(finalI).setLayoutY(event.getSceneY());
                });
            }
        });

        // Apply CSS styles to the buttons
        applyButtonStyles(previousButton, nextButton, changeBackgroundButton);

        Scene scene = new Scene(new HBox(10, shapePane, buttonBox), 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My Shape Slider Application(901015132)");
        primaryStage.show();
        showShape();
    }

    // Method to display the selected shape
    private void showShape() {
        shapePane.getChildren().clear();
        String currentShapeType = shapeTypes[currentShapeIndex];
        switch (currentShapeType) {
            case "triangle":
                displayTriangle();
                break;
            case "rectangle":
                displayRectangle();
                break;
            case "circle":
                displayCircle();
                break;
        }
    }

    // Method to display the triangle shape
    private void displayTriangle() {
        Polygon triangle = new Polygon(100, 0, 0, 200, 200, 200);
        triangle.setFill(backgroundColors[currentShapeIndex]);
        shapePane.getChildren().add(triangle);
    }

    // Method to display the rectangle shape
    private void displayRectangle() {
        Rectangle rectangle = new Rectangle(100, 100);
        rectangle.setFill(backgroundColors[currentShapeIndex]);
        shapePane.getChildren().add(rectangle);
    }

    // Method to display the circle shape
    private void displayCircle() {
        Circle circle = new Circle(50);
        circle.setFill(backgroundColors[currentShapeIndex]);
        shapePane.getChildren().add(circle);
    }

    // Method to display the previous shape
    private void showPreviousShape() {
        currentShapeIndex = (currentShapeIndex - 1 + shapeTypes.length) % shapeTypes.length;
        showShape();
    }

    // Method to display the next shape
    private void showNextShape() {
        currentShapeIndex = (currentShapeIndex + 1) % shapeTypes.length;
        showShape();
    }

    // Method to change the background color of the shape
    private void changeBackgroundColor() {
        backgroundColors[currentShapeIndex] = Color.color(Math.random(), Math.random(), Math.random());
        showShape();
    }

    // Method to apply CSS styles to the buttons
    private void applyButtonStyles(Button... buttons) {
        for (Button button : buttons) {
            button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-border-radius: 50px;");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
