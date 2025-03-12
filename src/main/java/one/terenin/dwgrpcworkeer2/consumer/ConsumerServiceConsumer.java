package one.terenin.dwgrpcworkeer2.consumer;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import one.terenin.ConsumerGrpcApiGrpc;
import one.terenin.Protos;
import one.terenin.dwgrpcworkeer2.common.DataHolder;
import one.terenin.dwgrpcworkeer2.observer.ConsumerRequestObserver;
import one.terenin.dwgrpcworkeer2.observer.ConsumerResponseObserver;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumerServiceConsumer {

    private final ConsumerGrpcApiGrpc.ConsumerGrpcApiBlockingStub blockingStub;
    private final ConsumerGrpcApiGrpc.ConsumerGrpcApiStub asyncStub;
    private final ConsumerResponseObserver observer;

    public Protos.DataBundle requestSingleData() {
        Protos.DataBundle bundle = blockingStub.sendDataMonoSingle(Protos.Empty.newBuilder().build());
        return bundle;
    }

    public List<Protos.DataBundle> requestMultipleData(int dataCount) {
        Protos.DataBundleRepeated repeated = blockingStub.sendDataMono(Protos.DataCountRequest.newBuilder().setCountOfData(dataCount).build());
        return repeated.getDataList();
    }

    // push to queue ->
    // -> take from queue -> never onCompleted()
    public void requestDataStream() {
        StreamObserver<Protos.DataBundle> dataBundleStreamObserver = asyncStub.sendDataDuplex(observer);
        while (true) {
            Protos.DataBundle peek = DataHolder.jsonDataQueue.peek();
            dataBundleStreamObserver.onNext(peek);
        }
    }

}
