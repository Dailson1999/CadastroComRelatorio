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
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfNome.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Campo Inválido!");
				}else {
					operacoes op = new operacoes(tfNome.getText());
					op.salvar();
					tfNome.setText("");
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
						String sql = "update funcionarios set nome=? where id=?";
						PreparedStatement stmt;
						stmt = con.prepareStatement(sql);
						stmt.setString(1, tfNome.getText());
						stmt.setString(2, tfId.getText());
						stmt.execute();
						stmt.close();
						con.close();
						JOptionPane.showMessageDialog(null, "Atualizado!");

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
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnDeletar.setBounds(427, 241, 89, 23);
		contentPane.add(btnDeletar);
	}
}
