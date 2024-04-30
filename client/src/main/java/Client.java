import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import QuickSort.ReturnCallBack;
import QuickSort.ReturnCallBackPrx;
import QuickSort.SortCoordinatorPrx;
import QuickSort.UnionResult;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;


public class Client implements ReturnCallBack {
    private static final Scanner sc = new Scanner(System.in);
    private CountDownLatch latch;
    public int[] lastResult;
    private long startTime;

    public static void main(String[] args) {
        try (Communicator communicator = Util.initialize(args)) {
            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("QuickClient", "default -p 0");
            Client client = new Client();
            adapter.add(client, Util.stringToIdentity("client"));
            adapter.activate();
            ObjectPrx base = communicator.stringToProxy("coordinator:default -p 27402");
            SortCoordinatorPrx coordinator = SortCoordinatorPrx.checkedCast(base);
            if (coordinator == null) {
                throw new Error("Invalid proxy");
            }
            System.out.println("Client connected to server");
            ReturnCallBackPrx callback = ReturnCallBackPrx.uncheckedCast(
                    adapter.createProxy(Util.stringToIdentity("client")));
            int[] data;
            do {
                data = menu(client);
                 if (data.length != 0) {
                    client.latch = new CountDownLatch(1);
                    client.startTime = System.nanoTime(); // Start timing
                    coordinator.startQuickSort(data, callback);
                    client.latch.await();
                }
            } while (data.length != 0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void sortResult(UnionResult result, Current current) {
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1e6;
        System.out.println("Received sorted data: " + Arrays.toString(result.data));
        System.out.println("Sorting took " + duration + " milliseconds.");
        lastResult = result.data;
        if (latch != null){latch.countDown();}
    }

    public static int[] menu(Client client) {

        System.out.println("""
                 \s
                 | Menu\s
                 1. Generate a random List.\s
                 2. Load data to sort.\s
                 0. Exit.\s
                \s""");

        System.out.print("Select an option: ");
        int selection = sc.nextInt();
        sc.nextLine();
        return menuSelection(client, selection);
    }

    public static int[] menuSelection(Client client, int selection) {
        int[] list = new int[0];

        boolean exit;

        do {
            exit = true;

            switch (selection) {
                case 1:
                    list = generateList();
                    break;
                case 2:
                    System.out.print("Enter the file name: ");
                    String fileName = sc.nextLine();
                    list = loadFile(fileName);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    exit = false;
                    break;
            }
        } while (!exit);
        return list;
    }

    public static int[] generateList() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of the list: ");
        int value = sc.nextInt();
        sc.nextLine();
        int[] data = new int[value];
        for (int i = 0; i < data.length; i++) {
            data[i] = (int) (Math.random() * 1000);
        }
        return data;
    }
    private static int[] loadFile(String fileName) {
        File assetsDir = new File("assets");
        File file = new File(assetsDir, fileName+".txt");
        List<Integer> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}