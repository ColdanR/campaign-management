package de.ravenguard.campmgnt.user.entities;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.smallrye.mutiny.Uni;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class UserProfile extends PanacheEntity {
  @NotNull
  @Column(nullable = false)
  LocalDate birthday;
  @Email
  @NotBlank
  @Column(unique = true, nullable = false)
  String email;
  @NotNull
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  Gender gender;
  @NotNull
  @Column(unique = true, nullable = false)
  UUID keyCloakId;
  @NotNull
  @Column(nullable = false)
  ZonedDateTime updatedAt;
  @NotNull
  @Column(nullable = false)
  ZonedDateTime registered;
  @NotBlank
  @Column(unique = true, nullable = false)
  String userName;
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  @NotNull
  Status status;

  public static Uni<UserProfile> getByKeyCloakId(UUID keyCloakId) {
    if (keyCloakId == null) {
      throw new NullPointerException("keyCloakId may not be null");
    }
    return find("keyCloakId", keyCloakId).<UserProfile>singleResult()
            .onFailure(NonUniqueResultException.class)
            .transform(DatabaseException::new)
            .onFailure(NoResultException.class)
            .transform(e -> new NotFoundException());
  }

  public static Uni<UserProfile> getByEMail(String email) {
    return find("email", email).<UserProfile>singleResult()
            .onFailure(NonUniqueResultException.class)
            .transform(DatabaseException::new)
            .onFailure(NoResultException.class)
            .transform(e -> new NotFoundException());
  }
}
