package com.doug.pointofsale;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class PointOfSaleApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        setSystemPropertyFromEnv(dotenv, "DB_URL");
        setSystemPropertyFromEnv(dotenv, "DB_CONNECTION_USERNAME");
        setSystemPropertyFromEnv(dotenv, "DB_CONNECTION_PASSWORD");
        setSystemPropertyFromEnv(dotenv, "PAY_NOW_ID");
        setSystemPropertyFromEnv(dotenv, "PAY_NOW_KEY");
        setSystemPropertyFromEnv(dotenv, "PAY_NOW_RESULT_URL");
        setSystemPropertyFromEnv(dotenv, "PAY_NOW_RETURN_URL");

        SpringApplication.run(PointOfSaleApplication.class, args);
    }

    private static void setSystemPropertyFromEnv(Dotenv dotenv, String key) {
        String value = dotenv.get(key);
        if (value != null) {
            System.setProperty(key, value);
        } else {
            System.err.println("Warning: Environment variable '" + key + "' was not found in your configuration files.");
        }
    }
}