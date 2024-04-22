package com.lableda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Criab3stocks_F1 {

    public static void criarArquivoF1() {
        // Arrays para armazenar as datas e os volumes negociados
        String[] datas = new String[1000]; // Suponha que haverá no máximo 1000 registros
        float[] volumes = new float[1000];
        int count = 0; // Contador para manter o controle do número de registros lidos

        String diretorioAtual = System.getProperty("user.dir");

        // Caminho relativo para o arquivo de entrada e de saída
        String caminhoEntrada = diretorioAtual + System.getProperty("file.separator") + "b3stocks_T1.csv";
        String caminhoSaida = diretorioAtual + System.getProperty("file.separator") + "b3stocks_F1.csv";

        // Ler o arquivo CSV e armazenar os dados nos arrays
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoEntrada))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Dividir a linha em campos usando a vírgula como delimitador
                String[] campos = line.split(",");

                // Extrair a data e o volume negociado do registro
                String data = campos[0];
                float volume = Float.parseFloat(campos[5]);

                // Redimensionar os arrays, se necessário
                if (count >= datas.length) {
                    int newLength = datas.length * 2;
                    datas = resizeArray(datas, newLength);
                    volumes = resizeArray(volumes, newLength);
                }

                // Armazenar os dados nos arrays
                datas[count] = data;
                volumes[count] = volume;
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Arrays para armazenar os maiores volumes negociados por dia
        String[] datasFiltradas = new String[count]; // Inicializamos com o mesmo tamanho de 'datas'
        float[] maioresVolumes = new float[count];
        int indice = 0; // Índice para manter o controle do número de registros filtrados

        // Identificar o maior volume negociado por dia
        for (int i = 0; i < count; i++) {
            boolean encontrado = false;
            for (int j = 0; j < indice; j++) {
                if (datas[i].equals(datasFiltradas[j])) {
                    encontrado = true;
                    if (volumes[i] > maioresVolumes[j]) {
                        maioresVolumes[j] = volumes[i];
                    }
                    break;
                }
            }
            if (!encontrado) {
                datasFiltradas[indice] = datas[i];
                maioresVolumes[indice] = volumes[i];
                indice++;
            }
        }

        // Escrever os registros filtrados no arquivo de saída
        try (PrintWriter pw = new PrintWriter(new FileWriter(caminhoSaida))) {
            for (int i = 0; i < indice; i++) {
                pw.println(datasFiltradas[i] + "," + maioresVolumes[i]);
            }

            System.out.println("Arquivo b3stocks_F1 gerado com sucesso: " + caminhoSaida);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Função para redimensionar um array
    private static String[] resizeArray(String[] array, int newLength) {
        String[] newArray = new String[newLength];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    // Função para redimensionar um array
    private static float[] resizeArray(float[] array, int newLength) {
        float[] newArray = new float[newLength];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }
}

