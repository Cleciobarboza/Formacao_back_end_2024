package repository.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Path;

public final class FileSystemDataSource {

    private static final String RW = "rw";

    private FileSystemDataSource(Path p) {
        super();
    }

    public static Reader getFileReader(Path p) throws IOException {
        return new FileReader(p.toFile());
    }

    public static Writer getFileWriter(Path p) throws IOException {
        return new FileWriter(p.toFile());
    }

    public static RandomAccessFile getRandomAccessFileToRW(Path p) throws IOException {
        return new RandomAccessFile(p.toAbsolutePath().toString(), RW);
    }

    public static BufferedReader getBufferToR(Path p) throws IOException {
        return new BufferedReader(getFileReader(p));
    }

    public static BufferedWriter getBufferToW(Path p) throws IOException {
        return new BufferedWriter(getFileWriter(p));
    }

    public static void close(Reader io) throws IOException {
        if (io != null) {
            io.close();
        }
    }

    public static void close(Writer io) throws IOException {
        if (io != null) {
            io.close();
        }
    }

    public static void close(RandomAccessFile io) throws IOException {
        if (io != null) {
            io.close();
        }
    }
}
