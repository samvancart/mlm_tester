/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlm_tester.ui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Sam
 */
public class MlmTesterUi extends Application {
    
    private Scene scene;

    @Override
    public void start(Stage primaryStage) {

        // main tab
        TabPane menuTabs = new TabPane();
        Tab mainTab = new Tab("Main");
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.TOP_LEFT);
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setPadding(new Insets(25, 25, 25, 25));

        Label nameLabel = new Label("Name:");
        gp.add(nameLabel, 0, 1);

        TextField nameField = new TextField();
        gp.add(nameField, 1, 1);

        Label groupsLabel = new Label("Group:");
        gp.add(groupsLabel, 3, 1);

        ComboBox groupsCb = new ComboBox();
        gp.add(groupsCb, 4, 1);

        Label emailLabel = new Label("Email:");
        gp.add(emailLabel, 0, 2);

        TextField emailField = new TextField();
        gp.add(emailField, 1, 2);

        Button submitButton = new Button("Add to Mailerlite");
        submitButton.setMinSize(120, 40);
        HBox buttonHb = new HBox(10);
        buttonHb.setAlignment(Pos.BOTTOM_LEFT);
        buttonHb.getChildren().add(submitButton);
        gp.add(buttonHb, 1, 4);

        mainTab.setContent(gp);

        // settings tab
        Tab settingsTab = new Tab("Settings");
        HBox settingsPane = new HBox(10);
        Label settingsLabel = new Label("This is the settings tab");
        settingsPane.getChildren().addAll(settingsLabel);
        settingsTab.setContent(settingsPane);

        menuTabs.getTabs().addAll(mainTab, settingsTab);
        menuTabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        scene = new Scene(menuTabs, 500, 250);

        menuTabs.prefHeightProperty().bind(scene.heightProperty());
        menuTabs.prefWidthProperty().bind(scene.widthProperty());

        primaryStage.setTitle("Mail List Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(MlmTesterUi.class);
    }
}
