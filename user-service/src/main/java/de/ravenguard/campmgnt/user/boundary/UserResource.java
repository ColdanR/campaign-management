package de.ravenguard.campmgnt.user.boundary;

import de.ravenguard.campmgnt.user.control.RegisterService;
import de.ravenguard.campmgnt.user.entities.UserProfile;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.DenyAll;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@DenyAll
public class UserResource {

    @Inject
    RegisterService registerService;

    @Path("users/{userId}")
    @NotNull
    @GET
    public Uni<@Valid UserProfile> getUserById(@PathParam("userId") @NotBlank String userId) {
        return null;
    }

    @Path("users")
    @NotNull
    @POST
    public Uni<@Valid UserProfile> registerUser(UserProfile userProfile) {
        return Uni.createFrom().item(userProfile);
    }
}
