package jr.kings.webtoon.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {


    

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessException)
            throws IOException, ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("customAccessDeniedHanlder....................");
        if(auth != null){
            log.warn("User: " + auth.getName() 
              + " attempted to access the protected URL: "
              + request.getRequestURI());
        }
        log.error("Access Denied Handler");

        log.error("Redirect....");

        response.sendRedirect("/accessError");

    }

}
