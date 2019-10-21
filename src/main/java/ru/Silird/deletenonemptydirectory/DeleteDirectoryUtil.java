package ru.Silird.deletenonemptydirectory;

import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;

@SuppressWarnings("WeakerAccess")
public class DeleteDirectoryUtil {
    private static final Logger log = LoggerFactory.getLogger(DeleteDirectoryUtil.class);

    public static boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteDirectory(file);
                }
            }
            Path p = path.toPath();
            try {
                Files.delete(p);
                return true;
            }
            catch (IOException ex) {
                ex.printStackTrace();
                return false;
            }
        }

        return true;
    }

    public static boolean deleteDirectory(Path path) {
        try {
            Files.walk(path)
                    .sorted(Comparator.reverseOrder())
//                .map(Path::toFile)
                    .forEach(
//                    if (!file.delete()) throw new DeleteIOException(file);
//                    try {
//                        Files.delete(file);
//                    }
//                    catch (IOException e) {
//                        e.printStackTrace();
//                        throw new DeleteIOException(file.toFile());
//                    }
                            DeleteDirectoryUtil::deleteFile
                    );
        }
        catch (IOException | DeleteIOException ex) {
            log.error("Ошибка удаления директории: " + path.toAbsolutePath(), ex);
            return false;
        }
//        catch (DeleteIOException ex) {
//            log.error(ex.getMessage(), ex);
//            return false;
//        }
        return true;
    }

    private static void deleteFile(Path path) {
        try {
            Files.delete(path);
        }
//        catch (DirectoryNotEmptyException ex) {
//            deleteFile(path);
//        }
        catch (IOException ex) {
            ex.printStackTrace();
            throw new DeleteIOException(path.toFile());
        }
    }

    public static boolean deleteDirectoryWalkTree(Path path) {
        FileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                if (exc != null) {
                    throw exc;
                }
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        };
        try {
            Files.walkFileTree(path, visitor);
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean deleteDirectoryCommonsIO(File path) {
        try {
            FileUtils.deleteDirectory(path);
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean deleteDirectorySpring(Path path) {
        try {
            return FileSystemUtils.deleteRecursively(path);
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteDirectoryForce(File path) {
        try {
            FileUtils.forceDelete(path);
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean deleteDirectoryForce1(File path) {
        try {
            FileDeleteStrategy.FORCE.delete(path);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
