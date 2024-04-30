//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.10
//
// <auto-generated>
//
// Generated from file `QuickSort.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package QuickSort;

public interface SortCoordinator extends com.zeroc.Ice.Object
{
    void createSubServer(SubServerUnionPrx subServer, com.zeroc.Ice.Current current);

    void startQuickSort(int[] data, ReturnCallBackPrx cb, com.zeroc.Ice.Current current);

    void receiveResult(UnionResult result, com.zeroc.Ice.Current current);

    /** @hidden */
    static final String[] _iceIds =
    {
        "::Ice::Object",
        "::QuickSort::SortCoordinator"
    };

    @Override
    default String[] ice_ids(com.zeroc.Ice.Current current)
    {
        return _iceIds;
    }

    @Override
    default String ice_id(com.zeroc.Ice.Current current)
    {
        return ice_staticId();
    }

    static String ice_staticId()
    {
        return "::QuickSort::SortCoordinator";
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_createSubServer(SortCoordinator obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        SubServerUnionPrx iceP_subServer;
        iceP_subServer = SubServerUnionPrx.uncheckedCast(istr.readProxy());
        inS.endReadParams();
        obj.createSubServer(iceP_subServer, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_startQuickSort(SortCoordinator obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        int[] iceP_data;
        ReturnCallBackPrx iceP_cb;
        iceP_data = istr.readIntSeq();
        iceP_cb = ReturnCallBackPrx.uncheckedCast(istr.readProxy());
        inS.endReadParams();
        obj.startQuickSort(iceP_data, iceP_cb, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_receiveResult(SortCoordinator obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        final com.zeroc.IceInternal.Holder<UnionResult> icePP_result = new com.zeroc.IceInternal.Holder<>();
        istr.readValue(v -> icePP_result.value = v, UnionResult.class);
        istr.readPendingValues();
        inS.endReadParams();
        UnionResult iceP_result = icePP_result.value;
        obj.receiveResult(iceP_result, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /** @hidden */
    final static String[] _iceOps =
    {
        "createSubServer",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "receiveResult",
        "startQuickSort"
    };

    /** @hidden */
    @Override
    default java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceDispatch(com.zeroc.IceInternal.Incoming in, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        int pos = java.util.Arrays.binarySearch(_iceOps, current.operation);
        if(pos < 0)
        {
            throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return _iceD_createSubServer(this, in, current);
            }
            case 1:
            {
                return com.zeroc.Ice.Object._iceD_ice_id(this, in, current);
            }
            case 2:
            {
                return com.zeroc.Ice.Object._iceD_ice_ids(this, in, current);
            }
            case 3:
            {
                return com.zeroc.Ice.Object._iceD_ice_isA(this, in, current);
            }
            case 4:
            {
                return com.zeroc.Ice.Object._iceD_ice_ping(this, in, current);
            }
            case 5:
            {
                return _iceD_receiveResult(this, in, current);
            }
            case 6:
            {
                return _iceD_startQuickSort(this, in, current);
            }
        }

        assert(false);
        throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
    }
}
