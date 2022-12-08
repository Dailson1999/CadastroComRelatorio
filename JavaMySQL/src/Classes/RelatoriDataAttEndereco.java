package Classes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import ConexaoMySQL.Conexao;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class RelatoriDataAttEndereco extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable tableData;
	private JButton btnVoltar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RelatoriDataAttEndereco frame = new RelatoriDataAttEndereco();
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
	public RelatoriDataAttEndereco() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Conexao.fazCon();
					String sql = "select *from vw_ultimaAttEndereco;";
					PreparedStatement stmt;
					stmt = con.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel)tableData.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						
						modelo.addRow(new Object[]{rs.getString("id"),rs.getString("nome"),rs.getString("cpf"),rs.getString("changedat")});
						
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
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 202);
		contentPane.add(scrollPane);
		
		tableData = new JTable();
		tableData.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "Nome", "CPF", "Data Atualiza\u00E7\u00E3o"
			}
		));
		scrollPane.setViewportView(tableData);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaCadastro exibir = new TelaCadastro();
				exibir.setVisible(true);
				setVisible(false);
				
			}
		});
		btnVoltar.setBounds(123, 227, 89, 23);
		contentPane.add(btnVoltar);
		
		JButton btnPDF = new JButton("PDF");
		btnPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				try {Connection con = Conexao.fazCon();
				String sql = "select *from vw_ultimaAttEndereco;";
				PreparedStatement stmt;
				stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				Document doc = new Document();
					PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\dails\\eclipse-workspace\\PDF_Relatorios.pdf"));
					
					doc.open();
					doc.setPageSize(PageSize.A4);
					doc.add((Element) tableData/*new Object[]{rs.getString("id"),rs.getString("nome"),rs.getString("cpf"),rs.getString("changedat")}*/);
					
				
				} catch (Exception e1) {
					// TODO: handle exception
					
				}
				
			}
		});
		btnPDF.setBounds(230, 227, 89, 23);
		contentPane.add(btnPDF);
	}
}
