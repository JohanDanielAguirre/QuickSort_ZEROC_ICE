import java.util.Arrays;

import QuickSort.UnionResult;
import com.zeroc.Ice.Current;

public class SubServerI implements QuickSort.SubServerUnion {

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);

            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return (i + 1);
    }
    @Override
    public void sort(int[] data, Current current) {
        quickSort(data, 0, data.length - 1);
        //Arrays.parallelSort(data);
        UnionResult result = new UnionResult(data);
        SubServer.coordinator.receiveResult(result);
    }
}