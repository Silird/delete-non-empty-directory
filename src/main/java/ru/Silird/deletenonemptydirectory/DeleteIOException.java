package ru.Silird.deletenonemptydirectory;

import java.io.File;

@SuppressWarnings("WeakerAccess")
public class DeleteIOException extends RuntimeException {
    public DeleteIOException(File file) {
        super("Ошибка удаления файла: " + file.getAbsolutePath());
    }
}
