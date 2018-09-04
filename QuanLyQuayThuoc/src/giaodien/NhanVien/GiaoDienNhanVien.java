package giaodien.NhanVien;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.ControlGiaoDien;
import control.DanhSachDuLieu;
import entity.CTHoaDonBan;
import entity.HoaDonBanHang;
import entity.KhachHang;
import entity.ThongTinThuoc;
import giaodien.GiaoDienDangNhap;
import giaodien.GiaoDienThongTinNhanVien;
import giaodien.QuanLy.GiaoDienQuanLy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.AllPermission;
import java.security.acl.Group;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.awt.ScrollPane;
import java.awt.Panel;
import javax.swing.ScrollPaneConstants;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.Label;
import javax.swing.JLayeredPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;
import java.awt.TextArea;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import javax.swing.JRadioButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class GiaoDienNhanVien extends JFrame {

	private JPanel contentPane, panelTTKH,panelTrangChu;
	private JScrollPane scrollPaneDanhSachThuoc,scrollPaneDanhSachDon,scrollPaneCTHD,scrollPaneDoanhThu;
	private DefaultTableModel tablemodelThuoc,tablemodelDoanhThu,tablemodelCTHD,tablemodelDanhSachDon;
	JTable tableDanhSachThuoc,tableDanhSachDon,tableCTHD,tableDoanhThu ;
	ControlGiaoDien control = new ControlGiaoDien();
	DanhSachDuLieu ds = new DanhSachDuLieu();
	GiaoDienDangNhap dn;
	public String IDNhanVien=dn.txtTK.getText();
	private JTextField txtMaHD;
	private JTextField txtNgayLap;
	private JTextField txtNguoiLap_timkiem;
	private JTextField txtTongTien_timkiem;
	private JTextField txtTongDoanhThu;
	private JPanel panelCTHD,panelDSHD,panelDSThuoc,panelDoanhThu;
	private JRadioButtonMenuItem chude1,chude2,chude3,chude4,chude5,chude6,chude7,chude8,chude9,chude10;
	public JButton btnLapHoaDon;
	GiaoDienThongTinNhanVien thongtinnv = new GiaoDienThongTinNhanVien();
	GiaoDienLapHoaDon laphoadon ;
	private JTextField txtCMND_CTHD;
	private JTextField txtTen_CTHD;
	private JTextField txtSDT_CTHD;
	private JTextField txtNgaySinh_CTHD;
	private TextArea textAreaMoTa;
	private JLabel lblCMND,lblSDT,lblNgaySinh,lblTen;
	private JLabel lblNewLabel_8;
	private JRadioButton rdbtnHienHet_DSHD,rdbtnTheoThang_DSHD,rdbtnChinhXac_DSHD,rdbtnTheoNam_DSHD;
	private JRadioButton rdbtnHienhet_Doanhthu,rdbtnTheoThang_Doanhthu,rdbtnChinhXac_Doanhthu,rdbtnTheonam_Doanhthu;
	private ButtonGroup GroupTimdon,GroupTimdoanhthu;
	private DateFormat dateformat =new SimpleDateFormat("yyyy-MM-dd");
	private JDateChooser dateChooser_DSHD,dateChooser_DoanhThu;
	private JLabel label;
	private JPanel panelTim_DSDon;
	private JMonthChooser monthChooser_DSHD,monthChooser_DoanhThu;
	private JYearChooser  yearChooser_DSHD,yearChooser_DoanhThu;
	private JLabel lblNewLabel_10;
	private JPanel panel_DoanhThu;
	private JLabel lblNewLabel_12;
	private JMenu menu_1;
	private JMenuItem menuItem;
	private JMenuItem mntmLpn;
	private JMenuItem mntmDanhSchn;
	private JMenuItem mntmDsn;
	private JMenuItem mntmDoanhThu;
	private JButton btnTrangDangNhap,btnDanhSachThuoc,btnDsHD,btnDoanhThu;
	public GiaoDienNhanVien() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GiaoDienNhanVien.class.getResource("/ser/pill.png")));
		setTitle("Nhân Viên");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 794, 21);
		contentPane.add(menuBar);

		JMenu mnNewMenu = new JMenu("Hệ thống");
		menuBar.add(mnNewMenu);

		JMenuItem mntmngXut = new JMenuItem("Đăng xuất");
		mntmngXut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		mntmngXut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dispose();
					new GiaoDienDangNhap().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JMenu menu = new JMenu("Đổi giao diện");
		mnNewMenu.add(menu);

		chude1 = new JRadioButtonMenuItem("McWin",true);
		chude1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doiGiaoDien("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			}
		});
		menu.add(chude1);

		chude2 = new JRadioButtonMenuItem("Luna");
		chude2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doiGiaoDien("com.jtattoo.plaf.luna.LunaLookAndFeel");
			}
		});
		menu.add(chude2);

		chude3 = new JRadioButtonMenuItem("Areo");
		chude3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doiGiaoDien("com.jtattoo.plaf.aero.AeroLookAndFeel");
			}
		});
		menu.add(chude3);

		chude4 = new JRadioButtonMenuItem("Texture");
		chude4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doiGiaoDien("com.jtattoo.plaf.texture.TextureLookAndFeel");
			}
		});
		menu.add(chude4);

		chude5 = new JRadioButtonMenuItem("aluminium");
		chude5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doiGiaoDien("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
			}
		});
		menu.add(chude5);

		chude6 = new JRadioButtonMenuItem("NimBus");
		chude6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doiGiaoDien("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
			}
		});
		menu.add(chude6);

		chude7 = new JRadioButtonMenuItem("Bernstein");
		chude7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doiGiaoDien("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
			}
		});
		menu.add(chude7);

		chude8 = new JRadioButtonMenuItem("Fast");
		chude8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doiGiaoDien("com.jtattoo.plaf.fast.FastLookAndFeel");
			}
		});
		menu.add(chude8);

		chude9 = new JRadioButtonMenuItem("Graphite");
		chude9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doiGiaoDien("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
			}
		});
		menu.add(chude9);

		chude10 = new JRadioButtonMenuItem("Mint");
		chude10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doiGiaoDien("com.jtattoo.plaf.mint.MintLookAndFeel");
			}
		});
		menu.add(chude10);
		mnNewMenu.add(mntmngXut);

		JMenuItem mntmNewMenuItem = new JMenuItem("Thoát");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		menu_1 = new JMenu("Chức năng");
		menuBar.add(menu_1);
		
		menuItem = new JMenuItem("Trang chủ");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnTrangDangNhap.doClick();
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_MASK));
		menu_1.add(menuItem);
		
		mntmLpn = new JMenuItem("Lập đơn");
		mntmLpn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnLapHoaDon.doClick();
			}
		});
		mntmLpn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_MASK));
		menu_1.add(mntmLpn);
		
		mntmDanhSchn = new JMenuItem("DS Thuốc");
		mntmDanhSchn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnDanhSachThuoc.doClick();
			}
		});
		mntmDanhSchn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_MASK));
		menu_1.add(mntmDanhSchn);
		
		mntmDsn = new JMenuItem("DS đơn");
		mntmDsn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnDsHD.doClick();
			}
		});
		mntmDsn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.CTRL_MASK));
		menu_1.add(mntmDsn);
		
		mntmDoanhThu = new JMenuItem("Doanh thu");
		mntmDoanhThu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnDoanhThu.doClick();
			}
		});
		mntmDoanhThu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, InputEvent.CTRL_MASK));
		menu_1.add(mntmDoanhThu);

		JMenu menuTroGiup = new JMenu("Trợ giúp");
		menuBar.add(menuTroGiup);

		ButtonGroup groupChuDe = new ButtonGroup();
		groupChuDe.add(chude1);
		groupChuDe.add(chude2);
		groupChuDe.add(chude3);
		groupChuDe.add(chude4);
		groupChuDe.add(chude5);
		groupChuDe.add(chude6);
		groupChuDe.add(chude7);
		groupChuDe.add(chude8);
		groupChuDe.add(chude9);
		groupChuDe.add(chude10);

		//----------------------------toolbar---------------------------------------
		ButtonGroup groupToolBar =new ButtonGroup();
		JPanel ThanhToolBar = new JPanel();
		ThanhToolBar.setOpaque(false);
		ThanhToolBar.setBounds(0, 24, 522, 80);
		contentPane.add(ThanhToolBar);
		ThanhToolBar.setLayout(new FlowLayout(FlowLayout.LEFT, 0,0));

		JToolBar toolBar = new JToolBar();
		toolBar.setMinimumSize(new Dimension(0, 0));
		ThanhToolBar.add(toolBar);
		toolBar.setVerifyInputWhenFocusTarget(false);

		btnTrangDangNhap = new JButton("Trang chủ");
		btnTrangDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelCTHD.setVisible(false);
				panelDoanhThu.setVisible(false);
				panelDSHD.setVisible(false);
				panelDSThuoc.setVisible(false);
				panelTrangChu.setVisible(true);
			}
		});
		btnTrangDangNhap.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnTrangDangNhap.setHorizontalTextPosition(SwingConstants.CENTER);
		btnTrangDangNhap.setVerticalAlignment(SwingConstants.TOP);
		btnTrangDangNhap.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnTrangDangNhap.setIcon(new ImageIcon(GiaoDienQuanLy.class.getResource("/ser/home.png")));
		btnTrangDangNhap.setPreferredSize(new Dimension(100, 75));
		btnTrangDangNhap.setMaximumSize(new Dimension(100, 100));
		toolBar.add(btnTrangDangNhap);
		groupToolBar.add(btnTrangDangNhap);

		btnLapHoaDon =new JButton("Lập hóa đơn");
		btnLapHoaDon.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLapHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GiaoDienLapHoaDon().setVisible(true);       
			}
		});
		toolBar.add(btnLapHoaDon);
		btnLapHoaDon.setIcon(new ImageIcon(GiaoDienNhanVien.class.getResource("/ser/bill.png")));
		btnLapHoaDon.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnLapHoaDon.setVerticalAlignment(SwingConstants.TOP);
		btnLapHoaDon.setPreferredSize(new Dimension(100, 75));
		btnLapHoaDon.setMaximumSize(new Dimension(100, 100));
		btnLapHoaDon.setHorizontalTextPosition(SwingConstants.CENTER);

		btnDanhSachThuoc = new JButton("DS thuốc");
		btnDanhSachThuoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCTHD.setVisible(false);
				panelTrangChu.setVisible(false);
				panelDoanhThu.setVisible(false);
				panelDSHD.setVisible(false);
				panelDSThuoc.setVisible(true);
				for(int i = tablemodelThuoc.getRowCount()-1;i>=0;i--)
					tablemodelThuoc.removeRow(i);
				try {
					ds.docThuoc();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (ThongTinThuoc thuoc : ds.listThuoc) 
				{
					Object[] row = {thuoc.getMaThuoc(),thuoc.getTenThuoc(),thuoc.getLoai(),thuoc.getNcc(),thuoc.getSoLuong(),thuoc.getGiaBan()};
					tablemodelThuoc.addRow(row);
				}
			}
		});
		groupToolBar.add(btnDanhSachThuoc);
		btnDanhSachThuoc.setIcon(new ImageIcon(GiaoDienNhanVien.class.getResource("/ser/death_list.png")));
		btnDanhSachThuoc.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDanhSachThuoc.setVerticalAlignment(SwingConstants.TOP);
		btnDanhSachThuoc.setPreferredSize(new Dimension(100, 75));
		btnDanhSachThuoc.setMaximumSize(new Dimension(100, 100));
		btnDanhSachThuoc.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDanhSachThuoc.setFont(new Font("Tahoma", Font.BOLD, 11));
		toolBar.add(btnDanhSachThuoc);

		btnDsHD = new JButton("DS hóa đơn");
		btnDsHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tablemodelCTHD.getDataVector().removeAllElements();
				tablemodelCTHD.fireTableDataChanged();
				panelCTHD.setVisible(false);
				panelDSThuoc.setVisible(false);
				panelDoanhThu.setVisible(false);
				panelTrangChu.setVisible(false);
				xoaDuLieuTrongTable();
				panelDSHD.setVisible(true);

				try {
					ds.docBangHDB();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (HoaDonBanHang hd : ds.listHDB)
				{
					if (hd.getMaNVLap().equalsIgnoreCase(IDNhanVien))
					{
						Object[] row = {hd.getMaHD(),hd.getMaNVLap(),hd.getNgayLap(),hd.getMaKH(),hd.getTongTien()};
						tablemodelDanhSachDon.addRow(row);
					}   
				}
			}
		});
		groupToolBar.add(btnDsHD);
		btnDsHD.setIcon(new ImageIcon(GiaoDienNhanVien.class.getResource("/ser/list.png")));
		btnDsHD.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDsHD.setVerticalAlignment(SwingConstants.TOP);
		btnDsHD.setPreferredSize(new Dimension(100, 75));
		btnDsHD.setMaximumSize(new Dimension(100, 100));
		btnDsHD.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDsHD.setFont(new Font("Tahoma", Font.BOLD, 11));
		toolBar.add(btnDsHD);

		btnDoanhThu = new JButton("Doanh thu");
		btnDoanhThu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCTHD.setVisible(false);
				panelDSThuoc.setVisible(false);
				panelDSHD.setVisible(false);
				panelTrangChu.setVisible(false);
				panelDoanhThu.setVisible(true);
				xoaDuLieuTrongTable();
				try {
					ds.docBangHDB();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (HoaDonBanHang hd : ds.listHDB) {
					if (hd.getMaNVLap().equalsIgnoreCase(IDNhanVien)) {
						Object[] row = {hd.getMaHD(),hd.getNgayLap(),hd.getMaNVLap(),hd.getTongTien()};
						tablemodelDoanhThu.addRow(row);
					}
				}
				txtTongDoanhThu.setText(control.tongDoanhThu(tablemodelDoanhThu,3)+"");
			}
		});
		groupToolBar.add(btnDoanhThu);
		btnDoanhThu.setIcon(new ImageIcon(GiaoDienNhanVien.class.getResource("/ser/report.png")));
		btnDoanhThu.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDoanhThu.setVerticalAlignment(SwingConstants.TOP);
		btnDoanhThu.setPreferredSize(new Dimension(100, 75));
		btnDoanhThu.setMaximumSize(new Dimension(100, 100));
		btnDoanhThu.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 11));
		toolBar.add(btnDoanhThu);
		//-----------------------------------------header table---------------------

		String[] headerDanhSachDon="Mã hóa đơn;Người lập;Ngày lập;Khách hàng;Tổng tiền".split(";");
		tablemodelDanhSachDon = new DefaultTableModel(headerDanhSachDon,0){ 
			@Override//Override lại phương thức isCellEditable 
			public boolean isCellEditable(int row, int column) { 
				return false;//Trả về false không cho edit. 
			} 
		}; 

		String[] headerCTHD="Tên thuốc;Số lượng;Giá bán".split(";");
		tablemodelCTHD = new DefaultTableModel(headerCTHD,0){ 
			@Override//Override lại phương thức isCellEditable 
			public boolean isCellEditable(int row, int column) { 
				return false;//Trả về false không cho edit. 
			} 
		}; 

		String[] headerDoanhThu="Mã đơn;Ngày lập;Người Lập;Tổng tiền".split(";");
		tablemodelDoanhThu = new DefaultTableModel(headerDoanhThu,0){ 
			@Override//Override lại phương thức isCellEditable 
			public boolean isCellEditable(int row, int column) { 
				return false;//Trả về false không cho edit. 
			} 
		}; 


		String[] header="Mã thuốc;Tên thuốc;Loại thuốc;Nhà cung cấp;Số lượng còn;Giá bán".split(";");
		tablemodelThuoc = new DefaultTableModel(header,0){ 
			@Override//Override lại phương thức isCellEditable 
			public boolean isCellEditable(int row, int column) { 
				return false;//Trả về false không cho edit. 
			} 
		}; 

		JPanel panelthongTin = new JPanel();
		panelthongTin.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Th\u00F4ng tin nh\u00E2n vi\u00EAn:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(233, 150, 122)));
		panelthongTin.setBounds(571, 24, 223, 80);
		contentPane.add(panelthongTin);
		panelthongTin.setLayout(null);

		label = new JLabel(dn.txtTK.getText()+"");
		label.setForeground(Color.BLUE);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				label.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				thongtinnv.setVisible(true);
			}
		});
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(59, 20, 78, 15);
		panelthongTin.add(label);

		JLabel lblNewLabel_1 = new JLabel("ID :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 20, 46, 14);
		panelthongTin.add(lblNewLabel_1);

		JLabel lblNewLabel_9 = new JLabel("Tên :");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_9.setBounds(10, 40, 46, 14);
		panelthongTin.add(lblNewLabel_9);

		JLabel lblTenNV = new JLabel("New label");
		lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTenNV.setBounds(59, 40, 154, 14);
		panelthongTin.add(lblTenNV);

		JLabel lblNewLabel_11 = new JLabel("Số ĐT:");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_11.setBounds(10, 60, 46, 14);
		panelthongTin.add(lblNewLabel_11);

		JLabel lbSoDT = new JLabel("New label");
		lbSoDT.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbSoDT.setBounds(59, 60, 154, 14);
		panelthongTin.add(lbSoDT);

		JLabel lblDangXuat = new JLabel("Đăng xuất");
		lblDangXuat.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDangXuat.setForeground(Color.BLUE);
		lblDangXuat.setBounds(147, 20, 76, 14);
		lblDangXuat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblDangXuat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblDangXuat.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					new GiaoDienDangNhap().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		panelthongTin.add(lblDangXuat);

		JLabel lblbanquyen = new JLabel("Made by cCc ");
		lblbanquyen.setBounds(696, 546, 88, 14);
		contentPane.add(lblbanquyen);
		lblbanquyen.setForeground(Color.LIGHT_GRAY);
		lblbanquyen.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		//----------------------------đa layer---------------------------------
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 115, 794, 456);
		contentPane.add(layeredPane);
		panelTrangChu = new JPanel();
		layeredPane.setLayer(panelTrangChu, 1);
		panelTrangChu.setForeground(new Color(173, 255, 47));
		panelTrangChu.setBounds(0, 11, 794, 445);
		layeredPane.add(panelTrangChu);
		panelTrangChu.setLayout(null);

		JPanel panel = new JPanel();
		panel.setEnabled(false);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		panel.setBounds(395, 11, 352, 190);
		panelTrangChu.add(panel);
		panel.setLayout(null);

		JTextArea txtrthngTinH = new JTextArea();
		txtrthngTinH.setOpaque(false);
		txtrthngTinH.setVerifyInputWhenFocusTarget(false);
		txtrthngTinH.setFocusable(false);
		txtrthngTinH.setFocusTraversalKeysEnabled(false);
		txtrthngTinH.setFocusTraversalPolicyProvider(true);
		txtrthngTinH.setBounds(10, 11, 339, 164);
		panel.add(txtrthngTinH);
		txtrthngTinH.setLineWrap(true);
		txtrthngTinH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtrthngTinH.setEditable(false);
		txtrthngTinH.setForeground(new Color(106, 90, 205));
		txtrthngTinH.setText("\t------THÔNG TIN-------\r\n    Hệ thống quản lý quầy thuốc của bệnh viện\r\n Thiết kế bởi:\r\n\t+ Trần Đình Chiến\t15091761\r\n\t+ Trần Hùng Cường\t15056921\r\n\t+ Nguyễn Văn Mạnh Cường\t15051431\r\n GV: Võ Thị Thanh Vân\r\n Lớp: DHKTPM11A\r\n Môn: Phát triển ứng dụng\r\n Khoa Công nghệ thông tin - Đại học Công Nghiệp TPHCM");
		txtrthngTinH.setBackground(new Color(0, 0, 0,0));
		JLabel baner = new JLabel("");
		baner.setIcon(new ImageIcon(GiaoDienNhanVien.class.getResource("/ser/baner2222.png")));
		baner.setBounds(0, 223, 794, 222);
		panelTrangChu.add(baner);

		lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(GiaoDienNhanVien.class.getResource("/ser/paramedic_spinning_st_a_ha.gif")));
		lblNewLabel_8.setBounds(10, 0, 365, 233);
		panelTrangChu.add(lblNewLabel_8);

		panelCTHD = new JPanel();
		panelCTHD.setBounds(10, 11, 774, 408);
		layeredPane.add(panelCTHD);
		panelCTHD.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Chi ti\u1EBFt h\u00F3a \u0111\u01A1n: ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		panelCTHD.setForeground(new Color(0, 255, 255));
		layeredPane.setLayer(panelCTHD, 0);
		panelCTHD.setLayout(null);

		JLabel lblMa = new JLabel("Mã:");
		lblMa.setBounds(31, 34, 36, 14);
		lblMa.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelCTHD.add(lblMa);
		txtMaHD = new JTextField();
		txtMaHD.setBounds(59, 31, 67, 20);
		txtMaHD.setEditable(false);
		txtMaHD.setEnabled(false);
		panelCTHD.add(txtMaHD);
		txtMaHD.setColumns(10);

		JLabel lblNgay = new JLabel("Ngày:");
		lblNgay.setBounds(136, 34, 52, 14);
		lblNgay.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelCTHD.add(lblNgay);

		txtNgayLap = new JTextField();
		txtNgayLap.setBounds(178, 31, 108, 20);
		txtNgayLap.setEnabled(false);
		txtNgayLap.setEditable(false);
		panelCTHD.add(txtNgayLap);
		txtNgayLap.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Người lập:");
		lblNewLabel_3.setBounds(307, 34, 67, 14);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelCTHD.add(lblNewLabel_3);

		txtNguoiLap_timkiem = new JTextField();
		txtNguoiLap_timkiem.setBounds(373, 31, 86, 20);
		txtNguoiLap_timkiem.setText(IDNhanVien);
		txtNguoiLap_timkiem.setEditable(false);
		txtNguoiLap_timkiem.setEnabled(false);
		panelCTHD.add(txtNguoiLap_timkiem);
		txtNguoiLap_timkiem.setColumns(10);

		txtTongTien_timkiem = new JTextField();
		txtTongTien_timkiem.setBounds(346, 59, 113, 20);
		txtTongTien_timkiem.setEditable(false);
		txtTongTien_timkiem.setEnabled(false);
		panelCTHD.add(txtTongTien_timkiem);
		txtTongTien_timkiem.setColumns(10);

		JLabel lblNewLabel = new JLabel("Tổng tiền hóa đơn: ");
		lblNewLabel.setBounds(216, 62, 121, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelCTHD.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("Danh sách thuốc bán:");
		lblNewLabel_2.setBounds(25, 65, 136, 14);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelCTHD.add(lblNewLabel_2);
		panelCTHD.add(scrollPaneCTHD = new JScrollPane(tableCTHD = new JTable(tablemodelCTHD)));
		tableCTHD.setForeground(new Color(210, 105, 30));

		JButton btnBack2 = new JButton("Quay lại");
		btnBack2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//------2 dòng này để xóa hết bảng cũ và hiện cái mới lên, ko có cái này thông tin chồng lên nhau
				tablemodelCTHD.getDataVector().removeAllElements();
				tablemodelCTHD.fireTableDataChanged();
				//-----------------
				panelDSThuoc.setVisible(false);
				panelDoanhThu.setVisible(false);
				panelCTHD.setVisible(false);
				panelDSHD.setVisible(true);
			}
		});
		btnBack2.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBack2.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnBack2.setVerticalAlignment(SwingConstants.TOP);
		btnBack2.setIcon(new ImageIcon(GiaoDienNhanVien.class.getResource("/ser/back4848.png")));
		btnBack2.setBounds(469, 11, 77, 75);
		panelCTHD.add(btnBack2);

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(GiaoDienNhanVien.class.getResource("/ser/banner3.png")));
		lblNewLabel_5.setBounds(9, 293, 757, 110);
		panelCTHD.add(lblNewLabel_5);

		panelTTKH = new JPanel();
		panelTTKH.setEnabled(false);
		panelTTKH.setBorder(new TitledBorder(null, "Th\u00F4ng tin kh\u00E1ch h\u00E0ng:", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panelTTKH.setBounds(552, 11, 214, 271);
		panelCTHD.add(panelTTKH);
		panelTTKH.setLayout(null);

		lblCMND = new JLabel("CMND:");
		lblCMND.setEnabled(false);
		lblCMND.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCMND.setBounds(10, 28, 46, 14);
		panelTTKH.add(lblCMND);

		txtCMND_CTHD = new JTextField();
		txtCMND_CTHD.setEditable(false);
		txtCMND_CTHD.setEnabled(false);
		txtCMND_CTHD.setBounds(74, 25, 130, 20);
		panelTTKH.add(txtCMND_CTHD);
		txtCMND_CTHD.setColumns(10);

		lblTen = new JLabel("Tên: ");
		lblTen.setEnabled(false);
		lblTen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTen.setBounds(10, 59, 46, 14);
		panelTTKH.add(lblTen);

		txtTen_CTHD = new JTextField();
		txtTen_CTHD.setEnabled(false);
		txtTen_CTHD.setEditable(false);
		txtTen_CTHD.setBounds(74, 56, 130, 20);
		panelTTKH.add(txtTen_CTHD);
		txtTen_CTHD.setColumns(10);

		lblSDT = new JLabel("SDT:");
		lblSDT.setEnabled(false);
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSDT.setBounds(10, 90, 46, 14);
		panelTTKH.add(lblSDT);

		txtSDT_CTHD = new JTextField();
		txtSDT_CTHD.setEditable(false);
		txtSDT_CTHD.setEnabled(false);
		txtSDT_CTHD.setBounds(74, 87, 130, 20);
		panelTTKH.add(txtSDT_CTHD);
		txtSDT_CTHD.setColumns(10);

		lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setEnabled(false);
		lblNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNgaySinh.setBounds(10, 121, 57, 14);
		panelTTKH.add(lblNgaySinh);

		txtNgaySinh_CTHD = new JTextField();
		txtNgaySinh_CTHD.setEditable(false);
		txtNgaySinh_CTHD.setEnabled(false);
		txtNgaySinh_CTHD.setBounds(74, 118, 130, 20);
		panelTTKH.add(txtNgaySinh_CTHD);
		txtNgaySinh_CTHD.setColumns(10);

		textAreaMoTa = new TextArea();
		textAreaMoTa.setEditable(false);
		textAreaMoTa.setEnabled(false);
		textAreaMoTa.setBounds(10, 144, 194, 117);
		panelTTKH.add(textAreaMoTa);
		scrollPaneCTHD.setBounds(25, 90, 524, 192);
		panelDSThuoc = new JPanel();
		panelDSThuoc.setBorder(new TitledBorder(null, "Danh s\u00E1ch thu\u1ED1c:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		panelDSThuoc.setBounds(10, 11, 774, 408);
		layeredPane.add(panelDSThuoc);
		panelDSThuoc.add(scrollPaneDanhSachThuoc = new JScrollPane(tableDanhSachThuoc= new JTable(tablemodelThuoc)));
		tableDanhSachThuoc.setForeground(new Color(148, 0, 211));
		scrollPaneDanhSachThuoc.setPreferredSize(new Dimension(750, 380));

		panelDSHD = new JPanel();
		panelDSHD.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Danh s\u00E1ch h\u00F3a \u0111\u01A1n: ", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 255)));
		layeredPane.setLayer(panelDSHD, 0);
		panelDSHD.setBounds(10, 11, 774, 408);
		layeredPane.add(panelDSHD);
		panelDSHD.setLayout(null);

		JLabel lblNgayLap_timkiem = new JLabel("Tìm theo thời gian lập:");
		lblNgayLap_timkiem.setBounds(22, 18, 141, 14);
		panelDSHD.add(lblNgayLap_timkiem);
		lblNgayLap_timkiem.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton btnXemChiTiet = new JButton("Xem chi tiết");
		btnXemChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xemChiTietHD();
			}
		});
		btnXemChiTiet.setBounds(156, 372, 125, 25);
		panelDSHD.add(btnXemChiTiet);

		btnXemChiTiet.setIcon(new ImageIcon(GiaoDienNhanVien.class.getResource("/ser/more_01.png")));
		btnXemChiTiet.setFont(new Font("Tahoma", Font.BOLD, 12));

		JButton btnTim_DSHoaDon = new JButton("Tìm");
		btnTim_DSHoaDon.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnTim_DSHoaDon.setHorizontalTextPosition(SwingConstants.CENTER);
		btnTim_DSHoaDon.setBounds(386, 18, 68, 57);
		panelDSHD.add(btnTim_DSHoaDon);
		btnTim_DSHoaDon.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnTim_DSHoaDon.setIcon(new ImageIcon(GiaoDienNhanVien.class.getResource("/ser/search.png")));
		btnTim_DSHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiemDSHD();
			}   
		});

		panelDSHD.add(scrollPaneDanhSachDon = new JScrollPane(tableDanhSachDon = new JTable(tablemodelDanhSachDon)));
		tableDanhSachDon.setForeground(new Color(165, 42, 42));
		tableDanhSachDon.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				int row = tableDanhSachDon.getSelectedRow();
			}
		});

		JLabel lblCcHan = new JLabel("Các hóa đơn đã lập:");
		lblCcHan.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCcHan.setBounds(41, 79, 176, 14);
		panelDSHD.add(lblCcHan);

		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setToolTipText("Uống thuốc đi bạn ơi");
		lblNewLabel_7.setIcon(new ImageIcon(GiaoDienNhanVien.class.getResource("/ser/doctor_doing_pill_end_a_ha.gif")));
		lblNewLabel_7.setBounds(471, 31, 280, 350);
		panelDSHD.add(lblNewLabel_7);


		GroupTimdon = new ButtonGroup();
		rdbtnHienHet_DSHD = new JRadioButton("Hiện hết đơn");
		rdbtnHienHet_DSHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoadJCalender_DSDon(true, false, false, false);
			}
		});
		rdbtnHienHet_DSHD.setSelected(true);
		rdbtnHienHet_DSHD.setBounds(191, 25, 101, 23);
		panelDSHD.add(rdbtnHienHet_DSHD);
		GroupTimdon.add(rdbtnHienHet_DSHD);

		rdbtnTheoThang_DSHD = new JRadioButton("Theo tháng");
		rdbtnTheoThang_DSHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoadJCalender_DSDon(false, false,true, false);
			}
		});
		rdbtnTheoThang_DSHD.setBounds(191, 49, 101, 23);
		panelDSHD.add(rdbtnTheoThang_DSHD);
		GroupTimdon.add(rdbtnTheoThang_DSHD);

		rdbtnChinhXac_DSHD = new JRadioButton("Chính xác");
		rdbtnChinhXac_DSHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoadJCalender_DSDon(false,true,false, false);
			}
		});
		rdbtnChinhXac_DSHD.setBounds(294, 25, 90, 23);
		panelDSHD.add(rdbtnChinhXac_DSHD);
		GroupTimdon.add( rdbtnChinhXac_DSHD);

		rdbtnTheoNam_DSHD = new JRadioButton("Theo năm");
		rdbtnTheoNam_DSHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoadJCalender_DSDon(false,false,false, true);
			}
		});
		rdbtnTheoNam_DSHD.setBounds(294, 49, 90, 23);
		panelDSHD.add(rdbtnTheoNam_DSHD);
		GroupTimdon.add(rdbtnTheoNam_DSHD);
		
		panelTim_DSDon = new JPanel();
		panelTim_DSDon.setBounds(10, 38, 153, 30);
		panelDSHD.add(panelTim_DSDon);
		panelTim_DSDon.setLayout(null);
		
		lblNewLabel_10 = new JLabel("Tìm hết các móc thời gian");
		lblNewLabel_10.setBounds(0, 0, 153, 30);
		panelTim_DSDon.add(lblNewLabel_10);

		scrollPaneDanhSachDon.setBounds(22, 99, 439, 262);
		panelDoanhThu = new JPanel();
		panelDoanhThu.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Th\u1ED1ng k\u00EA doanh thu:", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 255)));
		layeredPane.setLayer(panelDoanhThu, 0);
		panelDoanhThu.setBounds(10, 11, 774, 408);
		layeredPane.add(panelDoanhThu);
		panelDoanhThu.setLayout(null);

		JLabel lblChon = new JLabel("Chọn mốc thời gian:");
		lblChon.setBounds(10, 11, 110, 14);
		panelDoanhThu.add(lblChon);
		lblChon.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblNewLabel_4 = new JLabel("Tổng doanh thu:");
		lblNewLabel_4.setBounds(463, 368, 104, 15);
		panelDoanhThu.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtTongDoanhThu = new JTextField();
		txtTongDoanhThu.setBounds(572, 365, 195, 20);
		panelDoanhThu.add(txtTongDoanhThu);
		txtTongDoanhThu.setEnabled(false);
		txtTongDoanhThu.setEditable(false);
		txtTongDoanhThu.setColumns(10);
		JButton btntimDoanhThu = new JButton("Xem");
		btntimDoanhThu.setHorizontalTextPosition(SwingConstants.CENTER);
		btntimDoanhThu.setVerticalTextPosition(SwingConstants.BOTTOM);
		btntimDoanhThu.setBounds(378, 21, 75, 59);
		panelDoanhThu.add(btntimDoanhThu);
		btntimDoanhThu.setIcon(new ImageIcon(GiaoDienNhanVien.class.getResource("/ser/search.png")));
		btntimDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 11));
		btntimDoanhThu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				timKiem_DoanhThu();
			}   
		});
		panelDoanhThu.add(scrollPaneDoanhThu = new JScrollPane(tableDoanhThu = new JTable(tablemodelDoanhThu)));
		tableDoanhThu.setForeground(new Color(210, 105, 30));

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(GiaoDienNhanVien.class.getResource("/ser/doanhthu.png")));
		lblNewLabel_6.setBounds(463, 39, 301, 315);
		panelDoanhThu.add(lblNewLabel_6);
		scrollPaneDoanhThu.setBounds(10, 97, 443, 288);
		txtTongDoanhThu.setText(control.tongDoanhThu(tablemodelDoanhThu,3)+"");

		GroupTimdoanhthu=new ButtonGroup();

		rdbtnHienhet_Doanhthu = new JRadioButton("Hiện hết đơn");
		rdbtnHienhet_Doanhthu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadJCalender_Doanhthu(true, false, false, false);
			}
		});
		rdbtnHienhet_Doanhthu.setSelected(true);
		rdbtnHienhet_Doanhthu.setBounds(172, 21, 101, 23);
		panelDoanhThu.add(rdbtnHienhet_Doanhthu);
		GroupTimdoanhthu.add(rdbtnHienhet_Doanhthu);

		rdbtnTheoThang_Doanhthu = new JRadioButton("Theo tháng");
		rdbtnTheoThang_Doanhthu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadJCalender_Doanhthu(false, false,true, false);
			}
		});
		rdbtnTheoThang_Doanhthu.setBounds(172, 45, 101, 23);
		panelDoanhThu.add(rdbtnTheoThang_Doanhthu);
		GroupTimdoanhthu.add(rdbtnTheoThang_Doanhthu);

		rdbtnChinhXac_Doanhthu = new JRadioButton("Chính xác");
		rdbtnChinhXac_Doanhthu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadJCalender_Doanhthu(false, true,false, false);
			}
		});
		rdbtnChinhXac_Doanhthu.setBounds(275, 21, 90, 23);
		panelDoanhThu.add(rdbtnChinhXac_Doanhthu);
		GroupTimdoanhthu.add(rdbtnChinhXac_Doanhthu);

		rdbtnTheonam_Doanhthu = new JRadioButton("Theo năm");
		rdbtnTheonam_Doanhthu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadJCalender_Doanhthu(false,false,false, true);
			}
		});
		rdbtnTheonam_Doanhthu.setBounds(275, 45, 90, 23);
		panelDoanhThu.add(rdbtnTheonam_Doanhthu);
		GroupTimdoanhthu.add(rdbtnTheonam_Doanhthu);
		
		panel_DoanhThu = new JPanel();
		panel_DoanhThu.setBounds(10, 36, 152, 34);
		panelDoanhThu.add(panel_DoanhThu);
		panel_DoanhThu.setLayout(null);
		
		lblNewLabel_12 = new JLabel("Tìm hết các móc thời gian");
		lblNewLabel_12.setBounds(0, 0, 152, 34);
		panel_DoanhThu.add(lblNewLabel_12);
		try {
			ds.docNV();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		lblTenNV.setText(ds.timNVTheoMa(IDNhanVien).getHoTenNV());
		lbSoDT.setText(ds.timNVTheoMa(IDNhanVien).getSdt());
	}
	////////////////////////////////////////////
	//////////////////////
	void duaDuLieuTuListVaoTable()
	{
		try {
			ds.docThuoc();
			ds.docBangHDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ThongTinThuoc thuoc : ds.listThuoc) 
		{
			Object[] row = {thuoc.getMaThuoc(),thuoc.getTenThuoc(),thuoc.getLoai(),thuoc.getNcc(),thuoc.getSoLuong(),thuoc.getGiaBan()};
			tablemodelThuoc.addRow(row);
		}
		for (HoaDonBanHang hd : ds.listHDB)
		{
			if (hd.getMaNVLap().equalsIgnoreCase(IDNhanVien))
			{
				Object[] row = {hd.getMaHD(),hd.getMaNVLap(),hd.getNgayLap(),hd.getMaKH(),hd.getTongTien()};
				tablemodelDanhSachDon.addRow(row);
			}   
		}
		for (HoaDonBanHang hd : ds.listHDB) 
		{
			if (hd.getMaNVLap().equalsIgnoreCase(IDNhanVien)) {
				Object[] row = {hd.getMaHD(),hd.getNgayLap(),hd.getMaNVLap(),hd.getTongTien()};
				tablemodelDoanhThu.addRow(row);
			}
		}
	}

	void xoaDuLieuTrongTable()
	{
		for(int i = tablemodelThuoc.getRowCount()-1;i>=0;i--)
			tablemodelThuoc.removeRow(i);
		for(int i = tablemodelDanhSachDon.getRowCount()-1;i>=0;i--)
			tablemodelDanhSachDon.removeRow(i);
		for(int i = tablemodelDoanhThu.getRowCount()-1;i>=0;i--)
			tablemodelDoanhThu.removeRow(i);
	}
	public void doiGiaoDien(String chude)
	{
		try
		{
			UIManager.setLookAndFeel(chude);
			SwingUtilities.updateComponentTreeUI(GiaoDienNhanVien.this);
			control.LuuChuDe(chude);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void xemChiTietHD() {
		int row = tableDanhSachDon.getSelectedRow();
		if (row!=-1) {
			panelDSThuoc.setVisible(false);
			panelDoanhThu.setVisible(false);
			panelDSHD.setVisible(false);
			panelCTHD.setVisible(true);

			String maHD =(String) tableDanhSachDon.getValueAt(row, 0);
			String ngay =(String) tableDanhSachDon.getValueAt(row, 2);
			String tongTien = tableDanhSachDon.getValueAt(row, 4)+"";
			String CMND = tableDanhSachDon.getValueAt(row, 3)+"";
			try {
				ds.docBangCTHoaDonBan();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (CTHoaDonBan cthd : ds.listThuocBan)
			{
				if (cthd.getMaHD().equalsIgnoreCase(maHD)) {
					Object[] roww = {cthd.getTenThuoc(),cthd.getSoLuong(),cthd.getDonGia()};
					tablemodelCTHD.addRow(roww);
				}
			}
			txtMaHD.setText(maHD);
			txtNgayLap.setText(ngay);
			txtTongTien_timkiem.setText(tongTien);
			if (!CMND.equals("")) {
				panelTTKH.setEnabled(true);
				txtCMND_CTHD.setEnabled(true);
				txtTen_CTHD.setEnabled(true);
				txtSDT_CTHD.setEnabled(true);
				txtNgaySinh_CTHD.setEnabled(true);
				textAreaMoTa.setEnabled(true);
				lblCMND.setEnabled(true);
				lblSDT.setEnabled(true);
				lblTen.setEnabled(true);
				lblNgaySinh.setEnabled(true);
				try {
					ds.docBangKhachHang();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				KhachHang kh = new KhachHang();
				kh = ds.timKHTheoCMND(CMND);
				txtCMND_CTHD.setText(CMND);
				txtTen_CTHD.setText(kh.getHoTenKH());
				txtNgaySinh_CTHD.setText(kh.getNgaySinh());
				txtSDT_CTHD.setText(kh.getSdt());
				textAreaMoTa.setText(kh.getMoTaKH());
			}
			else
			{
				panelTTKH.setEnabled(false);
				txtCMND_CTHD.setEnabled(false);
				txtTen_CTHD.setEnabled(false);
				txtSDT_CTHD.setEnabled(false);
				txtNgaySinh_CTHD.setEnabled(false);
				textAreaMoTa.setEnabled(false);
				lblCMND.setEnabled(false);
				lblSDT.setEnabled(false);
				lblTen.setEnabled(false);
				lblNgaySinh.setEnabled(false);
				txtCMND_CTHD.setText("");
				txtTen_CTHD.setText("");
				txtNgaySinh_CTHD.setText("");
				txtSDT_CTHD.setText("");
				textAreaMoTa.setText("");
			}
		}
		else {
			JOptionPane.showMessageDialog(panelDSHD, "Vui lòng chọn hóa đơn cần xem !!!");
		}
	}
	public void timKiem_DoanhThu() 
	{
		for(int i = tablemodelDoanhThu.getRowCount()-1;i>=0;i--)
		{
			tablemodelDoanhThu.removeRow(i);
		}
		try {
			ds.docNV();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<HoaDonBanHang> dshdban=null;
		if(rdbtnHienhet_Doanhthu.isSelected())
		{
			dshdban=ds.listHDB;
		}
		else if(rdbtnChinhXac_Doanhthu.isSelected())
		{
			Date ngay = dateChooser_DoanhThu.getDate();
			dshdban=control.TimHDNBanTheoNgay(dateformat.format(ngay));
		}
		else if(rdbtnTheoThang_Doanhthu.isSelected())
		{
			dshdban=control.TimHDNBanTheoThang(monthChooser_DoanhThu.getMonth()+1);
		}
		else if(rdbtnTheonam_Doanhthu.isSelected())
		{
			dshdban=control.TimHDNBanTheoNam(yearChooser_DoanhThu.getYear());
		}

		for(HoaDonBanHang hd : dshdban)
		{
			if(hd.getMaNVLap().equals(IDNhanVien))
			{
				Object[] row = {hd.getMaHD(),hd.getNgayLap(),hd.getMaNVLap(),hd.getTongTien()};
				tablemodelDoanhThu.addRow(row);
			}
		}
	}
	public void timKiemDSHD() 
	{
		for(int i = tablemodelDanhSachDon.getRowCount()-1;i>=0;i--)
		{
			tablemodelDanhSachDon.removeRow(i);
		}
		try {
			ds.docNV();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<HoaDonBanHang> dshdban=null;
	
		if(rdbtnHienHet_DSHD.isSelected())
		{
			dshdban=ds.listHDB;
		}
		else if(rdbtnChinhXac_DSHD.isSelected())
		{
			Date ngay = dateChooser_DSHD.getDate();
			dshdban=control.TimHDNBanTheoNgay(dateformat.format(ngay));
		}
		else if(rdbtnTheoThang_DSHD.isSelected())
		{
			dshdban=control.TimHDNBanTheoThang(monthChooser_DSHD.getMonth()+1);
		}
		else if(rdbtnTheoNam_DSHD.isSelected())
		{
			dshdban=control.TimHDNBanTheoNam(yearChooser_DSHD.getYear());
		}
		for(HoaDonBanHang hd : dshdban)
		{
			if(hd.getMaNVLap().equals(IDNhanVien))
			{
				Object[] row = {hd.getMaHD(),hd.getMaNVLap(),hd.getNgayLap(),hd.getMaKH(),hd.getTongTien()};
				tablemodelDanhSachDon.addRow(row);
			}
		}
	}
	public void LoadJCalender_DSDon(boolean a, boolean b,boolean c,boolean d)
	{
		panelTim_DSDon.removeAll();
		panelTim_DSDon.repaint();
		panelTim_DSDon.revalidate();
		if(a==true)
		{
			panelTim_DSDon.add(new JLabel("Tìm hết các móc thời gian")).setSize(panelTim_DSDon.getSize());;
		}
		else if(b==true)
		{
			dateChooser_DSHD = new JDateChooser();
			dateChooser_DSHD.setDateFormatString("dd / MM /yyyy");
			dateChooser_DSHD.setLocale(new Locale("vi", "VN"));
			dateChooser_DSHD.setSize(panelTim_DSDon.getSize());
			panelTim_DSDon.add(dateChooser_DSHD);
		}
		else if(c==true)
		{
			monthChooser_DSHD = new JMonthChooser();
			monthChooser_DSHD .setMonth(0);
			monthChooser_DSHD .setLocale(new Locale("vi", "VN"));
			monthChooser_DSHD .setSize(panelTim_DSDon.getSize());
			panelTim_DSDon.add(monthChooser_DSHD );
		}
		else if(d==true)
		{
			yearChooser_DSHD = new JYearChooser();
			yearChooser_DSHD.setSize(panelTim_DSDon.getSize());
			panelTim_DSDon.add(yearChooser_DSHD);

		}	
	}
	public void LoadJCalender_Doanhthu(boolean a, boolean b,boolean c,boolean d)
	{
		panel_DoanhThu.removeAll();
		panel_DoanhThu.repaint();
		panel_DoanhThu.revalidate();
		if(a==true)
		{
			panel_DoanhThu.add(new JLabel("Tìm hết các móc thời gian")).setSize(panel_DoanhThu.getSize());;
		}
		else if(b==true)
		{
			dateChooser_DoanhThu = new JDateChooser();
			dateChooser_DoanhThu.setDateFormatString("dd / MM /yyyy");
			dateChooser_DoanhThu.setLocale(new Locale("vi", "VN"));
			dateChooser_DoanhThu.setSize(panel_DoanhThu.getSize());
			panel_DoanhThu.add(dateChooser_DoanhThu);
		}
		else if(c==true)
		{
			monthChooser_DoanhThu = new JMonthChooser();
			monthChooser_DoanhThu.setMonth(0);
			monthChooser_DoanhThu.setLocale(new Locale("vi", "VN"));
			monthChooser_DoanhThu.setSize(panel_DoanhThu.getSize());
			panel_DoanhThu.add(monthChooser_DoanhThu);
		}
		else if(d==true)
		{
			yearChooser_DoanhThu = new JYearChooser();
			yearChooser_DoanhThu.setSize(panel_DoanhThu.getSize());
			panel_DoanhThu.add(yearChooser_DoanhThu);

		}	
	}
}
