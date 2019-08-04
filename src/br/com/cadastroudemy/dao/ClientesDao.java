/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cadastroudemy.dao;

import br.com.cadastroudemy.jdbc.ConnectionFactory;
import br.com.cadastroudemy.model.Clientes;
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
public class ClientesDao {
    //metodos que fazem interação com a tabela de clientes no banco

    private Connection con;

    public ClientesDao() {
        this.con = new ConnectionFactory().getConnection();

    }

    //metodo da cadastro de cliente
    public void salvarCliente(Clientes obj) {
        try {
            //passo 1:criar comando sql
            String sql = "insert into tb_clientes(nome,rg,cpf,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado,nascimento"
                    + ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            //passo 2:conectar ao banco e organizar o comando sql
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getRg());
            pst.setString(3, obj.getCpf());
            pst.setString(4, obj.getEmail());
            pst.setString(5, obj.getTelefone());
            pst.setString(6, obj.getCelular());
            pst.setString(7, obj.getCep());
            pst.setString(8, obj.getEndereco());
            pst.setInt(9, obj.getNumero());
            pst.setString(10, obj.getComplemento());
            pst.setString(11, obj.getBairro());
            pst.setString(12, obj.getCidade());
            pst.setString(13, obj.getEstado());
            pst.setString(14, obj.getNascimento());

            //comando 3executar comando sql
            pst.execute();
            pst.close();
            con.close();

            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");

        } catch (SQLException erroSql) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar Cliente!" + erroSql);

        }
    }

    public void alterarCliente(Clientes obj) {
        try {
            //passo 1:criar comando sql
            String sql = "update tb_clientes set nome=?,rg=?,cpf=?,email=?,telefone=?,celular=?,cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=?,nascimento=? where id=?";

            //passo 2:conectar ao banco e organizar o comando sql
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getRg());
            pst.setString(3, obj.getCpf());
            pst.setString(4, obj.getEmail());
            pst.setString(5, obj.getTelefone());
            pst.setString(6, obj.getCelular());
            pst.setString(7, obj.getCep());
            pst.setString(8, obj.getEndereco());
            pst.setInt(9, obj.getNumero());
            pst.setString(10, obj.getComplemento());
            pst.setString(11, obj.getBairro());
            pst.setString(12, obj.getCidade());
            pst.setString(13, obj.getEstado());
            pst.setString(14, obj.getNascimento());
            pst.setInt(15, obj.getId());

            //comando 3executar comando sql
            pst.execute();
            pst.close();
            con.close();

            JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");

        } catch (SQLException erroSql) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar Cliente!" + erroSql);

        }

    }

    public void excluirCliente(Clientes obj) {
        //criar o sql

        try {
            //passo 1:criar comando sql
            String sql = "delete from tb_clientes where id=?";

            //passo 2:conectar ao banco e organizar o comando sql
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, obj.getId());

            //comando 3executar comando sql
            pst.execute();
            pst.close();
            con.close();

            JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso!");

        } catch (SQLException erroSql) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir Cliente!" + erroSql);

        }

    }

    //metodo para bsucar clientes do banco
    public List<Clientes> listarClientes() {

        try {
            //criação da lista 
            List<Clientes> lista = new ArrayList<>();

            //criar comando sql, organizar e executar
            String sql = "select * from tb_clientes";
            PreparedStatement pst = con.prepareStatement(sql);

            //quando se faz um selec no bunacop temos que armazenar em um objeto do tipo resultset
            ResultSet rst = pst.executeQuery();

            //pegar resultados do resultset percorrendo todos os campos  retornado
            while (rst.next()) {

                //criando objeto cliente para receber os valores , objj recebe o valor com o nome do campo do banco
                Clientes obj = new Clientes();
                obj.setId(rst.getInt("id"));
                obj.setNome(rst.getString("nome"));
                obj.setRg(rst.getString("rg"));
                obj.setCpf(rst.getString("cpf"));
                obj.setEmail(rst.getString("email"));
                obj.setTelefone(rst.getString("telefone"));
                obj.setCelular(rst.getString("celular"));
                obj.setCep(rst.getString("cep"));
                obj.setEndereco(rst.getString("endereco"));
                obj.setNumero(rst.getInt("numero"));
                obj.setComplemento(rst.getString("complemento"));
                obj.setBairro(rst.getString("bairro"));
                obj.setCidade(rst.getString("cidade"));
                obj.setEstado(rst.getString("estado"));
                obj.setNascimento(rst.getString("nascimento"));

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
    
    public List<Clientes> pesquisarClientePorNome(String nome){
        
        try {
            //criação da lista 
            List<Clientes> lista = new ArrayList<>();

            //criar comando sql, organizar e executar
            String sql = "select * from tb_clientes where nome like ?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1,nome);

            //quando se faz um selec no bunacop temos que armazenar em um objeto do tipo resultset
            ResultSet rst = pst.executeQuery();

            //pegar resultados do resultset percorrendo todos os campos  retornado
            while (rst.next()) {

                //criando objeto cliente para receber os valores , objj recebe o valor com o nome do campo do banco
                Clientes obj = new Clientes();
                obj.setId(rst.getInt("id"));
                obj.setNome(rst.getString("nome"));
                obj.setRg(rst.getString("rg"));
                obj.setCpf(rst.getString("cpf"));
                obj.setEmail(rst.getString("email"));
                obj.setTelefone(rst.getString("telefone"));
                obj.setCelular(rst.getString("celular"));
                obj.setCep(rst.getString("cep"));
                obj.setEndereco(rst.getString("endereco"));
                obj.setNumero(rst.getInt("numero"));
                obj.setComplemento(rst.getString("complemento"));
                obj.setBairro(rst.getString("bairro"));
                obj.setCidade(rst.getString("cidade"));
                obj.setEstado(rst.getString("estado"));
                obj.setNascimento(rst.getString("nascimento"));

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
