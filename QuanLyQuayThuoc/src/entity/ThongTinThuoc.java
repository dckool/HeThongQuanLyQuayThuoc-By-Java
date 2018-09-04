package entity;

import java.sql.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class ThongTinThuoc {
	private String maThuoc;
	private String tenThuoc;
	private String loai; //phân loại theo công dụng chính...có thể bỏ qa thuộc tính này cũng đc
	private String ncc;
	//private String moTa; //lưu ý hoặc ghi chú thêm cho thuốc
	private double giaBan;
	private double giaNhap;
	private String hsd;
	private int soLuong;
	private String donViTinh; //đơn vị tính
	 
	
	public String getMaThuoc() {
		return maThuoc;
	}


	public void setMaThuoc(String maThuoc) {
		this.maThuoc = maThuoc;
	}


	public String getTenThuoc() {
		return tenThuoc;
	}


	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}


	public String getLoai() {
		return loai;
	}


	public void setLoai(String loai) {
		this.loai = loai;
	}


	public String getNcc() {
		return ncc;
	}


	public void setNcc(String ncc) {
		this.ncc = ncc;
	}


	public double getGiaBan() {
		return giaBan;
	}


	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}


	public double getGiaNhap() {
		return giaNhap;
	}


	public void setGiaNhap(double giaNhap) {
		this.giaNhap = giaNhap;
	}


	public String getHsd() {
		return hsd;
	}


	public void setHsd(String hsd) {
		this.hsd = hsd;
	}


	public int getSoLuong() {
		return soLuong;
	}


	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}


	public String getDonViTinh() {
		return donViTinh;
	}


	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maThuoc == null) ? 0 : maThuoc.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ThongTinThuoc other = (ThongTinThuoc) obj;
		if (maThuoc == null) {
			if (other.maThuoc != null)
				return false;
		} else if (!maThuoc.equals(other.maThuoc))
			return false;
		return true;
	}

	
	public ThongTinThuoc(String maThuoc, String tenThuoc, String loai, String ncc, double giaBan,
			double giaNhap, String hsd, int soLuong, String donViTinh) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.loai = loai;
		this.ncc = ncc;
		this.giaBan = giaBan;
		this.giaNhap = giaNhap;
		this.hsd = hsd;
		this.soLuong = soLuong;
		this.donViTinh = donViTinh;
	}


	public ThongTinThuoc() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
