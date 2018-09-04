package entity;

public class HoaDonBanHang {
	private String maHD;
	private	String ngayLap;
	private String maNVLap;
	private String MaKH;
	private double tongTien;
	
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	} 
	public String getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(String ngayLap) {
		this.ngayLap = ngayLap;
	}
	public String getMaNVLap() {
		return maNVLap;
	}
	public void setMaNVLap(String maNVLap) {
		this.maNVLap = maNVLap;
	}
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	public String getMaKH() {
		return MaKH;
	}
	public void setMaKH(String maKH) {
		MaKH = maKH;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maHD == null) ? 0 : maHD.hashCode());
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
		HoaDonBanHang other = (HoaDonBanHang) obj;
		if (maHD == null) {
			if (other.maHD != null)
				return false;
		} else if (!maHD.equals(other.maHD))
			return false;
		return true;
	}
	public HoaDonBanHang(String maHD, String ngayLap, String maNVLap, String maKH, double tongTien) {
		super();
		this.maHD = maHD;
		this.ngayLap = ngayLap;
		this.maNVLap = maNVLap;
		MaKH = maKH;
		this.tongTien = tongTien;
	}
	public HoaDonBanHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
