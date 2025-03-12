package one.terenin.dwgrpcworkeer2.observer;

import io.grpc.stub.StreamObserver;
import one.terenin.Protos;

public class ConsumerRequestObserver implements StreamObserver<Protos.DataBundle> {

    private final StreamObserver<Protos.DataBundle> responseObserver;

    public ConsumerRequestObserver(StreamObserver<Protos.DataBundle> responseObserver) {
        this.responseObserver = responseObserver;
    }

    @Override
    public void onNext(Protos.DataBundle dataBundle) {
        //some work with accepted data bundle
        //maybe put it to queue again?
        responseObserver.onNext(dataBundle);
        // logging?
    }

    @Override
    public void onError(Throwable throwable) {
        responseObserver.onError(throwable);
    }

    @Override
    public void onCompleted() {
        //responseObserver.onCompleted();
    }
}
