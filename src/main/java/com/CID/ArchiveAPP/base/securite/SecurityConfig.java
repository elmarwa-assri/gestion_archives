package com.CID.ArchiveAPP.base.securite;

import com.CID.ArchiveAPP.base.securite.handler.CustomSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final CustomSuccessHandler successHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        // Public
                        .requestMatchers(
                                "/",
                                "/login",
                                "/api/v1/auth/**",
                                "/css/**",
                                "/logo.png",
                                "/js/**",
                                "/images/**",
                                "/favicon.ico",
                                "/error"
                        ).permitAll()

                        // API REST protégée (⚠️ noter /api/v1/…)
                        .requestMatchers(
                                "/api/v1/archives/**",
                                "/api/v1/bibliotheques/**"   // ou remplace par /api/v1/biblio/** si c'est ton vrai prefixe
                        ).hasAnyAuthority("AGENT", "ADMIN")

                        // Pages admin
                        .requestMatchers(
                                "/admin-dashboard",
                                "/gestion-users",
                                "/gestion-role",
                                "/consultation-archive",
                                "/consultation-biblio"
                        ).hasAuthority("ADMIN")

                        // Pages agent
                        .requestMatchers(
                                "/dashbord_agent_archivage",
                                "/archives/agent",
                                "/archives/ajouter",
                                "/bibliotheques/ajouter",
                                "/bibliotheques/agent",
                                "/recevoir-formulaire",
                                "/gestion-heatmap"
                        ).hasAuthority("AGENT")

                        // Pages cadre
                        .requestMatchers(
                                "/dashbord_cadre",
                                "/formulaire-cadre",
                                "/api/v1/archives/**",
                                "/consulter-cadre"
                        ).hasAuthority("CADRE")

                        // Pages chef de pôle
                        .requestMatchers(
                                "/dashbord_chef",
                                "/liste_archive_pole"
                        ).hasAuthority("CHEF")

                        // Le reste : authentifié
                        .anyRequest().authenticated()
                )

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")   // important : correspond à ton <form th:action="@{/login}">
                        .successHandler(successHandler)  // ou .defaultSuccessUrl("/admin-dashboard", true)
                        .failureUrl("/login?error")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )

                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
