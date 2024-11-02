package de.ravenguard.campmgnt.user.boundary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.kafka.clients.consumer.OffsetResetStrategy;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.kafka.InjectKafkaCompanion;
import io.quarkus.test.kafka.KafkaCompanionResource;
import io.smallrye.reactive.messaging.kafka.companion.ConsumerTask;
import io.smallrye.reactive.messaging.kafka.companion.KafkaCompanion;

@QuarkusTest
@QuarkusTestResource(KafkaCompanionResource.class)
class MessageListenerTest {

  @InjectKafkaCompanion
  KafkaCompanion companion;

  @Test
  void testProcessor() {
    companion.produceIntegers().usingGenerator(i -> new ProducerRecord<>("prices-in", "key" + i, i), 10);

    ConsumerTask<String, Integer> orders = companion.consumeIntegers()
        .withAutoCommit()
        .withOffsetReset(OffsetResetStrategy.EARLIEST)
        .fromTopics("prices-out", 10);
    orders.awaitRecords(10);
    
    var list = orders.getRecords();
    for (var i = 0; i < list.size(); i++) {
      var orderRecord = list.get(i);
      assertEquals(i * 2, orderRecord.value());
      assertEquals("key" + i, orderRecord.key());
    }
  }

  @Test
  void testProcessor2() {
    companion.produceIntegers().usingGenerator(i -> new ProducerRecord<>("prices-in2", "key" + i, i), 10);

    ConsumerTask<String, Integer> orders = companion.consumeIntegers()
        .withAutoCommit()
        .withOffsetReset(OffsetResetStrategy.EARLIEST)
        .fromTopics("prices-out2", 10);
    orders.awaitRecords(10);

    var list = orders.getRecords();
    list.sort((i, j) -> Integer.compare(i.value(), j.value()));
    for (var i = 0; i < list.size(); i++) {
      var orderRecord = list.get(i);
      assertEquals(i + 1, orderRecord.value());
      assertEquals("key" + i, orderRecord.key());
    }
  }
}