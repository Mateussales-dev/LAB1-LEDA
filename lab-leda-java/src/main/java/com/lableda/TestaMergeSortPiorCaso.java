package com.lableda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestaMergeSortPiorCaso {

    public static long testarOrdenacao(String caminhoArquivo) {
        long inicio = System.currentTimeMillis();

        // Ler os valores do arquivo
        String[] valores = lerValores(caminhoArquivo);

        // Ordenar os valores usando o Merge Sort
        mergeSort(valores);

        long fim = System.currentTimeMillis();

        // Calcular e retornar o tempo de execução em milissegundos
        return fim - inicio;
    }

    private static String[] lerValores(String caminhoArquivo) {
        try {
            // Contar o número de linhas no arquivo
            int numLinhas = 0;
            try (BufferedReader brCount = new BufferedReader(new FileReader(caminhoArquivo))) {
                while (brCount.readLine() != null) {
                    numLinhas++;
                }
            }

            // Inicializar o array de valores com o número de linhas
            String[] valores = new String[numLinhas];

            // Ler os valores do arquivo e armazená-los no array
            try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
                String line;
                int index = 0;
                while ((line = br.readLine()) != null) {
                    valores[index++] = line;
                }
            }

            return valores;
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
}
