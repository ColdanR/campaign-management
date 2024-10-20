package de.ravenguard.campmgnt.user.boundary;

import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.kafka.transactions.KafkaTransactions;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;

@ApplicationScoped
public class MessageListener {
  @Channel("prices-out")
  @OnOverflow(value = OnOverflow.Strategy.BUFFER, bufferSize = 500)
  KafkaTransactions<Integer> txProducer;

  @Channel("prices-out2")
  @OnOverflow(value = OnOverflow.Strategy.BUFFER, bufferSize = 500)
  KafkaTransactions<Integer> txProducer2;

  @Incoming("prices-in")
  @Retry(delay = 1000)
  public Uni<Void> emitInTransaction(Message<Integer> message) {
    return txProducer.withTransactionAndAck(message, emitter ->
        Uni.createFrom()
            .item(message)
            .map(Message::getPayload)
            .chain(payload -> {
              emitter.send(payload + 1);
              return Uni.createFrom().voidItem();
            })
    );
  }

  @Incoming("prices-in2")
  @Retry(delay = 1000)
  public Uni<Void> emitInTransaction2(Message<Integer> message) {
    return txProducer2.withTransactionAndAck(message, emitter -> {
          emitter.send(message.getPayload() + 1);
          return Uni.createFrom().voidItem();
        }
    );
  }
}