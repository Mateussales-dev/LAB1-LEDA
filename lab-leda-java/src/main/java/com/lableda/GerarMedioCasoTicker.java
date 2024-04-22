package com.lableda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GerarMedioCasoTicker {

    public void gerarMedioCaso(String caminhoSaida) {
        // Diretório atual do projeto
        String diretorioAtual = System.getProperty("user.dir");

        // Caminho relativo para o arquivo de entrada e de saída
        String caminhoEntrada = diretorioAtual + System.getProperty("file.separator") + "b3stocks_T1.csv";
        String novoCaminhoSaida = diretorioAtual + System.getProperty("file.separator") + caminhoSaida;

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoEntrada));
             PrintWriter pw = new PrintWriter(new FileWriter(novoCaminhoSaida))) {

            String line;
            while ((line = br.readLine()) != null) {
                // Dividir a linha em campos usando a vírgula como delimitador
                String[] campos = line.split(",");
                pw.println(campos[1]); // Supondo que o campo de ticket esteja no índice 1
            }

            System.out.println("Arquivo gerado com sucesso: " + caminhoSaida);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
