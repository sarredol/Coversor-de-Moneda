import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {
    public static void main(String[] args) throws IOException {

        String url_str = "https://v6.exchangerate-api.com/v6/e7ad98513ac0d7061f0aefbf/latest/USD";
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonObject = root.getAsJsonObject();

        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");


        EXTERNA:
        while (true){
            System.out.println("CONVERSOR DE MONEDAS");
            System.out.println("1 - Soles Peruanos a dolares\n" +
                    "2 - Pesos Mexicanos a dolares\n" +
                    "3 - Pesos Colombianos a dolares\n" +
                    "4 - Salir");
            System.out.println("INGRESE UNA OPCIÓN: ");
            Scanner leer = new Scanner(System.in);
            char opcion = leer.next().charAt(0);

            switch (opcion){
                case '1' :
                    double valorPen = conversionRates.get("PEN").getAsDouble();
                    convertidor(valorPen, "Soles Peruanos");
                    break;
                case '2' :
                    double valorMxn =  conversionRates.get("MXN").getAsDouble();
                    convertidor(valorMxn, "Pesos Mexicano");
                    break;
                case '3' :
                    double valorCop =  conversionRates.get("COP").getAsDouble();
                    convertidor(valorCop, "Pesos Colombianos");
                    break;
                case '4' :
                    System.out.println("CERRANDO PROGRAMA");
                    break EXTERNA;
                default:
                    System.out.println("OPCIÓN INCORRECTA");
            }
        }

    }

    static void convertidor(double valorDolar,String pais ){

        Scanner leer = new Scanner(System.in);
        System.out.printf("Ingrese la cantidad de %s: ", pais);
        double cantidadMoneda = leer.nextDouble();

        double dolares = cantidadMoneda / valorDolar;

        dolares = (double) Math.round(dolares * 100)/100;

        System.out.println("----------------------------------------");
        System.out.println("|   Tienes $" + dolares + " Dolares   |");
        System.out.println("----------------------------------------");


    }
}
