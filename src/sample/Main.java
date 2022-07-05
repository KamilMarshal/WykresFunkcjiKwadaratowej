/*
Autor: Kamil Marszałek

Program rysujący wykres funkcji kwadratowej oraz wyznaczający miejsca zerowe.
Zawiera obsługę błędów.
 */
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception{
        // tworzenie stage, scene, załączający plik fxml i wywołujący cały program
        Parent root = FXMLLoader.load(getClass().getResource("sampleChart.fxml"));
        primaryStage.setTitle("Program rysujący wykres funkcji liniowej");
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
