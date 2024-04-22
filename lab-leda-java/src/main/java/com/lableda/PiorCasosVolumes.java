package com.lableda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PiorCasosVolumes {
    public void gerarPiorCaso(String caminhoSaida) {
        // Diretório atual do projeto
        String diretorioAtual = System.getProperty("user.dir");

        // Caminho relativo para o arquivo de entrada e de saída
        String caminhoEntrada = diretorioAtual + System.getProperty("file.separator") + "piorCasoVolume.csv";
        String novoCaminhoSaida = diretorioAtual + System.getProperty("file.separator") + caminhoSaida;

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoEntrada));
             PrintWriter pw = new PrintWriter(new FileWriter(novoCaminhoSaida))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] campos = line.split(",");
                pw.println(campos[0]); 
            }

            System.out.println("Arquivo gerado com sucesso: " + caminhoSaida);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
