/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tabelahash;

import java.io.File;

import javax.swing.JOptionPane;

/**
 *
 * @author vinic
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        StopWord stopWord = new StopWord();
        stopWord.setStopWords("arquivos/stopword.txt");

        File file = Diretorio.abreDiretorio();

        Hash hash = new Hash();

        hash = Diretorio.indexacao(file, hash);

        hash.salvarTabela();

        JOptionPane.showMessageDialog(null, "Tabela Hash criada com sucesso!");

        do {
            String palavra = JOptionPane.showInputDialog("Digite a palavra que deseja buscar: ");
            palavra = Diretorio.formataPalavra(palavra);
            if (palavra.equals("")) {
                JOptionPane.showMessageDialog(null, "Palavra inválida!");
            } else {
                Palavra palavraChave = hash.buscar(palavra);
                if (palavraChave == null) {
                    JOptionPane.showMessageDialog(null, "Palavra não encontrada!");
                } else {
                    palavraChave.imprimeOcorrencias();
                }
            }
        } while (JOptionPane.showConfirmDialog(null, "Deseja buscar outra palavra?") == 0);

    }

}
