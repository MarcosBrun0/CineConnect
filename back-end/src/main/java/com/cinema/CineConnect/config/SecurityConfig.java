//package com.cinema.CineConnect.config;
//
//import com.cinema.CineConnect.repository.AuthRepository;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final AuthRepository authRepository;
//
//    public SecurityConfig(AuthRepository authRepository) {
//        this.authRepository = authRepository;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return email -> {
//            return authRepository.findClientByEmail(email).map(client -> User.builder()
//                    .username(client.email())
//                    .password(client.password())
//                    .roles("CLIENT")
//                     .build()).or(() -> authRepository.findEmployeeByEmail(email).map(employee -> User.builder().username(employee.email())
//                    .password(employee.password())
//                    .roles("EMPLOYEE").build())).orElseThrow(() -> new UsernameNotFoundException("Email não encontrado" + email));};
//    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                // Habilita a autenticação HTTP Basic
//                .httpBasic(Customizer.withDefaults())
//                // Desabilita CSRF (é padrão em APIs stateless)
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        // Permite acesso a todos (não autenticado) para a rota de criação de usuários
//                        .requestMatchers("/api/register").permitAll()
//                        // Exemplo: Rotas de Funcionários exigem papel EMPLOYEE
//                        .requestMatchers("/api/employee/**").hasRole("EMPLOYEE")
//                        // Rotas de Clientes exigem papel CLIENT
//                        .requestMatchers("/api/client/**").hasRole("CLIENT")
//                        // Qualquer outra requisição deve ser autenticada
//                        .anyRequest().authenticated()
//                );
//        return http.build();
//    }
//}
