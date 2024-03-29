package giaodien;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dieukhien.QuanLyHoaDon;
import doituong.HoaDon;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;

public class GiaoDienChonKhachHangTra extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private DefaultTableModel tablemodel;
	private QuanLyHoaDon HD;
	private JButton btnTraBangDia, btnTroVe, btnTimKiem;

	public GiaoDienChonKhachHangTra() {
		setTitle("Trả Băng Đĩa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 941, 594);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] colNameHD = "Mã Phiếu Thuê;Tên Khách Hàng;Tên Nhân Viên;Ngày Lập Phiếu".split(";");
		tablemodel = new DefaultTableModel(colNameHD, 0);
		JScrollPane scrollPane = new JScrollPane(table = new JTable(tablemodel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 57, 905, 391);
		contentPane.add(scrollPane);

		capNhatBangDuLieuHoaDon();

		JLabel lblMPhiuThu = new JLabel("SDT Khách Hàng:");
		lblMPhiuThu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMPhiuThu.setBounds(10, 504, 140, 22);
		contentPane.add(lblMPhiuThu);

		textField = new JTextField();
		textField.setBounds(136, 507, 167, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTimKiem.setBounds(313, 504, 116, 23);
		contentPane.add(btnTimKiem);

		btnTraBangDia = new JButton("Trả Băng Đĩa");
		btnTraBangDia.setBackground(Color.GREEN);
		btnTraBangDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTraBangDia.setBounds(502, 494, 178, 43);
		contentPane.add(btnTraBangDia);

		JLabel lblTrBnga = new JLabel("Trả Băng Đĩa");
		lblTrBnga.setFont(new Font("Sitka Small", Font.BOLD, 22));
		lblTrBnga.setBounds(387, 11, 178, 35);
		contentPane.add(lblTrBnga);

		btnTroVe = new JButton("Trở Về");
		btnTroVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTroVe.setBounds(773, 504, 116, 23);
		contentPane.add(btnTroVe);

		btnTraBangDia.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnTroVe.addActionListener(this);
	}

	private void capNhatBangDuLieuHoaDon() {
		int rowCount = table.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			tablemodel.removeRow(i - 1);
		}
		HD = new QuanLyHoaDon();
		List<HoaDon> list = HD.docTuBang1();
		for (HoaDon hoaDon : list) {
			String[] rowData = { hoaDon.getMaHoaDon(), hoaDon.getTenKhachHang(), hoaDon.getTenNhanVien(),
					hoaDon.getNgayLapHoaDon().toString() };
			tablemodel.addRow(rowData);
		}
		table.setModel(tablemodel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnTraBangDia)) {
			int row = table.getSelectedRow();
			if (row != -1) {
				HD = new QuanLyHoaDon();
				String maHD = (String) table.getValueAt(row, 0);
				GiaoDienChiTietPhieuThue hd = new GiaoDienChiTietPhieuThue(maHD, 0);
				hd.setVisible(true);
				setVisible(false);
			}
		} else if (o.equals(btnTroVe)) {
			setVisible(false);
		}

	}
}
