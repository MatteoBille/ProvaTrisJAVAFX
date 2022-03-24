package com.example.demo;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.*;

public class HelloController {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    @FXML
    private List<Rectangle> rectangleList;

    private Partita partita;
    double x1 = 179;
    double y1 = 74;
    double size = 68;

    @FXML
    private void initialize() {
        partita = new Partita();

        Group fieldLine = drawField();
        anchorPane.getChildren().add(fieldLine);

        Map<Integer[], Pane> text = TextedArea();
        Pane[] textPane;
        textPane = text.values().toArray(new Pane[0]);


        Group textGroup = new Group(textPane);

        setActionListener(text);
        anchorPane.getChildren().add(textGroup);
    }

    private void setActionListener(Map<Integer[], Pane> text) {
        text.entrySet().forEach(el -> {
            el.getValue().setOnMouseClicked(event -> {
                partita.giocaMossa(el.getKey()[0], el.getKey()[1]);
                Label lab = (Label) el.getValue().getChildren().get(0);
                lab.setText("" + partita.getValoreCasella(el.getKey()[0], el.getKey()[1]));
                checkVittoria();
            });
        });
    }

    private boolean checkVittoria() {
        String result = partita.checkVittoria();
        System.out.println(result);
        if (result != null) {
            String[] line = result.split(",");
            String direction = line[0];
            int column = Integer.parseInt(line[1]);
            drawVictoryLine(direction, column);
            return true;
        } else {
            return false;
        }

    }

    private void drawVictoryLine(String direction, int column) {
        System.out.println("ciao");
        Line victoryLine= new Line();
        double x;
        double y;
        double xfin;
        double yfin;
        switch (direction) {
            case "C":
                x = x1 + (14 + size) * column + size/2;
                y = y1;
                yfin= y1+232;

                victoryLine = new Line(x, y, x, yfin);
                victoryLine.setStroke(Color.RED);
                victoryLine.setOpacity(1);
                victoryLine.setStrokeWidth(5);
                break;
            case "R":
                x = x1;
                y = y1 + (14 + size) * column +size/2;
                xfin=x1 +232;
                victoryLine = new Line(x, y, xfin, y);
                victoryLine.setStroke(Color.RED);
                victoryLine.setOpacity(1);
                victoryLine.setStrokeWidth(5);
                break;
            case "D":
                if (column == 1) {
                    x = x1;
                    y = y1;
                    xfin =x1 + 232;
                    yfin =  y1 + 232 ;
                    victoryLine = new Line(x, y, xfin, yfin);
                    victoryLine.setStroke(Color.RED);
                    victoryLine.setOpacity(1);
                    victoryLine.setStrokeWidth(5);
                } else if (column == 2) {
                    x =x1 + 232;
                    y = y1;
                    xfin =x1;
                    yfin=y1 + 232;
                    victoryLine = new Line(x, y, xfin, yfin);
                    victoryLine.setStroke(Color.RED);
                    victoryLine.setOpacity(1);
                    victoryLine.setStrokeWidth(5);
                }
                break;
        }
        anchorPane.getChildren().add(victoryLine);

    }

    private Group drawField() {
        Group lines;
        Rectangle[] fieldDelimiter = new Rectangle[4];
        fieldDelimiter[0] = new Rectangle(247, 74, 14, 232);
        fieldDelimiter[1] = new Rectangle(329, 74, 14, 232);
        fieldDelimiter[2] = new Rectangle(179, 142, 232, 14);
        fieldDelimiter[3] = new Rectangle(179, 224, 232, 14);
        Arrays.stream(fieldDelimiter).forEach(e -> {
            e.setStroke(Color.TRANSPARENT);
            e.setFill(Color.LIGHTBLUE);
        });
        return lines = new Group(fieldDelimiter);
    }

    private Map<Integer[], Pane> TextedArea() {
        Map<Integer[], Pane> panes = new HashMap<>();


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Pane tempPane = new Pane();
                Label label = new Label();

                double xTemp = x1 + (14 + size) * j;
                double yTemp = y1 + (14 + size) * i;
                tempPane.setLayoutX(xTemp);
                tempPane.setLayoutY(yTemp);

                tempPane.getChildren().add(label);
                label.setPrefSize(size, size);
                label.setMaxSize(size, size);
                label.relocate(0, 0);
                label.setBackground(new Background(new BackgroundFill(Color.DARKCYAN, new CornerRadii(0), new Insets(0))));
                label.setTextFill(Color.WHITE);
                label.setAlignment(Pos.CENTER);
                label.setFont(new Font("Arial", 32));
                panes.put(new Integer[]{i, j}, tempPane);
            }
        }


        return panes;
    }
}