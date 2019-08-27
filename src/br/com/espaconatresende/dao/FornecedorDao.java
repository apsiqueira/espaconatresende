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
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author power
 */
public class FornecedorDao {

    private Connection con;

    public FornecedorDao() {
        this.con = new ConnectionFactory().getConnection();
    }

    public boolean salvarFornecedores (Fornecedores obj) {
        try {

            //passo 1:criar comando sql
            String sql = "insert into tb_fornecedores(marca,cnpj,vendedor,email,celular,telefone,cep,endereco,numero,bairro,cidade,complemento,estado,rg,cpf"
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
            return true;
//correto era lançar a exceção para quem chamou tratar para não perder dados do cliente na tela
        } catch (Exception e) {
            if (e.toString().contains("rg_UNIQUE")) {
                JOptionPane.showMessageDialog(null, "Rg ja existente!");
              

            } 
            else if(e.toString().contains("cpf_UNIQUE")){
                 JOptionPane.showMessageDialog(null, "Cpf ja existente!");
               
            }
            
            else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar Fornecedor!");
                System.out.println(e.toString());
              

            }

        }
        return false;
    }

    public List<Fornecedores> listarFornecedores() {
        try {
            //criação da lista 
            List<Fornecedores> lista = new ArrayList<>();

            //criar comando sql, organizar e executar
            String sql = "select * from tb_fornecedores";
            PreparedStatement pst = con.prepareStatement(sql);

            //quando se faz um selec no bunacop temos que armazenar em um objeto do tipo resultset
            ResultSet rst = pst.executeQuery();

            //pegar resultados do resultset percorrendo todos os campos  retornado
            while (rst.next()) {

                //criando objeto cliente para receber os valores , objj recebe o valor com o nome do campo do banco
                Fornecedores obj = new Fornecedores();
                obj.setId(rst.getInt("id"));
                obj.setMarca(rst.getString("marca"));
                obj.setCnpj(rst.getString("cnpj"));
                obj.setEmail(rst.getString("email"));
                obj.setCelular(rst.getString("celular"));
                obj.setTelefone(rst.getString("telefone"));
                obj.setCep(rst.getString("cep"));
                obj.setEndereco(rst.getString("endereco"));
                obj.setNumero(rst.getInt("numero"));
                obj.setBairro(rst.getString("bairro"));
                obj.setCidade(rst.getString("cidade"));
                obj.setComplemento(rst.getString("complemento"));
                obj.setEstado(rst.getString("estado"));
                obj.setRg(rst.getString("rg"));
                obj.setCpf(rst.getString("cpf"));

                //adicionar o objeto na lista 
                lista.add(obj);

            }

            //retorna a lista 
            return lista;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
        return null;
    }

    public void excluirFornecedor(Fornecedores obj) {
        try {
            String sql = "delete from tb_fornecedores where id=?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, obj.getId());

            pst.execute();

            pst.close();

            con.close();

            JOptionPane.showMessageDialog(null, "Fornecedor excluido com sucesso");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir funcionario");
        }

    }

    public void alterarFornecedor(Fornecedores obj) {
        try {
            //passo 1:criar comando sql
            String sql = "update tb_fornecedores set marca=?,cnpj=?,vendedor=?,email=?,celular=?,telefone=?,cep=?,endereco=?,numero=?,bairro=?,cidade=?,complemento=?,estado=?,rg=?,cpf=? where id=?";

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
         
            pst.setInt(16, obj.getId());

            //comando 3executar comando sql
            pst.execute();
            pst.close();
            con.close();

            JOptionPane.showMessageDialog(null, "Funcionario alterado com sucesso");

        } catch (SQLException erroSql) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar funcionario : " + erroSql);
           

        }

    }

    public List<Fornecedores> consultaFornecedorPorMarca(String marca) {
        try {
            //criação da lista 
            List<Fornecedores> lista = new ArrayList<>();

            //criar comando sql, organizar e executar
            String sql = "select * from tb_fornecedores where marca like ?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, marca);

            //quando se faz um selec no bunacop temos que armazenar em um objeto do tipo resultset
            ResultSet rst = pst.executeQuery();

            //pegar resultados do resultset percorrendo todos os campos  retornado
            while (rst.next()) {

                //criando objeto cliente para receber os valores , objj recebe o valor com o nome do campo do banco
                Fornecedores obj = new Fornecedores();
                obj.setId(rst.getInt("id"));
                obj.setMarca(rst.getString("marca"));
                obj.setCnpj(rst.getString("cnpj"));
                obj.setEmail(rst.getString("email"));
                obj.setCelular(rst.getString("celular"));
                obj.setTelefone(rst.getString("telefone"));
                obj.setCep(rst.getString("cep"));
                obj.setEndereco(rst.getString("endereco"));
                obj.setNumero(rst.getInt("numero"));
                obj.setBairro(rst.getString("bairro"));
                obj.setCidade(rst.getString("cidade"));
                obj.setComplemento(rst.getString("complemento"));
                obj.setEstado(rst.getString("estado"));
                obj.setRg(rst.getString("rg"));
                obj.setCpf(rst.getString("cpf"));

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

    public Fornecedores buscaCep(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Fornecedores pesquisaFornecedorPeloVendedor(String nome) {
        try {

            String sql = "select * from tb_fornecedores where vendedor=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, nome);

            //quando se faz um selec no bunacop temos que armazenar em um objeto do tipo resultset
            ResultSet rst = pst.executeQuery();

            //pegar resultados do resultset percorrendo todos os campos  retornado
            Fornecedores obj = new Fornecedores();
            if (rst.next()) {
               

                //criando objeto cliente para receber os valores , objj recebe o valor com o nome do campo do banco
                obj.setId(rst.getInt("id"));
                obj.setMarca(rst.getString("marca"));
                 obj.setVendedor(rst.getString("vendedor"));
                obj.setCnpj(rst.getString("cnpj"));
                obj.setEmail(rst.getString("email"));
                obj.setCelular(rst.getString("celular"));
                obj.setTelefone(rst.getString("telefone"));
                obj.setCep(rst.getString("cep"));
                obj.setEndereco(rst.getString("endereco"));
                obj.setNumero(rst.getInt("numero"));
                obj.setBairro(rst.getString("bairro"));
                obj.setCidade(rst.getString("cidade"));
                obj.setComplemento(rst.getString("complemento"));
                obj.setEstado(rst.getString("estado"));
                obj.setRg(rst.getString("rg"));
                obj.setCpf(rst.getString("cpf"));
                //adicionar o objeto na lista 
                
            }
            return obj;

        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "erro: " +e);

        }
        return null;
    }

    public void salvarProduto(Produtos obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
       public Fornecedores pesquisaFornecedorPelaMarca(String nome) {
        try {

            String sql = "select * from tb_fornecedores where marca=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, nome);

            //quando se faz um selec no bunacop temos que armazenar em um objeto do tipo resultset
            ResultSet rst = pst.executeQuery();

            //pegar resultados do resultset percorrendo todos os campos  retornado
            Fornecedores obj = new Fornecedores();
            if (rst.next()) {
               

                //criando objeto cliente para receber os valores , objj recebe o valor com o nome do campo do banco
                obj.setId(rst.getInt("id"));
                obj.setMarca(rst.getString("marca"));
                 obj.setVendedor(rst.getString("vendedor"));
                obj.setCnpj(rst.getString("cnpj"));
                obj.setEmail(rst.getString("email"));
                obj.setCelular(rst.getString("celular"));
                obj.setTelefone(rst.getString("telefone"));
                obj.setCep(rst.getString("cep"));
                obj.setEndereco(rst.getString("endereco"));
                obj.setNumero(rst.getInt("numero"));
                obj.setBairro(rst.getString("bairro"));
                obj.setCidade(rst.getString("cidade"));
                obj.setComplemento(rst.getString("complemento"));
                obj.setEstado(rst.getString("estado"));
                obj.setRg(rst.getString("rg"));
                obj.setCpf(rst.getString("cpf"));
                //adicionar o objeto na lista 
                
            }
            return obj;

        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "erro: " +e);

        }
        return null;
    }
    

}
