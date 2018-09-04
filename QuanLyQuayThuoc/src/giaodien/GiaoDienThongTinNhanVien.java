package giaodien;


import java.awt.Color;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import control.ControlGiaoDien;
import control.KetNoiSQL;
import entity.NhanVien;
import giaodien.NhanVien.GiaoDienNhanVien;
import giaodien.QuanLy.GiaoDienQuanLy;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JFormattedTextField;

public class GiaoDienThongTinNhanVien extends JFrame {

	private MaskFormatter formattext,formattextcmnd;
	private JPanel panel;
	private JTextField txtHoTen;
	private JFormattedTextField txtNgaySinh;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JPasswordField txtMatKhau;
	private JTextField txtCMND;
	private JRadioButton rbtnNam, rbtnNu;
	GiaoDienDangNhap dn;
	ControlGiaoDien control = new ControlGiaoDien();
	public GiaoDienThongTinNhanVien()  {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GiaoDienThongTinNhanVien.class.getResource("/ser/user.png")));
		setResizable(false);
		setTitle(dn.txtTK.getText());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 200, 274, 396);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		try {
			formattext = new MaskFormatter("####-##-##");
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		txtCMND = new JTextField();
		txtCMND.setToolTipText("9 hoặc 12 chữ số");
		txtCMND.setEnabled(false);
		txtCMND.setBounds(76, 164, 175, 20);
		panel.add(txtCMND);
		txtCMND.setColumns(10);

		
		JButton btnOk = new JButton("OK");
		btnOk.setMnemonic(KeyEvent.VK_ENTER);
		btnOk.setToolTipText("Enter để lưu");
		btnOk.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOk.setIcon(new ImageIcon(GiaoDienThongTinNhanVien.class.getResource("/ser/save.png")));
		btnOk.setBounds(139, 322, 80, 23);
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				luu();
			}
		});
		panel.add(btnOk);
		
		JButton btnDoiMK = new JButton(" ");
		btnDoiMK.setOpaque(false);
		btnDoiMK.setVerifyInputWhenFocusTarget(false);
		btnDoiMK.setToolTipText("Thay đổi");
		btnDoiMK.setFocusPainted(false);
		
		btnDoiMK.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDoiMK.setIcon(new ImageIcon(GiaoDienThongTinNhanVien.class.getResource("/ser/edit.png")));
		btnDoiMK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new GiaoDienDoiMatKhau().setVisible(true);
			}
		});
		btnDoiMK.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnDoiMK.setBounds(223, 288, 28, 23);
		btnDoiMK.setBackground(Color.decode("#EEEEEE"));
	      btnDoiMK.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyPressed(KeyEvent e) {
	                if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
	                {
	                    dispose();
	                }
	            }
	        });
		panel.add(btnDoiMK);

		txtHoTen = new JTextField();
		txtHoTen.setEnabled(false);
		txtHoTen.setEditable(false);
		txtHoTen.setBounds(76, 139, 175, 20);
		panel.add(txtHoTen);
		txtHoTen.setColumns(10);

		JLabel lblHoTen = new JLabel("Họ Tên: ");
		lblHoTen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHoTen.setBounds(10, 142, 46, 14);
		panel.add(lblHoTen);

		JLabel lblNgaySinh = new JLabel("Ngày Sinh:");
		lblNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNgaySinh.setBounds(10, 192, 65, 14);
		panel.add(lblNgaySinh);

		JLabel lblGioiTinh = new JLabel("Giới Tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGioiTinh.setBounds(10, 217, 65, 14);
		panel.add(lblGioiTinh);

		JLabel lblSDT = new JLabel("SDT:");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSDT.setBounds(10, 242, 46, 14);
		panel.add(lblSDT);

		txtNgaySinh = new JFormattedTextField(formattext);
		txtNgaySinh.setToolTipText("Nhập theo yyyy-mm-dd");
		txtNgaySinh.setEnabled(false);
		txtNgaySinh.setBounds(76, 189, 175, 20);
		panel.add(txtNgaySinh);
		txtNgaySinh.setColumns(10);

		txtSDT = new JTextField();
		txtSDT.setToolTipText("10 hoặc 11 chữ số");
		txtSDT.setEnabled(false);
		txtSDT.setBounds(76, 239, 175, 20);
		panel.add(txtSDT);
		txtSDT.setColumns(10);

		JLabel lblDiaChi = new JLabel("Địa Chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDiaChi.setBounds(10, 267, 46, 14);
		panel.add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setEnabled(false);
		txtDiaChi.setBounds(77, 264, 174, 20);
		panel.add(txtDiaChi);
		txtDiaChi.setColumns(10);

		JLabel lblMatKhau = new JLabel("Mật Khẩu:");
		lblMatKhau.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMatKhau.setBounds(10, 292, 65, 14);
		panel.add(lblMatKhau);

		txtMatKhau = new JPasswordField();
		txtMatKhau.setEnabled(false);
		txtMatKhau.setBounds(77, 289, 136, 20);
		panel.add(txtMatKhau);
		txtMatKhau.setColumns(10);

		JLabel lblCMND = new JLabel("CMND:");
		lblCMND.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCMND.setBounds(10, 167, 46, 14);
		panel.add(lblCMND);




		rbtnNam = new JRadioButton("Nam");
		rbtnNam.setBounds(86, 213, 73, 23);
		rbtnNam.setEnabled(false);
		panel.add(rbtnNam);

		rbtnNu = new JRadioButton("Nữ");
		rbtnNu.setBounds(192, 213, 62, 23);
		rbtnNu.setEnabled(false);
		panel.add(rbtnNu);


		ButtonGroup gr = new ButtonGroup();
		gr.add(rbtnNam);
		gr.add(rbtnNu);
		
		//-----------------phím tắt---------------------------
				//-----------nút ok--------------------
		txtSDT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnOk.doClick();
				}
			}
		});
		txtDiaChi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnOk.doClick();
				}
			}
		});
		txtHoTen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnOk.doClick();
				}
			}
		});
		txtCMND.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnOk.doClick();
				}
			}
		});
		txtNgaySinh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnOk.doClick();
				}
			}
		});
		rbtnNu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnOk.doClick();
				}
			}
		});
		rbtnNam.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnOk.doClick();
				}
			}
		});
				//-------------------Thoát--------------
		
		txtSDT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
				{
					dispose();
				}
			}
		});
		txtHoTen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
				{
					dispose();
				}
			}
		});
		txtNgaySinh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
				{
					dispose();
				}
			}
		});
		txtDiaChi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
				{
					dispose();
				}
			}
		});
		txtCMND.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
				{
					dispose();
				}
			}
		});
		rbtnNam.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
				{
					dispose();
				}
			}
		});
		rbtnNu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
				{
					dispose();
				}
			}
		});

			
		//-----------------------------------------------------------
		

		JButton btnSua = new JButton("Sửa");
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSua.setIcon(new ImageIcon(GiaoDienThongTinNhanVien.class.getResource("/ser/edit.png")));
		btnSua.setBounds(56, 322, 80, 23);
		panel.add(btnSua);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(GiaoDienThongTinNhanVien.class.getResource("/ser/ava.png")));
		//lblNewLabel.setIcon(new ImageIcon(GiaoDienThongTinNhanVien.class.getResource("/ser/ava.png")));
		lblNewLabel.setBounds(76, 25, 136, 104);
		panel.add(lblNewLabel);

		JLabel lblnh = new JLabel("Ảnh:");
		lblnh.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblnh.setBounds(131, 11, 28, 14);
		panel.add(lblnh);
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				khoaMoCacTruong(true);
			}
		});

		try {
			dienDuLieuVaoForm();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public  void dienDuLieuVaoForm() throws SQLException
	{
		String maNVa = dn.txtTK.getText();
		Connection con =  KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="select * from NhanVien "+"where MaNV=?";
			PreparedStatement pretamt = con.prepareStatement(sql);
			pretamt.setString(1, maNVa);
			ResultSet rs = pretamt.executeQuery();
			while(rs.next())
			{
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				String gioiTinh= rs.getString(3);
				String ngaySinh = rs.getString(4);
				String sDT = rs.getString(5);
				String diaChi = rs.getString(6);
				String mk = rs.getString(7);
				String cmnd = rs.getString(8);

				txtCMND.setText(cmnd);
				txtNgaySinh.setText(ngaySinh);
				txtSDT.setText(sDT);
				txtMatKhau.setText(mk);
				txtDiaChi.setText(diaChi);
				txtHoTen.setText(ten);
				if (gioiTinh.equals("Nam"))
					rbtnNam.setSelected(true);
				if (gioiTinh.equals("Nữ"))
					rbtnNu.setSelected(true);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.close();
		}
	}
	
	public void khoaMoCacTruong(boolean a)
	{
		txtHoTen.setEditable(a);
		txtHoTen.setEnabled(a);
		txtCMND.setEditable(a);
		txtCMND.setEnabled(a);
		txtNgaySinh.setEditable(a);
		txtNgaySinh.setEnabled(a);
		txtDiaChi.setEditable(a);
		txtDiaChi.setEnabled(a);
		txtSDT.setEditable(a);
		txtSDT.setEnabled(a);
		rbtnNam.setEnabled(a);
		rbtnNu.setEnabled(a);
	}
    public void luu() {
    	String diaChiMoi = txtDiaChi.getText()+"";
		String sdtMoi = txtSDT.getText()+"";
		String maNV = dn.txtTK.getText()+"";
		String hoTen = txtHoTen.getText()+"";
		String cMND = txtCMND.getText()+"";
		String ngaySinh = txtNgaySinh.getText()+"";
		String gioiTinh = "Nữ";
		if(rbtnNam.isSelected())
			gioiTinh= "Nam";
		int sokituCMND = control.demSoKiTuSo1Chuoi(cMND);
		int sokituSDT = control.demSoKiTuSo1Chuoi(sdtMoi);
		if ((control.kiemTraCMND(cMND)==true) && (control.kiemTraSDT(sdtMoi)==true)) {
			try {
				control.suaDuLieuNVTrongSQL(maNV, ngaySinh, sdtMoi, diaChiMoi, cMND, gioiTinh);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			khoaMoCacTruong(false);
			dispose();
		}
		else {
			if (control.kiemTraCMND(cMND)==false)
				JOptionPane.showMessageDialog(panel, "Số CMND ko hợp lệ !! CMND phải có 12 hoặc 8 CHỮ SỐ");
			if (control.kiemTraSDT(sdtMoi)==false)
				JOptionPane.showMessageDialog(panel, "SĐT ko hợp lệ !! SĐT phải có 10 hoặc 11 CHỮ SỐ");
		}

    }
}
