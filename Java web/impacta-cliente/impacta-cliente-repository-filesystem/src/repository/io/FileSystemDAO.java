package repository.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;

import domain.Closeable;

abstract class FileSystemDAO implements Closeable {

    final RandomAccessFile source;
    final long lengthFile;

    FileSystemDAO(Path p) throws IOException {
        super();

        this.source = FileSystemDataSource.getRandomAccessFileToRW(p);
        this.lengthFile = source.length();
    }

    /** {@inheritDoc} */
    @Override
    public void close() throws Exception {
        if (source != null) {
            source.close();
        }
    }
}
