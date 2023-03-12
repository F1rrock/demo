package com.example.demotest;

import com.example.demotest.common.enums.DiscountRangesFilterValues;
import com.example.demotest.features.main.domain.use_cases.data_cases.service_cases.concretes.GetAllServices;
import com.example.demotest.features.main.domain.use_cases.decorated_cases.concretes.FilterServicesByDiscount;
import com.example.demotest.features.main.domain.use_cases.params.DecoratedCaseParam;
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
        final var getAllServices = di.get(GetAllServices.class);
        final var result = Objects.requireNonNull(getAllServices).call(null);
        result.fold(
                error -> {
                    System.out.println(error.getMessage());
                    return null;
                },
                values -> {
                    final var filterServices = di.get(FilterServicesByDiscount.class);
                    final var newList = Objects.requireNonNull(filterServices).call(
                            new DecoratedCaseParam<>(
                                    values,
                                    DiscountRangesFilterValues.FIFTEEN_TO_THIRTY
                            )
                    );
                    newList.fold(
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
                    return null;
                }
        );
    }
}