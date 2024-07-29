package ru.otus;

import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.protobuf.SequenceMessage;
import ru.otus.protobuf.SequenceServiceGrpc;

public class GRPCClient {

  private static final Logger logger = LoggerFactory.getLogger(GRPCClient.class);

  private static final String HOST = "localhost";
  private static final int PORT = 8190;

  private static long currentValueFromServer = 0;
  private static boolean shouldTakeNumberFromServer = false;

  public static void main(String[] args) {
    var channel = ManagedChannelBuilder.forAddress(HOST, PORT)
        .usePlaintext()
        .build();

    var message = SequenceMessage.newBuilder()
        .setFirstValue(1)
        .setLastValue(30)
        .build();

    var newStub = SequenceServiceGrpc.newStub(channel);
    newStub.runSequence(message, new StreamObserver<>() {
      @Override
      public void onNext(SequenceMessage msg) {
        currentValueFromServer = msg.getCurrentValue();
        shouldTakeNumberFromServer = true;
        logger.info("Number from server:{}", msg.getCurrentValue());
      }

      @Override
      public void onError(Throwable t) {
        logger.error("Error", t);
      }

      @Override
      public void onCompleted() {
        logger.info("Done");
      }
    });

    long currentValue = 0;
    for (int i = 0; i < 50; i++) {
      if (shouldTakeNumberFromServer) {
        currentValue = currentValue + currentValueFromServer + 1L;
        shouldTakeNumberFromServer = false;
      } else {
        currentValue = currentValue + 1L;
      }

      logger.info("currentValue:{}", currentValue);

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    channel.shutdown();
  }
}
