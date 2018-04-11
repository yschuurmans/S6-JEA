package Logic;

import Annotations.TokenRequired;
import com.sun.javaws.exceptions.InvalidArgumentException;
import domain.PermissionGroup;
import domain.Role;
import domain.User;
import io.jsonwebtoken.Jwts;
import service.UserService;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.validation.constraints.Null;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.Key;

@Provider
@TokenRequired
@Priority(Priorities.AUTHENTICATION)
class JWTTokenNeededFilter implements ContainerRequestFilter {

    @Inject
    UserService userService;

    @Context
    ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {


        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {

            // Validate the token
            Key key = KeyGenerator.generateKey();
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);

            //String justTheToken = token.substring("Bearer".length()).trim();

            Role requiredRole = Role.None;
            Method test = resourceInfo.getResourceMethod();
            Class test2 = resourceInfo.getResourceClass();


            try {
                requiredRole = resourceInfo.getResourceClass().getAnnotation(TokenRequired.class).requiredPermissionGroup();
            } catch (NullPointerException ignored) {
            }
            try {
                requiredRole = resourceInfo.getResourceMethod().getAnnotation(TokenRequired.class).requiredPermissionGroup();
            } catch (NullPointerException ignored) {
            }

            String username = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
            User currentUser = userService.findByName(username);
            if (currentUser.getPermissionGroup().ordinal() < requiredRole.ordinal())
                throw new SecurityException("Role does not have permission");

        } catch (SecurityException e) {
            System.out.println("Role does not have permission");
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());

        } catch (Exception e) {
            System.out.println("#### invalid token : " + token);
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}