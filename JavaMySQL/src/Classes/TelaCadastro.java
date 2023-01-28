package Classes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import ConexaoMySQL.Conexao;

import javax.swing.border.EtchedBorder;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfId;
	private JTextField tfCpf;
	private JTextField tdEndereco;
	private JTextField tfCidade;
	private JTextField tfCelular;
	private JTextField tfCargo;
	private JTextField tfSalario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
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
	public TelaCadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		tfNome = new JTextField();
		tfNome.setBounds(64, 8, 86, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		tfCpf = new JTextField();
		tfCpf.setBounds(64, 39, 86, 20);
		contentPane.add(tfCpf);
		tfCpf.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("CPF");
		lblNewLabel_2.setBounds(10, 42, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		tdEndereco = new JTextField();
		tdEndereco.setBounds(64, 70, 86, 20);
		contentPane.add(tdEndereco);
		tdEndereco.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Endereço");
		lblNewLabel_3.setBounds(10, 73, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		tfCidade = new JTextField();
		tfCidade.setBounds(64, 101, 86, 20);
		contentPane.add(tfCidade);
		tfCidade.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Cidade");
		lblNewLabel_4.setBounds(10, 104, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		tfCelular = new JTextField();
		tfCelular.setBounds(64, 132, 86, 20);
		contentPane.add(tfCelular);
		tfCelular.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Celular");
		lblNewLabel_5.setBounds(10, 138, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		tfCargo = new JTextField();
		tfCargo.setBounds(64, 163, 86, 20);
		contentPane.add(tfCargo);
		tfCargo.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Cargo");
		lblNewLabel_6.setBounds(10, 166, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		tfSalario = new JTextField();
		tfSalario.setBounds(64, 194, 86, 20);
		contentPane.add(tfSalario);
		tfSalario.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Salario");
		lblNewLabel_7.setBounds(10, 197, 46, 14);
		contentPane.add(lblNewLabel_7);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfNome.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Campo Inválido!");
				}else {
					operacoes op = new operacoes(tfNome.getText(),tfCpf.getText(),tdEndereco.getText(),tfCidade.getText(),tfCelular.getText(),tfCargo.getText(),Float.parseFloat(tfSalario.getText()));	
					op.salvar();
					tfNome.setText("");
					tdEndereco.setText("");
					tfCargo.setText("");
					tfCelular.setText("");
					tfCidade.setText("");
					tfCpf.setText("");
					tfSalario.setText("");
				}
			}
		});
		btnSalvar.setBounds(10, 309, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnFuncionarios = new JButton("Funcionários");
		btnFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaListar exibir = new TelaListar();
				exibir.setVisible(true);/*Mostra tela listar*/
				setVisible(false);/*esconde tela cad*/
				
			}
		});
		btnFuncionarios.setBounds(307, 309, 110, 23);
		contentPane.add(btnFuncionarios);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Campo Inválido!");
				}else {
					try {
						Connection con = Conexao.fazCon();
						String sql = "update funcionarios set nome=?,cpf=?,endereco=?,cidade=?,celular=?,cargo=?,salario=? where id=?";
						PreparedStatement stmt;
						stmt = con.prepareStatement(sql);
						stmt.setString(8, tfId.getText());
						stmt.setString(1, tfNome.getText());
						stmt.setString(2, tfCpf.getText());
						stmt.setString(3, tdEndereco.getText());
						stmt.setString(4, tfCidade.getText());
						stmt.setString(5, tfCelular.getText());
						stmt.setString(6, tfCargo.getText());
						stmt.setString(7,tfSalario.getText());
						stmt.execute();
						stmt.close();
						con.close();
						JOptionPane.showMessageDialog(null, "Atualizado!");
						tfCpf.setText("");
						tfNome.setText("");
						tdEndereco.setText("");
						tfCidade.setText("");
						tfCelular.setText("");
						tfCargo.setText("");
						tfSalario.setText("");

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				}
		});
		btnAtualizar.setBounds(427, 275, 89, 23);
		contentPane.add(btnAtualizar);
		
		tfId = new JTextField();
		tfId.setBounds(209, 8, 55, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Id");
		lblNewLabel_1.setBounds(181, 11, 18, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Conexao.fazCon();
					String sql = "select *from funcionarios where id=?";
					PreparedStatement stmt;
					stmt = con.prepareStatement(sql);
					stmt.setString(1, tfId.getText());
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						
						tfNome.setText(rs.getString("nome"));
						tfId.setText(rs.getString("id"));
						tfCpf.setText(rs.getString("cpf"));
						tdEndereco.setText(rs.getString("endereco"));
						tfCidade.setText(rs.getString("cidade"));
						tfCelular.setText(rs.getString("celular"));
						tfCargo.setText(rs.getString("cargo"));
						tfSalario.setText(rs.getString("salario"));
					}
					rs.close();
					stmt.execute();
					stmt.close();
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnBuscar.setBounds(427, 309, 89, 23);
		contentPane.add(btnBuscar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Campo Inválido!");
				}else {
					try {
						Connection con = Conexao.fazCon();
						String sql = "delete from funcionarios where id=?";
						PreparedStatement stmt;
						stmt = con.prepareStatement(sql);
						stmt.setString(1, tfId.getText());
						
						JOptionPane.showMessageDialog(null, "Deletado!");

						stmt.execute();
						stmt.close();
						con.close();
						tfId.setText("");
						tfNome.setText("");
						tdEndereco.setText("");
						tfCargo.setText("");
						tfCelular.setText("");
						tfCidade.setText("");
						tfCpf.setText("");
						tfSalario.setText("");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnDeletar.setBounds(427, 241, 89, 23);
		contentPane.add(btnDeletar);
		
		JButton btnRelEndereco = new JButton("Relatorio Endereço");
		btnRelEndereco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RelatorioEnderecos exibir = new RelatorioEnderecos();
				exibir.setVisible(true);/*Mostra tela listar*/
				setVisible(false);/*esconde tela cad*/
				
			}
		});
		btnRelEndereco.setBounds(291, 7, 225, 23);
		contentPane.add(btnRelEndereco);
		
		JButton btnRelSalario = new JButton("Relatorio Salario");
		btnRelSalario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Relatoria_Salario exibir = new Relatoria_Salario();
				exibir.setVisible(true);/*Mostra tela listar*/
				setVisible(false);/*esconde tela cad*/
				
			}
		});
		btnRelSalario.setBounds(291, 38, 225, 23);
		contentPane.add(btnRelSalario);
		
		JButton btnRelDeletados = new JButton("Relatorio Deletados");
		btnRelDeletados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Relatorio_Deletados exibir = new Relatorio_Deletados();
				exibir.setVisible(true);/*Mostra tela listar*/
				setVisible(false);/*esconde tela cad*/
				
				
			}
		});
		btnRelDeletados.setBounds(291, 69, 225, 23);
		contentPane.add(btnRelDeletados);
		
		JButton btnDataAttSalario = new JButton("Relatorio Data ATT Salario");
		btnDataAttSalario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RelatoriaDataAttSalarial exibir = new RelatoriaDataAttSalarial();
				exibir.setVisible(true);/*Mostra tela listar*/
				setVisible(false);/*esconde tela cad*/
				
				
			}
		});
		btnDataAttSalario.setBounds(291, 100, 225, 23);
		contentPane.add(btnDataAttSalario);
		
		JButton btnDataAttEndereco = new JButton("Relatorio Data Att Endereco");
		btnDataAttEndereco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RelatoriDataAttEndereco exibir = new RelatoriDataAttEndereco();
				exibir.setVisible(true);/*Mostra tela listar*/
				setVisible(false);/*esconde tela cad*/
				
			}
		});
		btnDataAttEndereco.setBounds(291, 131, 225, 23);
		contentPane.add(btnDataAttEndereco);
		
	}
}
