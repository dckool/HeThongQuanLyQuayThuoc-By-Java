package entity;

public class CTHoaDonNhap {
	private String maHDN;
	private String maThuoc;
	private int soLuong;
	private String hsd;
	private int tinhTrang;
	public String getMaHDN() {
		return maHDN;
	}
	public void setMaHDN(String maHDN) {
		this.maHDN = maHDN;
	} 
	public String getMaThuoc() {
		return maThuoc;
	}
	public void setMaThuoc(String maThuoc) {
		this.maThuoc = maThuoc;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public String getHsd() {
		return hsd;
	}
	public void setHsd(String hsd) {
		this.hsd = hsd;
	}
	
	public int getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(int tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maHDN == null) ? 0 : maHDN.hashCode());
		result = prime * result + ((maThuoc == null) ? 0 : maThuoc.hashCode());
		return result;
	}
	
	public CTHoaDonNhap(String maHDN, String maThuoc, int soLuong, String hsd, int tinhTrang) {
		super();
		this.maHDN = maHDN;
		this.maThuoc = maThuoc;
		this.soLuong = soLuong;
		this.hsd = hsd;
		this.tinhTrang = tinhTrang;
	}
	public CTHoaDonNhap() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
