package giaodien.QuanLy;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.DanhSachDuLieu;
import entity.CTHoaDonBan;
import entity.HoaDonBanHang;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GiaoDienChiTietDoanhThu extends JFrame {

	private JPanel contentPane;
	private JTextField txtma;
	private JTextField txtTongDoanhThu;
	private JTextField txtTen;
	private DefaultTableModel tablemodel;
	private JTable table;
	private JScrollPane scrollPane ;
	private DanhSachDuLieu ds = new DanhSachDuLieu();
	private GiaoDienQuanLy ql;

	/**
	 * Create the frame.
	 */
	public GiaoDienChiTietDoanhThu(String maHD,String maNV,String tenNV,double tongTien) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 421, 501);
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(100, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã NV");
		lblNewLabel.setBounds(10, 10, 100, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên NV ");
		lblNewLabel_1.setBounds(10, 35, 100, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tổng đơn giá");
		lblNewLabel_2.setBounds(10, 60, 100, 14);
		contentPane.add(lblNewLabel_2);
		
		txtma = new JTextField();
		txtma.setEditable(false);
		txtma.setBounds(138, 10, 168, 20);
		contentPane.add(txtma);
		txtma.setColumns(10);
		
		txtTen = new JTextField();
		txtTen.setEditable(false);
		txtTen.setColumns(10);
		txtTen.setBounds(138, 35, 168, 20);
		contentPane.add(txtTen);
		
		txtTongDoanhThu = new JTextField();
		txtTongDoanhThu.setEditable(false);
		txtTongDoanhThu.setBounds(138, 60, 168, 20);
		contentPane.add(txtTongDoanhThu);
		txtTongDoanhThu.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Chi tiết đơn");
		lblNewLabel_3.setBounds(10, 87, 81, 16);
		contentPane.add(lblNewLabel_3);
		
		String[] header="Mã thuốc;Tên thuốc;Số lượng bán; Đơn giá".split(";");
		tablemodel =new DefaultTableModel(header, 0){ 
            @Override//Override lại phương thức isCellEditable 
            public boolean isCellEditable(int row, int column) { 
                return false;//Trả về false không cho edit. 
            } 
        }; 
		
		contentPane.add(scrollPane =new JScrollPane(table=new JTable(tablemodel)));
		scrollPane.setBounds(10, 111, 385, 340);
		
		txtma.setText(maNV);
		txtTen.setText(tenNV);
		txtTongDoanhThu.setText(tongTien+"");
		
		duaDuLieuLenTable(maHD);
		
	}
	public void duaDuLieuLenTable(String maHD)
	{
		try {
			ds.docBangCTHoaDonBan();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(CTHoaDonBan ctHDB : ds.listThuocBan)
		{	
			if(ctHDB.getMaHD().equals(maHD))
			{
				Object[] row = {ctHDB.getMaThuoc(),ctHDB.getTenThuoc(),ctHDB.getSoLuong(),ctHDB.getDonGia()};
				tablemodel.addRow(row);
			}
		}	
	}
}
