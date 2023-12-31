package vit.projects.hudeem.services;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class FileService {

    @SneakyThrows
    public String readFromInputStream(InputStream inputStream) {
        if (inputStream == null) throw new RuntimeException("Empty InputStream");
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        inputStream.close();
        return resultStringBuilder.toString();
    }
}
