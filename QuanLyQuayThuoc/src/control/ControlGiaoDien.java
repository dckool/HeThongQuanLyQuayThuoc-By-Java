package control;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entity.CTHoaDonBan;
import entity.CTHoaDonNhap;
import entity.HoaDonBanHang;
import entity.HoaDonNhapHang;
import entity.KhachHang;
import entity.NhanVien;
import entity.ThongTinThuoc;
import jxl.CellType;
import jxl.Workbook;
import jxl.biff.drawing.ComboBox;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.format.Orientation;
import jxl.format.Pattern;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ControlGiaoDien {
	DanhSachDuLieu ds = new DanhSachDuLieu();
	final String filename="data.txt";
	//////Xử lý dữ liệu bên SQL
	//---------------DL Thuốc------------------------------
	public boolean themThuocVaoSQL(ThongTinThuoc thuoc) throws SQLException
	{
		int giaNhap = (int)Math.round(thuoc.getGiaNhap());
		int giaBan = (int)Math.round(thuoc.getGiaBan());
		Connection con =KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="insert into DSThuoc values(?,?,?,?,?,?,?,?,?);";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, thuoc.getMaThuoc());
			pstmt.setString(2, thuoc.getTenThuoc());
			pstmt.setString(3, thuoc.getLoai());
			pstmt.setInt(4, thuoc.getSoLuong());
			pstmt.setString(5, thuoc.getDonViTinh());
			pstmt.setString(6, thuoc.getNcc());
			pstmt.setInt(7,giaNhap);
			pstmt.setInt(8,giaBan);
			pstmt.setString(9, thuoc.getHsd());
			pstmt.execute();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.close();
			return false;
		}
	}

	public boolean xoaThuocTrongSQL(String ma) throws SQLException
	{
		Connection con =KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="delete from DSThuoc"+" where MaThuoc = ? ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,ma);
			pstmt.execute();
			return true;
		} catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();	
			con.close();
		}
		return false;
	}

	public boolean SuaDuLieuThuocTrongSQL(ThongTinThuoc thuocmoi) throws SQLException
	{
		Connection con =KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="update dbo.DSThuoc set Ten=? ,Loai=? ,SoLuong=? ,DonViTinh=? ,NCC=? ,GiaNhap=? ,Giaban= ?,HanSD=? where MaThuoc = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(9,thuocmoi.getMaThuoc());
			pstmt.setString(1,thuocmoi.getTenThuoc());
			pstmt.setString(2,thuocmoi.getLoai());
			pstmt.setInt(3,thuocmoi.getSoLuong());
			pstmt.setString(4,thuocmoi.getDonViTinh());
			pstmt.setString(5,thuocmoi.getNcc());
			pstmt.setDouble(6,thuocmoi.getGiaNhap());
			pstmt.setDouble(7,thuocmoi.getGiaBan());
			pstmt.setString(8,thuocmoi.getHsd());
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();	
			con.close();
		}
		return false;
	}
	//------------------------DL Nhân Viên--------------------

	public boolean suaDuLieuNVTrongSQL(String maNV,String ngaySinh,String sdt,String diaChi,String CMND,String gioiTinh) throws SQLException
	{
		Connection con =KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="update dbo.NhanVien set NgaySinh= ? ,SDT= ? ,DiaChi= ? ,CMND= ?,GioiTinh=? where MaNV = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(6,maNV);
			pstmt.setString(1,ngaySinh);
			pstmt.setString(2,sdt);
			pstmt.setString(3,diaChi);
			pstmt.setString(4,CMND);
			pstmt.setString(5,gioiTinh);
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();	
			con.close();
		}
		return false;
	}
	//-----------Doi pass
	public boolean doiPass(String passmoi,String maNV) throws SQLException
	{
		Connection con =KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="update dbo.NhanVien set Pass=? where MaNV = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, passmoi);
			pstmt.setString(2, maNV);
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();	
			con.close();
		}
		return false;
	}
	//-------------------------
	public boolean suaDiaChiVaSDT(String diaChiMoi,String sdtMoi,String maNV) throws SQLException
	{
		Connection con =KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="update dbo.NhanVien set DiaChi=? ,SDT=? where MaNV = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, diaChiMoi);
			pstmt.setString(2, sdtMoi);
			pstmt.setString(3, maNV);
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();	
			con.close();
		}
		return false;
	}
	//----------------------Tim Nhan Vien theo ID------------
	public  NhanVien docDuLieuNhanVien(String maNV) throws SQLException
	{
		Connection con =  KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="select * from NhanVien "+"where MaNV=?";
			PreparedStatement pretamt = con.prepareStatement(sql);
			pretamt.setString(1, maNV);
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

				return  new NhanVien(ma, ten, ngaySinh, gioiTinh, sDT, diaChi, mk,cmnd);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.close();
		}
		return null;
	}
	//----------------------Phân quyền truy cập----------------
	public boolean PhanQuyenNV(NhanVien nv)
	{
		String[] a=nv.getMaNv().split("",3);
		String phanquyen=a[0]+a[1];
		if(phanquyen.equals("QL"))
			return true;
		else
			return false;


	}
	//----------------------DL Hóa đơn Bán---------------------

	public void themHDBVaoSQL(HoaDonBanHang hdb) throws SQLException
	{
		int tongTien = (int)Math.round(hdb.getTongTien());
		Connection con =KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="insert into HoaDon values(?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, hdb.getMaHD());
			pstmt.setString(2, hdb.getMaNVLap());
			pstmt.setString(3, hdb.getNgayLap());
			pstmt.setString(4, hdb.getMaKH());
			pstmt.setInt(5, tongTien);
			pstmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
			con.close();
		}
	}

	public HoaDonBanHang timHDBtheoMa(String maHDB) throws SQLException
	{
		HoaDonBanHang hdb = new HoaDonBanHang();
		Connection con =  KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="select * from HoaDon "+"where MaHD=?";
			PreparedStatement pretamt = con.prepareStatement(sql);
			pretamt.setString(1, maHDB);
			ResultSet rs = pretamt.executeQuery();
			while(rs.next())
			{
				String maHD = rs.getString(1);
				String maNVLap = rs.getString(2);
				String ngaylap= rs.getString(3);
				String maKH = rs.getString(4);
				Double tongTien = rs.getDouble(5);
				hdb.setMaHD(maHD);
				hdb.setMaKH(maKH);
				hdb.setNgayLap(ngaylap);
				hdb.setTongTien(tongTien);
				hdb.setMaNVLap(maNVLap);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.close();
		}
		return hdb;

	}
	//-------------------DL CT hóa đơn bán--------------------
	public void themCTHoaDonBanVaoSQL(CTHoaDonBan ctHDB) throws SQLException
	{
		int donGia = (int)Math.round(ctHDB.getDonGia());
		Connection con =KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="insert into dbo.ChiTietHoaDon values(?,?,?,?,?);";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ctHDB.getMaHD());
			pstmt.setString(2, ctHDB.getMaThuoc());
			pstmt.setString(3, ctHDB.getTenThuoc());
			pstmt.setInt(4, ctHDB.getSoLuong());
			pstmt.setInt(5, donGia);
			pstmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.close();
		}

	}

	//----------------------DL Hóa đơn Nhập-------------------

	public void themHDNVaoSQL(HoaDonNhapHang hdn) throws SQLException
	{
		int tongTien = (int)Math.round(hdn.getTongGiaNhap());
		Connection con =KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="insert into HoaDonNhap values(?,?,?);";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, hdn.getMaHDN());
			pstmt.setString(2, hdn.getNgayNhap());
			pstmt.setInt(3,tongTien);
			pstmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.close();
		}
	}

	public boolean xoaHDNtrongSQL(String maHDN) throws SQLException
	{
		Connection con =KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="delete from HoaDonNhap"+" where MaHDN = ? ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,maHDN);
			pstmt.execute();
			return true;
		} catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();	
			con.close();
		}
		return false;
	}
	
	public void suaTinhTrangHDN(String maHDN) throws SQLException
	{
		Connection con =KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="update ChiTietHoaDonNhap set TinhTrang=? where MaThuoc = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "2");
			pstmt.setString(2, maHDN);
			pstmt.executeUpdate();
		} catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();	
			con.close();
		}
	}
	//-------------------DL CT hóa đơn nhập--------------------
	public void themCTHoaDonNhapVaoSQL(CTHoaDonNhap ctHDN) throws SQLException
	{
		Connection con =KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="insert into dbo.ChiTietHoaDonNhap values(?,?,?,?,?);";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ctHDN.getMaHDN());
			pstmt.setString(2, ctHDN.getMaThuoc());
			pstmt.setInt(3, ctHDN.getSoLuong());
			pstmt.setString(4, ctHDN.getHsd());
			pstmt.setInt(5, ctHDN.getTinhTrang());
			pstmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.close();
		}

	}

	public boolean xoaCTHoaDonNhaptrongSQL(String maHDN) throws SQLException
	{
		Connection con =KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="delete from ChiTietHoaDonNhap"+" where MaHDN = ? ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,maHDN);
			pstmt.execute();
			return true;
		} catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();	
			con.close();
		}
		return false;
	}
	//---------------------DL Khách Hàng------------------
	public void themKHVaoSQL (KhachHang kh) throws SQLException
	{
		Connection con =KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="insert KhachHang values(?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, kh.getCMND());
			pstmt.setString(2, kh.getHoTenKH());
			pstmt.setString(3, kh.getNgaySinh());
			pstmt.setString(4, kh.getSdt());
			pstmt.setString(5, kh.getMoTaKH());
			pstmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.close();
		}
	}
	public boolean suaDuLieuKhachHangTrongSQL(KhachHang khmoi) throws SQLException
	{
		Connection con =KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="update dbo.KhachHang set MoTa=?, TenKH=?, NgaySinh=?, SDT=? where CMND = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,khmoi.getMoTaKH());
			pstmt.setString(5,khmoi.getCMND());
			pstmt.setString(2, khmoi.getHoTenKH());
			pstmt.setString(3, khmoi.getNgaySinh());
			pstmt.setString(4, khmoi.getSdt());
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();	
			con.close();
		}
		return false;
	}

	//---------------Hàm xử lý--------------------------
	public double tongDoanhThuCaNhan(String maNV,String ngayLap)
	{
		double tong=0;
		for(HoaDonBanHang hdb : ds.listHDB)
		{
			if(hdb.getMaNVLap().equalsIgnoreCase(maNV) && hdb.getNgayLap().equalsIgnoreCase(ngayLap))
			{
				tong+=hdb.getTongTien();
			}
		}
		return tong;
	}
	public double tongDoanhThu(DefaultTableModel tbm, int vitri)
	{
		double tong = 0;
		for(int i=tbm.getRowCount()-1;i>=0;i--)
		{
			double tong2=Double.parseDouble(tbm.getValueAt(i, vitri)+"");
			tong+=tong2;
		}
		return tong;
	}
	public double tinhTongGiaChoTungLoaiThuocBan(ThongTinThuoc thuoc, int soLuong)
	{
		double gia =0;
		gia = thuoc.getGiaBan()*soLuong;
		return gia;
	}
	public String layChuoiNgayThangNam(JComboBox ngay,JComboBox thang,JComboBox nam)
	{
		String date = nam.getSelectedItem().toString()+"-"+thang.getSelectedItem().toString()+"-"+ngay.getSelectedItem().toString();
		return date;
	}
	public String layChuoiThangNam(JComboBox thang,JComboBox nam)
	{
		String month = nam.getSelectedItem().toString()+"-"+thang.getSelectedItem().toString();
		return month;
	}
	public String layChuoiNam(JComboBox nam)
	{
		String year = nam.getSelectedItem().toString();
		return year;
	}

	public long kiemTraDulieuNhapSo(String s, JPanel cc, String tb) {
		try {
			long a = Long.parseLong(s);
			return a;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			JOptionPane.showMessageDialog(cc, tb);
			return -1;
		}
	}
	public int KiemTraTinhTrang(ThongTinThuoc thuoc)
	{
		int quyDinhSoLuong = 0;
		if(thuoc.getDonViTinh().equals("Vien"))
			quyDinhSoLuong = 50;
		if(thuoc.getDonViTinh().equals("Lo"))
			quyDinhSoLuong = 5;
		if(thuoc.getDonViTinh().equals("Hop"))
			quyDinhSoLuong = 2;
		if(thuoc.getDonViTinh().equals("Tupe"))
			quyDinhSoLuong = 10;
		if(thuoc.getDonViTinh().equals("Goi"))
			quyDinhSoLuong = 5;
		Date today=new Date(System.currentTimeMillis());
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
		String s=timeFormat.format(today.getTime());
		if(thuoc.getSoLuong()>quyDinhSoLuong && !thuoc.getHsd().equals(s))
		{
			String[] ngayhientai=s.split("-");
			String[] hsd=thuoc.getHsd().split("-");
			int ngay,thang,nam;
			int ngayhsd,thanghsd,namhsd;
			ngay=Integer.parseInt(ngayhientai[2]);
			thang=Integer.parseInt(ngayhientai[1]);
			nam=Integer.parseInt(ngayhientai[0]);
			ngayhsd=Integer.parseInt(hsd[2]);
			thanghsd=Integer.parseInt(hsd[1]);
			namhsd=Integer.parseInt(hsd[0]);
			if(nam==namhsd)
			{
				if(thang==thanghsd)
				{
					if(ngay<ngayhsd)
						return 0;
					else
						return 1;
				}	
				if(thang<thanghsd)
					return 0;
				else
					return 1;
			}		
			if(nam<namhsd)
				return 0;
			else		//năm > năm hsd
				return 1;
		}
		else
		{
			if(thuoc.getSoLuong()<quyDinhSoLuong)
				return 2;
			return 0;
		}

	}
	public String layNgayHeThong()
	{
		Date today=new Date(System.currentTimeMillis());
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
		String s=timeFormat.format(today.getTime());
		return s;
	}
	public double tinhTienLoi(String maThuoc, int soLuong)
	{
		try {
			ds.docThuoc();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double giaBan=ds.TimThuocTheoMa(maThuoc).getGiaBan();
		double giaMua=ds.TimThuocTheoMa(maThuoc).getGiaNhap();
		double tongTienBan = giaBan*soLuong;
		double tongTienMua = giaMua*soLuong;
		double tienLoi = tongTienBan-tongTienMua;
		return tienLoi;
	}
	public void autoCapNhatThuocHetHan(ThongTinThuoc thuocHetHan,JPanel panel) throws SQLException // Cập nhật số lượng và hsd cho các thuốc hết hạn (đưa thuốc từ kho lên)
	{
		String dsthuoc="";
		ds.docBangCTHoaDonNhap();
		if(KiemTraTinhTrang(thuocHetHan)>0)
		{
			for(CTHoaDonNhap ctHDN : ds.listThuocNhap)
			{
				if(ctHDN.getTinhTrang()==1)
				{
					if(thuocHetHan.getMaThuoc().equals(ctHDN.getMaThuoc()) && ctHDN.getTinhTrang() == 1 || ctHDN.getTinhTrang() == 2)
					{
					
						String tenThuoc = ds.TimThuocTheoMa(ctHDN.getMaThuoc()).getTenThuoc();
						thuocHetHan.setSoLuong(ctHDN.getSoLuong());
						thuocHetHan.setHsd(ctHDN.getHsd());
						suaTinhTrangHDN(ctHDN.getMaThuoc());
						SuaDuLieuThuocTrongSQL(thuocHetHan);
						dsthuoc+=tenThuoc+"\n";
					}
				}
			}
		}
		if(!dsthuoc.equals(""))
		{
			JOptionPane.showMessageDialog(panel,"Các thuốc sau : \n"+dsthuoc+"đã hết số lượng hoặc HSD và đã được cập nhật!");
		}
		
	}
	public void truSoLuongThuocDaBan(String maThuoc,int soLuong)
	{
		try {
			ds.docThuoc();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ThongTinThuoc thuoc = new ThongTinThuoc();
		thuoc=ds.TimThuocTheoMa(maThuoc);
		int soLuongConLai = thuoc.getSoLuong()- soLuong;
		if(soLuongConLai>=0)
			thuoc.setSoLuong(soLuongConLai);
		try {
			SuaDuLieuThuocTrongSQL(thuoc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	}

	//-------------------Lưu chủ đề-----------------
	public String TaiChuDe()
	{
		BufferedReader br =null;
		try
		{
			if(new File(filename).exists())
			{
				br = new BufferedReader(new FileReader(filename));
				String line = br.readLine();
				if(!line.equals(""))
				{
					br.close();
					return line;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "com.jtattoo.plaf.mcwin.McWinLookAndFeel";
	}
	//
	public void LuuChuDe(String chude){
		BufferedWriter bw;
		try 
		{
			bw= new BufferedWriter(new FileWriter(filename));
			bw.write(chude);
			bw.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}
	public boolean kiemTraDuLieuSo(String s) {
		int soKiTuSo = 0,soKiTuChu= 0;
		for(int i=0;i<s.length();i++){
			if(Character.isLetter(s.charAt(i))) 
				soKiTuChu++; //đếm số chữ
			else if(Character.isDigit(s.charAt(i))) 
				soKiTuSo++;//đếm các số trong chuỗi                   
		}
		if (soKiTuChu==0 && soKiTuSo>=0)
			return true;
		return false;
	}
	public boolean kiemTraDuLieuChu(String s) {
		int soKiTuSo = 0,soKiTuChu= 0;
		for(int i=0;i<s.length();i++){
			if(Character.isLetter(s.charAt(i))) 
				soKiTuChu++; //đếm số chữ
			else if(Character.isDigit(s.charAt(i))) 
				soKiTuSo++;//đếm các số trong chuỗi                   
		}
		if (soKiTuChu>=0 && soKiTuSo==0)
			return true;
		return false;
	}
	public int demSoKiTuSo1Chuoi(String s) {
		int soKiTuSo = 0,soKiTu= 0;
		for(int i=0;i<s.length();i++){
			if(Character.isLetter(s.charAt(i))) 
				soKiTu++; //đếm số chữ
			else if(Character.isDigit(s.charAt(i))) 
				soKiTuSo++;//đếm các số trong chuỗi                   
		}
		return soKiTuSo;
	}
	public boolean kiemTraCMND(String cmnd) {
	       boolean soAm = false;
	    try {
	        if (Long.parseLong(cmnd)>0)
                soAm=true;
	    }catch (Exception e) {
	    }
		if ((kiemTraDuLieuSo(cmnd)==true) && soAm==true && (demSoKiTuSo1Chuoi(cmnd) == 12 || demSoKiTuSo1Chuoi(cmnd)==9))
			return true;
		return false;
	}
	public boolean kiemTraSDT(String sdt) {
        boolean soAm = false;
     try {
         if (Long.parseLong(sdt)>0)
             soAm=true;
     }catch (Exception e) {
     }
		if (((kiemTraDuLieuSo(sdt)==true) && soAm==true && (demSoKiTuSo1Chuoi(sdt) == 11 || demSoKiTuSo1Chuoi(sdt)==10))
		        || sdt.equals(""))
			return true;
		return false;
	}

	//
	public boolean kiemTraNgayHopLe(String date) { //đưa vào chuỗi date yyyy-MM-dd đã định dạng 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date testDate = null;
		try {
			testDate =sdf.parse(date);
		}
		catch (ParseException e) {
			return false;
		}
		if (sdf.format(testDate).equals(date)  && kiemTraNgaySoVoiHienTai(date, layNgayHeThong())) {
			return true;
		}
		return false;
	}
	public boolean kiemTraNgaySoVoiHienTai(String date1,String heThong) {
	    java.util.Date testDate = null;
	    java.util.Date testDate2 = null;
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    try {
            testDate = sdf.parse(date1);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    try {
            testDate2=sdf.parse(heThong);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    if ( testDate .before(testDate2))
	        return true;
	    return false;
	}
	//
	public void GhiEXECL(String fileName,String chuoi)
    {    
        WritableWorkbook workbook;
        try {

            //Tạo file excel
            workbook = Workbook.createWorkbook(new File(fileName));
            // Tạo Sheel trong file
            WritableSheet sheet1 = workbook.createSheet("Báo cáo thu chi",0);
            // Cắt chuỗi
            // row bắt đầu và col bắt đầu
            WritableFont cellFont = new WritableFont(WritableFont.TIMES, 20);
            cellFont.setColour(Colour.BLUE);
            WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
            cellFormat.setAlignment(Alignment.CENTRE);
            cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            sheet1.mergeCells(0, 0, 4, 0);
            sheet1.addCell(new Label(0, 0, "Báo cáo thu chi", cellFormat));
            int rowstart=1,colstart =0;
            String[] data=chuoi.split(";");
            String[] header ="Mã;Tên;Số lượng;Đơn vị tính;Tiền lời;".split(";");
            for(int run1 =0,col=0;run1<header.length;run1++,col++)
            {
                sheet1.addCell(new Label(col,1,header[run1]));  
            }
            for(int run2=0;run2<=data.length-1;run2++,colstart++)
            {
                if ((colstart)%5==0) 
                {
                    rowstart++;
                    colstart=0;
                }
                if(colstart%4==0)
                {

                    sheet1.addCell(new Label(colstart, rowstart,data[run2]));
                }
                else
                    sheet1.addCell(new Label(colstart,rowstart,data[run2]));
            }
            // write file
            workbook.write();
            // close
            workbook.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
	public ArrayList<HoaDonNhapHang> TimHDNHangTheoThang(int Thang)
	{
		ArrayList<HoaDonNhapHang> ds =new ArrayList<HoaDonNhapHang>();
		Connection con =KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="select * from HoaDonNhap where MONTH(NgayLap) = ? ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Thang);
			ResultSet rs =pstmt.executeQuery();
			while(rs.next())
			{
				String mahd = rs.getString(1);
				String ngay = rs.getString(2);
				Double gia =rs.getDouble(3);
				HoaDonNhapHang hd = new HoaDonNhapHang(mahd, ngay, gia);
				ds.add(hd);
			}
			return ds;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public ArrayList<HoaDonNhapHang> TimHDNHangTheoNam(int Nam)
	{
		ArrayList<HoaDonNhapHang> ds =new ArrayList<HoaDonNhapHang>();
		Connection con =KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="select * from HoaDonNhap where YEAR(NgayLap) = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Nam);
			ResultSet rs =pstmt.executeQuery();
			while(rs.next())
			{
				String mahd = rs.getString(1);
				String ngay = rs.getString(2);
				Double gia =rs.getDouble(3);
				HoaDonNhapHang hd = new HoaDonNhapHang(mahd, ngay, gia);
				ds.add(hd);
			}
			return ds;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public String DSLoaithuoc()
	{
		String loai="";
		Connection con =  KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="select DISTINCT  Loai from dbo.DSThuoc";
			Statement stmt =con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				loai+=rs.getString(1)+";";
			}
			return loai;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public String DSNCC()
	{
		String NCC="";
		Connection con =  KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="select DISTINCT  NCC from dbo.DSThuoc";
			Statement stmt =con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				NCC+=rs.getString(1)+";";
			}
			return NCC;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public String DSDonViTinh()
	{
		String dvt="";
		Connection con =  KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="select DISTINCT DonViTinh from dbo.DSThuoc";
			Statement stmt =con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				dvt+=rs.getString(1)+";";
			}
			return dvt;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public ArrayList<HoaDonBanHang> DanhSachDoanhThu(String ngay)
	{
		ArrayList<HoaDonBanHang> dshang =new ArrayList<HoaDonBanHang>();;
		Connection con = KetNoiSQL.getInstance().connect();
		try 
		{
			String sqltimnam="select * from [HoaDon] where year([NgayLap]) = ? order by [NgayLap]";
			PreparedStatement pstmt = con.prepareStatement(sqltimnam);
			pstmt.setString(1, ngay);
			ResultSet rs =pstmt.executeQuery();
			while(rs.next())
			{
				String maHD = rs.getString(1);
				String ngayLap = rs.getString(3);
				String maNVLap = rs.getString(2);
				String maKH = rs.getString(4);
				Double tongTien= rs.getDouble(5);
				HoaDonBanHang hdb = new HoaDonBanHang(maHD, ngayLap, maNVLap, maKH, tongTien);
				dshang.add(hdb);
			}
			return dshang;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	public ArrayList<HoaDonBanHang> TimHDNBanTheoNgay(String ngay)
	{
		ArrayList<HoaDonBanHang> ds =new ArrayList<HoaDonBanHang>();
		Connection con =KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="select * from HoaDon where NgayLap = ? order by NgayLap,MaNVLap";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ngay);
			ResultSet rs =pstmt.executeQuery();
			while(rs.next())
			{
				String maHD = rs.getString(1);
				String ngayLap = rs.getString(3);
				String maNVLap = rs.getString(2);
				String maKH = rs.getString(4);
				Double tongTien= rs.getDouble(5);
				HoaDonBanHang hdb = new HoaDonBanHang(maHD, ngayLap, maNVLap, maKH, tongTien);
				ds.add(hdb);
			}
			return ds;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public ArrayList<HoaDonBanHang> TimHDNBanTheoThang(int thang)
	{
		ArrayList<HoaDonBanHang> ds =new ArrayList<HoaDonBanHang>();
		Connection con =KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="select * from HoaDon where MONTH(NgayLap) = ?  order by NgayLap,MaNVLap";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, thang);
			ResultSet rs =pstmt.executeQuery();
			while(rs.next())
			{
				String maHD = rs.getString(1);
				String ngayLap = rs.getString(3);
				String maNVLap = rs.getString(2);
				String maKH = rs.getString(4);
				Double tongTien= rs.getDouble(5);
				HoaDonBanHang hdb = new HoaDonBanHang(maHD, ngayLap, maNVLap, maKH, tongTien);
				ds.add(hdb);
			}
			return ds;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public ArrayList<HoaDonBanHang> TimHDNBanTheoNam(int nam)
	{
		ArrayList<HoaDonBanHang> ds =new ArrayList<HoaDonBanHang>();
		Connection con =KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="select * from HoaDon where YEAR(NgayLap) = ? order by NgayLap,MaNVLap";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, nam);
			ResultSet rs =pstmt.executeQuery();
			while(rs.next())
			{
				String maHD = rs.getString(1);
				String ngayLap = rs.getString(3);
				String maNVLap = rs.getString(2);
				String maKH = rs.getString(4);
				Double tongTien= rs.getDouble(5);
				HoaDonBanHang hdb = new HoaDonBanHang(maHD, ngayLap, maNVLap, maKH, tongTien);
				ds.add(hdb);
			}
			return ds;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public  ArrayList<NhanVien> LocDuLieuNhanVientheoten(String Ten) 
	{
		ArrayList<NhanVien> dsnv=new ArrayList<NhanVien>();
		Connection con =  KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="select * from NhanVien "+"where HoTen like ?";
			PreparedStatement pretamt = con.prepareStatement(sql);
			pretamt.setString(1, Ten+"%");
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
					NhanVien nv = new NhanVien(ma, ten, ngaySinh, gioiTinh, sDT, diaChi, mk,cmnd);
					dsnv.add(nv);
				}
			return  dsnv;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public  ArrayList<NhanVien> LocDuLieuNhanVientheoma(String maNV) 
	{
		ArrayList<NhanVien> dsnv=new ArrayList<NhanVien>();
		Connection con =  KetNoiSQL.getInstance().connect();
		try 
		{
			String sql="select * from NhanVien "+"where MaNV like ?";
			PreparedStatement pretamt = con.prepareStatement(sql);
			pretamt.setString(1, maNV+"%");
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
				NhanVien nv = new NhanVien(ma, ten, ngaySinh, gioiTinh, sDT, diaChi, mk,cmnd);
				dsnv.add(nv);
			}
			return  dsnv;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
}