package com.leonardo.apicontroleestacionamento.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true) //habilita a segurança nos métodos com suporte para anotações pré e pós-invocação. prePostEnabled = true permite o uso de anotações @PreAuthorize/@PostAuthorize, que podem ser usadas para expressar regras de segurança antes ou depois da execução de métodos, respectivamente.
public class WebSecurityConfig{ //Define a classe que contém a configuração de segurança para a aplicação.

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{//Método que é essencial para configurar a segurança baseada em HTTP. Define um bean para configurar a segurança baseada em HTTP usando a classe HttpSecurity.
        http
                .csrf(AbstractHttpConfigurer::disable)//Desabilita a proteção CSRF. CSRF é uma técnica de ataque que força um usuário final a executar ações indesejadas em uma aplicação web em que está autenticado. 
                .httpBasic(Customizer.withDefaults())// Habilita a autenticação HTTP Basic, que é um esquema simples que codifica o nome de usuário e a senha do usuário e os envia no cabeçalho da solicitação HTTP.
                .authorizeHttpRequests((authorize) -> {//Configura a autorização de solicitações HTTP.
                    authorize.anyRequest().authenticated();//Especifica que qualquer solicitação deve ser autenticada, ou seja, o usuário deve estar logado para acessar qualquer parte da aplicação.
                });
        return http.build();//Constrói e retorna o SecurityFilterChain configurado.
    }

    @Bean
    public PasswordEncoder passwordEncoder(){//Define outro bean para a codificação de senha.
        return new BCryptPasswordEncoder();//Retorna uma instância do BCryptPasswordEncoder, que é uma implementação de PasswordEncoder que usa o algoritmo BCrypt para hash de senhas. O BCrypt é forte contra ataques de força bruta, pois o custo de hash pode ser ajustado para torná-lo mais lento.
    }
}