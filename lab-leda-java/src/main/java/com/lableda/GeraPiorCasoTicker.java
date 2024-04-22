package com.lableda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GeraPiorCasoTicker {

    public void gerarPiorCaso() {
        String diretorioAtual = System.getProperty("user.dir");
        String caminhoEntrada = diretorioAtual + System.getProperty("file.separator") + "melhorCasoTicker.csv";
        String novoCaminhoSaida = diretorioAtual + System.getProperty("file.separator") + "piorCasoTicker.csv";

        int numLinhas = contarLinhas(caminhoEntrada);
        String[] linhas = lerLinhas(caminhoEntrada, numLinhas);
        inverterOrdemLinhas(linhas);

        salvarLinhasOrdenadas(linhas, novoCaminhoSaida);
    }

    private int contarLinhas(String caminhoArquivo) {
        int numLinhas = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            while (br.readLine() != null) {
                numLinhas++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numLinhas;
    }

    private String[] lerLinhas(String caminhoArquivo, int numLinhas) {
        String[] linhas = new String[numLinhas];
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String line;
            int index = 0;
            while ((line = br.readLine()) != null) {
                linhas[index++] = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linhas;
    }

    private void inverterOrdemLinhas(String[] linhas) {
        int i = 0;
        int j = linhas.length - 1;
        while (i < j) {
            String temp = linhas[i];
            linhas[i] = linhas[j];
            linhas[j] = temp;
            i++;
            j--;
        }
    }

    private void salvarLinhasOrdenadas(String[] linhas, String caminhoSaida) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(caminhoSaida))) {
            for (String linha : linhas) {
                pw.println(linha);
            }
            System.out.println("Arquivo gerado com sucesso: " + caminhoSaida);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
