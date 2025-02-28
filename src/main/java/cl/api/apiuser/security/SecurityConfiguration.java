package cl.api.apiuser.security;

import cl.api.apiuser.util.ConstantesUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Value("${security.user.username}")
    private String userUsername;

    @Value("${security.admin.username}")
    private String adminUsername;

    @Value("${security.user.password}")
    private String userPassword;

    @Value("${security.admin.password}")
    private String adminPassword;

    //**************************** SPRING SECURITY CONFIGURATION ***********************************

    /**
     * Configures Spring Security's HTTP request authorization.
     *
     * @param httpSecurity HttpSecurity object for customization
     * @return SecurityFilterChain Spring Security filterChain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        //LOGIN
                        .requestMatchers("/api-user/api/v1/user/login").permitAll()
                        //SWAGGER-UI
                        .requestMatchers("/api-user/swagger-ui.html").hasRole(ConstantesUtil.ROLE_ADMIN)
                        //USER CONTROLLER
                        .requestMatchers("/api-user/**").hasRole(ConstantesUtil.ROLE_ADMIN)
                        .requestMatchers("/api-user/api/v1/user/hearthbeat").hasRole(ConstantesUtil.ROLE_ADMIN)
                        .requestMatchers("/api-user/api/v1/user/registro").hasRole(ConstantesUtil.ROLE_ADMIN)
                        //H2 DATABASE
                        .requestMatchers("/api-user/h2-console").hasRole(ConstantesUtil.ROLE_ADMIN)
                        .requestMatchers("/api-user/h2-console/**").hasRole(ConstantesUtil.ROLE_ADMIN)
                        .anyRequest()
                        .authenticated()
                )
                .headers(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    /**
     * Define un bean PasswordEncoder para codificar contraseñas.
     *
     * @return PasswordEncoder basado en BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates an in-memory user details service with
     * predefined users.
     */
    @Bean
    public UserDetailsService users(PasswordEncoder encoder) {
        UserDetails user = User.builder()
                .username(userUsername)
                .password(encoder.encode(userPassword))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username(adminUsername)
                .password(encoder.encode(adminPassword))
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

}
