package com.spachecor.ejerciciofinalsgb;

import com.spachecor.ejerciciofinalsgb.model.documents.DocumentosManager;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws IOException {
        DocumentosManager.exportarBBDD(Paths.get("src/main/resources/com/spachecor/ejerciciofinalsgb/dbexample").toAbsolutePath().toString());
        System.out.println(Arrays.toString(DocumentosManager.listarDocumentos()));

    }
}
