package ro.ubb.movie.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.movie.client.ui.Console;

import java.io.IOException;

public class ClientApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "ro.ubb.movie.client.config"
                );

        Console console = context.getBean(Console.class);
        console.runConsole();

        System.out.println("bye ");
    }
}
