public class Genner {

    private static final SimpleDateFormat date4mat = new SimpleDateFormat(
            "dd-MMM-yy"
            );

    private static int randInRange (int a, int b) {
        return (new Random).nextInt() % (b - a + 1) + a;
    }

    private static Date dateRandomer () {
        Calendar calen = Calendar.getInstance();
        calen.set(Calendar.YEAR, randInRange(2016, 2017));
        calen.set(Calendar.MONTH, randInRange(1, 12));
        calen.set(Calendar.DAY_OF_MONTH, randInRange(1, 31));
        return calen.getTime();
    }

    private static String idRandomer () {
        String mon = String.format("%2d", randInRange(1, 12));
        String day = String.format("%2d", randInRange(1, 28));
        String textSeq = String.format("%4d", randInRange(1, 9999));
        mon.replace(' ', '0');
        day.replace(' ', '0');
        textSeq.replace(' ', '0');
        return "2017" + mon + day + textSeq;
    }

    private static Map<String, String> pigGenner (int n) {
        Map<String, String> rs = new HashMap<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();

            String maHeo = idRandomer();
            int chieuDai = randInRange(20, 120);
            int chieuCao = chieuDai / 2;
            int canNang = chieuDai * chieuCao / 10;
            Date ngayNuoi = dateGenner();
            String theTai = String.format("%12d", randInRange(1, 99999));
            theTai.replace(' ', '0');
            String loaiThucAn = "(SELECT REF(lta))"
                + " FROM LoaiThucAn lta WHERE lta.MaLoaiThucAn='201704050180')";
            String chuong = "(SELECT REF(c)"
                + " FROM CHUONG c WHERE c.MaChuong='201703241024')";

            sb.append("INSERT INTO HEO VALUES (\n");
            sb.append(String.format("    '%s',\n", maHeo));
            sb.append(String.format("    %d,\n", chieuCao));
            sb.append(String.format("    %d,\n", chieuDai));
            sb.append(String.format("    %d,\n", canNang));
            sb.append(String.format("    '%s',\n", date4mat.format(ngayNuoi)));
            sb.append(String.format("    %s,\n", "null"));
            sb.append(String.format("    %s,\n", "null"));
            sb.append(String.format("    %s,\n", "null"));
            sb.append(String.format("    %s,\n", loaiThucAn));
            sb.append(String.format("    %s,\n", chuong));
            sb.append(String.format("    '%s',\n", theTai));
            sb.append(String.format("    '%s'\n", "LichSuDiChuyen_ntabtyp()"));
            sb.append(");\n");

            rs.put(maHeo, sb.toString());
        }
        return rs;
    }

    private static Map<String, String> sickLogGenner (int n) {
        Map<String, String> rs = new HashMap<>();
        Map<String, String> heo = pigGenner(n);
        rs.putAll(heo);

        Iterator maHeo = heo.keySet().getIterator();
        String[] maBenh = {"201702040011", "201702041022", "201702042012"};

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();

            String maBenhAn = idRandomer();
            String heo_ref = String.format("(SELECT REF(h)"
                + " FROM HEO h WHERE h.MaHeo='%s')", maHeo.next());
            Date ngayTao = dateRandomer();

            String prefix = "";
            String benh_ref = String.format("(SELECT REF(b)"
                + " FROM Benh b WHERE b.MaBenh='%s')",
                maBenh[randInRange(1,3)]);
            Date phatBenh = dateRandomer();
            Calendar calen = Calendar.getInstance();
            calen.setTime(phatBenh);
            calen.add(Calendar.MONTH, randInRange(1, 5));
            Date hetBenh = calen.getTime();

            StringBuilder chiTiet = new StringBuilder();
            chiTiet.append("    ChiTietBenh_ntabtyp(\n");

            for (int j = randInRange(1, 5); j >= 0; j--) {
                chiTiet.append(prefix);
                chiTiet.append("        ChiTietBenh_objtyp(\n");
                chiTiet.append(String.format("        %s,\n", benh_ref));
                chiTiet.append(String.format("        '%s',\n",
                            date4mat.format(phatBenh)
                        )
                );
                chiTiet.append(String.format("        '%s',\n",
                            date4mat.format(hetBenh)
                        )
                );
                chiTiet.append(String.format("        %s,\n", "null"));
                chiTiet.append(String.format("        %s,\n", "null"));
                chiTiet.append("        )\n");
                prefix = "        ,\n";
            }
            chiTiet.append("    )\n");

            sb.append("INSERT INTO BenhAn VALUES (\n");
            sb.append(String.format("    '%s',\n", maBenhAn));
            sb.append(String.format("    %s,\n", heo_ref));
            sb.append(String.format("    '%s',\n", date4mat.format(NgayTao)));
            sb.append(String.format("    %s,\n", "LichSuDungThuoc_ntabtyp()"));
            sb.append(chiTiet.toString());
            sb.append(");\n")
            
            rs.put(maBenhAn, sb.toString());
        }
        return rs;
    }

    public static void main (String[] args) {
        System.out.println("#### start work");
        SickLogGenner();
        System.out.println("#### done work");
    }
}
