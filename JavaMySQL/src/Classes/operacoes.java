package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ConexaoMySQL.Conexao;

public class operacoes {
	private int id; 
	private String nome; 
    private String cpf; 
    private String endereco;
    private String cidade;
    private String celular;
    private String cargo;
    private String salario;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getSalario() {
		return salario;
	}
	public void setSalario(String salario) {
		this.salario = salario;
	}
	
	public operacoes(String nome) {
		
		this.nome = nome;
		this.id = id;
		/*this.cpf = cpf;
		this.endereco = endereco;
		this.cidade = cidade;
		this.celular = celular;
		this.cargo = cargo;
		this.salario = salario;*/
	}
    
	public void salvar() {
		try {
			Connection con = Conexao.fazCon();
			String sql = "insert into funcionarios(nome) value (?)";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null, "Cadastrado");
		}catch(SQLException e){
			e.printStackTrace();
			
		}
	}
	
	public void atualizar() {
		
		try {
			Connection con = Conexao.fazCon();
			String sql = "update funcionarios set nome=? where id=?";
			PreparedStatement stmt;
			stmt = con.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.setInt(2, id);
			stmt.execute();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void buscar() {
		
		try {
			Connection con = Conexao.fazCon();
			String sql = "select *from funcionarios where id=?";
			PreparedStatement stmt;
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			stmt.execute();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}