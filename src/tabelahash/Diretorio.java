package tabelahash;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;

public class Diretorio {

    public static File abreDiretorio() {
        JFileChooser dialogo = new JFileChooser();
        dialogo.setDialogTitle("Selecione a pasta onde estão os arquivos");
        dialogo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        dialogo.setAcceptAllFileFilterUsed(false);
        dialogo.showOpenDialog(null);
        String caminho = null;

        try {
            caminho = dialogo.getSelectedFile().getAbsolutePath();
        } catch (Exception e) {
            System.out.println("Erro ao abrir diretório" + e.getMessage());
        }

        File file = new File(caminho);
        return file;
    }

    public static Hash indexacao(File file, Hash hash) {
        File[] arquivos = file.listFiles();
        for (File arquivo : arquivos) {
            if (arquivo.getName().endsWith(".txt")) {
                hash = leitura(arquivo, hash);
            }
        }
        return hash;
    }

    public static Hash leitura(File arquivo, Hash hash) {
        try {
            FileReader conteudo = new FileReader(arquivo);
            try (BufferedReader bufLeitura = new BufferedReader(conteudo)) {
                String palavra = null;
                while ((palavra = bufLeitura.readLine()) != null) {
                    String[] palavras = palavra.split(" ");
                    for (String p : palavras) {
                        p = formataPalavra(p);
                        if (!p.equals("") && !StopWord.stopWord(p) && p.length() > 2) {
                            Palavra palavraChave = hash.buscar(p);
                            if (palavraChave == null) {
                                palavraChave = new Palavra(p);
                                Ocorrencia ocorrencia = new Ocorrencia(arquivo.getName());
                                palavraChave.addOcorrencia(ocorrencia);
                                hash.inserir(palavraChave, ocorrencia);
                            } else {
                                Ocorrencia ocorrencia = new Ocorrencia(arquivo.getName());
                                palavraChave.addOcorrencia(ocorrencia);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return hash;
    }

    public static String formataPalavra(String palavra) {
        palavra = palavra.replaceAll("[^a-zA-ZÀ-ÿ]", "");
        palavra = palavra.toLowerCase();
        return palavra;
    }
}
