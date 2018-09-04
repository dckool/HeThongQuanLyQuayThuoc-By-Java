create database QuanLyQuayThuoc_Nhom09
on
	primary (name = QuanLyQuayThuocdata, filename = 'E:\CSDL\QuanLyQuayThuoc_Nhom09.mdf', size = 20MB, maxsize = 40MB, filegrowth = 20%)
log on
	(name = QuanLyQuayThuoclog, filename = 'E:\CSDL\QuanLyQuayThuoc_Nhom09.ldf', size = 5MB, maxsize = 10MB, filegrowth = 1MB)

use QuanLyQuayThuoc_Nhom09

create table NhanVien
(MaNV varchar(10) primary key not null, 
HoTen nvarchar(50) not null,
GioiTinh nvarchar(10) not null,
NgaySinh date not null,
SDT varchar(15),
DiaChi nvarchar(50),
Pass varchar(30) not null,
CMND varchar(30) not null
)

create table DSThuoc
(MaThuoc varchar(10) primary key not null,
Ten varchar(30) not null,
Loai nvarchar(30) not null,
SoLuong int,
DonViTinh varchar(10),
NCC nvarchar(50),
GiaNhap money,
GiaBan money,
HanSD date,
)

create table KhachHang
(CMND varchar(15) primary key not null,
TenKH nvarchar(30),
NgaySinh date,
SDT varchar(15),
MoTa nvarchar(100)
)

create table HoaDon
(MaHD varchar(10) primary key not null,
MaNVLap varchar(10) foreign key references NhanVien(MaNV),  --id của nv lập
NgayLap date,
MaKH varchar(15) ,
TongTien money
)

create table ChiTietHoaDon
(MaHD varchar(10) foreign key references HoaDon(MaHD),
MaThuoc varchar(10) foreign key references DSThuoc(MaThuoc),
TenThuoc nvarchar(30),
SoLuong int not null,
DonGia money not null
)

create table HoaDonNhap
(MaHDN varchar(10) primary key not null,
NgayLap date,
TongGiaNhap money
)

create table ChiTietHoaDonNhap
(MaHDN varchar(10) foreign key references HoaDonNhap(MaHDN),
MaThuoc varchar(10) foreign key references DSThuoc(MaThuoc),
SoLuong int not null,
HSD date,
TinhTrang int
)


set dateformat dMy

insert NhanVien values('QL001',N'Trần Đình Chiến',N'Nam','26/03/1997','0977621504',N'312 Quang Trung','1','150917611')
insert NhanVien values('NV001',N'Trần Hùng Cường',N'Nam','10/02/1997','0987212312',N'Nguyễn Oanh','1','150569211')
insert NhanVien values('NV002',N'Nguyễn Văn Mạnh Cường',N'Nam','21/09/1997','01221212121',N'Bình Tân','1','150514311')

insert DSThuoc values ('A001','COTRIM STADA FORTE','Khang Sinh',500,'Vien','cCc',900,1500,'20/10/2018')
insert DSThuoc values ('A002','COTTUF L100ML','Ho Hap',70,'Lo','cCc',13000,20000,'20/10/2018')
insert DSThuoc values ('A003','COVERSYL 5ML L30V','Tim mach',50,'Lo','cCc',22000,25000,'20/10/2018')
insert DSThuoc values ('A004','CURAM 625MG','Khang Sinh',1000,'Vien','cCc',500,900,'20/10/2020')
insert DSThuoc values ('A005','CURIOSIN T15G','Dung Ngoai',20,'Hop','cCc',30000,35000,'20/12/2018')
insert DSThuoc values ('A006','DAI DAM DUC THINH','Thuc Pham Chuc Nang',70,'Lo','cCc',17000,22000,'20/10/2018')
insert DSThuoc values ('A007','Dactarin T10G','Dung Ngoai',200,'Tupe','cCc',5000,7000,'20/10/2018')
insert DSThuoc values ('A008','Davic H10G x 10G','Vitamin',100,'Goi','cCc',15000,20000,'20/10/2018')
insert DSThuoc values ('A009','Decogen H25V x 4V','Ha Sot Giam Dau',500,'Vien','cCc',300,500,'20/10/2018')
insert DSThuoc values ('A010','Doctor Cool H3BF','Ha Sot Giam Dau',70,'Lo','cCc',10000,15000,'20/10/2018')
insert DSThuoc values ('A011','Dogalic L100V','Thuc Pham Chuc Nang',70,'Lo','cCc',10000,15000,'20/10/2018')
insert DSThuoc values ('A012','Doctor Cool H3B','Ha Sot Giam Dau',70,'Lo','cCc',10000,15000,'20/10/2018')
insert DSThuoc values ('A013','Doctor Cool H5B','Ha Sot Giam Dau',70,'Lo','cCc',10000,15000,'20/10/2018')
insert DSThuoc values ('A014','Doctor Cool H9B','Ha Sot Giam Dau',70,'Lo','cCc',10000,15000,'20/10/2018')
insert DSThuoc values ('A015','Doctor Cool H17B','Ha Sot Giam Dau',70,'Lo','cCc',10000,15000,'20/10/2018')
insert DSThuoc values ('A016','Amikachin','Khang sinh',1000,'Vien','cCc',600,800,'20/10/2018')
insert DSThuoc values ('A017','Amumu','Khang sinh',1000,'Vien','cCc',650,800,'20/10/2018')
insert DSThuoc values ('A018','Alista','Khang sinh',1000,'Vien','cCc',750,850,'20/10/2018')
insert DSThuoc values ('A019','Bilumium','Dung ngoai',100,'Tupe','cCc',15000,18000,'20/10/2018')
insert DSThuoc values ('A020','Bamill','Thuc Pham Chuc Nang',100,'Lo','cCc',30000,35000,'20/10/2018')
insert DSThuoc values ('A021','Erythromycin','Khang sinh',1000,'Lo','cCc',600,750,'20/10/2018')
insert DSThuoc values ('A022','Evelynum','Vitamin',100,'Lo','cCc',25000,30000,'20/10/2018')
insert DSThuoc values ('A023','Foctocol','Ha Sot Giam Dau',1000,'Vien','cCc',450,600,'20/10/2018')
insert DSThuoc values ('A024','Killua','Dung ngoai',100,'Tupe','cCc',40000,50000,'20/10/2018')
insert DSThuoc values ('A025','VitaMila','Vitamin',100,'Lo','cCc',50000,55000,'20/10/2018')



insert KhachHang values ('123456789',N'Nguyễn Đức Vũ','28/03/1997','0121354643',N'Bệnh trĩ mãn tính')
insert KhachHang values ('987654321',N'Nguyễn Tấn Đạt','17/8/1996','0904758375',N'Xi đa thời kì cuối')
insert KhachHang values ('111111111',N'Tùng Văn Sơn','27/6/1999','0908564664',N'Đau đầu rùa')

insert HoaDon values ('HD1','NV001','15/9/2017','123456789',14000)
insert HoaDon values ('HD2','NV002','16/10/2017','123456789',20000)
insert HoaDon values ('HD3','NV001','17/10/2017','123456789',20000)
insert HoaDon values ('HD4','NV002','18/9/2017','987654321',20000)
insert HoaDon values ('HD5','NV001','19/9/2015','987654321',20000)
insert HoaDon values ('HD6','NV002','20/9/2017','987654321',20000)
insert HoaDon values ('HD7','NV001','21/9/2016','111111111',20000)
insert HoaDon values ('HD8','NV002','22/9/2017','111111111',20000)


insert ChiTietHoaDon values('HD1','A001','COTRIM STADA FORTE',1,2000)
insert ChiTietHoaDon values('HD1','A002','COTTUF L100ML',1,3000)
insert ChiTietHoaDon values('HD1','A003','COVERSYL 5ML L30V',1,4000)
insert ChiTietHoaDon values('HD1','A004','CURAM 625MG',1,5000)

insert ChiTietHoaDon values('HD2','A001','COTRIM STADA FORTE',1,5000)
insert ChiTietHoaDon values('HD2','A002','COTTUF L100ML',1,5000)
insert ChiTietHoaDon values('HD2','A003','COVERSYL 5ML L30V',1,4000)
insert ChiTietHoaDon values('HD2','A004','CURAM 625MG',1,6000)

insert ChiTietHoaDon values('HD3','A001','COTRIM STADA FORTE',1,5000)
insert ChiTietHoaDon values('HD3','A002','COTTUF L100ML',1,5000)
insert ChiTietHoaDon values('HD3','A003','COVERSYL 5ML L30V',1,4000)
insert ChiTietHoaDon values('HD3','A004','CURAM 625MG',1,6000)

insert ChiTietHoaDon values('HD4','A001','COTRIM STADA FORTE',1,5000)
insert ChiTietHoaDon values('HD4','A002','COTTUF L100ML',1,5000)
insert ChiTietHoaDon values('HD4','A003','COVERSYL 5ML L30V',1,4000)
insert ChiTietHoaDon values('HD4','A004','CURAM 625MG',1,6000)

insert ChiTietHoaDon values('HD5','A001','COTRIM STADA FORTE',1,5000)
insert ChiTietHoaDon values('HD5','A002','COTTUF L100ML',1,5000)
insert ChiTietHoaDon values('HD5','A003','COVERSYL 5ML L30V',1,4000)
insert ChiTietHoaDon values('HD5','A004','CURAM 625MG',1,6000)

insert ChiTietHoaDon values('HD6','A001','COTRIM STADA FORTE',1,5000)
insert ChiTietHoaDon values('HD6','A002','COTTUF L100ML',1,5000)
insert ChiTietHoaDon values('HD6','A003','COVERSYL 5ML L30V',1,4000)
insert ChiTietHoaDon values('HD6','A004','CURAM 625MG',1,6000)

insert ChiTietHoaDon values('HD7','A001','COTRIM STADA FORTE',1,5000)
insert ChiTietHoaDon values('HD7','A002','COTTUF L100ML',1,5000)
insert ChiTietHoaDon values('HD7','A003','COVERSYL 5ML L30V',1,4000)
insert ChiTietHoaDon values('HD7','A004','CURAM 625MG',1,6000)

insert ChiTietHoaDon values('HD8','A001','COTRIM STADA FORTE',1,5000)
insert ChiTietHoaDon values('HD8','A002','COTTUF L100ML',1,5000)
insert ChiTietHoaDon values('HD8','A003','COVERSYL 5ML L30V',1,4000)
insert ChiTietHoaDon values('HD8','A004','CURAM 625MG',1,6000)

insert HoaDonNhap values('N1','27/6/1999',200000)
insert HoaDonNhap values('N2','26/6/2015',300000)

insert ChiTietHoaDonNhap values('N1','A001',100,'26/10/2018',1)
insert ChiTietHoaDonNhap values('N1','A002',200,'26/10/2018',1)
insert ChiTietHoaDonNhap values('N1','A003',300,'26/10/2018',1)
insert ChiTietHoaDonNhap values('N1','A004',400,'26/10/2018',1)

insert ChiTietHoaDonNhap values('N2','A005',400,'26/10/2018',1)
insert ChiTietHoaDonNhap values('N2','A006',500,'26/10/2018',1)
insert ChiTietHoaDonNhap values('N2','A007',600,'26/10/2018',1)
insert ChiTietHoaDonNhap values('N2','A008',700,'26/10/2018',1)
