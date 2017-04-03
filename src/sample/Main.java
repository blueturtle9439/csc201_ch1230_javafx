package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("JavaFX ch1230");
        primaryStage.show();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(15, 15, 15, 15));

        Scene scene = new Scene(grid, 500, 325);
        primaryStage.setScene(scene);


        Text scenetitle = new Text("Please enter a filename");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label file = new Label("filename:");
        grid.add(file, 0, 1);
        TextField fileinput = new TextField();
        grid.add(fileinput, 1, 1);


        Button btn = new Button("count letters");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 2);

        final TextArea result = new TextArea();
        grid.add(result, 1, 3);


        btn.setOnAction(new EventHandler<ActionEvent>() {
            String answer = "";

            @Override
            public void handle(ActionEvent e) {
                Map temp = null;
                try {
                    temp = count(fileinput.getText());
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                for (int i = 65; i < 91; i++)
                    answer += "Numbers of " + (char) i + "\'s: " + temp.get((char) i) + "\n";

                result.setText(answer);
            }
        });
    }

    public static Map<Character, Counter> count(String filename) throws FileNotFoundException {
        InputStream in = new FileInputStream(filename);
        Scanner scanner = new Scanner(in);
        Map<Character, Counter> map = new HashMap<>();

        for (int i = 65; i < 91; i++)
            map.put((char) i, new Counter());


        while (true) {
            try {
                String str = scanner.nextLine();
                str = str.toUpperCase();
                char[] arr = str.toCharArray();
                for (char ch : arr) {
                    Counter cnt = map.get(ch);
                    if (cnt == null)
                        continue;
                    cnt.increase();
                }

            } catch (Exception e) {
                break;
            }
        }
        return map;

    }

}


class Counter {
    int count;

    public Counter() {
        this.count = 0;
    }

    public void increase() {
        count++;
    }

    public String toString() {
        return "" + count;
    }

    //numbers of A is counted 1
}

