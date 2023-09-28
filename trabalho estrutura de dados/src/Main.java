import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o tamanho do vetor: ");
        int tamanho = scanner.nextInt();
        int[] vetor = new int[tamanho];

        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt(100); // Preenche o vetor com valores aleatórios de 0 a 99
        }
        System.out.println("Vetor inicial: "  + Arrays.toString(vetor));
        while (true) {
            System.out.println("\nEscolha o tipo de ordenação:");
            System.out.println("1 - Ordenação em bolha");
            System.out.println("2 - Ordenação rápida (Quicksort)");
            System.out.println("3 - Ordenação Mergesort");
            System.out.println("4 - Ordenação Heap Sort");
            System.out.println("0 - Sair");

            int escolha = scanner.nextInt();

            if (escolha == 1) {
                long startTime = System.nanoTime();
                bubbleSort(vetor);
                long endTime = System.nanoTime();
                printVector(vetor);
                System.out.println("Tempo de execução: " + (endTime - startTime) / 1e9 + " segundos");
            } else if (escolha == 2) {
                long startTime = System.nanoTime();
                quickSort(vetor, 0, vetor.length - 1);
                long endTime = System.nanoTime();
                printVector(vetor);
                System.out.println("Tempo de execução: " + (endTime - startTime) / 1e9 + " segundos");
            } else if (escolha == 3) {
                long startTime = System.nanoTime();
                mergeSort(vetor);
                long endTime = System.nanoTime();
                printVector(vetor);
                System.out.println("Tempo de execução: " + (endTime - startTime) / 1e9 + " segundos");
            } else if (escolha == 4) {
                long startTime = System.nanoTime();
                heapSort(vetor);
                long endTime = System.nanoTime();
                printVector(vetor);
                System.out.println("Tempo de execução: " + (endTime - startTime) / 1e9 + " segundos");
            } else if (escolha == 0) {
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Implementação do Bubble Sort
    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Implementação do Quick Sort
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Implementação do Merge Sort
    private static void mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    // Implementação do Heap Sort
    private static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    // Função para imprimir o vetor
    private static void printVector(int[] arr) {
        System.out.println("Vetor ordenado: " + Arrays.toString(arr));
    }
}
