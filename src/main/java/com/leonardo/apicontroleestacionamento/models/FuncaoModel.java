package com.leonardo.apicontroleestacionamento.models;

import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;

import com.leonardo.apicontroleestacionamento.enums.NomeFuncao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Classe que representa uma autoridade concedida a um usuario.

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_funcao")
public class FuncaoModel implements GrantedAuthority {//Permite que funcaoModel seja usado pelo Spring Security para representar uma autoridade concedida a um usuário.

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private NomeFuncao nomeFuncao;

    @Override
    public String getAuthority() {//Método exigido pela interface GrantedAuthority, retorna o nome da role como uma String, que é usado pelo Spring Security para determinar as permissões do usuário.
        return this.nomeFuncao.toString();
    }
}