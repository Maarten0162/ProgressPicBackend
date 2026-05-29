package com.Maarten0162.ProgressPicBackend;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProgressPicBackendApplication {

	public static void main(String[] args) {
		loadEnv();
		SpringApplication.run(ProgressPicBackendApplication.class, args);
	}

	 private static void loadEnv() {
        Path envFile = Path.of(".env");
        if (!Files.exists(envFile)) return;
        try (var lines = Files.lines(envFile)) {
            lines
                .filter(line -> !line.isBlank() && !line.startsWith("#") && line.contains("="))
                .forEach(line -> {
                    int idx = line.indexOf('=');
                    String key = line.substring(0, idx).trim();
                    String value = line.substring(idx + 1).trim()
                                       .replaceAll("(^\")|(\"$)", "");
                    System.setProperty(key, value);
                });
        } catch (IOException e) {
            System.err.println("Could not load .env: " + e.getMessage());
        }
    }

}
