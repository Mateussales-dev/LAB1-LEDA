package com.lableda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;


public class GeraMelhorCasoFlutuacoes {

    public void gerarMelhorCaso() {
        String diretorioAtual = System.getProperty("user.dir");
        String caminhoEntrada = diretorioAtual + System.getProperty("file.separator") + "b3stocks_T1.csv";
        String novoCaminhoSaida = diretorioAtual + System.getProperty("file.separator") + "melhorCasoFlutuacoes.csv";

        RegistroFlutuacoes[] registros = lerRegistros(caminhoEntrada);

        // Ordenar os registros com base na diferença entre high e low
        mergeSort(registros, 0, registros.length - 1);

        salvarRegistrosOrdenados(registros, novoCaminhoSaida);
    }

    private RegistroFlutuacoes[] lerRegistros(String caminhoArquivo) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(caminhoArquivo));
            String line;
            int numLinhas = 0;
            while (br.readLine() != null) {
                numLinhas++;
            }

            RegistroFlutuacoes[] registros = new RegistroFlutuacoes[numLinhas];
            br.close();

            br = new BufferedReader(new FileReader(caminhoArquivo));
            int index = 0;
            while ((line = br.readLine()) != null) {
                String[] campos = line.split(",");
                double high = Double.parseDouble(campos[4]);
                double low = Double.parseDouble(campos[5]);
                int diferenca = (int) (high - low);
                registros[index++] = new RegistroFlutuacoes(high, low, diferenca);
            }
            return registros;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void mergeSort(RegistroFlutuacoes[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(RegistroFlutuacoes[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        RegistroFlutuacoes[] L = new RegistroFlutuacoes[n1];
        RegistroFlutuacoes[] R = new RegistroFlutuacoes[n2];

        for (int i = 0; i < n1; ++i) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i].diferenca <= R[j].diferenca) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    private void salvarRegistrosOrdenados(RegistroFlutuacoes[] registros, String caminhoSaida) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(caminhoSaida))) {
            for (RegistroFlutuacoes registro : registros) {
                pw.println(registro);
            }
            System.out.println("Arquivo gerado com sucesso: " + caminhoSaida);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class RegistroFlutuacoes {
        private double high;
        private double low;
        private int diferenca;

        public RegistroFlutuacoes(double high, double low, int diferenca) {
            this.high = high;
            this.low = low;
            this.diferenca = diferenca;
        }

        @Override
        public String toString() {
            return String.format(Locale.US,"%.2f,%.2f", high, low);
        }
    }
}
