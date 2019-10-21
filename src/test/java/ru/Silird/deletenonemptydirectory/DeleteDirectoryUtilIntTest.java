package ru.Silird.deletenonemptydirectory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteDirectoryUtilIntTest {
    private static final String BASE_TEST_DIRECTORY = "target/ioTests";

    @BeforeEach
    private void createAndTestDirectories() throws IOException {
        String dir1 = "1";
        String dir2 = "2";
        String dir3 = "3";
        String subDir1 = "s1";
        String subDir2 = "s2";
        String subDir3 = "s3";
        String fileName = "file";
        createDirectory(Paths.get(BASE_TEST_DIRECTORY));
        createDirectory(Paths.get(BASE_TEST_DIRECTORY, dir1));
        createDirectory(Paths.get(BASE_TEST_DIRECTORY, dir2));
        createDirectory(Paths.get(BASE_TEST_DIRECTORY, dir3));
        createDirectory(Paths.get(BASE_TEST_DIRECTORY, dir1, subDir1));
        createDirectory(Paths.get(BASE_TEST_DIRECTORY, dir1, subDir2));
        createDirectory(Paths.get(BASE_TEST_DIRECTORY, dir1, subDir3));
        createFile(Paths.get(BASE_TEST_DIRECTORY, dir1, subDir1, fileName));

        assertTrue(Files.exists(Paths.get(BASE_TEST_DIRECTORY)));
        assertTrue(Files.exists(Paths.get(BASE_TEST_DIRECTORY, dir1, subDir1, fileName)));
    }

    @Test
    public void deleteDirectoryPath_ExpectCleanDirectory() {
        boolean result = DeleteDirectoryUtil.deleteDirectory(Paths.get(BASE_TEST_DIRECTORY));

        assertTrue(result);
        assertFalse(Files.exists(Paths.get(BASE_TEST_DIRECTORY)));
    }

    @Test
    public void deleteDirectoryFile_ExpectCleanDirectory() {
        boolean result = DeleteDirectoryUtil.deleteDirectory(Paths.get(BASE_TEST_DIRECTORY).toFile());

        assertTrue(result);
        assertFalse(Files.exists(Paths.get(BASE_TEST_DIRECTORY)));
    }

    @Test
    public void deleteDirectoryWalkTree_ExpectCleanDirectory() {
        boolean result = DeleteDirectoryUtil.deleteDirectoryWalkTree(Paths.get(BASE_TEST_DIRECTORY));

        assertTrue(result);
        assertFalse(Files.exists(Paths.get(BASE_TEST_DIRECTORY)));
    }

    @Test
    public void deleteDirectoryCommonsIO_ExpectCleanDirectory() {
        boolean result = DeleteDirectoryUtil.deleteDirectoryCommonsIO(Paths.get(BASE_TEST_DIRECTORY).toFile());

        assertTrue(result);
        assertFalse(Files.exists(Paths.get(BASE_TEST_DIRECTORY)));
    }

    @Test
    public void deleteDirectoryForce_ExpectCleanDirectory() {
        boolean result = DeleteDirectoryUtil.deleteDirectoryForce(Paths.get(BASE_TEST_DIRECTORY).toFile());

        assertTrue(result);
        assertFalse(Files.exists(Paths.get(BASE_TEST_DIRECTORY)));
    }

    @Test
    public void deleteDirectoryForce1_ExpectCleanDirectory() {
        boolean result = DeleteDirectoryUtil.deleteDirectoryForce1(Paths.get(BASE_TEST_DIRECTORY).toFile());

        assertTrue(result);
        assertFalse(Files.exists(Paths.get(BASE_TEST_DIRECTORY)));
    }

    @Test
    public void DeleteDirectoryUtil_ExpectCleanDirectory() {
        boolean result = DeleteDirectoryUtil.deleteDirectorySpring(Paths.get(BASE_TEST_DIRECTORY));

        assertTrue(result);
        assertFalse(Files.exists(Paths.get(BASE_TEST_DIRECTORY)));
    }

    private void createFile(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    private void createDirectory(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
    }
}
