/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.espaconatresende.model;

/**
 *
 * @author power
 */
public class Funcionarios extends Clientes{
    // funcionarios herda de clientes todos os metodos e atributos
    
    //inserir os atributos que nao temos 
    private String senha;
    private String cargo;
    private String nivelAcesso;
    
    
    //geters e seters

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

  
      
}
