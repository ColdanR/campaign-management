package de.ravenguard.campmgnt.user.boundary;

import de.ravenguard.campmgnt.user.entities.UserProfile;
import io.smallrye.mutiny.Uni;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    @Path("/{userId}")
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
