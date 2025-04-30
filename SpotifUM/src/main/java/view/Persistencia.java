package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import User.User;

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

    public static void saveUsers(Map<String, User> users) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.bin"))) {
            oos.writeObject(users);
        }
    }
    
    
    public static Map<String, User> loadUsers() throws IOException, ClassNotFoundException {
        File f = new File("users.bin");
        if (!f.exists()) return new HashMap<>(); // ficheiro ainda não existe
    
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.bin"))) {
            return (Map<String, User>) ois.readObject();
        }
    }





}






