package ru.otus.service;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.protobuf.SequenceMessage;
import ru.otus.protobuf.SequenceServiceGrpc;

public class SequenceServiceImpl extends SequenceServiceGrpc.SequenceServiceImplBase {

  private static final Logger logger = LoggerFactory.getLogger(SequenceServiceImpl.class);

  @Override
  public void runSequence(SequenceMessage request, StreamObserver<SequenceMessage> responseObserver) {
    for (long current = request.getFirstValue(); current < request.getLastValue(); current++) {
      logger.info("current value:{}", current);

      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      SequenceMessage message = SequenceMessage.newBuilder()
          .setCurrentValue(current)
          .build();

      responseObserver.onNext(message);
    }

    responseObserver.onCompleted();
  }

}
