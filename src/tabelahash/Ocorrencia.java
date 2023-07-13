package tabelahash;

public class Ocorrencia {
    String nomeArquivo;
    int numeroOcorrencias;

    public Ocorrencia(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.numeroOcorrencias = 1;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void incrementaQuantidade() {
        this.numeroOcorrencias++;
    }
}
