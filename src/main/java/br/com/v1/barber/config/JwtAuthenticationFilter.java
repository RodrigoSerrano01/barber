package br.com.v1.barber.config;

import br.com.v1.barber.domain.User;
import br.com.v1.barber.repository.UserRepository;
import br.com.v1.barber.service.impl.JwtServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//
//    private JwtServiceImpl jwtService;
//
//
//    private UserRepository userRepository;

//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//
//        final String authHeader = request.getHeader("Authorization");
//        final String token;
//        final String email;
//
//        // Se não houver token, segue para o próximo filtro
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        token = authHeader.substring(7); // Remove "Bearer "
//        email = jwtService.extractUsername(token);
//
//        // Se o usuário existe e ainda não está autenticado no contexto do Spring
//        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            User user = userRepository.findByEmail(email).orElse(null);
//
//            // Verifica se o token é válido
//            if (user != null && jwtService.isTokenValid(token, user.getName())) {
//                UsernamePasswordAuthenticationToken authToken =
//                        new UsernamePasswordAuthenticationToken(
//                                user, null, null // Coloque roles se houver
//                        );
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}

    @Component
    public class JwtAuthenticationFilter extends OncePerRequestFilter {

        private final JwtServiceImpl jwtService;
        private UserRepository userRepository;

        // Injete o service via construtor
        public JwtAuthenticationFilter(JwtServiceImpl jwtService) {
            this.jwtService = jwtService;
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain filterChain)
                throws ServletException, IOException {
            String token = request.getHeader("Authorization");
            final String email;
            email = jwtService.extractUsername(token);
            User user = userRepository.findByEmail(email).orElse(null);
            if (token != null && jwtService.isTokenValid(token, user.getName())) {
                // lógica de autenticação
            }

            filterChain.doFilter(request, response);
        }
    }
