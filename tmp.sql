SET SERVEROUTPUT ON;

DROP TABLE ThucAn;
DROP TYPE ThucAn_objtyp;
DROP TABLE LoaiThucAn;
DROP TYPE LoaiThucAn_objtyp;
DROP TABLE NhaCungCap;
DROP TYPE NhaCungCap_objtyp;

/**
 * Nha Cung Cap Object Type
 */
CREATE TYPE NhaCungCap_objtyp AS OBJECT (
    MaNCC       Char(12),
    TenNCC      Varchar2(64),
    DiaChi      Varchar2(128),
    SoDienThoai Char(12),
    MoTa        Varchar2(512),
    NoPhaiTra   Number(6)
)
/

/**
 * Nha Cung Cap Object Table
 */
CREATE TABLE NhaCungCap OF NhaCungCap_objtyp (MaNCC PRIMARY KEY) 
    OBJECT ID PRIMARY KEY;

/**
 * Insert data into table Nha Cung Cap
 */
INSERT INTO NhaCungCap VALUES (
    '201704120001',
    'Trung tam Cam 2n',
    '122 Ly Thuong Kiet, Q.1, HCM',
    '01672276101',
    'Nha cung cap cam gia suc si/le',
    2000
);
INSERT INTO NhaCungCap VALUES (
    '201704120003',
    'Trai heo giong 9g',
    '12 Ly Thai To, Q.9, HCM',
    '01648848488',
    'Chuyen cung cap heo giong cac loai',
    1000
);
INSERT INTO NhaCungCap VALUES (
    '201704120005',
    'Nha thuoc thu y YThu',
    '144 Bach Dang, Q.3, HCM',
    '0980373024',
    'Cung cap thuoc thu y cac loai tren toan quoc',
    900
);
INSERT INTO NhaCungCap VALUES (
    '201704120007',
    'Trung - tho dien',
    '6 Nguyen Thi Minh Khai, Q.Phu Nhuan, HCM',
    '0908809909',
    'Chuyen cung cap, lap dat, sua chua thiet bi dien gia dung',
    0
);

/**
 * Loai Thuc An Object Type
 */
CREATE TYPE LoaiThucAn_objtyp AS OBJECT (
    MaLoaiThucAn    Char(12),
    TenLoaiThucAn   Varchar2(64),
    MoTa            Varchar2(128)
)
/

/**
 * Loai Thuc An Table
 */
CREATE TABLE LoaiThucAn OF LoaiThucAn_objtyp (MaLoaiThucAn PRIMARY KEY)
    OBJECT ID PRIMARY KEY;

/**
 * Thuc An Object Type
 */
CREATE TYPE ThucAn_objtyp AS OBJECT (
    MaThucAn        Varchar2(12),
    NhaCungCap_ref  REF NhaCungCap_objtyp,
    LoaiThucAn_ref  REF LoaiThucAn_objtyp,
    DonVi           Varchar2(32),
    ConLai          Number(3),
    NgaySanXuat     Date,
    NgayHetHan      Date,
    MucBaoDong      Number(3)
)
/

/**
 * Thuc An Table
 */
CREATE TABLE ThucAn OF ThucAn_objtyp (MaThucAn PRIMARY KEY)
    OBJECT ID PRIMARY KEY;
