import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;

public class SubServer {
    static QuickSort.SortCoordinatorPrx coordinator;

    public static void main(String[] args) {
        try (Communicator communicator = Util.initialize(args)) {

            // Create an adapter an initialize the worker
            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("MergeSubServer", "default -p 0");
            adapter.add(new SubServerI(), Util.stringToIdentity("subServer"));
            adapter.activate();

            // Register the worker with the coordinator
            com.zeroc.Ice.ObjectPrx main = communicator.stringToProxy("coordinator:default -p 27402");
            coordinator = QuickSort.SortCoordinatorPrx.checkedCast(main);
            if (coordinator == null) {
                throw new Error("Invalid proxy");
            } else {
                coordinator.createSubServer(
                        QuickSort.SubServerUnionPrx.checkedCast(adapter.createProxy(Util.stringToIdentity("subServer"))));
            }

            communicator.waitForShutdown();
        }
    }
}