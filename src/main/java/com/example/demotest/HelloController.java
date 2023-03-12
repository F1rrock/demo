package com.example.demotest;

import com.example.demotest.features.main.domain.use_cases.data_cases.client_cases.concretes.GetAllClients;
import com.example.demotest.features.main.injection.DependencyInjection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final var di = DependencyInjection.getInstance();
        final var getAllClients = di.get(GetAllClients.class);
        final var result = Objects.requireNonNull(getAllClients).call(null);
        result.fold(
                error -> {
                    System.out.println(error.getMessage());
                    return null;
                },
                list -> {
                    for (var elem : list) {
                        System.out.println(elem);
                    }
                    return null;
                }
        );
    }
}