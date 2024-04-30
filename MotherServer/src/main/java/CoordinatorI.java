import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import QuickSort.ReturnCallBackPrx;
import QuickSort.SubServerUnionPrx;
import QuickSort.UnionResult;
import com.zeroc.Ice.Current;

public class CoordinatorI implements QuickSort.SortCoordinator {
    private final List<SubServerUnionPrx> subServers = new ArrayList<>();
    private int[] results;
    private int resultIndex;
    ReturnCallBackPrx cb;

    @Override
    public void createSubServer(SubServerUnionPrx subSever, Current current) {
        subServers.add(subSever);
    }


    @Override
    public void receiveResult(UnionResult result, Current current) {
        System.out.println("Received partial result");
        int[] partialResult = result.data;
        System.arraycopy(partialResult, 0, results, resultIndex, partialResult.length);
        resultIndex += partialResult.length;

        if (resultIndex == results.length) {
            sortResults();
        }
    }

    @Override
    public void startQuickSort(int[] data, ReturnCallBackPrx cb, Current current) {
        this.cb = cb;
        int numSubServers = subServers.size();
        int chunkSize = data.length / numSubServers;
        results = new int[data.length];
        resultIndex = 0;

        ExecutorService executor = Executors.newFixedThreadPool(numSubServers);

        for (int i = 0; i < numSubServers; i++) {
            int start = i * chunkSize;
            int end = (i == numSubServers - 1) ? data.length : (i + 1) * chunkSize;
            int[] chunk = Arrays.copyOfRange(data, start, end);
            int finalI = i;

            executor.submit(() -> {
                subServers.get(finalI).sort(chunk);
                System.out.println("Sent partial data to worker:" + finalI + ".");
            });
        }

        executor.shutdown();
    }

    private void sortResults() {
        quickSort(results, 0, results.length - 1);
        UnionResult finalResult = new UnionResult(results);
        cb.sortResult(finalResult);
    }

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
}