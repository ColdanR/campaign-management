package de.ravenguard.campmgnt.user.boundary;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
public class MessageListenerTest {

  @InjectKafkaCompanion
  KafkaCompanion companion;

  @Test
  void testProcessor() {
    companion.produceIntegers().usingGenerator(i -> new ProducerRecord<>("prices-in", i));

    ConsumerTask<String, Integer> orders = companion.consumeIntegers().fromTopics("prices-out", 10);
    orders.awaitCompletion();
    assertEquals(10, orders.count());
  }

  @Test
  void testProcessor2() {
    companion.produceIntegers().usingGenerator(i -> new ProducerRecord<>("prices-in2", i));

    ConsumerTask<String, Integer> orders = companion.consumeIntegers().fromTopics("prices-out2", 10);
    orders.awaitCompletion();
    assertEquals(10, orders.count());
  }
}