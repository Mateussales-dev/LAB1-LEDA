package com.lableda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestarHeapSort {

    public static long testarOrdenacao(String caminhoArquivo) {
        long inicio = System.currentTimeMillis();

        // Ler os valores do arquivo
        String[] valores = lerValores(caminhoArquivo);

        // Ordenar os valores usando o Heap Sort
        heapSort(valores);

        long fim = System.currentTimeMillis();

        // Calcular e retornar o tempo de execução em milissegundos
        return fim - inicio;
    }

    private static String[] lerValores(String caminhoArquivo) {
        try {
            
            int numLinhas = 0;
            try (BufferedReader brCount = new BufferedReader(new FileReader(caminhoArquivo))) {
                while (brCount.readLine() != null) {
                    numLinhas++;
                }
            }

            
            String[] valores = new String[numLinhas];

           
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

    private static void heapSort(String[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            String temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    private static void heapify(String[] arr, int n, int i) {
        int largest = i; 
        int left = 2 * i + 1; 
        int right = 2 * i + 2; 

        if (left < n && arr[left].compareTo(arr[largest]) > 0) {
            largest = left;
        }

        if (right < n && arr[right].compareTo(arr[largest]) > 0) {
            largest = right;
        }

        if (largest != i) {
            String swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }
}
