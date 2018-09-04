package giaodien.NhanVien;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

import control.ControlGiaoDien;
import control.DanhSachDuLieu;
import entity.CTHoaDonBan;
import entity.HoaDonBanHang;
import entity.KhachHang;
import entity.ThongTinThuoc;
import giaodien.GiaoDienDangNhap;
import giaodien.GiaoDienThongTinNhanVien;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import javax.swing.JList;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Toolkit;

public class GiaoDienLapHoaDon extends JFrame {

    private JFrame frmLapHoaDon;
    private JTable table,tableThemThuoc;
    public DefaultTableModel tablemode,	tablemodelBangThemThuoc;
    private JScrollPane scrollPane_1;
    private JTextField txtTongTien;
    private JPanel panelDienThongTin, panelBangThemThuoc;
    private JComboBox cbbNgay,cbbThang,cbbNam;
    GiaoDienDangNhap dn;
    MaskFormatter formattextsl,formatextcmnd_sdt;
    ControlGiaoDien control = new ControlGiaoDien() ;
    GiaoDienThongTinNhanVien thongtinNV = new GiaoDienThongTinNhanVien();
    DanhSachDuLieu ds = new DanhSachDuLieu();
    private JCheckBox chkKeDon;
    private JTextArea txtAMota;
    private JTextField txtNguoiLap;
    private JTextField txtMa;
    private JTextField txtNgay;
    private JTextField txtCMND;
    private JTextField txtTenKH;
    private JTextField txtSDT;
    private JTextField txtTenThuoc_TimKiem;
    private JTextField txtSoLuong;
    private JTextField txtLoai;
    private JTextField txtSoLuong_BangThemThuoc;
    private JScrollPane scrollPaneBangThemThuoc_1;
    private DefaultListModel<String> listmodel;
    private JList list;
    private JScrollPane jsc;
    private JButton btnHuyBangThemThuoc;
    public GiaoDienLapHoaDon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(GiaoDienLapHoaDon.class.getResource("/ser/bill.png")));
        setResizable(false);
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setTitle("Lập hóa đơn");
        setBounds(500, 100, 490, 600);
        getContentPane().setLayout(null);

        String[] ngay={"01", "02", "03", "04", "05", "06", "07", "08", "09",
                "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
                "24", "25", "26", "27", "28", "29", "30", "31"};

        String[] thang ={"01", "02", "04", "05", "06", "07", "08", "09", "10", "11", "12"};


        String[] headerBangThemThuoc="Tên thuốc;Số lượng;Đơn vị tính;Đơn giá".split(";");
        tablemodelBangThemThuoc = new DefaultTableModel(headerBangThemThuoc,0){ 
            @Override//Override lại phương thức isCellEditable 
            public boolean isCellEditable(int row, int column) { 
                return false;//Trả về false không cho edit. 
            } 
        };


        String [] header="Tên thuốc;Số lượng;Đơn vị tính;Đơn giá;Thành tiền".split(";");
        JScrollPane scrollPane = new JScrollPane();
        tablemode =new DefaultTableModel(header, 0){ 
            @Override//Override lại phương thức isCellEditable 
            public boolean isCellEditable(int row, int column) { 
                return false;//Trả về false không cho edit. 
            } 
        };

        JLabel lblTongTien = new JLabel("Tổng tiền:");
        lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTongTien.setBounds(243, 490, 72, 20);
        getContentPane().add(lblTongTien);

        txtTongTien = new JTextField();
        txtTongTien.setEditable(false);
        txtTongTien.setEnabled(false);
        txtTongTien.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtTongTien.setBounds(326, 490, 137, 22);
        txtTongTien.setBorder(null);
        getContentPane().add(txtTongTien);
        txtTongTien.setColumns(10);

        JButton btnXoq = new JButton("Xong");
        btnXoq.setToolTipText("Enter");

        btnXoq.setHorizontalAlignment(SwingConstants.LEFT);
        btnXoq.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnXoq.setIcon(new ImageIcon(GiaoDienLapHoaDon.class.getResource("/ser/done.png")));
        btnXoq.setBounds(131, 521, 117, 34);
        getContentPane().add(btnXoq);

        JButton btnHuy = new JButton("Hủy");
        btnHuy.setToolTipText("Esc");
        btnHuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                txtTenThuoc_TimKiem.setText("");
                txtSoLuong.setText("");
                txtSoLuong_BangThemThuoc.setText("");
                txtTongTien.setText("");
                for(int i = tablemode.getRowCount()-1;i>=0;i--)
                    tablemode.removeRow(i);
                dispose();
            }
        });
        btnHuy.setIcon(new ImageIcon(GiaoDienLapHoaDon.class.getResource("/ser/deletered.png")));
        btnHuy.setSelectedIcon(new ImageIcon(GiaoDienLapHoaDon.class.getResource("/ser/deletered.png")));
        btnHuy.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnHuy.setBounds(258, 521, 117, 34);
        getContentPane().add(btnHuy);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 484, 279);
        getContentPane().add(layeredPane);

        panelDienThongTin = new JPanel();
        layeredPane.setLayer(panelDienThongTin, 1);
        panelDienThongTin.setToolTipText("Thông tin");
        panelDienThongTin.setBounds(0, 0, 484, 279);
        layeredPane.add(panelDienThongTin);
        panelDienThongTin.setLayout(null);

        JLabel label = new JLabel("THÔNG TIN HÓA ĐƠN");
        label.setForeground(new Color(75, 0, 130));
        label.setFont(new Font("Times New Roman", Font.BOLD, 22));
        label.setBounds(111, 11, 258, 34);
        panelDienThongTin.add(label);

        chkKeDon = new JCheckBox("Có kê đơn");

        chkKeDon.setToolTipText("Chọn nếu bán có kê đơn");
        chkKeDon.setFont(new Font("Tahoma", Font.BOLD, 11));
        chkKeDon.setBounds(24, 100, 94, 23);
        panelDienThongTin.add(chkKeDon);

        JLabel lblMHan = new JLabel("Mã hóa đơn:");
        lblMHan.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblMHan.setBounds(21, 67, 78, 17);
        panelDienThongTin.add(lblMHan);

        JLabel lblNgyLp = new JLabel("Ngày lập:");
        lblNgyLp.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNgyLp.setBounds(164, 67, 59, 17);
        panelDienThongTin.add(lblNgyLp);

        JLabel label_3 = new JLabel("Người lập:");
        label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
        label_3.setBounds(301, 67, 65, 17);
        panelDienThongTin.add(label_3);

        txtNguoiLap = new JTextField();
        txtNguoiLap.setText((String) null);
        txtNguoiLap.setEnabled(false);
        txtNguoiLap.setEditable(false);
        txtNguoiLap.setColumns(10);
        txtNguoiLap.setBounds(362, 65, 55, 20);
        panelDienThongTin.add(txtNguoiLap);

        txtMa = new JTextField();
        txtMa.setEnabled(false);
        txtMa.setEditable(false);
        txtMa.setColumns(10);
        txtMa.setBounds(99, 65, 55, 20);
        panelDienThongTin.add(txtMa);

        txtNgay = new JTextField();
        txtNgay.setEnabled(false);
        txtNgay.setEditable(false);
        txtNgay.setColumns(10);
        txtNgay.setBounds(219, 65, 72, 20);
        panelDienThongTin.add(txtNgay);

        JButton btnThongTinNhanVien = new JButton("");
        btnThongTinNhanVien.setIcon(new ImageIcon(GiaoDienLapHoaDon.class.getResource("/ser/user.png")));
        btnThongTinNhanVien.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                thongtinNV.setVisible(true);

            }
        });
        btnThongTinNhanVien.setBounds(417, 60, 33, 29);
        panelDienThongTin.add(btnThongTinNhanVien);

        JPanel panelThongtinKH = new JPanel();
        panelThongtinKH.setLayout(null);
        panelThongtinKH.setForeground(Color.RED);
        panelThongtinKH.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Th\u00F4ng tin kh\u00E1ch h\u00E0ng:", TitledBorder.TRAILING, TitledBorder.TOP, null, new Color(0, 128, 0)));
        panelThongtinKH.setBounds(10, 104, 464, 133);
        panelDienThongTin.add(panelThongtinKH);

        JLabel label_4 = new JLabel("CMND: ");
        label_4.setEnabled(false);
        label_4.setBounds(10, 26, 67, 14);
        panelThongtinKH.add(label_4);

        JLabel label_5 = new JLabel("Tên KH:");
        label_5.setEnabled(false);
        label_5.setBounds(10, 51, 67, 14);
        panelThongtinKH.add(label_5);

        JLabel label_6 = new JLabel("Ngày sinh:");
        label_6.setEnabled(false);
        label_6.setBounds(10, 76, 67, 14);
        panelThongtinKH.add(label_6);

        JLabel label_7 = new JLabel("SDT:");
        label_7.setEnabled(false);
        label_7.setBounds(10, 101, 67, 14);
        panelThongtinKH.add(label_7);

        txtCMND = new JTextField();
        txtCMND.setEditable(false);
        txtCMND.setColumns(10);
        txtCMND.setBounds(87, 26, 143, 20);
        panelThongtinKH.add(txtCMND);

        txtTenKH = new JTextField();
        txtTenKH.setEditable(false);
        txtTenKH.setColumns(10);
        txtTenKH.setBounds(87, 51, 167, 20);
        panelThongtinKH.add(txtTenKH);

        txtSDT = new JTextField();
        txtSDT.setEditable(false);
        txtSDT.setColumns(10);
        txtSDT.setBounds(87, 101, 167, 20);
        panelThongtinKH.add(txtSDT);

        btnThongTinNhanVien.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
                {
                    btnHuy.doClick();
                }
            }
        });

        JButton btnKiemTra = new JButton("");
        btnKiemTra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cmnd;
                boolean kt = false;
                cmnd = txtCMND.getText()+"";
                boolean ktcmnd=control.kiemTraCMND(cmnd);
                if (ktcmnd ==true) {
                    try {
                        ds.docBangKhachHang();
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    for(KhachHang kh : ds.listKhachHang)
                    {
                        if(kh.getCMND().equalsIgnoreCase(cmnd))
                        {
                            String[] date=kh.getNgaySinh().split("-");
                            txtTenKH.setText(kh.getHoTenKH()+"");
                            txtSDT.setText(kh.getSdt()+"");
                            txtAMota.setText(kh.getMoTaKH()+"");
                            cbbThang.setSelectedItem(date[1]);
                            cbbNgay.setSelectedItem(date[2]);
                            cbbNam.setSelectedItem(date[0]);
                            kt =true;
                        }
                    }
                    if (kt == false) {
                        JOptionPane.showMessageDialog(panelThongtinKH, "Khách hàng chưa có, vui lòng thêm mới !!");
                        txtTenKH.setText("");
                        txtSDT.setText("");
                        txtAMota.setText("");
                    }
                }
                else
                    JOptionPane.showMessageDialog(panelThongtinKH, "Số CMND ko hợp lệ !! CMND phải có 12 hoặc 9 CHỮ SỐ !!");
            }
        });
        btnKiemTra.setIcon(new ImageIcon(GiaoDienLapHoaDon.class.getResource("/ser/search.png")));
        btnKiemTra.setToolTipText("Kiểm tra");
        btnKiemTra.setEnabled(false);
        btnKiemTra.setBounds(231, 26, 23, 21);
        panelThongtinKH.add(btnKiemTra);

        txtAMota = new JTextArea();
        txtAMota.setEditable(false);
        txtAMota.setBackground(Color.WHITE);
        txtAMota.setBounds(264, 46, 190, 75);
        panelThongtinKH.add(txtAMota);

        JLabel label_8 = new JLabel("Mô tả:");
        label_8.setEnabled(false);
        label_8.setBounds(264, 26, 46, 14);
        panelThongtinKH.add(label_8);

        cbbNgay = new JComboBox(new Object[]{});
        cbbNgay.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
        cbbNgay.setEnabled(false);
        cbbNgay.setEditable(false);
        cbbNgay.setBounds(87, 76, 46, 20);
        panelThongtinKH.add(cbbNgay);

        cbbThang = new JComboBox(new Object[]{});
        cbbThang.setModel(new DefaultComboBoxModel(new String[] { "01", "02","03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
        cbbThang.setEnabled(false);
        cbbThang.setEditable(false);
        cbbThang.setBounds(143, 76, 46, 20);
        panelThongtinKH.add(cbbThang);

        cbbNam = new JComboBox();
        cbbNam.setEnabled(false);
        cbbNam.setModel(new DefaultComboBoxModel(new String[] {"1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2017"}));
        cbbNam.setBounds(199, 76, 55, 20);
        panelThongtinKH.add(cbbNam);

        JLabel label_9 = new JLabel("Tên thuốc:");
        label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
        label_9.setBounds(10, 248, 65, 22);
        panelDienThongTin.add(label_9);

        JLabel label_10 = new JLabel("Số lượng:");
        label_10.setFont(new Font("Tahoma", Font.BOLD, 11));
        label_10.setBounds(250, 248, 55, 22);
        panelDienThongTin.add(label_10);

        txtTenThuoc_TimKiem = new JTextField("Tên thuốc...");
        txtTenThuoc_TimKiem.setForeground(Color.GRAY);
        txtTenThuoc_TimKiem.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                txtTenThuoc_TimKiem.setText("");
                txtTenThuoc_TimKiem.setForeground(Color.BLACK);
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(!txtTenThuoc_TimKiem.getText().equals(""))
                {
                    //Không làm gì cả
                }
                else
                {
                    txtTenThuoc_TimKiem.setForeground(Color.GRAY);
                    txtTenThuoc_TimKiem.setText("Tên thuốc....");
                    jsc.setVisible(false);
                }
            }
        });

        txtTenThuoc_TimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(txtTenThuoc_TimKiem.getText().length()>0)
                {
                    if(!txtTenThuoc_TimKiem.getText().trim().equals(""))
                    {
                        listmodel.removeAllElements();
                        try {
                            String kq=ds.Timtenthuoc(txtTenThuoc_TimKiem.getText());
                            String[] data=kq.split(";");
                            if(!kq.equals(""))
                            {
                                for(int i=0;i<data.length;i++)
                                {
                                    if(i<6)
                                        listmodel.addElement(data[i]);
                                    else
                                        break;
                                }
                                jsc.setVisible(true);
                            }
                            else
                            {
                                listmodel.removeAllElements();
                                jsc.setVisible(false);
                            }
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                            txtTenThuoc_TimKiem.setText("");
                        }
                    }
                    else
                    {
                        jsc.setVisible(false);
                        listmodel.removeAllElements();
                    }
                }
                else
                {
                    listmodel.removeAllElements();
                    jsc.setVisible(false);
                }
            }
        });

        txtTenThuoc_TimKiem.setColumns(10);
        txtTenThuoc_TimKiem.setBounds(75, 249, 142, 21);
        panelDienThongTin.add(txtTenThuoc_TimKiem);

        txtSoLuong = new JTextField();
        txtSoLuong.setToolTipText("Vui lòng nhập số: ");
        txtSoLuong.setColumns(10);
        txtSoLuong.setBounds(315, 250, 48, 20);
        panelDienThongTin.add(txtSoLuong);

        JButton btnXoa = new JButton("");
        btnXoa.setIcon(new ImageIcon(GiaoDienLapHoaDon.class.getResource("/ser/cancel.png")));
        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int row = table.getSelectedRow();
                int soLuongMua = Integer.parseInt(tablemode.getValueAt(row, 1)+"");
                int soLuongCon = timThuocTrongBangTheoTenTraVeSoLuong(tablemode.getValueAt(row, 0)+"", tablemodelBangThemThuoc);
                if (row==-1)
                    JOptionPane.showMessageDialog(panelDienThongTin, "Vui lòng chọn thuốc");
                else {
                    int sel = JOptionPane.showConfirmDialog(panelDienThongTin, "Bạn có muốn xóa không?","Xóa", JOptionPane.YES_NO_OPTION);
                    if (sel == JOptionPane.YES_OPTION)
                    {
                        for(int i = tablemodelBangThemThuoc.getRowCount()-1;i>=0;i--)
                            if((tablemode.getValueAt(row, 0)+"").equals(tablemodelBangThemThuoc.getValueAt(i, 0)+""))
                                tablemodelBangThemThuoc.setValueAt(soLuongCon+soLuongMua, i, 1);
                        tablemode.removeRow(row);
                    }		
                }
                txtTongTien.setText(tongTien(tablemode)+"");
            }
        });
        btnXoa.setToolTipText("Xóa");
        btnXoa.setBounds(437, 248, 26, 23);
        panelDienThongTin.add(btnXoa);

        JButton btnThemNangCao = new JButton("");
        btnThemNangCao.setIcon(new ImageIcon(GiaoDienLapHoaDon.class.getResource("/ser/get_info.png")));
        btnThemNangCao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                txtSoLuong_BangThemThuoc.requestFocus();
                if(tablemodelBangThemThuoc.getRowCount()==0)	
                    for(ThongTinThuoc thuoc : ds.listThuoc)
                    {
                        Object[] row ={thuoc.getTenThuoc(),thuoc.getSoLuong(),thuoc.getDonViTinh(),thuoc.getGiaBan()};
                        tablemodelBangThemThuoc.addRow(row);
                    }
                panelBangThemThuoc.setVisible(true);
                panelDienThongTin.setVisible(false);
            }
        });
        btnThemNangCao.setToolTipText("Mở bảng");
        btnThemNangCao.setBounds(217, 249, 26, 21);
        panelDienThongTin.add(btnThemNangCao);

        JButton btnThem = new JButton("");
        btnThem.setIcon(new ImageIcon(GiaoDienLapHoaDon.class.getResource("/ser/add.png")));
        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                them_panelThongTin();
            }
        });
        btnThem.setToolTipText("Thêm");
        btnThem.setBounds(405, 248, 26, 23);
        panelDienThongTin.add(btnThem);

        txtLoai = new JTextField();
        txtLoai.setEnabled(false);
        txtLoai.setEditable(false);
        txtLoai.setColumns(10);
        txtLoai.setBounds(362, 250, 33, 20);
        panelDienThongTin.add(txtLoai);
        txtNguoiLap.setText(dn.txtTK.getText());

        chkKeDon.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                if(chkKeDon.isSelected())
                {
                    panelThongtinKH.setEnabled(true);
                    label_4.setEnabled(true);
                    label_5.setEnabled(true);
                    label_6.setEnabled(true);
                    label_7.setEnabled(true);
                    label_8.setEnabled(true);
                    txtCMND.setEditable(true);txtCMND.setText("");txtCMND.requestFocus();
                    cbbNgay.setEnabled(true);
                    cbbNgay.setEditable(true);
                    cbbThang.setEnabled(true);
                    cbbThang.setEditable(true);
                    cbbNam.setEnabled(true);
                    cbbNam.setEditable(true);
                    txtSDT.setEditable(true);  txtSDT.setText("");
                    txtTenKH.setEditable(true);txtTenKH.setText("");
                    txtAMota.setEditable(true);txtAMota.setText("");
                    txtAMota.setBackground(Color.decode("#ffffff"));
                    btnKiemTra.setEnabled(true);
                }
                else {
                    panelThongtinKH.setEnabled(false);
                    label_4.setEnabled(false);
                    label_5.setEnabled(false);
                    label_6.setEnabled(false);
                    label_7.setEnabled(false);
                    label_8.setEnabled(false);
                    txtCMND.setEditable(false);txtCMND.setText("");txtCMND.requestFocus();
                    cbbNgay.setEnabled(false);
                    cbbNgay.setEditable(false);
                    cbbThang.setEnabled(false);
                    cbbThang.setEditable(false);
                    cbbNam.setEnabled(false);
                    cbbNam.setEditable(false);
                    txtSDT.setEditable(false);  txtSDT.setText("");
                    txtTenKH.setEditable(false);txtTenKH.setText("");
                    txtAMota.setEditable(false);txtAMota.setText("");
                    txtAMota.setBackground(Color.decode("#ffffff"));
                    btnKiemTra.setEnabled(false);
                }

            }
        });
        txtMa.setText("HD"+ds.demSoHDBan());

        panelBangThemThuoc = new JPanel();
        layeredPane.setLayer(panelBangThemThuoc, 0);
        panelBangThemThuoc.setForeground(Color.ORANGE);
        panelBangThemThuoc.setBounds(10, 11, 464, 268);
        layeredPane.add(panelBangThemThuoc);
        panelBangThemThuoc.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ch\u1ECDn thu\u1ED1c t\u1EEB b\u1EA3ng \u0111\u1EC3 th\u00EAm v\u00E0o h\u00F3a \u0111\u01A1n:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 0)));
        panelBangThemThuoc.setLayout(null);
        panelBangThemThuoc.add(scrollPaneBangThemThuoc_1 = new JScrollPane(tableThemThuoc = new JTable(tablemodelBangThemThuoc) ));
        scrollPaneBangThemThuoc_1.setBounds(10, 21, 370, 238);

        tableThemThuoc.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                // TODO Auto-generated method stub
                txtSoLuong_BangThemThuoc.requestFocus();
            }
        });

        JButton btnThem_BangThemThuoc = new JButton("");
        btnThem_BangThemThuoc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                them_panelBangThemThuoc();
            }
        });
        btnThem_BangThemThuoc.setToolTipText("Thêm");
        btnThem_BangThemThuoc.setIcon(new ImageIcon(GiaoDienLapHoaDon.class.getResource("/ser/add1.png")));
        btnThem_BangThemThuoc.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnThem_BangThemThuoc.setBounds(390, 106, 64, 64);
        panelBangThemThuoc.add(btnThem_BangThemThuoc);

        JLabel lblSLng = new JLabel("Số lượng:");
        lblSLng.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblSLng.setBounds(391, 36, 52, 14);
        panelBangThemThuoc.add(lblSLng);

        txtSoLuong_BangThemThuoc = new JFormattedTextField(formatextcmnd_sdt);
        txtSoLuong_BangThemThuoc.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent arg0) {
                if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
                {
                    btnThem_BangThemThuoc.doClick();
                }	
                else if(arg0.getKeyCode()==KeyEvent.VK_LEFT)
                {
                   btnHuyBangThemThuoc.doClick();
                }
            }
        });
        
                btnHuyBangThemThuoc = new JButton("");
                btnHuyBangThemThuoc.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        panelBangThemThuoc.setVisible(false);
                        panelDienThongTin.setVisible(true);
                    }
                });
                btnHuyBangThemThuoc.setToolTipText("Quay lại");
                btnHuyBangThemThuoc.setIcon(new ImageIcon(GiaoDienLapHoaDon.class.getResource("/ser/back4848.png")));
                btnHuyBangThemThuoc.setFont(new Font("Tahoma", Font.BOLD, 11));
                btnHuyBangThemThuoc.setBounds(390, 193, 64, 64);
                panelBangThemThuoc.add(btnHuyBangThemThuoc);
        txtSoLuong_BangThemThuoc.setToolTipText("Vui lòng nhập số");
        txtSoLuong_BangThemThuoc.setBounds(390, 61, 64, 20);
        txtSoLuong_BangThemThuoc.setColumns(10);
        panelBangThemThuoc.add(txtSoLuong_BangThemThuoc);

        //------tìm kiếm có gợi ý-------------
        listmodel =new DefaultListModel<String>();
        list = new JList<String>(listmodel);
        jsc =new JScrollPane(list);
        jsc.setBounds(76, 280, 143, 107);
        getContentPane().add(jsc);
        jsc.setVisible(false);
        list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                // TODO Auto-generated method stub
                Object data = list.getSelectedValue();
                if(data!=null)
                {
                    txtTenThuoc_TimKiem.setText(list.getSelectedValue()+"");
                    jsc.setVisible(false);
                    ThongTinThuoc thuoc2 = ds.TimThuocTheoTen(txtTenThuoc_TimKiem.getText());
                    txtLoai.setText(thuoc2.getDonViTinh()+"");
                }
                else
                {
                    txtTenThuoc_TimKiem.setText("");
                }

            }
        });
        getContentPane().add(scrollPane_1 = new JScrollPane(table =new JTable(tablemode)));
        scrollPane_1.setSize(464, 194);
        scrollPane_1.setLocation(10, 290);

        JScrollPane scrollPaneBangThemThuoc = new JScrollPane((Component) null);
        scrollPaneBangThemThuoc.setBounds(10, 36, 373, 223);

        //--------------------------------------

        txtNgay.setText(control.layNgayHeThong());
        try {
            ds.docThuoc();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        btnXoq.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                luuToanBoTTHoaDon();
            }	
        });

        //--------------------------------------------------

    }

    public void themThuocTuBangVaoHoaDon() {
        int row = tableThemThuoc.getSelectedRow();
        if (row!=-1) {
            String tenThuoc = tableThemThuoc.getValueAt(row, 0)+"";
            int soluong = Integer.parseInt(txtSoLuong_BangThemThuoc.getText());
            String dvt = tableThemThuoc.getValueAt(row, 2) +"";
            double dongia = (double) tableThemThuoc.getValueAt(row, 3);
            double tt = soluong*dongia;
            Object a[] = {tenThuoc,soluong,dvt,dongia,tt};
            tablemode.addRow(a);
        }
    }
    public void themThuocTuBangVaoHoaDon_TangSoLuong(String ten,int sl) {
        int row = tableThemThuoc.getSelectedRow();
        if (row!=-1) {
            String dvt = tableThemThuoc.getValueAt(row, 2) +"";
            double dongia = (double) tableThemThuoc.getValueAt(row, 3);
            double tt = sl*dongia;
            Object a[] = {ten,sl,dvt,dongia,tt};
            tablemode.addRow(a);
        }
    }
    public double tongTien(DefaultTableModel tbm)
    {
        double tong = 0;
        for(int i=tbm.getRowCount()-1;i>=0;i--)
        {
            double tong2=Double.parseDouble(tbm.getValueAt(i, 4)+"");
            tong+=tong2;
        }
        return tong;
    }

    //---------------------các hàm của lập hóa đơn--------------------
    public void luuToanBoTTHoaDon() {
        if(table.getRowCount()>=1)
        {
            if(chkKeDon.isSelected())
            {	
                String date = control.layChuoiNgayThangNam(cbbNgay, cbbThang, cbbNam);
                String sdt = txtSDT.getText()+"";
                String cmnd = txtCMND.getText()+"";
                if (kiemTraTTKH(sdt, cmnd, date)) {
                    luuHD();
                    luuCTHD();
                    luuKhachHang();
                    dispose();
                    JOptionPane.showMessageDialog(panelDienThongTin, "Lập hóa đơn thành công (CÓ lưu thông tin khách hàng)");
                }
                else {
                    if (!control.kiemTraCMND(txtCMND.getText()))
                        JOptionPane.showMessageDialog(panelDienThongTin, "Số CMND ko hợp lệ !! CMND phải có 12 hoặc 9 CHỮ SỐ không âm");
                    if(!control.kiemTraSDT(txtSDT.getText()))
                        JOptionPane.showMessageDialog(panelDienThongTin, "SĐT ko hợp lệ !! SĐT phải có 10 hoặc 11 CHỮ SỐ không âm");
                    if (!control.kiemTraNgayHopLe(date))
                        JOptionPane.showMessageDialog(panelDienThongTin, "Ngày sinh của khách hàng không hợp lệ !! ");
                }
            }
            else {
                luuHD();
                luuCTHD();
                dispose();
                JOptionPane.showMessageDialog(panelDienThongTin, "Lập hóa đơn thành công (KHÔNG lưu thông tin khách hàng)");
            }
        }
        else
            JOptionPane.showMessageDialog(panelDienThongTin, "Danh sách thuốc bán không được rỗng!");
    }

    public void luuHD() {
        HoaDonBanHang hdb = new HoaDonBanHang();
        hdb.setMaHD(txtMa.getText()+"");
        hdb.setMaNVLap(txtNguoiLap.getText()+"");
        hdb.setNgayLap(txtNgay.getText()+"");
        hdb.setMaKH(txtCMND.getText()+"");
        hdb.setTongTien(Double.parseDouble(txtTongTien.getText()+""));
        try {
            control.themHDBVaoSQL(hdb);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void luuCTHD() {
        for(int i=table.getRowCount()-1;i>=0;i--)
        {
            String tenThuoc = tablemode.getValueAt(i, 0)+"";
            String maThuoc = ds.TimThuocTheoTen(tenThuoc).getMaThuoc();
            int soLuong = Integer.parseInt(tablemode.getValueAt(i, 1)+"");
            CTHoaDonBan ctHDB = new CTHoaDonBan();
            ctHDB.setMaHD(txtMa.getText()+"");
            ctHDB.setMaThuoc(maThuoc);
            ctHDB.setTenThuoc(tenThuoc);
            ctHDB.setSoLuong(soLuong);
            ctHDB.setDonGia(Double.parseDouble(tablemode.getValueAt(i, 3)+""));
            try {
                control.themCTHoaDonBanVaoSQL(ctHDB);
                control.truSoLuongThuocDaBan(maThuoc,soLuong);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void luuKhachHang() {
        boolean kt = false;
        KhachHang kh = new KhachHang();
        kh.setCMND(txtCMND.getText());
        kh.setHoTenKH(txtTenKH.getText());
        kh.setNgaySinh( control.layChuoiNgayThangNam(cbbNgay, cbbThang, cbbNam));
        kh.setSdt(txtSDT.getText());
        kh.setMoTaKH(txtAMota.getText());
        try {
            ds.docBangKhachHang();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        for(KhachHang kh1 : ds.listKhachHang)
        {
            if(kh1.getCMND().equals(txtCMND.getText()))
            {

                try {
                    control.suaDuLieuKhachHangTrongSQL(kh);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                kt = true;
            }
        }
        if(kt == false)
        {
            try {
                control.themKHVaoSQL(kh);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
    //------------------kết thúc các hàm của lâp hóa đơn--------------------------------
    public void them_panelBangThemThuoc() {	
        int row = tableThemThuoc.getSelectedRow();
        String ten = tableThemThuoc.getValueAt(row, 0)+"";
        boolean kqTim = timThuocTrongBangTheoTen(ten, tablemode);
        if (row==-1)
            JOptionPane.showMessageDialog(panelBangThemThuoc, "Vui lòng chọn thuốc cần thêm !!!");
        else {
            if (txtSoLuong_BangThemThuoc.getText().equals("")) 
                JOptionPane.showMessageDialog(panelBangThemThuoc, "Vui lòng nhập số lượng !! ");
            else {
                if (control.kiemTraDuLieuSo(txtSoLuong_BangThemThuoc.getText()+"")==true && (kqTim==false)) {
                    int sl = Integer.parseInt(txtSoLuong_BangThemThuoc.getText());
                    if (sl<=0)
                        JOptionPane.showMessageDialog(panelBangThemThuoc, "Số lượng không được âm và khác 0 !!");
                    else {
                        if(sl<=Integer.parseInt(tablemodelBangThemThuoc.getValueAt(row, 1)+""))
                        {
                            int soLuongConLai =Integer.parseInt(tablemodelBangThemThuoc.getValueAt(row, 1)+"")- sl;
                            themThuocTuBangVaoHoaDon();
                            txtTongTien.setText(tongTien(tablemode)+"");
                            tableThemThuoc.setValueAt(soLuongConLai, row, 1);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(panelBangThemThuoc, "Thuốc ko đủ số lượng để bán!");
                        }
                    }
                }
                else {
                    if (control.kiemTraDuLieuSo(txtSoLuong_BangThemThuoc.getText()+"")==false)
                        JOptionPane.showMessageDialog(panelBangThemThuoc, "Số lượng phải bao gồm các CHỮ SỐ");
                    if (timThuocTrongBangTheoTen(ten, tablemode)) {

                        int sl = Integer.parseInt(txtSoLuong_BangThemThuoc.getText());
                        int slCu = timThuocTrongBangTheoTenTraVeSoLuong(ten, tablemode);
                        if (sl<=0)
                            JOptionPane.showMessageDialog(panelBangThemThuoc, "Số lượng không được âm và khác 0 !!");
                        else {
                            if(sl<=Integer.parseInt(tablemodelBangThemThuoc.getValueAt(row, 1)+""))
                            {	
                                int soLuongConLai = Integer.parseInt(tablemodelBangThemThuoc.getValueAt(row, 1)+"") - sl;
                                for(int i = tablemode.getRowCount()-1;i>=0;i--)
                                    if (tablemode.getValueAt(i, 0).equals(ten))
                                        tablemode.removeRow(i);
                                themThuocTuBangVaoHoaDon_TangSoLuong(ten,(sl+slCu));
                                txtTongTien.setText(tongTien(tablemode)+"");
                                tableThemThuoc.setValueAt(soLuongConLai, row, 1);
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(panelBangThemThuoc, "Thuốc ko đủ số lượng để bán!");
                            }
                        }
                    }
                }
            }
        }
        txtSoLuong_BangThemThuoc.setText("");
    }
    public void them_panelThongTin() {
        String ten = txtTenThuoc_TimKiem.getText()+"";
        boolean ketQua = timThuocTrongBangTheoTen(ten, tablemode);
        if ((control.kiemTraDuLieuSo(txtSoLuong.getText()+"")==true) && (ketQua == false)  && (!txtSoLuong.getText().equals(""))) {
            int soluong = Integer.parseInt(txtSoLuong.getText());
            if (soluong>0)
            {
                try {
                    ds.docThuoc();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                for(ThongTinThuoc thuoc : ds.listThuoc)
                {
                    if(ten.equals(thuoc.getTenThuoc()+""))
                    {
                        if(soluong <= thuoc.getSoLuong())
                        {
                            Object[] row = {ten,soluong,thuoc.getDonViTinh(),thuoc.getGiaBan(),soluong*thuoc.getGiaBan()};
                            tablemode.addRow(row);
                            for(int i = tablemodelBangThemThuoc.getRowCount()-1;i>=0;i--)
                                if(ten.equals(tablemodelBangThemThuoc.getValueAt(i, 0)+""))
                                    tablemodelBangThemThuoc.setValueAt(Integer.parseInt(tablemodelBangThemThuoc.getValueAt(i, 1)+"")-soluong, i, 1);
                        }
                        else 
                            JOptionPane.showMessageDialog(panelDienThongTin, "Số lượng thuốc không đủ để bán");
                    }
                }
                txtTongTien.setText(tongTien(tablemode)+"");
            }
            else 
                JOptionPane.showMessageDialog(panelDienThongTin, "Số lượng không âm và phải khác 0 !! ");
        }
        else {
            if (control.kiemTraDuLieuSo(txtSoLuong.getText()+"")==false)
                JOptionPane.showMessageDialog(panelDienThongTin, "Số lượng phải là CHỮ SỐ");
            if (txtSoLuong.getText().equals(""))
                JOptionPane.showMessageDialog(panelDienThongTin, "Số lượng không được để trống!!");
            else {
                if (ketQua==true) {
                    int soluongMoi = Integer.parseInt(txtSoLuong.getText());
                    int soluongCu = timThuocTrongBangTheoTenTraVeSoLuong(ten, tablemode);
                    try {
                        ds.docThuoc();
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    for(ThongTinThuoc thuoc : ds.listThuoc)
                    {
                        if(ten.equals(thuoc.getTenThuoc()+""))
                        {
                            if((soluongCu+soluongMoi) <= thuoc.getSoLuong())
                            {
                                for(int i = tablemode.getRowCount()-1;i>=0;i--)
                                    if (tablemode.getValueAt(i, 0).equals(ten))
                                        tablemode.removeRow(i);
                                Object[] row = {ten,soluongCu+soluongMoi,thuoc.getDonViTinh(),thuoc.getGiaBan(),(soluongCu+soluongMoi)*thuoc.getGiaBan()};
                                tablemode.addRow(row);
                                for(int i = tablemodelBangThemThuoc.getRowCount()-1;i>=0;i--)
                                    if(ten.equals(tablemodelBangThemThuoc.getValueAt(i, 0)+""))
                                        tablemodelBangThemThuoc.setValueAt(Integer.parseInt(tablemodelBangThemThuoc.getValueAt(i, 1)+"")-soluongMoi, i, 1);
                            }
                            else 
                                JOptionPane.showMessageDialog(panelDienThongTin, "Số lượng thuốc không đủ để bán");
                        }
                    }
                    txtTongTien.setText(tongTien(tablemode)+"");
                }
            }
        }
    }
    public boolean kiemTraTTKH(String sdt, String cmnd,String ngaysinh) {
        if ((control.kiemTraCMND(cmnd)) && (control.kiemTraSDT(sdt)) && (control.kiemTraNgayHopLe(ngaysinh)))
            return true;
        return false;
    }
    public boolean timThuocTrongBangTheoTen(String ten, TableModel tbm) {
        for(int i = tbm.getRowCount()-1;i>=0;i--)
            if (tbm.getValueAt(i, 0).equals(ten))
                return  true;
        return false;
    }
    public int timThuocTrongBangTheoTenTraVeSoLuong(String ten, TableModel tbm) {
        for(int i = tbm.getRowCount()-1;i>=0;i--)
            if (tbm.getValueAt(i, 0).equals(ten))
                return  (int)tbm.getValueAt(i, 1);
        return 0;
    }
}