
/**
 * Drop table before drop object type
 */
DROP TABLE ThuChi;
DROP TABLE Tinh;
DROP TABLE BenhAn;
DROP TABLE LichSuThaiKy;
DROP TABLE Heo;
DROP TABLE Benh;
DROP TABLE Thuoc;
DROP TABLE VatDung;
DROP TABLE Chuong;
DROP TABLE LichSuChoAn;
DROP TABLE ThucAn;
DROP TABLE LoaiThucAn;
DROP TABLE NhaCungCap;
/**
 * Drop type before execute remain script
 */
DROP TYPE idAssembling;
DROP TYPE ThuChi_objtyp;
DROP TYPE ChiTietThuChi_ntabtyp;
DROP TYPE ChiTietThuChi_objtyp;
DROP TYPE Tinh_objtyp;
DROP TYPE BenhAn_objtyp;
DROP TYPE LichSuThaiKy_objtyp;
DROP TYPE Heo_objtyp;
DROP TYPE LichSuDiChuyen_ntabtyp;
DROP TYPE LichSuDiChuyen_objtyp;
DROP TYPE ChiTietBenh_ntabtyp;
DROP TYPE ChiTietBenh_objtyp;
DROP TYPE Benh_objtyp;
DROP TYPE LichSuDungThuoc_ntabtyp;
DROP TYPE LichSuDungThuoc_objtyp;
DROP TYPE Thuoc_objtyp;
DROP TYPE VatDung_objtyp;
DROP TYPE LichSuChoAn_objtyp;
DROP TYPE CTChoAn_ntabtyp;
DROP TYPE ChiTietChoAn_objtyp;
DROP TYPE Chuong_objtyp;
DROP TYPE LSXuatKhoTA_ntabtyp;
DROP TYPE LichSuXuatKhoThucAn_objtyp;
DROP TYPE ThucAn_objtyp;
DROP TYPE LoaiThucAn_objtyp;
DROP TYPE NhaCungCap_objtyp;
/**
 * Utility
 */
CREATE TYPE idAssembling AS OBJECT (
    prefix  Varchar2(8),
    last_seq Number
)
/

CREATE OR REPLACE FUNCTION numberToSequenceChars(n IN Number)
RETURN Varchar2 AS
BEGIN
    IF (n < 10) THEN
        return '000' || TO_CHAR(n);
    ELSIF (n < 100) THEN
        return '00' || TO_CHAR(n);
    ELSIF (n < 1000) THEN
        return '0' || TO_CHAR(n);
    ELSIF (n < 10000) THEN
        return TO_CHAR(n);
    ELSE
        return '0000';
    END IF;
END;
/

/**
 * Create object type
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

CREATE TYPE LoaiThucAn_objtyp AS OBJECT (
    MaLoaiThucAn    Char(12),
    TenLoaiThucAn   Varchar2(64),
    MoTa            Varchar2(128),
    DonVi           Varchar2(32),
    MucBaoDong      Number(2)
)
/

CREATE TYPE ThucAn_objtyp AS OBJECT (
    MaThucAn        Char(12),
    NhaCungCap_ref  REF NhaCungCap_objtyp,
    LoaiThucAn_ref  REF LoaiThucAn_objtyp,
    ConLai          Number(3,2),
    NgaySanXuat     Date,
    NgayHetHan      Date
)
/

CREATE TYPE LichSuXuatKhoThucAn_objtyp AS OBJECT (
    ThucAn_ref      REF ThucAn_objtyp,
    SoLuong         Number(3,2),
    DonVi           Varchar2(32)
)
/

CREATE TYPE LSXuatKhoTA_ntabtyp AS TABLE OF LichSuXuatKhoThucAn_objtyp
/

CREATE TYPE Chuong_objtyp AS OBJECT (
    MaChuong        Char(12),
    TinhTrang       Number(1),
    SoLuongToiDa    Number(2),
    ViTri           Varchar2(64),
    MoTa            Varchar2(128)
)
/

CREATE TYPE ChiTietChoAn_objtyp AS OBJECT (
    Chuong_ref      REF Chuong_objtyp,
    LoaiThucAn_ref  REF LoaiThucAn_objtyp,
    SoLuong         Number(3,2),
    DonVi           Varchar2(32)
)
/

CREATE TYPE CTChoAn_ntabtyp AS TABLE OF ChiTietChoAn_objtyp
/

CREATE TYPE LichSuChoAn_objtyp AUTHID CURRENT_USER AS OBJECT (
    MaLSCA          Char(12),
    ThoiGian        Date,
    GhiChu          Varchar2(128),
    ThucAn_ntab     LSXuatKhoTA_ntabtyp,
    Chuong_ntab     CTChoAn_ntabtyp
)
/

CREATE TYPE VatDung_objtyp AS OBJECT (
    MaVatDung       Char(12),
    TenVatDung      Varchar2(64),
    GiaMua          Number(3),
    MoTa            Varchar2(128),
    NhaCungCap_ref  REF NhaCungCap_objtyp,
    Chuong_ref      REF Chuong_objtyp
)
/

CREATE TYPE Thuoc_objtyp AS OBJECT (
    MaThuoc         Char(12),
    TenThuoc        Varchar2(64),
    ThanhPhan       Varchar2(128),
    ChiDinh         Varchar2(128),
    DonVi           Varchar2(32),
    ConLai          Number(3),
    NhaCungCap_ref  REF NhaCungCap_objtyp,
    NgaySanXuat     Date,
    NgayHetHan      Date
)
/

CREATE TYPE LichSuDungThuoc_objtyp AS OBJECT (
    Thuoc_ref       REF Thuoc_objtyp,
    NgayDung        Date,
    Lieu            Number(3),
    DonVi           Varchar2(32),
    HinhThucSuDung  Number(1)
)
/

CREATE TYPE LichSuDungThuoc_ntabtyp AS TABLE OF LichSuDungThuoc_objtyp
/

CREATE TYPE Benh_objtyp AS OBJECT (
    MaBenh          Char(12),
    TenBenh         Varchar2(64),
    MoTaTrieuChung  Varchar2(512),
    LoaiBenh        Varchar2(64)
)
/

CREATE TYPE ChiTietBenh_objtyp AS OBJECT (
    id              Number(3),
    Benh_ref        REF Benh_objtyp,
    NgayPhatBenh    Date,
    NgayHetBenh     Date,
    TinhTrang       Char(6),
    GhiChu          Varchar2(512)
)
/

CREATE TYPE ChiTietBenh_ntabtyp AS TABLE OF ChiTietBenh_objtyp
/

CREATE TYPE LichSuDiChuyen_objtyp AS OBJECT (
    ChuongNguon_ref     REF Chuong_objtyp,
    ChuongDich_ref      REF Chuong_objtyp,
    NgayDiChuyen        Date,
    GhiChu              Varchar2(128)
)
/

CREATE TYPE LichSuDiChuyen_ntabtyp AS TABLE OF LichSuDiChuyen_objtyp
/

CREATE TYPE Heo_objtyp AS OBJECT (
    MaHeo               Char(12),
    ChieuCao            Number(5,2),
    ChieuDai            Number(5,2),
    CanNang             Number(5,2),
    NgayBatDauNuoi      Date,
    NgayKetThuc         Date,
    Nguon_ref           REF Heo_objtyp,
    NhaCungCap_ref      REF NhaCungCap_objtyp,
    LoaiThucAn_ref      REF LoaiThucAn_objtyp,
    Chuong_ref          REF Chuong_objtyp,
    MaTheTai            Varchar2(12),
    LSDiChuyen_ntab     LichSuDiChuyen_ntabtyp
)
/

CREATE TYPE LichSuThaiKy_objtyp AS OBJECT (
    MaLSTK          Char(12),
    NgayGhiNhan     Date,
    NoiDung         Varchar2(128),
    Heo_ref         REF Heo_objtyp
)
/

CREATE TYPE BenhAn_objtyp AUTHID CURRENT_USER AS OBJECT (
    MaBenhAn            Char(12),
    Heo_ref             REF Heo_objtyp,
    NgayTao             Date,
    LSDungThuoc_ntab    LichSuDungThuoc_ntabtyp,
    ChiTietBenh_ntab    ChiTietBenh_ntabtyp,
    MEMBER FUNCTION themChiTietBenh RETURN NUMBER
);
/

CREATE OR REPLACE TYPE BODY BenhAn_objtyp AS
    MEMBER FUNCTION themChiTietBenh RETURN NUMBER AS
        NUMBER nextId;
    BEGIN
        SELECT MAX(id) INTO nextId FROM TABLE(ChiTietBenh_ntab);
        nextId := nextId + 1;
        /*
        INSERT INTO TABLE(ChiTietBenh_ntab) VALUES (
            nextId,
            (SELECT REF(b) FROM Benh WHERE MaBenh=benhId)
            ngayPhatBenh,
            ngayHetBenh,
            tinhTrang,
            ghiChu
        );
        */
        RETURN nextId;
    END;
END;
/

CREATE TYPE Tinh_objtyp AS OBJECT (
    MaTinh              Char(12),
    TenTinh             Varchar2(64),
    NguonGoc            Varchar2(64),
    DacDiem             Varchar2(128),
    NhaCungCap_ref      REF NhaCungCap_objtyp,
    NgaySanXuat         Date,
    NgayHetHan          Date,
    LichSuThaiKy_ref    REF LichSuThaiKy_objtyp
)
/

CREATE TYPE ChiTietThuChi_objtyp AS OBJECT (
    MaDoiTuongThuChi    Char(12),
    LoaiDoiTuongThuChi  Char(1),
    Gia                 Number(4),
    GhiChu              Varchar2(256)
)
/

CREATE TYPE ChiTietThuChi_ntabtyp AS TABLE OF ChiTietThuChi_objtyp
/

CREATE TYPE ThuChi_objtyp AUTHID CURRENT_USER AS OBJECT (
    MaThuChi        Char(12),
    NgayThuChi      Date,
    GhiChu          Varchar2(256),
    LoaiThuChi      Number(1),
    TriGia          Number(5),
    ChiTiet_ntab    ChiTietThuChi_ntabtyp
)
/

/**
 * Create table from object type
 */
CREATE TABLE NhaCungCap OF NhaCungCap_objtyp (MaNCC PRIMARY KEY) 
    OBJECT ID PRIMARY KEY
/

CREATE TABLE LoaiThucAn OF LoaiThucAn_objtyp (MaLoaiThucAn PRIMARY KEY)
    OBJECT ID PRIMARY KEY
/

CREATE TABLE ThucAn OF ThucAn_objtyp (
        PRIMARY KEY (MaThucAn),
        FOREIGN KEY (LoaiThucAn_ref) REFERENCES LoaiThucAn,
        FOREIGN KEY (NhaCungCap_ref) REFERENCES NhaCungCap
    )
    OBJECT ID PRIMARY KEY
/

CREATE TABLE Chuong OF Chuong_objtyp (MaChuong PRIMARY KEY)
    OBJECT ID PRIMARY KEY
/

CREATE TABLE LichSuChoAn OF LichSuChoAn_objtyp (MaLSCA PRIMARY KEY)
    OBJECT ID PRIMARY KEY
    NESTED TABLE ThucAn_ntab STORE AS DanhSachThucAnDaDung 
    RETURN AS LOCATOR
    NESTED TABLE Chuong_ntab STORE AS DanhSachChuongChoAn 
    RETURN AS LOCATOR
/
ALTER TABLE DanhSachThucAnDaDung
    ADD (SCOPE FOR (ThucAn_ref) IS ThucAn);
ALTER TABLE DanhSachChuongChoAn
    ADD (SCOPE FOR (Chuong_ref) IS Chuong,
         SCOPE FOR (LoaiThucAn_ref) IS LoaiThucAn);

CREATE TABLE  VatDung OF VatDung_objtyp (
        PRIMARY KEY (MaVatDung),
        FOREIGN KEY (NhaCungCap_ref) REFERENCES NhaCungCap,
        FOREIGN KEY (Chuong_ref) REFERENCES Chuong
    )
    OBJECT ID PRIMARY KEY
/

CREATE TABLE Thuoc OF Thuoc_objtyp (
        PRIMARY KEY (MaThuoc),
        FOREIGN KEY (NhaCungCap_ref) REFERENCES NhaCungCap
    )
    OBJECT ID PRIMARY KEY
/

CREATE TABLE Benh OF Benh_objtyp (MaBenh PRIMARY KEY)
    OBJECT ID PRIMARY KEY
/

CREATE TABLE Heo OF Heo_objtyp (
        PRIMARY KEY (MaHeo),
        FOREIGN KEY (Nguon_ref) REFERENCES Heo,
        FOREIGN KEY (NhaCungCap_ref) REFERENCES NhaCungCap,
        FOREIGN KEY (LoaiThucAn_ref) REFERENCES LoaiThucAn,
        FOREIGN KEY (Chuong_ref) REFERENCES Chuong
    )
    OBJECT ID PRIMARY KEY
    NESTED TABLE LSDiChuyen_ntab STORE AS LichSuDiChuyen
/
ALTER TABLE LichSuDiChuyen
    ADD (SCOPE FOR (ChuongNguon_ref) IS Chuong,
         SCOPE FOR (ChuongDich_ref) IS Chuong);

CREATE TABLE LichSuThaiKy OF LichSuThaiKy_objtyp (
    PRIMARY KEY (MaLSTK),
    FOREIGN KEY (Heo_ref) REFERENCES Heo
    )
    OBJECT ID PRIMARY KEY
/

CREATE TABLE BenhAn OF BenhAn_objtyp (
        PRIMARY KEY (MaBenhAn),
        FOREIGN KEY (Heo_ref) REFERENCES Heo
    )
    OBJECT ID PRIMARY KEY
    NESTED TABLE LSDungThuoc_ntab STORE AS LichSuSuDungThuoc,
    NESTED TABLE ChiTietBenh_ntab STORE AS ChiTietBenh
/
ALTER TABLE LichSuSuDungThuoc
    ADD (SCOPE FOR (Thuoc_ref) IS Thuoc);
ALTER TABLE ChiTietBenh
    ADD (SCOPE FOR (Benh_ref) IS Benh);

CREATE TABLE Tinh OF Tinh_objtyp (
        PRIMARY KEY (MaTinh),
        FOREIGN KEY (NhaCungCap_ref) REFERENCES NhaCungCap,
        FOREIGN KEY (LichSuThaiKy_ref) REFERENCES LichSuThaiKy
    )
    OBJECT ID PRIMARY KEY
/

CREATE TABLE ThuChi OF ThuChi_objtyp (MaThuChi PRIMARY KEY)
    OBJECT ID PRIMARY KEY
    NESTED TABLE ChiTiet_ntab STORE AS ChiTietThuChi
/

/**
 * Insert data
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

INSERT INTO LoaiThucAn VALUES (
    '201702050009',
    'Con co 00-09',
    'Thuc an tap an cho heo con tu 7 ngay tuoi - 9 kg',
    'Bao',
    3
);
INSERT INTO LoaiThucAn VALUES (
    '201702090920',
    'Con co 09-20',
    'Thuc an hon hop cho heo con tu 9kg - 20kg',
    'Bao',
    8
);
INSERT INTO LoaiThucAn VALUES (
    '201702052050',
    'Delice 15',
    'Thuc an hon hop san pham cao cap cho trang trai heo tu 20kg - 50kg',
    'Bao',
    10
);
INSERT INTO LoaiThucAn VALUES (
    '201704050180',
    'Con co C18A',
    'Hon hop dung cho heo nai mang thai',
    'Bao',
    3
);
INSERT INTO LoaiThucAn VALUES (
    '201704020012',
    'Porcy 18B',
    'Hon hop dun cho heo nai nuoi con',
    'Bao',
    3
);

INSERT INTO Chuong VALUES (
    '201702051001',
    1,
    20,
    'Phia trong day trai',
    'Chuong nuoi heo thit'
);
INSERT INTO Chuong VALUES (
    '201703241024',
    1,
    10,
    'Ben trai cua vao',
    'Chuong nuoi heo con tach bay'
);
INSERT INTO Chuong VALUES (
    '201703052016',
    0,
    1,
    'Phia trong day ben phai',
    'Chuong nuoi heo nai'
);
INSERT INTO Chuong VALUES (
    '201703021234',
    2,
    1,
    'Phia ngoai day ben phai',
    'Chuong nuoi heo nai'
);

INSERT INTO ThucAn VALUES (
    '201702010012',
    (SELECT REF(ncc) FROM NhaCungCap ncc WHERE ncc.MaNCC='201704120001'),
    (SELECT REF(lta) FROM LoaiThucAn lta WHERE lta.MaLoaiThucAn='201702050009'),
    0.5,
    TO_DATE('2017-04-01', 'yyyy-mm-dd'),
    TO_DATE('2017-06-01', 'yyyy-mm-dd')
);
INSERT INTO ThucAn VALUES (
    '201702010022',
    (SELECT REF(ncc) FROM NhaCungCap ncc WHERE ncc.MaNCC='201704120001'),
    (SELECT REF(lta) FROM LoaiThucAn lta WHERE lta.MaLoaiThucAn='201702050009'),
    1,
    TO_DATE('2017-04-07', 'yyyy-mm-dd'),
    TO_DATE('2017-06-08', 'yyyy-mm-dd')
);
INSERT INTO ThucAn VALUES (
    '201702010222',
    (SELECT REF(ncc) FROM NhaCungCap ncc WHERE ncc.MaNCC='201704120001'),
    (SELECT REF(lta) FROM LoaiThucAn lta WHERE lta.MaLoaiThucAn='201702050009'),
    0.5,
    TO_DATE('2017-04-21', 'yyyy-mm-dd'),
    TO_DATE('2017-06-11', 'yyyy-mm-dd')
);
INSERT INTO ThucAn VALUES (
    '201702010013',
    (SELECT REF(ncc) FROM NhaCungCap ncc WHERE ncc.MaNCC='201704120001'),
    (SELECT REF(lta) FROM LoaiThucAn lta WHERE lta.MaLoaiThucAn='201702050009'),
    1,
    TO_DATE('2017-04-01', 'yyyy-mm-dd'),
    TO_DATE('2017-06-01', 'yyyy-mm-dd')
);
INSERT INTO ThucAn VALUES (
    '201702010112',
    (SELECT REF(ncc) FROM NhaCungCap ncc WHERE ncc.MaNCC='201704120007'),
    (SELECT REF(lta) FROM LoaiThucAn lta WHERE lta.MaLoaiThucAn='201702050920'),
    0.7,
    TO_DATE('2017-04-01', 'yyyy-mm-dd'),
    TO_DATE('2017-06-01', 'yyyy-mm-dd')
);
INSERT INTO ThucAn VALUES (
    '201702010092',
    (SELECT REF(ncc) FROM NhaCungCap ncc WHERE ncc.MaNCC='201704120007'),
    (SELECT REF(lta) FROM LoaiThucAn lta WHERE lta.MaLoaiThucAn='201702050920'),
    1,
    TO_DATE('2017-03-01', 'yyyy-mm-dd'),
    TO_DATE('2017-05-01', 'yyyy-mm-dd')
);
INSERT INTO LichSuChoAn VALUES (
    '201705060001',
    SYSDATE,
    'Cho heo con an',
    LSXuatKhoTA_ntabtyp(),
    CTChoAn_ntabtyp()
);
INSERT INTO TABLE (
    SELECT l.ThucAn_ntab
    FROM LichSuChoAn l
    WHERE l.MaLSCA = '201705060001'
)
    SELECT REF(ta), 0.5, 'Bao'
    FROM ThucAn ta
    WHERE ta.MaThucAn = '201702010222'
    UNION ALL
    SELECT REF(ta), 0.5, 'Bao'
    FROM ThucAn ta
    WHERE ta.MaThucAn = '201702010012'
;
INSERT INTO TABLE (
    SELECT l.Chuong_ntab
    FROM LichSuChoAn l
    WHERE l.MaLSCA = '201705060001'
)
    SELECT REF(c), REF(lta), 0.2, 'Bao'
    FROM Chuong c, LoaiThucAn lta
    WHERE c.MaChuong = '201703241024' AND lta.MaLoaiThucAn = '201702050009'
    UNION ALL
    SELECT REF(c), REF(lta), 0.8, 'Bao'
    FROM Chuong c, LoaiThucAn lta
    WHERE c.MaChuong = '201702051001' AND lta.MaLoaiThucAn = '201702050009'
;

INSERT INTO VatDung VALUES (
    '201701011011',
    'Quat gio',
    210,
    'Quat treo tuong, dung cho heo nai',
    (SELECT REF(ncc) FROM NhaCungCap ncc WHERE ncc.MaNCC='201704120007'),
    (SELECT REF(c) FROM CHUONG c WHERE c.MaChuong='201703052016')
);
INSERT INTO VatDung VALUES (
    '201701011019',
    'Bong den suoi',
    180,
    'Bong suoi cho heo con duoi 8 tuan tuoi',
    (SELECT REF(ncc) FROM NhaCungCap ncc WHERE ncc.MaNCC='201704120007'),
    (SELECT REF(c) FROM CHUONG c WHERE c.MaChuong='201703052016')
);
INSERT INTO VatDung VALUES (
    '201701012919',
    'Mang an tu dong',
    280,
    'Mang an tu dong bang inoc, 50kg',
    (SELECT REF(ncc) FROM NhaCungCap ncc WHERE ncc.MaNCC='201704120001'),
    (SELECT REF(c) FROM CHUONG c WHERE c.MaChuong='201702051001')
);

INSERT INTO Thuoc VALUES (
    '201701020023',
    'Bocin-pharm',
    'Florphenicol 15%, DOxycyclin 7.5%',
    'Suyen, tu huyet trung, MMA',
    'ml',
    50,
    (SELECT REF(ncc) FROM NhaCungCap ncc WHERE ncc.MaNCC='201704120005'),
    TO_DATE('2017-05-01', 'yyyy-mm-dd'),
    TO_DATE('2017-08-01', 'yyyy-mm-dd')
);
INSERT INTO Thuoc VALUES (
    '201701020203',
    'Pharcolapi',
    'Ampicillin 10%, colistin 25MUI',
    'Hon dich khang sinh tong hop da gia',
    'ml',
    50,
    (SELECT REF(ncc) FROM NhaCungCap ncc WHERE ncc.MaNCC='201704120005'),
    TO_DATE('2017-05-01', 'yyyy-mm-dd'),
    TO_DATE('2017-08-01', 'yyyy-mm-dd')
);
INSERT INTO Thuoc VALUES (
    '201701020403',
    'Phartocin',
    'Oxytocin 10UI/ml',
    'Tiem thuc de, kich sua, tri viem vu',
    'ml',
    50,
    (SELECT REF(ncc) FROM NhaCungCap ncc WHERE ncc.MaNCC='201704120005'),
    TO_DATE('2017-05-01', 'yyyy-mm-dd'),
    TO_DATE('2017-08-01', 'yyyy-mm-dd')
);

INSERT INTO Benh VALUES (
    '201702042012',
    'Dich ta lon',
    'u ru, bo an, sot cao 40-42 0C, cac phan da mong ung do',
    'Nguy hiem'
);
INSERT INTO Benh VALUES (
    '201702041022',
    'Pho thuong han',
    'Giam an, bu it, uong nhieu nuoc lanh, gam tuong, long xu, dung run run, sot caao',
    'Tuong doi nguy hiem'
);
INSERT INTO Benh VALUES (
    '201702040011',
    'Dong dau lon',
    'De non, bieng an, tai hoi xam',
    'Bao dong'
);

INSERT INTO HEO VALUES (
    '201701029382',
    2.5,
    1.9,
    1.2,
    '20-DEC-16',
    '12-JUN-17',
    null,
    (SELECT REF(ncc) FROM NhaCungCap ncc WHERE ncc.MaNCC='201704120003'),
    (SELECT REF(lta) FROM LoaiThucAn lta WHERE lta.MaLoaiThucAn='201704050180'),
    (SELECT REF(c) FROM Chuong c WHERE c.MaChuong='201703021234'),
    'thetaiso1',
    LichSuDiChuyen_ntabtyp()
);
INSERT INTO HEO VALUES (
    '201701229322',
    2.5,
    1.9,
    120.21,
    '20-MAY-16',
    '12-JUN-17',
    null,
    (SELECT REF(ncc) FROM NhaCungCap ncc WHERE ncc.MaNCC='201704120003'),
    (SELECT REF(lta) FROM LoaiThucAn lta WHERE lta.MaLoaiThucAn='201704050180'),
    (SELECT REF(c) FROM Chuong c WHERE c.MaChuong='201703021234'),
    'thetaiso1',
    LichSuDiChuyen_ntabtyp()
);

INSERT INTO LichSuThaiKy VALUES (
    '201710109293',
    '12-MAY-17',
    'Tha tinh dot 1',
    (SELECT REF(h) FROM HEO h WHERE h.MaHeo='201701029382')
);

INSERT INTO Tinh VALUES (
    '201710202923',
    'MaRock',
    'VietNam',
    'Heo bo',
    (SELECT REF(ncc) FROM NhaCungCap ncc WHERE ncc.MaNCC='201704120003'),
    '18-MAY-17',
    '20-MAY-17',
    (SELECT REF(lstk) FROM LichSuThaiKy lstk WHERE lstk.MaLSTK='201710109293')
);
