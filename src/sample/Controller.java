package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


public class Controller{
    //dodawanie obiektów z fxml-a
    @FXML
    TextField aTextField, bTextField, cTextField;
    @FXML
    Button narysujButton, wyczyscButton;
    @FXML
    Label niepoprawneDane, equation;
    @FXML
    Pane ukladWspol;

    public void sprawdzDane(MouseEvent mouseEvent)
    {
        System.out.println("sprawdź dane");
        //sprawdzanie poprawności wprowadzonych danych
        String atext = aTextField.getText();
        String btext = bTextField.getText();
        String ctext = cTextField.getText();
        String wzor = "[-+]?[0-9]*[.]?[0-9]+";

        if (atext.isBlank()||btext.isBlank()||ctext.isBlank())
        {
            niepoprawneDane.setText("Wprowadź dane! Są puste pola");
            System.out.println("Blank");
            return;
        }
        else if (!atext.matches(wzor)||!btext.matches(wzor)||!ctext.matches(wzor))
        {
            niepoprawneDane.setText("Wprowadź liczby!");
            System.out.println("!liczba");
            return;
        }
        niepoprawneDane.setText("");
        float a = Float.parseFloat(atext);
        float b = Float.parseFloat(btext);
        float c = Float.parseFloat(ctext);
        System.out.println("a="+a+" b="+b+" c="+c);

        float x1,x2;
        //wypisywanie miejsc zerowych
        if (a!=0)
        {
            float delta = ((b*b)-(4*a*c));
            if (delta>0)
            {
                x1 = (float) ((-b-Math.sqrt(delta))/(2*a));
                x2 = (float) ((-b+Math.sqrt(delta))/(2*a));
                equation.setText("dwa miejsca zerowe:   x₁="+x1+"   x₂="+x2);
            }
            else if (delta==0)
            {
                x1 = ((-b)/(2*a));
                equation.setText("miejsce zerowe = "+x1);
            }
            else equation.setText("brak miejsc zerowych");
        }
        else if (b==0 && c!=0) equation.setText("miejsce zerowe = "+c);
        else if (b==0 && c==0) equation.setText("Nieskończenie wiele miejsc zerowych");
        else equation.setText("miejsce zerowe = "+(-c/b));
        //wywołanie metody rysującej funkcję
        rysuj(a,b,c);
    }



    public void rysuj(float a, float b, float c)
    {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Oś X");
        yAxis.setLabel("Oś Y");
        //tworzenie wykresu
        final LineChart<Number, Number> lineChart =
                new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setMinHeight(600);
        lineChart.setMinWidth(750);
        /*lineChart.setTitle("2");
        //wypisywanie wzoru funkcji
        if (a != 0)
            lineChart.setTitle("Wykres funkcji f(x)=" + a + "x²+" + b + "x" + "+" + c);
        else if (b != 0)
            lineChart.setTitle("Wykres funkcji f(x)=" + b + "x" + "+" + c);
        else lineChart.setTitle("Wykres funkcji f(x)=" + c);*/

        var series = new XYChart.Series();
        //dodawanie punktów należących do wykresu z dokładnością 0.1
        for (int i = 0; i <= 100; i++) {
            float x = (float) (-5.0 + (i * 0.1));

            float y = ((a * x * x) + (b * x) + c);

            System.out.println("x=" + x + "; y1=" + y);

            series.getData().add(new XYChart.Data(x, y));
        }
        lineChart.getData().add(series);
        ukladWspol.getChildren().add(lineChart);

    }

    public void czyscDane(MouseEvent mouseEvent)
    {
        //czyczenie danych
        aTextField.setText("");
        bTextField.setText("");
        cTextField.setText("");
        equation.setText("");
        ukladWspol.getChildren().clear();
    }


}
