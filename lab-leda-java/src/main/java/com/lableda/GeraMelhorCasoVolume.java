package com.lableda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GeraMelhorCasoVolume {

    public static void gerarMelhorCaso() {
        // Diretório atual do projeto
        String diretorioAtual = System.getProperty("user.dir");

        // Caminho relativo para o arquivo de entrada e de saída
        String caminhoEntrada = diretorioAtual + System.getProperty("file.separator") + "b3stocks_T1.csv";
        String caminhoSaida = diretorioAtual + System.getProperty("file.separator") + "melhorCasoVolume.csv";

        // Ler os volumes do arquivo de entrada
        String[] volumes = lerVolumes(caminhoEntrada);

        // Ordenar os volumes
        mergeSort(volumes);

        // Salvar os volumes ordenados no arquivo de saída
        salvarVolumesOrdenados(volumes, caminhoSaida);
    }

    private static String[] lerVolumes(String caminhoArquivo) {
        try {
            int numLinhas = 0;
            try (BufferedReader brCount = new BufferedReader(new FileReader(caminhoArquivo))) {
                while (brCount.readLine() != null) {
                    numLinhas++;
                }
            }
    
            String[] volumes = new String[numLinhas];
    
            // Ler os volumes do arquivo
            try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
                String line;
                int index = 0;
                while ((line = br.readLine()) != null) {
                    String[] campos = line.split(",");
                    String volume = campos[6]; 
                    volumes[index++] = volume;
                }
            }
    
            return volumes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    

    private static void mergeSort(String[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        String[] temp = new String[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);
    }

    private static void mergeSort(String[] arr, String[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, temp, left, mid);
            mergeSort(arr, temp, mid + 1, right);
            merge(arr, temp, left, mid, right);
        }
    }

    private static void merge(String[] arr, String[] temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (temp[i].compareTo(temp[j]) <= 0) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            arr[k] = temp[i];
            k++;
            i++;
        }
    }

    private static void salvarVolumesOrdenados(String[] volumes, String caminhoSaida) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(caminhoSaida))) {
            for (String volume : volumes) {
                pw.println(volume);
            }
            System.out.println("Arquivo gerado com sucesso: " + caminhoSaida);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
