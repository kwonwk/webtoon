// package jr.kings.webtoon.security;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.Ordered;
// import org.springframework.core.annotation.Order;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;

// import javax.servlet.*;
// import java.io.IOException;

// @Order(-99)
// @Configuration
// public class RootFilter implements Filter {
//     @Override
//     public void init(FilterConfig filterConfig) throws ServletException {

//     }

//     @Override
//     public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//         System.out.print("Auth =>>> ");
//         System.out.println(authentication);
//         filterChain.doFilter(servletRequest, servletResponse);
//     }

//     @Override
//     public void destroy() {

//     }
// }