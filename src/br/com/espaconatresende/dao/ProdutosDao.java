/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.espaconatresende.dao;

import br.com.espaconatresende.jdbc.ConnectionFactory;
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
            String sql = "select p.id,p.nome_produto,p.preco,p.qtd_estoque,p.descricao,f.marca from tb_produtos as p\n"
                    + "inner join tb_fornecedores as f on (p.for_id=f.id);";

            PreparedStatement pst = con.prepareStatement(sql);

            //quando se faz um selec no bunacop temos que armazenar em um objeto do tipo resultset
            ResultSet rst = pst.executeQuery();

            //pegar resultados do resultset percorrendo todos os campos  retornado
            while (rst.next()) {

                //criando objeto cliente para receber os valores , objj recebe o valor com o nome do campo do banco
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

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

    public void salvarProduto(Produtos obj) {
        try {

            String sql = "insert into tb_produtos(nome_produto,for_id,preco,qtd_estoque,descricao) values(?,?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, obj.getNomeProduto());
            pst.setInt(2, obj.getFornecedor().getId());
            pst.setDouble(3, obj.getPreco());
            pst.setInt(4, obj.getQuantidadeProduto());
            pst.setString(5, obj.getDescricaoProduto());

            pst.execute();
            pst.close();

            JOptionPane.showMessageDialog(null, "Produto Cdastrado com sucesso");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao cadastrar produto no banco de dados");
            System.out.println(e.toString());

        }

    }

    public void alterarProduto(Produtos obj) {
        try {
            //passo 1:criar comando sql
            String sql = "update tb_produtos set nome_produto=?,for_id=?,preco=?,qtd_estoque=?,descricao=? where id=?";

            //passo 2:conectar ao banco e organizar o comando sql
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, obj.getNomeProduto());
            pst.setInt(2, obj.getFornecedor().getId());
            pst.setDouble(3, obj.getPreco());
            pst.setInt(4, obj.getQuantidadeProduto());
            pst.setString(5, obj.getDescricaoProduto());
            pst.setInt(6, obj.getId());

            //comando 3executar comando sql
            pst.execute();
            pst.close();

            JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");

        } catch (SQLException erroSql) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar Produto no Banco!" + erroSql);

        }

    }

    public void excluirProduto(Produtos obj) {
        try {
            //passo 1:criar comando sql
            String sql = "delete from tb_produtos where id=?";

            //passo 2:conectar ao banco e organizar o comando sql
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, obj.getId());

            //comando 3executar comando sql
            pst.execute();
            pst.close();
            con.close();

            JOptionPane.showMessageDialog(null, "Produto excluido com sucesso!");

        } catch (SQLException erroSql) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir Produto do banco!" + erroSql);

        }

    }

    public Produtos pesquisarProdutoPorNome(String nome) {
        try {
            String sql = "select p.id,p.nome_produto,f.marca,p.preco,p.qtd_estoque,p.descricao from tb_produtos as p "
                    + "inner join tb_fornecedores as f on (p.for_id=f.id) where p.nome_produto=?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, nome);

            ResultSet rst = pst.executeQuery();
            Produtos obj = new Produtos();
            Fornecedores fornecedor = new Fornecedores();

            if (rst.next()) {
                obj.setId(rst.getInt("p.id"));
                obj.setNomeProduto(rst.getString("p.nome_produto"));
                obj.setPreco(rst.getDouble("p.preco"));
                fornecedor.setNome(rst.getString("f.marca"));
                obj.setFornecedor(fornecedor);
                obj.setQuantidadeProduto(rst.getInt("p.qtd_estoque"));
                obj.setDescricaoProduto(rst.getString("p.descricao"));

                return obj;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na pesquisa do produto");
            System.out.println(e.toString());

        }
        return null;

    }

    public List<Produtos> consultarProdutoPeloFornecedor(String nome) {

        try {
            //criação da lista 
            List<Produtos> listaProdutos = new ArrayList<>();

            //criar comando sql, organizar e executar
            String sql = "select p.id,p.nome_produto,f.marca,p.preco,p.qtd_estoque,p.descricao from tb_produtos as p "
                    + "inner join tb_fornecedores as f on (p.for_id=f.id) where p.nome_produto like?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, nome);

            ResultSet rst = pst.executeQuery();
            Produtos obj = new Produtos();
            Fornecedores fornecedor = new Fornecedores();

            if (rst.next()) {
                obj.setId(rst.getInt("p.id"));
                obj.setNomeProduto(rst.getString("p.nome_produto"));
                obj.setFornecedor(fornecedor);
                fornecedor.setNome(rst.getString("f.marca"));
                obj.setPreco(rst.getDouble("p.preco"));
                obj.setQuantidadeProduto(rst.getInt("p.qtd_estoque"));
                obj.setDescricaoProduto(rst.getString("p.descricao"));

                listaProdutos.add(obj);
                return listaProdutos;
            }

            return null;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na pesquisa do produto");
            System.out.println(e.toString());

        }
        return null;
    }
    public Produtos pesquisarProdutoPorId(int idBusca) {
        try {
            String sql = "select from tb_produtos id = ?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idBusca);

            ResultSet rst = pst.executeQuery();
            Produtos obj = new Produtos();
            Fornecedores fornecedor = new Fornecedores();

            if (rst.next()) {
                obj.setId(rst.getInt("p.id"));
                obj.setNomeProduto(rst.getString("p.nome_produto"));
                obj.setPreco(rst.getDouble("p.preco"));
                fornecedor.setNome(rst.getString("f.marca"));
                obj.setFornecedor(fornecedor);
                obj.setQuantidadeProduto(rst.getInt("p.qtd_estoque"));
                obj.setDescricaoProduto(rst.getString("p.descricao"));

                return obj;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na pesquisa do produto");
            System.out.println(e.toString());

        }
        return null;

    }
}
