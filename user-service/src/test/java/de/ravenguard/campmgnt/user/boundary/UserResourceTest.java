package de.ravenguard.campmgnt.user.boundary;

import org.junit.jupiter.api.Test;

import de.ravenguard.campmgnt.user.entities.Gender;
import de.ravenguard.campmgnt.user.entities.Status;
import de.ravenguard.campmgnt.user.entities.UserProfile;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.Response;

import static io.restassured.RestAssured.given;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@QuarkusTest
@TestHTTPEndpoint(UserResource.class)
class UserResourceTest {

    @Test
    void getUserById_not_implemented() {
        given()
                .pathParam("userId", "test")
                .when()
                .get("{userId}")
                .then()
                .statusCode(Response.Status.NOT_IMPLEMENTED.getStatusCode());
    }

    @Test
    void registerUser_not_implemented() {
        given()
                .body(
                        UserProfile.builder()
                                .birthday(LocalDate.now())
                                .email("test@test.com")
                                .gender(Gender.UNKNOWN)
                                .keyCloakId(UUID.randomUUID())
                                .userName("testUSer")
                                .updatedAt(ZonedDateTime.now())
                                .registered(ZonedDateTime.now())
                                .status(Status.REGISTERED)
                                .build())
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(Response.Status.NOT_IMPLEMENTED.getStatusCode());
    }
}
