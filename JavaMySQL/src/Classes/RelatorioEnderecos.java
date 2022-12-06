package Classes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ConexaoMySQL.Conexao;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RelatorioEnderecos extends JFrame {

	private JPanel contentPane;
	private JTable tableRelatorio;
	private JButton btnVoltar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RelatorioEnderecos frame = new RelatorioEnderecos();
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
	public RelatorioEnderecos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 503, 202);
		contentPane.add(scrollPane);
		
		tableRelatorio = new JTable();
		tableRelatorio.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "Nome", "Endere\u00E7o", "Cidade", "Celular", "Endere\u00E7o_Antigo", "Cidade_Anterior", "Celular_Anterior"
			}
		));
		tableRelatorio.getColumnModel().getColumn(7).setPreferredWidth(91);
		scrollPane.setViewportView(tableRelatorio);
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					Connection con = Conexao.fazCon();
					String sql = "select *from vw_endereco;";
					PreparedStatement stmt;
					stmt = con.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel)tableRelatorio.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						
						modelo.addRow(new Object[]{rs.getString("id"),rs.getString("nome"),rs.getString("endereco"),rs.getString("cidade"),rs.getString("celular"),rs.getString("endereco2"),rs.getString("cidade2"),rs.getString("celular2")});
						
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
		btnVoltar.setBounds(109, 227, 89, 23);
		contentPane.add(btnVoltar);
	}
}
