package one.terenin.dwgrpcworkeer2.controller;

import io.grpc.stub.StreamObserver;
import one.terenin.ConsumerGrpcApiGrpc;
import one.terenin.Protos;

import one.terenin.dwgrpcworkeer2.common.DataHolder;
import one.terenin.dwgrpcworkeer2.observer.ConsumerRequestObserver;
import org.springframework.stereotype.Service;

@Service
public class ConsumerController extends ConsumerGrpcApiGrpc.ConsumerGrpcApiImplBase {

    @Override
    public void sendDataMono(Protos.DataCountRequest request, StreamObserver<Protos.DataBundleRepeated> responseObserver) {
        boolean possibleToSend = !DataHolder.jsonDataQueue.isEmpty();
        if (possibleToSend) {
            int countOfData = request.getCountOfData();
            Protos.DataBundleRepeated.Builder repeated = Protos.DataBundleRepeated.newBuilder(); // maximize size
            while (countOfData > 0) {
                countOfData--;
                Protos.DataBundle curr = DataHolder.jsonDataQueue.poll();
                if (curr != null) {
                    repeated.addData(curr);
                } else {
                    break;
                }
            }
            responseObserver.onNext(repeated.build());
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new RuntimeException("Data was not generated or accepted yet"));
        }
    }

    @Override
    public void sendDataMonoSingle(Protos.Empty request, StreamObserver<Protos.DataBundle> responseObserver) {
        boolean possibleToSend = !DataHolder.jsonDataQueue.isEmpty();
        if (possibleToSend) {
            responseObserver.onNext(DataHolder.jsonDataQueue.poll());
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new RuntimeException("Data was not generated or accepted yet"));
        }
    }

    @Override
    public StreamObserver<Protos.DataBundle> sendDataDuplex(StreamObserver<Protos.DataBundle> responseObserver) {
        return new ConsumerRequestObserver(responseObserver);
    }
}
