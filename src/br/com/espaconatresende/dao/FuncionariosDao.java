/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.espaconatresende.dao;

import br.com.espaconatresende.jdbc.ConnectionFactory;
import br.com.espaconatresende.model.Funcionarios;
import br.com.espaconatresende.suport.Utilitarios;
import br.com.espaconatresende.suport.WebServiceCep;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
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
public class FuncionariosDao {

    private Connection con;

    public FuncionariosDao() {
        this.con = new ConnectionFactory().getConnection();

    }

    public void salvarFuncionario(Funcionarios obj) {
        try {
            //passo 1:criar comando sql
            String sql = "insert into tb_funcionarios(nome,rg,cpf,email,senha,cargo,nivel_acesso,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado,nascimento"
                    + ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            //passo 2:conectar ao banco e organizar o comando sql
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getRg());
            pst.setString(3, obj.getCpf());
            pst.setString(4, obj.getEmail());
            pst.setString(5, obj.getSenha());
            pst.setString(6, obj.getCargo());
            pst.setString(7, obj.getNivelAcesso());

            pst.setString(8, obj.getTelefone());
            pst.setString(9, obj.getCelular());
            pst.setString(10, obj.getCep());
            pst.setString(11, obj.getEndereco());
            pst.setInt(12, obj.getNumero());
            pst.setString(13, obj.getComplemento());
            pst.setString(14, obj.getBairro());
            pst.setString(15, obj.getCidade());
            pst.setString(16, obj.getEstado());
            pst.setString(17, obj.getNascimento());

            //comando 3executar comando sql
            pst.execute();
            pst.close();
            con.close();

            JOptionPane.showMessageDialog(null, "Funcionario cadastrado com sucesso!");

        } catch (SQLException erroSql) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar Funcionario!" + erroSql);

        }
    }

    public List<Funcionarios> pesquisarFuncionarioPorNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Funcionarios> listarFuncionarios() {

        try {
            //criação da lista 
            List<Funcionarios> lista = new ArrayList<>();

            //criar comando sql, organizar e executar
            String sql = "select * from tb_funcionarios";
            PreparedStatement pst = con.prepareStatement(sql);

            //quando se faz um selec no bunacop temos que armazenar em um objeto do tipo resultset
            ResultSet rst = pst.executeQuery();

            //pegar resultados do resultset percorrendo todos os campos  retornado
            while (rst.next()) {

                //criando objeto cliente para receber os valores , objj recebe o valor com o nome do campo do banco
                Funcionarios obj = new Funcionarios();
                obj.setId(rst.getInt("id"));
                obj.setNome(rst.getString("nome"));
                obj.setRg(rst.getString("rg"));
                obj.setCpf(rst.getString("cpf"));
                obj.setEmail(rst.getString("email"));
                obj.setSenha(rst.getString("email"));
                obj.setCargo(rst.getString("email"));
                obj.setNivelAcesso(rst.getString("email"));
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

    //metodo consulta por nome 
    public Funcionarios consultaPorNome(String nome) {

        try {

            String sql = "select * from tb_funcionarios where nome=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, nome);

            //quando se faz um selec no bunacop temos que armazenar em um objeto do tipo resultset
            ResultSet rst = pst.executeQuery();

            //pegar resultados do resultset percorrendo todos os campos  retornado
            Funcionarios obj = new Funcionarios();
            if (rst.next()) {

                //criando objeto cliente para receber os valores , objj recebe o valor com o nome do campo do banco
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
            }
            return obj;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Cliente não encontrado");
            return null;
        }

    }


    public void excluirFuncionario(Funcionarios obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void alterarFuncionario(Funcionarios obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Funcionarios consultaFuncionarioPorNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Funcionarios buscaCep(String cep) {

        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);

        Funcionarios obj = new Funcionarios();
        Boolean conexao = Utilitarios.consegueConectar("http://www.google.com.br");

        try {
            if (webServiceCep.wasSuccessful()) {
                obj.setEndereco(webServiceCep.getLogradouroFull());
                obj.setCidade(webServiceCep.getCidade());
                obj.setBairro(webServiceCep.getBairro());
                obj.setEstado(webServiceCep.getUf());
                return obj;

            } else if (conexao == false) {

                JOptionPane.showMessageDialog(null, "Erro de conexão com internet.");
            } else {

                JOptionPane.showMessageDialog(null, "Erro no formato do cep");
            }
        } catch (Exception e) {
        }
        return null;
    }

}
