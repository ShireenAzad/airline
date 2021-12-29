package com.everest.airline.filehandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDeletion {
    public  void deleteFile(long number) throws IOException {
        String filePath="/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database/"+number+".txt";
        Path path = Paths.get(filePath);
        boolean result=Files.deleteIfExists(path);
        if (!result)
            throw new NoSuchFileException("No such File Exist");
    }
}

