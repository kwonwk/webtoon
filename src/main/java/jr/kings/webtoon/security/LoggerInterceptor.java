// package jr.kings.webtoon.security;

// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
// import org.springframework.ui.Model;
// import org.springframework.web.servlet.ModelAndView;
// import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// import jr.kings.webtoon.socialLogin.NaverLoginBO;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @Component
// public class LoggerInterceptor extends HandlerInterceptorAdapter {
    
//     /* NaverLoginBO */
//     @Autowired
//     private NaverLoginBO naverLoginBO;

//     @Autowired
//     private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
//         this.naverLoginBO = naverLoginBO;
//     }
    
    
//     // controller로 보내기 전 이벤트 작동(false - controller로 요청을 안함)
//     @Override
//     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//             throws Exception {
//         log.debug("============================== START ==============================");
//         // log.info(" Class \t: " + handler.getClass());
//         // log.info(" Request URI \t: " + request.getRequestURI());
//         // log.info(" Servlet URI \t: " + request.getServletPath());

//         // Enumeration<String> paramNames = request.getParameterNames();

//         // while (paramNames.hasMoreElements()) {
//         // String key = (String) paramNames.nextElement();
//         // String value = request.getParameter(key);
//         // log.info("# RequestParameter: " + key + "=" + value + "");
//         // }
//         // log.info("====================================================================
//         // ");
//         /* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
//         HttpSession session = request.getSession();
//         String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
//         // https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
//         // redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
//         // System.out.println("네이버:" + naverAuthUrl);
//         // 네이버
//         request.setAttribute("url", naverAuthUrl);
//         return super.preHandle(request, response, handler);
//     }

//     // controller 처리 이후 이벤트 작동
//     @Override
//     public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//             ModelAndView modelAndView) throws Exception {
//         // log.info("================================ END
//         // ================================");
//     }

//     // view 처리 이후 이벤트 작동
//     @Override
//     public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//             throws Exception {
//         // log.info("================================ END
//         // ================================");
//     }
// }