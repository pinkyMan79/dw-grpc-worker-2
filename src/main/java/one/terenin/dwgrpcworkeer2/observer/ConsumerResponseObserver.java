package one.terenin.dwgrpcworkeer2.observer;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import one.terenin.Protos;
import one.terenin.dwgrpcworkeer2.common.DataHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerResponseObserver implements StreamObserver<Protos.DataBundle> {

    @Override
    public void onNext(Protos.DataBundle dataBundle) {
        //some work with accepted data bundle
        //maybe put it yo queue again?
        DataHolder.jsonDataQueue.add(dataBundle);
    }

    @Override
    public void onError(Throwable throwable) {
        log.warn(throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        log.info("Consumer completed");
    }
}
