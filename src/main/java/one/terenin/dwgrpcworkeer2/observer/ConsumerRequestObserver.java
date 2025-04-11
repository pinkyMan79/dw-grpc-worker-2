package one.terenin.dwgrpcworkeer2.observer;

import io.grpc.stub.StreamObserver;
import lombok.Getter;
import lombok.Setter;
import one.terenin.Protos;

import java.util.function.Function;

public class ConsumerRequestObserver implements StreamObserver<Protos.DataBundle> {

    private final StreamObserver<Protos.DataBundle> responseObserver;
    @Setter
    @Getter
    private Function<Protos.DataBundle, Protos.DataBundle> _proc;

    public ConsumerRequestObserver(StreamObserver<Protos.DataBundle> responseObserver) {
        this.responseObserver = responseObserver;
    }

    @Override
    public void onNext(Protos.DataBundle dataBundle) {
        //some work with accepted data bundle
        //maybe put it to queue again?
        responseObserver.onNext(_proc.apply(dataBundle));
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
