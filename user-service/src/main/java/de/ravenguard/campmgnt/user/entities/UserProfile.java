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
import java.util.Objects;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "USER_PROFILE")
public class UserProfile extends PanacheEntity {
  @NotNull
  @Column(name = "BIRTHDAY", nullable = false)
  LocalDate birthday;
  @Email
  @NotBlank
  @Column(name = "EMAIL", unique = true, nullable = false)
  String email;
  @NotNull
  @Column(name = "GENDER", nullable = false)
  @Enumerated(EnumType.STRING)
  Gender gender;
  @NotNull
  @Column(name = "KEYCLOAK_ID", unique = true, nullable = false)
  UUID keyCloakId;
  @NotNull
  @Column(name = "LAST_MODIFIED_TS", nullable = false)
  ZonedDateTime updatedAt;
  @NotNull
  @Column(name = "REGISTERED_TS", nullable = false)
  ZonedDateTime registered;
  @NotBlank
  @Column(name = "USERNAME", unique = true, nullable = false)
  String userName;
  @Column(name = "STATUS", nullable = false)
  @Enumerated(EnumType.STRING)
  @NotNull
  Status status;

  public static Uni<UserProfile> getByKeyCloakId(UUID keyCloakId) {
    if (Objects.isNull(keyCloakId)) {
      return Uni.createFrom().failure(new NullPointerException("Parameter 'keyCloakId' may not be null!"));
    }
    return find("keyCloakId", keyCloakId).<UserProfile>singleResult()
            .onFailure(NonUniqueResultException.class)
            .transform(DatabaseException::new)
            .onFailure(NoResultException.class)
            .transform(e -> new NotFoundException());
  }

  public static Uni<UserProfile> getByEMail(String email) {
    if (Objects.isNull(email)) {
      return Uni.createFrom().failure(new NullPointerException("Parameter 'email' may not be null!"));
    }
    return find("email", email).<UserProfile>singleResult()
            .onFailure(NonUniqueResultException.class)
            .transform(DatabaseException::new)
            .onFailure(NoResultException.class)
            .transform(e -> new NotFoundException());
  }
}
