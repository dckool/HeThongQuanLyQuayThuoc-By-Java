package entity;

public class HoaDonNhapHang {
	private String maHDN;
	private String ngayNhap;
	private double tongGiaNhap;
	public String getMaHDN() {
		return maHDN;
	}
	public void setMaHDN(String maHDN) {
		this.maHDN = maHDN;
	}
	public String getNgayNhap() {
		return ngayNhap;
	} 
	public void setNgayNhap(String ngayNhap) {
		this.ngayNhap = ngayNhap;
	}
	public double getTongGiaNhap() {
		return tongGiaNhap;
	}
	public void setTongGiaNhap(double tongGiaNhap) {
		this.tongGiaNhap = tongGiaNhap;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maHDN == null) ? 0 : maHDN.hashCode());
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
		HoaDonNhapHang other = (HoaDonNhapHang) obj;
		if (maHDN == null) {
			if (other.maHDN != null)
				return false;
		} else if (!maHDN.equals(other.maHDN))
			return false;
		return true;
	}
	public HoaDonNhapHang(String maHDN, String ngayNhap, double tongGiaNhap) {
		super();
		this.maHDN = maHDN;
		this.ngayNhap = ngayNhap;
		this.tongGiaNhap = tongGiaNhap;
	}
	public HoaDonNhapHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
