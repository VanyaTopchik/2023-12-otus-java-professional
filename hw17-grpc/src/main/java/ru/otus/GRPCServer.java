package ru.otus;

import io.grpc.ServerBuilder;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.service.SequenceServiceImpl;

public class GRPCServer {

  private static final Logger logger = LoggerFactory.getLogger(GRPCServer.class);

  public static final int PORT = 8190;

  public static void main(String[] args) throws IOException, InterruptedException {
    var sequenceService = new SequenceServiceImpl();

    var server = ServerBuilder.forPort(PORT).addService(sequenceService).build();
    server.start();

    logger.info("Waiting client connections");
    server.awaitTermination();
  }
}
