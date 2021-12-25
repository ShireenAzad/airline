package com.everest.airline;

import java.io.IOException;
import java.nio.file.*;

public class FileDeletion {
    public  void deleteFile(long number) throws IOException {
        String filePath="/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database/"+number+".txt";
        Path path = Paths.get(filePath);

        try {
            Files.delete(path);
            System.out.println("File or directory deleted successfully");
        } catch (NoSuchFileException e) {
            System.out.printf("No such file or directory: %s\n", path);
        } catch (DirectoryNotEmptyException e) {
            System.out.printf("Directory %s is not empty\n", path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
