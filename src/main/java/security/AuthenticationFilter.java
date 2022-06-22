package security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import model.User;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;

public class AuthenticationFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestCtx) {
        boolean isSecure = requestCtx.getSecurityContext().isSecure();
        String scheme = requestCtx.getUriInfo().getRequestUri().getScheme();
        // Users are treated as guests, unless a valid JWT is provided
        MySecurityContext msc = new MySecurityContext(null, scheme);
        String authHeader = requestCtx.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring("Bearer".length()).trim();
            try {
                // Validate the token
                JwtParser parser =
                        Jwts.parser().setSigningKey(AuthenticationResource.key);
                Claims claims = parser.parseClaimsJws(token).getBody();
                String user = claims.getSubject();
                msc = new MySecurityContext(User.getUserByName(user), scheme);
            } catch (JwtException | IllegalArgumentException e) {
                System.out.println("Invalid JWT, processing as guest!");
            }
        }
        requestCtx.setSecurityContext(msc);
    }

}
