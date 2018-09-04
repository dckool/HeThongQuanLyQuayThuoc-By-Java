package entity;

public class NhanVien {
	private String maNv;
	private String hoTenNV;
	private String ngaySinh;
	private String gioiTinh;
	private String sdt;
	private String diaChi;
	private String pass; //dùng khi đăng nhập
	private String cmnd;
	
	public String getCmnd() {
		return cmnd;
	}
 
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getMaNv() {
		return maNv;
	}

	public void setMaNv(String maNv) {
		this.maNv = maNv;
	}

	public String getHoTenNV() {
		return hoTenNV;
	}

	public void setHoTenNV(String hoTenNV) {
		this.hoTenNV = hoTenNV;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maNv == null) ? 0 : maNv.hashCode());
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
		NhanVien other = (NhanVien) obj;
		if (maNv == null) {
			if (other.maNv != null)
				return false;
		} else if (!maNv.equals(other.maNv))
			return false;
		return true;
	}

	public NhanVien(String maNv, String hoTenNV, String ngaySinh, String gioiTinh, String sdt, String diaChi,
			String pass,String cmnd) {
		super();
		this.maNv = maNv;
		this.hoTenNV = hoTenNV;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.pass = pass;
		this.cmnd = cmnd;
	}

	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
