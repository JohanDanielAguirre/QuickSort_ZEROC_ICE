module QuickSort {
    sequence<int> IntSeq;

    class UnionResult {
            IntSeq data;
};

        interface SubServerUnion {
        void sort(IntSeq data);
        };

        interface ReturnCallBack {
        void sortResult(UnionResult result);
        };

        interface SortCoordinator {
        void createSubServer(SubServerUnion* subServer);
        void startQuickSort(IntSeq data, ReturnCallBack* cb);
        void receiveResult(UnionResult result);
        };
        };