package de.ravenguard.campmgnt.user.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import io.quarkus.test.hibernate.reactive.panache.TransactionalUniAsserter;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.vertx.RunOnVertxContext;

@QuarkusTest
public class UserProfileTest {
    private static final UUID USER_ID = UUID.fromString("8c7af4c3-5295-4766-a527-453d6fbe33ec");
    private static final String EMAIL = "exists@test.com";

    @Test
    @RunOnVertxContext
    void testGetByEMail_null(TransactionalUniAsserter asserter) {
        asserter.assertFailedWith(() -> UserProfile.getByEMail(null), t -> {
            assertTrue(t instanceof NullPointerException);
            assertEquals("Parameter 'email' may not be null!", t.getMessage());
        });
    }

    @Test
    @RunOnVertxContext
    void testGetByKeyCloakId_notFound(TransactionalUniAsserter asserter) {
        asserter.assertFailedWith(() -> UserProfile.getByKeyCloakId(UUID.randomUUID()), NotFoundException.class);
    }

    @Test
    @RunOnVertxContext
    void testGetByEMail_notFound(TransactionalUniAsserter asserter) {
        asserter.assertFailedWith(() -> UserProfile.getByEMail("test@test.com"), NotFoundException.class);
    }

    @Test
    @RunOnVertxContext
    void testGetByKeyCloakId_null(TransactionalUniAsserter asserter) {
        asserter.assertFailedWith(() -> UserProfile.getByKeyCloakId(null), t -> {
            assertTrue(t instanceof NullPointerException);
            assertEquals("Parameter 'keyCloakId' may not be null!", t.getMessage());
        });
    }

    @Test
    @RunOnVertxContext
    void testGetByEMail_found(TransactionalUniAsserter asserter) {
        asserter.assertThat(() -> UserProfile.getByEMail(EMAIL), item -> {
            assertEquals(EMAIL, item.getEmail());
            assertEquals(USER_ID, item.getKeyCloakId());
            assertEquals(LocalDate.of(2000, 2, 2), item.getBirthday());
            assertEquals(Gender.UNKNOWN, item.getGender());
            assertEquals("test-user", item.getUserName());
            assertEquals(Status.REGISTERED, item.getStatus());
        });
    }

    @Test
    @RunOnVertxContext
    void testGetByKeyCloakId_found(TransactionalUniAsserter asserter) {
        asserter.assertThat(() -> UserProfile.getByKeyCloakId(USER_ID), item -> {
            assertEquals(EMAIL, item.getEmail());
            assertEquals(USER_ID, item.getKeyCloakId());
            assertEquals(LocalDate.of(2000, 2, 2), item.getBirthday());
            assertEquals(Gender.UNKNOWN, item.getGender());
            assertEquals("test-user", item.getUserName());
            assertEquals(Status.REGISTERED, item.getStatus());
        });
    }
}
