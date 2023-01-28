package Classes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ConexaoMySQL.Conexao;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class TelaListar extends JFrame {

	private JPanel contentPane;
	private JTable tbFuncionarios;
	private JButton btnVoltar;
	private JButton btnNewButton;
	private JButton btnBuscaGerente;
	private JButton btnEndereco;
	private JButton btnCpf;
	private JButton btnDiretor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListar frame = new TelaListar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaListar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 180);
		contentPane.add(scrollPane);
		
		tbFuncionarios = new JTable();
		tbFuncionarios.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Nome", "CPF", "Endere\u00E7o", "Cidade", "Celular", "Cargo", "Sal\u00E1rio"
			}
		));
		scrollPane.setViewportView(tbFuncionarios);
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Conexao.fazCon();
					String sql = "select *from funcionarios";
					PreparedStatement stmt;
					stmt = con.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel)tbFuncionarios.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						
						 modelo.addRow(new Object[]{rs.getString("id"),rs.getString("nome"),rs.getString("cpf"),rs.getString("endereco"),rs.getString("cidade"),rs.getString("celular"),rs.getString("cargo"),rs.getString("salario")});
						
					}
					
					
					rs.close();
					stmt.close();
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnListar.setBounds(10, 227, 89, 23);
		contentPane.add(btnListar);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaCadastro exibir = new TelaCadastro();
				exibir.setVisible(true);
				setVisible(false);
				
			}
		});
		btnVoltar.setBounds(110, 227, 89, 23);
		contentPane.add(btnVoltar);
		
		JButton btnClasse = new JButton("Busca Nome");
		btnClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Conexao.fazCon();
					String sql = "select * from Busca_Nome";
					PreparedStatement stmt;
					stmt = con.prepareStatement(sql);
					
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel)tbFuncionarios.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						
						modelo.addRow(new Object[]{rs.getString("id"),rs.getString("Resultado_Busca"),rs.getString("cpf"),rs.getString("endereco"),rs.getString("cidade"),rs.getString("celular"),rs.getString("cargo"),rs.getString("salario")});

						
					}
					
					
					rs.close();
					stmt.close();
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnClasse.setBounds(146, 202, 108, 23);
		contentPane.add(btnClasse);
		
		btnNewButton = new JButton("Classe Salario");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Conexao.fazCon();
					String sql = "select * from salario_funcionarios";
					PreparedStatement stmt;
					stmt = con.prepareStatement(sql);
					
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel)tbFuncionarios.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						
						modelo.addRow(new Object[]{rs.getString("id"),rs.getString("classe"),rs.getString("cpf"),rs.getString("endereco"),rs.getString("cidade"),rs.getString("celular"),rs.getString("cargo"),rs.getString("salario")});

						
					}
					
					
					rs.close();
					stmt.close();
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
					
				}
		});
		btnNewButton.setBounds(10, 202, 120, 23);
		contentPane.add(btnNewButton);
		
		btnBuscaGerente = new JButton("Gerente");
		btnBuscaGerente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Conexao.fazCon();
					String sql = "select * from Cargo_Gerente;";
					PreparedStatement stmt;
					stmt = con.prepareStatement(sql);
					
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel)tbFuncionarios.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						
						modelo.addRow(new Object[]{rs.getString("id"),rs.getString("gerente_nome"),rs.getString("cpf"),rs.getString("endereco"),rs.getString("cidade"),rs.getString("celular"),rs.getString("cargo"),rs.getString("salario")});

						
					}
					
					
					rs.close();
					stmt.close();
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnBuscaGerente.setBounds(209, 227, 89, 23);
		contentPane.add(btnBuscaGerente);
		
		btnEndereco = new JButton("Endereço");
		btnEndereco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Conexao.fazCon();
					String sql = "select *from Busca_endereco;";
					PreparedStatement stmt;
					stmt = con.prepareStatement(sql);
					
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel)tbFuncionarios.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						
						modelo.addRow(new Object[]{rs.getString("id"),rs.getString("nome"),rs.getString("cpf"),rs.getString("endereco_do_id"),rs.getString("cidade"),rs.getString("celular"),rs.getString("cargo"),rs.getString("salario")});

						
					}
					
					
					rs.close();
					stmt.close();
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnEndereco.setBounds(264, 202, 89, 23);
		contentPane.add(btnEndereco);
		
		btnCpf = new JButton("CPF");
		btnCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Conexao.fazCon();
					String sql = "select *from Busca_cpf;";
					PreparedStatement stmt;
					stmt = con.prepareStatement(sql);
					
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel)tbFuncionarios.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						
						modelo.addRow(new Object[]{rs.getString("id"),rs.getString("nome"),rs.getString("cpf_do_id"),rs.getString("endereco"),rs.getString("cidade"),rs.getString("celular"),rs.getString("cargo"),rs.getString("salario")});

						
					}
					
					
					rs.close();
					stmt.close();
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnCpf.setBounds(301, 227, 89, 23);
		contentPane.add(btnCpf);
		
		btnDiretor = new JButton("Diretor");
		btnDiretor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					Connection con = Conexao.fazCon();
					String sql = "select *from Busca_Diretor;";
					PreparedStatement stmt;
					stmt = con.prepareStatement(sql);
					
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel)tbFuncionarios.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						
						modelo.addRow(new Object[]{rs.getString("id"),rs.getString("nome"),rs.getString("cpf"),rs.getString("endereco"),rs.getString("cidade"),rs.getString("celular"),rs.getString("diretor_do_id"),rs.getString("salario")});

						
					}
					
					
					rs.close();
					stmt.close();
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnDiretor.setBounds(363, 202, 75, 23);
		contentPane.add(btnDiretor);
	}
}
