package giaodien;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dieukhien.QuanLyHoaDon;
import doituong.HoaDon;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GiaoDienDanhSachHoaDon extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tablemodelHD;
	private QuanLyHoaDon HD;
	private JButton btnXemChiTiet, btnTimKiem, btnTroVe;
	private int trThai;

	public GiaoDienDanhSachHoaDon(String tenKH, String tenNV, Date tuNgay, Date denNgay, int trangThai) {
		this.trThai = trangThai;
		setTitle("Quản Lý Hóa Đơn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 953, 594);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] colNameHD = "Mã Phiếu Thuê;Tên Khách Hàng;Tên Nhân Viên;Ngày Lập Phiếu;Ngày Trả Đĩa".split(";");
		tablemodelHD = new DefaultTableModel(colNameHD, 0);
		JScrollPane scrollPane = new JScrollPane(table = new JTable(tablemodelHD),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 45, 927, 456);
		contentPane.add(scrollPane);
		capNhatBangDuLieuHoaDon(tenKH, tenNV, tuNgay, denNgay, trangThai);

		btnXemChiTiet = new JButton("Xem Chi Tiết");
		btnXemChiTiet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXemChiTiet.setBounds(149, 512, 151, 23);
		contentPane.add(btnXemChiTiet);
		btnXemChiTiet.addActionListener(this);

		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTimKiem.setBounds(423, 512, 107, 23);
		contentPane.add(btnTimKiem);

		JLabel lblDanhSchPhiu = new JLabel("Danh Sách Phiếu Thuê");
		lblDanhSchPhiu.setFont(new Font("Sitka Small", Font.BOLD, 22));
		lblDanhSchPhiu.setBounds(312, 11, 267, 35);
		contentPane.add(lblDanhSchPhiu);

		btnTroVe = new JButton("Trở Về");
		btnTroVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTroVe.setBounds(676, 512, 107, 23);
		contentPane.add(btnTroVe);

		btnTimKiem.addActionListener(this);
		btnTroVe.addActionListener(this);
	}

	private void capNhatBangDuLieuHoaDon(String tenKH, String tenNV, Date tuNgay, Date denNgay, int trThai) {
		int rowCount = table.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			tablemodelHD.removeRow(i - 1);
		}
		HD = new QuanLyHoaDon();
		List<HoaDon> list = HD.timKiemHoaDon(tenKH, tenNV, tuNgay, denNgay, trThai);
		for (HoaDon hoaDon : list) {
			if (trThai == 1) {
				String[] rowData = { hoaDon.getMaHoaDon(), hoaDon.getTenKhachHang(), hoaDon.getTenNhanVien(),
						hoaDon.getNgayLapHoaDon().toString(), hoaDon.getNgayTra().toString() };
				tablemodelHD.addRow(rowData);
			} else {
				String[] rowData = { hoaDon.getMaHoaDon(), hoaDon.getTenKhachHang(), hoaDon.getTenNhanVien(),
						hoaDon.getNgayLapHoaDon().toString() };
				tablemodelHD.addRow(rowData);
			}

		}
		table.setModel(tablemodelHD);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnXemChiTiet)) {
			int row = table.getSelectedRow();
			if (row != -1) {
				String maHD = (String) table.getValueAt(row, 0);
				// JOptionPane.showMessageDialog(this, maHD);
				GiaoDienChiTietPhieuThue ct = new GiaoDienChiTietPhieuThue(maHD, trThai);
				ct.setVisible(true);
				setVisible(false);
			}
		} else if (o.equals(btnTimKiem)) {
			GiaoDienTimKiemHoaDon hd = new GiaoDienTimKiemHoaDon();
			hd.setVisible(true);
			setVisible(false);
		} else if (o.equals(btnTroVe)) {
			setVisible(false);
		}
	}
}