package giaodien;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dieukhien.QuanLyNhanVien;
import dieukhien.QuanLyTaiKhoan;
import doituong.TaiKhoan;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class GiaoDienDangNhap extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtTenTK;
	private JPasswordField pwfMatKhau;
	private JLabel lblTiKhon;
	private JLabel lblMtKhu;
	private JButton btnDangNhap, btnThoat;
	private QuanLyTaiKhoan TK;
	private QuanLyNhanVien NV;
	
	public GiaoDienDangNhap() {
		setTitle("Đăng nhập");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\PC\\Downloads\\icons8-login-64.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 315, 180);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtTenTK = new JTextField();
		txtTenTK.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenTK.setBounds(106, 16, 160, 25);
		contentPane.add(txtTenTK);
		txtTenTK.setColumns(10);

		pwfMatKhau = new JPasswordField();
		pwfMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pwfMatKhau.setBounds(106, 61, 160, 25);
		contentPane.add(pwfMatKhau);

		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDangNhap.setBounds(30, 101, 112, 25);
		contentPane.add(btnDangNhap);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnThoat.setBounds(171, 101, 95, 25);
		contentPane.add(btnThoat);

		lblTiKhon = new JLabel("Tài Khoản :");
		lblTiKhon.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTiKhon.setIcon(new ImageIcon("C:\\Users\\PC\\Downloads\\icons8-user-24.png"));
		lblTiKhon.setBounds(10, 11, 85, 34);
		contentPane.add(lblTiKhon);

		lblMtKhu = new JLabel("Mật Khẩu :");
		lblMtKhu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMtKhu.setIcon(new ImageIcon("C:\\Users\\PC\\Downloads\\icons8-key-24.png"));
		lblMtKhu.setBounds(10, 56, 85, 34);
		contentPane.add(lblMtKhu);
		btnDangNhap.addActionListener(this);
		btnThoat.addActionListener(this);
		pwfMatKhau.addActionListener(this);
	}

	private String kiemTraTK(String tk, String mk) {
		TK = new QuanLyTaiKhoan();
		List<TaiKhoan> list = TK.docTuBang();
		for (TaiKhoan taiKhoan : list) {
			if (tk.equals(taiKhoan.getTenTK()))
				if (mk.equals(taiKhoan.getMatKhau()))
					return taiKhoan.getMaTK().trim();
		}
		return null;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnDangNhap) || o.equals(pwfMatKhau)) {
			String tk = txtTenTK.getText();
			char[] m = pwfMatKhau.getPassword();
			String mk = "";
			for (int i = 0; i < m.length; i++) {
				mk += m[i];
			}
			String ma = kiemTraTK(tk, mk);
			if (ma != null) {
				NV = new QuanLyNhanVien();
				String maNV = NV.layMaNV(ma);
				GiaoDienTong t = new GiaoDienTong(maNV);
				t.setVisible(true);
				setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, "Sai tên tài khoản hoặc mật khẩu ");
				pwfMatKhau.setText("");
			}
		} else if (o.equals(btnThoat)) {
			setVisible(false);
		}
	}
}
