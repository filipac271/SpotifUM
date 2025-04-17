package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Persistencia {

    public void readLine(String csvdiretoria) {

        try (BufferedReader br = new BufferedReader(new FileReader(csvdiretoria))) {
            String linha = br.readLine();
            if (linha != null) {
                String[] campos = linha.split(";");
                for (String campo : campos) {
                    System.out.println(campo.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeLine(String linha, String csvDiretoria) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvDiretoria, true))) {
            bw.write(linha); 
            bw.newLine(); 
            System.out.println("Linha escrita com sucesso: " + linha);
            
        } catch (IOException e) {
            System.out.println("n funfa");
            e.printStackTrace();
        }
    }

}
