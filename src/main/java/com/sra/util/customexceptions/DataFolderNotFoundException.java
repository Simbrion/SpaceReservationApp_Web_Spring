package com.sra.util.customexceptions;

import java.io.FileNotFoundException;

public class DataFolderNotFoundException extends FileNotFoundException {

    public DataFolderNotFoundException(String message) {
        super(message);
    }

}
