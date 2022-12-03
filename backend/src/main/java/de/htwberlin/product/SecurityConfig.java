package de.htwberlin.product;

//
// @EnableWebSecurity
// public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.cors().configurationSource(corsFilter()).and().build();
//    }
//
//    CorsConfigurationSource corsFilter() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
//        corsConfiguration.setAllowedMethods(
//                Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource =
//                new UrlBasedCorsConfigurationSource();
//        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//        return urlBasedCorsConfigurationSource;
//    }
// }
