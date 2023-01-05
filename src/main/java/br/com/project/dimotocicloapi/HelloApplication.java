package br.com.project.dimotocicloapi;

import br.com.project.dimotocicloapi.adapters.controllers.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class HelloApplication extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    new LoginView().view();
  }

  public static void main(String[] args) {
    launch();
  }
}
