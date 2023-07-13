package tabelahash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class StopWord {

    static ArrayList<String> stopWords = new ArrayList<String>();

    public void setStopWords(String caminho) {
        String caminhoStopWords = caminho;
        try {
            FileReader conteudo = new FileReader(caminhoStopWords);
            try (BufferedReader bufLeitura = new BufferedReader(conteudo)) {
                String palavra = null;
                while ((palavra = bufLeitura.readLine()) != null) {
                    stopWords.add(palavra);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo");
        }
    }

    static boolean stopWord(String p) {
        for (String s : stopWords) {
            if ((s.toLowerCase()).equals(p)) {
                return true;
            }
        }
        return false;
    }

}
