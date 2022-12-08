package Classes;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
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
    private float salario;
    
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
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	
	public operacoes(String nome, String cpf, String endereco, String cidade, String celular, String cargo, float  salario) {
		
		this.nome = nome;
		this.id = id;
		this.cpf = cpf;
		this.endereco = endereco;
		this.cidade = cidade;
		this.celular = celular;
		this.cargo = cargo;
		this.salario = salario;
	}
    
	public void salvar() {
		try {
			Connection con = Conexao.fazCon();
			String sql = "insert into funcionarios(nome,cpf,endereco,cidade,celular,cargo,salario) value (?,?,?,?,?,?,?)";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.setString(2, cpf);
			stmt.setString(3, endereco);
			stmt.setString(4, cidade);
			stmt.setString(5, celular);
			stmt.setString(6, cargo);
			stmt.setFloat(7, salario);
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
			String sql = "update funcionarios set nome=?,cpf=?,endereco=?,cidade=?,celular=?,cargo=?,salario=? where id=?";
			PreparedStatement stmt;
			stmt = con.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.setString(1, nome);
			stmt.setString(2, cpf);
			stmt.setString(3, endereco);
			stmt.setString(4, cidade);
			stmt.setString(5, celular);
			stmt.setString(6, cargo);
			stmt.setFloat(7, salario);
			stmt.setInt(2, id);
			stmt.execute();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static String gerarPDF(String relatorio ) {
		Connection con = Conexao.fazCon();
		Document doc = new Document();
		
		try {
			PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\dails\\eclipse-workspace\\PDF_Relatorios.pdf"));
			
			doc.open();
			doc.setPageSize(PageSize.A4);
			doc.add(new Paragraph(relatorio));
			
			
		} catch (DocumentException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			
		}
		return relatorio;
	}
	
	/*public void buscar() {
		
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
	}*/
}