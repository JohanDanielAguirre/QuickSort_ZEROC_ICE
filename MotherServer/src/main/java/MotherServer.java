import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;

public class MotherServer {

    public static void main(String[] args) {
        try (Communicator communicator = Util.initialize(args, "MotherServer.cfg")) {

            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("QuickSort", "default -p 27402");
            adapter.add(new CoordinatorI(), Util.stringToIdentity("coordinator"));
            adapter.activate();

            communicator.waitForShutdown();
        }
    }
}