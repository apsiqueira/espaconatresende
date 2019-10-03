/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.espaconatresende.dao;

import br.com.espaconatresende.jdbc.ConnectionFactory;
import br.com.espaconatresende.model.Procedimentos;
import br.com.espaconatresende.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author power
 */
public class ProcedimentoDao {

    private Connection con;
    //construtor ja chamndo a conexão com o banco

    public ProcedimentoDao() {

        this.con = new ConnectionFactory().getConnection();
    }

    ;

    public List<Procedimentos> consultarProdedimentoPeloNomeCompleto(String nomeProc) {
        try {
            //criação da lista 
            List<Procedimentos> lista = new ArrayList<>();

            //criar comando sql, organizar e executar
            String sql = "select * from tb_procedimentos where nomeProcedimento like ?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, nomeProc);

            //quando se faz um select no bunco temos que armazenar em um objeto do tipo resultset
            ResultSet rst = pst.executeQuery();

            //pegar resultados do resultset percorrendo todos os campos  retornado
            while (rst.next()) {

                //criando objeto cliente para receber os valores , objj recebe o valor com o nome do campo do banco
                Procedimentos obj = new Procedimentos();
                obj.setId(rst.getInt("id"));
                obj.setNomeProcedimento(rst.getString("nomeProc"));
                obj.setDescricaoProcedimento(rst.getString("descricao"));
                obj.setPreco(rst.getDouble("preco"));

                //adicionar o objeto na lista 
                lista.add(obj);

            }

            //retorna a lista 
            return lista;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
        return null;

    }

    public List<Procedimentos> listarProcedimentos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
