/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.espaconatresende.dao;

import br.com.espaconatresende.jdbc.ConnectionFactory;
import br.com.espaconatresende.model.Clientes;
import br.com.espaconatresende.model.Fornecedores;
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
public class ProdutosDao {
    
    private Connection con;

    public ProdutosDao() {
        this.con = new ConnectionFactory().getConnection();

    }

    public List<Produtos> listarProdutos() {
        

        try {
            //criação da lista 
            List<Produtos> lista = new ArrayList<Produtos>();

            //criar comando sql, organizar e executar
            String sql = "select p.id,p.nome_produto,p.preco,p.qtd_estoque,p.descricao,f.marca from tb_produtos as p\n" +
"inner join tb_fornecedores as f on (p.for_id=f.id);";
            
            PreparedStatement pst = con.prepareStatement(sql);

            //quando se faz um selec no bunacop temos que armazenar em um objeto do tipo resultset
            ResultSet rst = pst.executeQuery();

            //pegar resultados do resultset percorrendo todos os campos  retornado
            while (rst.next()) {

                //criando objeto cliente para receber os valores , objj recebe o valor com o nome do campo do banco
                Produtos obj = new Produtos();
                Fornecedores  f = new Fornecedores();
                
                
                obj.setId(rst.getInt("p.id"));
                
                obj.setNomeProduto(rst.getString("p.nome_produto"));
                
                obj.setPreco(rst.getDouble("p.preco"));
                
                obj.setQuantidadeProduto(rst.getInt("p.qtd_estoque"));
                
                obj.setDescricaoProduto(rst.getString("p.descricao"));
                
                f.setMarca(rst.getString("f.marca"));
                
                obj.setFornecedor(f);
                
                
              
                

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
    
}
