package com.example.cezar;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SzyfrCezara extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label tekstLabel = new Label("Tekst:");
        Label szyfrLabel = new Label("Szyfr (liczba przesunięć):");
        Label wynikLabel = new Label("Wynik:");

        TextField tekstField = new TextField();
        TextField szyfrField = new TextField();
        szyfrField.setPromptText("Podaj liczbę");

        TextArea wynikField = new TextArea();
        wynikField.setEditable(false);

        Button przyciskEncrypt = new Button("Zaszyfruj");
        przyciskEncrypt.setOnAction(event -> {
            String tekst = tekstField.getText();
            String szyfrString = szyfrField.getText();

            try {
                int szyfr = Integer.parseInt(szyfrString);
                String zaszyfrowanyTekst = szyfrCezara(tekst, szyfr);
                wynikField.setText(zaszyfrowanyTekst);
            } catch (NumberFormatException e) {
                wynikField.setText("Błąd: Szyfr musi być liczbą całkowitą.");
            }
        });

        Button przyciskDecrypt = new Button("Deszyfruj");
        przyciskDecrypt.setOnAction(event -> {
            String tekst = tekstField.getText();
            String szyfrString = szyfrField.getText();

            try {
                int szyfr = Integer.parseInt(szyfrString);
                String odszyfrowanyTekst = deszyfrCezara(tekst, szyfr);
                wynikField.setText(odszyfrowanyTekst);
            } catch (NumberFormatException e) {
                wynikField.setText("Błąd: Szyfr musi być liczbą całkowitą.");
            }
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(
                tekstLabel, tekstField,
                szyfrLabel, szyfrField,
                przyciskEncrypt, przyciskDecrypt,
                wynikLabel, wynikField
        );

        Scene scene = new Scene(layout, 400, 350);
        primaryStage.setTitle("Szyfr Cezara");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String szyfrCezara(String tekst, int przesuniecie) {
        String alfabet = "aąbcćdeęfghijklłmnńoópqrsśtuvwxyzźż";
        String alfabetDuze = alfabet.toUpperCase();
        StringBuilder wynik = new StringBuilder();
        for (char znak : tekst.toCharArray()) {
            if (alfabet.indexOf(znak) != -1) {
                int pozycja = (alfabet.indexOf(znak) + przesuniecie) % alfabet.length();
                wynik.append(alfabet.charAt(pozycja));
            } else if (alfabetDuze.indexOf(znak) != -1) {
                int pozycja = (alfabetDuze.indexOf(znak) + przesuniecie) % alfabetDuze.length();
                wynik.append(alfabetDuze.charAt(pozycja));
            } else {
                wynik.append(znak);
            }
        }
        return wynik.toString();
    }

    private String deszyfrCezara(String tekst, int przesuniecie) {
        String alfabet = "aąbcćdeęfghijklłmnńoópqrsśtuvwxyzźż";
        String alfabetDuze = alfabet.toUpperCase();
        StringBuilder wynik = new StringBuilder();
        for (char znak : tekst.toCharArray()) {
            if (alfabet.indexOf(znak) != -1) {
                int pozycja = (alfabet.indexOf(znak) - przesuniecie + alfabet.length()) % alfabet.length();
                wynik.append(alfabet.charAt(pozycja));
            } else if (alfabetDuze.indexOf(znak) != -1) {
                int pozycja = (alfabetDuze.indexOf(znak) - przesuniecie + alfabetDuze.length()) % alfabetDuze.length();
                wynik.append(alfabetDuze.charAt(pozycja));
            } else {
                wynik.append(znak);
            }
        }
        return wynik.toString();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
