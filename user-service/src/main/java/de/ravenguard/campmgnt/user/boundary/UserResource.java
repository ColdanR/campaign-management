package de.ravenguard.campmgnt.user.boundary;

import de.ravenguard.campmgnt.user.control.RegisterService;
import de.ravenguard.campmgnt.user.entities.UserProfile;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.DenyAll;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@DenyAll
public class UserResource {

    @Inject
    RegisterService registerService;

    @Path("users/{userId}")
    @NotNull
    @GET
    public Uni<UserProfile> getUserById(@PathParam("userId") @NotBlank String userId) {
        return Uni.createFrom().failure(new WebApplicationException(Status.NOT_IMPLEMENTED));
    }

    @Path("/")
    @NotNull
    @POST
    public Uni<UserProfile> registerUser(@Valid UserProfile userProfile) {
        return Uni.createFrom().failure(new WebApplicationException(Status.NOT_IMPLEMENTED));
    }
}
