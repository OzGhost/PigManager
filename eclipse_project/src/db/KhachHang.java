package db;

import java.util.ArrayList;
import java.util.List;

public class KhachHang {
    
    private static String tableName = "KHACHHANG";
    private static List<Field> columns = new ArrayList<>(4);
    
    private String ma;
    private String hoTen;
    private String ngaySinh;
    private int gioiTinh;
    
    /**
     * Setting column and table name by init function of Entity class
     */
    public static void init(){
        final String[] labels = {"MAKH", "HOTEN", "NGSINH", "GIOITINH"};
        final short[] dataTypeCodes = {
                Field.DATA_TYPE_STRING,
                Field.DATA_TYPE_STRING,
                Field.DATA_TYPE_STRING,
                Field.DATA_TYPE_INT
        };
        final Class<?>[] dataTypes = {
                new String().getClass(),
                new String().getClass(),
                new String().getClass(),
                new Integer(0).getClass()
        };
        final String[] functionNames = {"Ma", "HoTen", "NgaySinh", "GioiTinh"};
        for (int i = 0; i < labels.length; i++){
            columns.add(new Field( labels[i], dataTypeCodes[i], dataTypes[i], functionNames[i] ));
        }
    }
    
    public static void test(){
        System.out.println(tableName);
        columns.forEach(System.out::println);
    }
    
    /**
     * No argument constructor
     */
    public KhachHang() {
        this.ma = "";
        this.hoTen = "";
        this.ngaySinh = "";
        this.gioiTinh = 1;
    }
    
    /**
     * All argument constructor
     * 
     * @param ma
     * @param hoTen
     * @param ngaySinh
     * @param gioiTinh
     */
    public KhachHang(String ma, String hoTen, String ngaySinh, int gioiTinh){
        this.ma = ma;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
    }
    
    // Getter
    public String getMa() {
        return this.ma;
    }
    public String getHoTen() {
        return this.hoTen;
    }
    public String getNgaySinh() {
        return this.ngaySinh;
    }
    public int getGioiTinh() {
        return this.gioiTinh;
    }
    
    // Setter
    public void setMa(String ma) {
        this.ma = ma;
    }
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    public void setGioiTinh(Integer gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
    public static void findOne(){
        new EntityConvertor<KhachHang>().toObject(
                db.sendForResult("select * from " + tableName), columns);
    }
}
