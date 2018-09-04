package entity;

public class KhachHang {
	//private int maKH; //đánh số thứ tự tăng dần(ht auto đánh số cho maKH khi thêm KH (Mốt về nhớ ép kiểu về int)
	private String CMND; //thuộc tính quan trọng để tìm kiếm(khoa chinh)
	private String hoTenKH;
	private String ngaySinh;
	private String sdt;
	private String moTaKH; //tiền sử bệnh án
	public String getCMND() {
		return CMND;
	}
	public void setCMND(String cMND) {
		CMND = cMND;
	}
	public String getHoTenKH() {
		return hoTenKH;
	}
	public void setHoTenKH(String hoTenKH) {
		this.hoTenKH = hoTenKH;
	}
	public String getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getMoTaKH() {
		return moTaKH;
	}
	public void setMoTaKH(String moTaKH) {
		this.moTaKH = moTaKH;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CMND == null) ? 0 : CMND.hashCode());
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
		KhachHang other = (KhachHang) obj;
		if (CMND == null) {
			if (other.CMND != null)
				return false;
		} else if (!CMND.equals(other.CMND))
			return false;
		return true;
	}
	public KhachHang( String cMND, String hoTenKH, String ngaySinh, String sdt, String moTaKH) {
		super();
		CMND = cMND;
		this.hoTenKH = hoTenKH;
		this.ngaySinh = ngaySinh;
		this.sdt = sdt;
		this.moTaKH = moTaKH;
	}
	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
