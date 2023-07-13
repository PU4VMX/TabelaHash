package tabelahash;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Hash {
    private static final int tamanho_inicial = 10;
    private List<LinkedList<Palavra>> tabela;
    private int tamanho;

    public Hash() {
        this.tabela = new ArrayList<>(tamanho_inicial);
        for (int i = 0; i < tamanho_inicial; i++) {
            tabela.add(new LinkedList<>());
        }
        this.tamanho = 0;
    }

    private int calcularHash(String palavraChave) {
        int hash = palavraChave.hashCode();
        return Math.abs(hash) % tabela.size();
    }

    public void inserir(Palavra palavra, Ocorrencia ocorrencia) {
        int indice = calcularHash(palavra.getPalavraChave());
        LinkedList<Palavra> listaPalavras = tabela.get(indice);
        for (Palavra p : listaPalavras) {
            if (p.getPalavraChave().equals(palavra.getPalavraChave())) {
                p.addOcorrencia(ocorrencia);
                return;
            }
        }
        listaPalavras.add(palavra);
        tamanho++;
    }

    public Palavra buscar(String palavraChave) {
        int indice = calcularHash(palavraChave);
        LinkedList<Palavra> listaPalavras = tabela.get(indice);
        for (Palavra palavra : listaPalavras) {
            if (palavra.getPalavraChave().equals(palavraChave)) {
                return palavra;
            }
        }
        return null;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void salvarTabela() {
        try {
            FileWriter arquivo = new FileWriter("arquivos/tabelaHash.txt");
            for (int i = 0; i < tabela.size(); i++) {
                arquivo.write("Indice: " + i + "\n");
                LinkedList<Palavra> listaPalavras = tabela.get(i);
                for (Palavra palavra : listaPalavras) {
                    arquivo.write(palavra.getPalavraChave() + " ");
                    for (Ocorrencia ocorrencia : palavra.ocorrencias) {
                        arquivo.write(ocorrencia.getNomeArquivo() + " ");
                    }
                    arquivo.write("\n");
                }
                arquivo.write("\n");
            }
            arquivo.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar tabela hash: " + e.getMessage());
        }
    }

}