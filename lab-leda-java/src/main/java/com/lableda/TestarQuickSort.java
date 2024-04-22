package com.lableda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestarQuickSort {

    public static long testarOrdenacao(String caminhoArquivo) {
        long inicio = System.currentTimeMillis();

        // Ler os valores do arquivo
        String[] valores = lerValores(caminhoArquivo);

        // Ordenar os valores usando o Quick Sort
        quickSort(valores, 0, valores.length - 1);

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

    private static void quickSort(String[] arr, int low, int high) {
        if (low < high) {
            // Encontra o índice do pivô
            int pi = partition(arr, low, high);

            // Ordena recursivamente os elementos antes e depois do pivô
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(String[] arr, int low, int high) {
        // Seleciona o elemento do meio como pivô
        String pivot = arr[(low + high) / 2];
        int i = low - 1;
        int j = high + 1;

        while (true) {
            do {
                i++;
            } while (arr[i].compareTo(pivot) < 0);

            do {
                j--;
            } while (arr[j].compareTo(pivot) > 0);

            if (i >= j) {
                return j;
            }

            // Troca arr[i] e arr[j]
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}

