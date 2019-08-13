/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.espaconatresende.dao;

import br.com.espaconatresende.jdbc.ConnectionFactory;
import br.com.espaconatresende.model.Fornecedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author power
 */
public class FornecedorDao {
    
    
    private Connection con;

    public FornecedorDao( ) {
        this.con = new ConnectionFactory().getConnection();
    }
    
    public void salvarFornecedores(Fornecedores obj) {
        try {
            //passo 1:criar comando sql
            String sql = "insert into tb_fornecedores(marca,cpj,vendedor,email,celular,telefone,cep,endereco,numero,bairro,cidade,complemento,estado,rg,cpf"
                    + ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            //passo 2:conectar ao banco e organizar o comando sql
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, obj.getMarca());
            pst.setString(2, obj.getCnpj());
            pst.setString(3, obj.getVendedor());
            pst.setString(4, obj.getEmail()); 
            pst.setString(5, obj.getCelular());
            pst.setString(6, obj.getTelefone());
            pst.setString(7, obj.getCep());
            pst.setString(8, obj.getEndereco());
            pst.setInt(9, obj.getNumero());
            pst.setString(10, obj.getBairro());
            pst.setString(11, obj.getCidade());
            pst.setString(12, obj.getComplemento());
            pst.setString(13, obj.getEstado());
            pst.setString(14, obj.getRg());
            pst.setString(15, obj.getCpf());

            //comando 3executar comando sql
            pst.execute();
            pst.close();
            con.close();

            JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!");

        } catch (SQLException erroSql) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar Fornecedor!" + erroSql);

        }
    }
}
