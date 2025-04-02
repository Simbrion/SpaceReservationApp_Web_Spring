package com.sra.util;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class Reader {

    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public BufferedReader getReader() {
        return this.bufferedReader;
    }

    public String readLine() throws IOException {
        return bufferedReader.readLine();
    }
}
