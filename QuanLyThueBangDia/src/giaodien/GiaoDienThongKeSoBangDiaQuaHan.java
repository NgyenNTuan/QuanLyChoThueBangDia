package giaodien;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import database.Database;
import datepicker.DateLabelFormatter;
import dieukhien.QuanLyBangDia;
import dieukhien.QuanLyChiTietHoaDon;
import dieukhien.QuanLyHoaDon;
import doituong.BangDia;
import doituong.ChiTietHoaDon;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;

public class GiaoDienThongKeSoBangDiaQuaHan extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private QuanLyBangDia qlbd;
	private QuanLyHoaDon qlhd;
	private QuanLyChiTietHoaDon qlcthd;
	private JLabel lbltong,lblngay;
	private Properties p;
	private UtilDateModel model1, model2;
	private JDatePanelImpl datePanel1, datePanel2;
	private JButton btnluu,btninBaoCao,btntroVe;
	
	public GiaoDienThongKeSoBangDiaQuaHan(){
		setTitle("Giao Diện Thống Kê Băng Đĩa Chưa Trả");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 837, 705);
		setLocationRelativeTo(null);
		taoGiaoDien();
	}

	public void taoGiaoDien() {
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] headers = "Mã Băng Đĩa;Tên Băng Đĩa;Số Lượng".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scrollPane = new JScrollPane(table = new JTable(tableModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.addMouseListener(this);
		scrollPane.setBounds(22, 75, 781, 437);
		contentPane.add(scrollPane);

		JLabel lblthongKeBangDiaTreHan = new JLabel("THỐNG KÊ SỐ BĂNG ĐĨA ĐÃ QUÁ HẠN TRẢ");
		lblthongKeBangDiaTreHan.setForeground(Color.BLUE);
		lblthongKeBangDiaTreHan.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblthongKeBangDiaTreHan.setBounds(226, 29, 399, 36);
		contentPane.add(lblthongKeBangDiaTreHan);
		
		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		model1 = new UtilDateModel();
		datePanel1 = new JDatePanelImpl(model1, p);
		
		model2 = new UtilDateModel();
		datePanel2 = new JDatePanelImpl(model2, p);
		
		JLabel lbltongSoDiaChuaTra = new JLabel("Tổng Số Băng Đĩa  Chưa Trả :");
		lbltongSoDiaChuaTra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbltongSoDiaChuaTra.setBounds(22, 537, 221, 27);
		contentPane.add(lbltongSoDiaChuaTra);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Thanh C\u00F4ng C\u1EE5", TitledBorder.LEADING, TitledBorder.TOP,

				null, Color.BLUE));
		panel.setBackground(Color.WHITE);
		panel.setBounds(133, 579, 544, 79);
		contentPane.add(panel);

		btninBaoCao = new JButton("In Báo Cáo");
		btninBaoCao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btninBaoCao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btninBaoCao.setBackground(Color.GREEN);
		btninBaoCao.setBounds(24, 25, 115, 31);
		panel.add(btninBaoCao);

		btnluu = new JButton("Lưu");
		btnluu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnluu.setBackground(Color.WHITE);
		btnluu.setBounds(231, 25, 99, 31);
		panel.add(btnluu);

		btntroVe = new JButton("Trở Về");
		btntroVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btntroVe.setBackground(Color.WHITE);
		btntroVe.setBounds(435, 25, 99, 31);
		panel.add(btntroVe);
		
		lbltong = new JLabel("");
		lbltong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbltong.setBounds(226, 537, 46, 22);
		contentPane.add(lbltong);
		
		JLabel lblNgy = new JLabel("Ngày :");
		lblNgy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgy.setBounds(653, 10, 46, 27);
		contentPane.add(lblNgy);
		
		lblngay = new JLabel("");
		lblngay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblngay.setBounds(709, 10, 94, 27);
		contentPane.add(lblngay);
		btninBaoCao.addActionListener(this);
		btnluu.addActionListener(this);
		btntroVe.addActionListener(this);
		capNhatBangDuLieu();
	}

	public void capNhatBangDuLieu() {
		int rowCount = table.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			tableModel.removeRow(i - 1);
		}
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "select BangDia.IDBangDia,BangDia.TenBD,SoLuongBDChuaTra=sum(ChiTietHoaDon.SoLuong) \r\n" + 
					"from HoaDon join ChiTietHoaDon on HoaDon.IDHoaDon=ChiTietHoaDon.IDHoaDon\r\n" + 
					"			join BangDia on ChiTietHoaDon.IDBangDia=BangDia.IDBangDia\r\n" + 
					"where HoaDon.NgayTraDia is null and DATEDIFF(day,HoaDon.NgayLapHD,GETDATE())>7\r\n" + 
					"group by BangDia.IDBangDia, BangDia.TenBD";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maBD = rs.getString(1);
				String tenBD = rs.getString(2);
				String SoLuong= rs.getString(3);
				String[] rowData = { maBD, tenBD,SoLuong };
				tableModel.addRow(rowData);
			}
			table.setModel(tableModel);
			tinhTong();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void tinhTong() {
		int tong=0;
		int rowCount=table.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			String tongso= (String) table.getValueAt(i, 2);
			int t=Integer.parseInt(tongso);
			tong+=t;
		}
		lbltong.setText(tong+"");
		LocalDate day=LocalDate.now();
		lblngay.setText(day+"");
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();
		if(o.equals(btntroVe)) {
			setVisible(false);
		}else if(o.equals(btninBaoCao)) {
			
		}else if(o.equals(btnluu)) {
			
		}

	}
}
