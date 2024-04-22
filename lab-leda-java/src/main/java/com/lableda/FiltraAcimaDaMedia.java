package com.lableda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FiltraAcimaDaMedia {

    public static void filtrarRegistrosAcimaDaMedia() {
        // Diretório atual do projeto
        String diretorioAtual = System.getProperty("user.dir");

        // Caminho relativo para o arquivo de entrada e de saída
        String caminhoEntrada = diretorioAtual + System.getProperty("file.separator") + "b3stocks_T1.csv";
        String caminhoSaida = diretorioAtual + System.getProperty("file.separator") + "b3stocks_T2.csv";
        
        // Variáveis para armazenar a soma do volume negociado e o número de registros
        float somaVolume = 0;
        int numRegistros = 0;

        // Calcular a soma do volume negociado e o número de registros
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoEntrada))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] campos = line.split(",");
                float volume = Float.parseFloat(campos[5]);
                somaVolume += volume;
                numRegistros++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Calcular a média diária de volume negociado
        float mediaDiaria = somaVolume / numRegistros;

        // Filtrar os registros que possuem volume negociado acima da média
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoEntrada));
             PrintWriter pw = new PrintWriter(new FileWriter(caminhoSaida))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] campos = line.split(",");
                float volume = Float.parseFloat(campos[5]);
                if (volume > mediaDiaria) {
                    pw.println(line);
                }
            }

            System.out.println("Registros filtrados com sucesso e salvos em: " + caminhoSaida);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
