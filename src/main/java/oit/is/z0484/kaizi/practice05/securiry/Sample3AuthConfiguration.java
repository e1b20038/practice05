package oit.is.z0484.kaizi.practice05.securiry;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class Sample3AuthConfiguration {

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserBuilder users = User.builder();

    UserDetails customer1 = users
        .username("customer1")
        .password("$2y$10$E4/smORCtsBTQ2FiBcoUs.3hkEvlD7i0zKFMzx.9xMevgHGMHAlHu")
        .roles("CUSTOMER")
        .build();
    UserDetails customer2 = users
        .username("customer2")
        .password("$2y$10$T5EASzIeaT.AgYbTJux7UenZgC7sh8zGokrSdbI4GQHhu./84jXhu")
        .roles("CUSTOMER")
        .build();
    UserDetails seller1 = users
        .username("seller1")
        .password("$2y$10$qR1mI1KgQI2ume9HI.JsIu.JU7t3BI6HKi9./I15GHTCrfsia0Vr.")
        .roles("SELLER")
        .build();

    return new InMemoryUserDetailsManager(customer1, customer2, seller1);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.formLogin();
    http.authorizeHttpRequests()
        .mvcMatchers("/sample5/**").authenticated()
        .mvcMatchers("/sample58/**").authenticated()
        ;
    http.logout().logoutSuccessUrl("/");

    http.csrf().disable();
    http.headers().frameOptions().disable();

    return http.build();
  }
}
