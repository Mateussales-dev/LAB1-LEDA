package com.lableda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Criab3stocks_T1 {
    public void criarArquivoT1() {
        // Diretório atual do projeto
        String diretorioAtual = System.getProperty("user.dir");

        // Caminho relativo para o arquivo de entrada e de saída
        String caminhoEntrada = diretorioAtual + System.getProperty("file.separator") + "b3_stocks_1994_2020.csv";
        String caminhoSaida = diretorioAtual + System.getProperty("file.separator") + "b3stocks_T1.csv";

        // Ler o arquivo CSV e gerar o novo arquivo
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoEntrada));
             PrintWriter pw = new PrintWriter(new FileWriter(caminhoSaida))) {

            String line = br.readLine();

            // Processar as linhas válidas
            while ((line = br.readLine()) != null) {
                String[] campos = line.split(",");

                // Transformar o campo de data de YYYY-MM-DD para DD/MM/YYYY
                campos[0] = transformarData(campos[0]);

                // Escrever a linha transformada no novo arquivo
                StringBuilder linhaTransformada = new StringBuilder();
                for (int i = 0; i < campos.length; i++) {
                    linhaTransformada.append(campos[i]);
                    if (i < campos.length - 1) {
                        linhaTransformada.append(",");
                    }
                }
                pw.println(linhaTransformada);
            }

            System.out.println("Arquivo b3stocks_T1.csv gerado com sucesso: " + caminhoSaida);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Função para transformar a data de YYYY-MM-DD para DD/MM/YYYY
    private static String transformarData(String data) {
        String[] partes = data.split("-");
        if (partes.length < 3) {
            throw new IllegalArgumentException("Formato de data inválido: " + data);
        }
        return partes[2] + "/" + partes[1] + "/" + partes[0];
    }
}



