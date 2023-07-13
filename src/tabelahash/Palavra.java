package tabelahash;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Palavra {
    String palavra;
    ArrayList<Ocorrencia> ocorrencias;

    public Palavra(String palavra) {
        this.palavra = palavra;
        this.ocorrencias = new ArrayList<>();
    }

    public void addOcorrencia(Ocorrencia ocorrencia) {
        for (Ocorrencia o : ocorrencias) {
            if (o.getNomeArquivo().equals(ocorrencia.getNomeArquivo())) {
                o.incrementaQuantidade();
                return;
            }
        }
        ocorrencias.add(ocorrencia);
    }

    public String getPalavraChave() {
        return palavra;
    }

    public void imprimeOcorrencias() {
        // armazenar temporariamente as ocorrencias
        ArrayList<Ocorrencia> ocorrenciasTemp = new ArrayList<>();
        for (Ocorrencia o : ocorrencias) {
            ocorrenciasTemp.add(o);
        }
        // ordenar as ocorrencias
        for (int i = 0; i < ocorrenciasTemp.size(); i++) {
            for (int j = 0; j < ocorrenciasTemp.size() - 1; j++) {
                if (ocorrenciasTemp.get(j).numeroOcorrencias < ocorrenciasTemp.get(j + 1).numeroOcorrencias) {
                    Ocorrencia aux = ocorrenciasTemp.get(j);
                    ocorrenciasTemp.set(j, ocorrenciasTemp.get(j + 1));
                    ocorrenciasTemp.set(j + 1, aux);
                }
            }
        }
        // exibir no joptionpane todas as ocorrencias em uma unica janela
        String mensagem = "Palavra: " + palavra + "\n";
        for (Ocorrencia o : ocorrenciasTemp) {
            mensagem += "Arquivo: " + o.getNomeArquivo() + " - Ocorrencias: " + o.numeroOcorrencias + "\n";
        }
        JOptionPane.showMessageDialog(null, mensagem);
    }

}
