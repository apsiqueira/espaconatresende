/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.espaconatresende.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author power
 */
public class ConnectionFactory {

    //metodo 
    public Connection getConnection() {

        Connection conexao = null;

        //tratamanto de erro try catch
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bdvendas", "pablo", "123");
            return conexao;

        } catch (Exception erro) {

            throw new RuntimeException(erro);

        } 

    }

}
