package de.ravenguard.campmgnt.user.boundary;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

@ApplicationScoped
public class MessageListener {
  @Incoming("prices-in")
  @Outgoing("prices-out")
  public Uni<Integer> emitInTransaction(Integer payload) {
    return Uni.createFrom()
        .item(() -> payload * 2);
  }

  @Incoming("prices-in2")
  @Outgoing("prices-out2")
  public Uni<Integer> emitInTransaction2(Integer payload) {
    return Uni.createFrom()
        .item(() -> payload + 1);
  }
}