package de.ravenguard.campmgnt.user.boundary;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestHTTPEndpoint(UserResource.class)
public class UserResourceTest {
    
}
