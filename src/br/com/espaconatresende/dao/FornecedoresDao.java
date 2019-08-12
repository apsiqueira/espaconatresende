/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.espaconatresende.dao;

import br.com.espaconatresende.jdbc.ConnectionFactory;
import java.sql.Connection;

/**
 *
 * @author power
 */
public class FornecedoresDao {
    
    
    private Connection con;

    public FornecedoresDao( ) {
        this.con = new ConnectionFactory().getConnection();
    }
    
}
