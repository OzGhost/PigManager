import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Genner {

    private static final SimpleDateFormat date4mat = new SimpleDateFormat(
            "dd-MMM-yy"
            );

    private static int randInRange (int a, int b) {
        return Math.abs((new Random()).nextInt()) % (b - a + 1) + a;
    }

    private static Date dateRandomer () {
        Calendar calen = Calendar.getInstance();
        calen.set(Calendar.YEAR, randInRange(2015, 2016));
        calen.set(Calendar.MONTH, randInRange(0, 16));
        calen.set(Calendar.DAY_OF_MONTH, randInRange(1, 31));
        return calen.getTime();
    }

    private static String idRandomer () {
        String mon = String.format("%2d", randInRange(1, 12));
        String day = String.format("%2d", randInRange(1, 28));
        String textSeq = String.format("%4d", randInRange(1, 9999));
        return "2017" + mon.replace(' ', '0') 
        	+ day.replace(' ', '0') 
        	+ textSeq.replace(' ', '0')
    	;
    }

    private static Map<String, String> pigGenner (int n) {
        Map<String, String> rs = new HashMap<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();

            String maHeo = idRandomer();
            int chieuDai = randInRange(20, 120);
            int chieuCao = chieuDai / 2;
            int canNang = chieuDai * chieuCao / 10;
            Date ngayNuoi = dateRandomer();
            String theTai = String.format("%12d", randInRange(1, 99999));
            String loaiThucAn = "(SELECT REF(lta)"
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
            sb.append(String.format("    '%s',\n", theTai.replace(' ', '0')));
            sb.append(String.format("    %s\n", "LichSuDiChuyen_ntabtyp()"));
            sb.append(");\n");

            rs.put(maHeo, sb.toString());
        }
        return rs;
    }

    private static Map<String, String> sickLogGenner (int n, String[] maHeo) {

        if (n != maHeo.length) {
            System.out.println("---- WARNING: not equal between sicklogs"
                    + " ids of pig");
            return new HashMap<>();
        }
        Map<String, String> rs = new HashMap<>();
        String[] maBenh = {"201702040011", "201702041022", "201702042012"};

        int heoPointer = 0;
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();

            String maBenhAn = idRandomer();
            String heo_ref = String.format("(SELECT REF(h)"
                + " FROM HEO h WHERE h.MaHeo='%s')", maHeo[heoPointer]);
            Date ngayTao = dateRandomer();

            String prefix = "";

            StringBuilder chiTiet = new StringBuilder();
            chiTiet.append("    ChiTietBenh_ntabtyp(\n");

            for (int j = randInRange(1, 5); j >= 0; j--) {
            	String benh_ref = String.format("(SELECT REF(b)"
            			+ " FROM Benh b WHERE b.MaBenh='%s')",
            			maBenh[randInRange(0,2)]);
            	Date phatBenh = dateRandomer();
            	Calendar calen = Calendar.getInstance();
            	calen.setTime(phatBenh);
            	calen.add(Calendar.MONTH, randInRange(1, 5));
            	Date hetBenh = calen.getTime();
                chiTiet.append(prefix);
                chiTiet.append("        ChiTietBenh_objtyp(\n");
                chiTiet.append(String.format("            %s,\n", benh_ref));
                chiTiet.append(String.format("            '%s',\n",
                            date4mat.format(phatBenh)
                        )
                );
                chiTiet.append(String.format("            '%s',\n",
                            date4mat.format(hetBenh)
                        )
                );
                chiTiet.append(String.format("            %s,\n", "null"));
                chiTiet.append(String.format("            %s\n", "null"));
                chiTiet.append("        )\n");
                prefix = "        ,\n";
            }
            chiTiet.append("    )\n");

            sb.append("INSERT INTO BenhAn VALUES (\n");
            sb.append(String.format("    '%s',\n", maBenhAn));
            sb.append(String.format("    %s,\n", heo_ref));
            sb.append(String.format("    '%s',\n", date4mat.format(ngayTao)));
            sb.append(String.format("    %s,\n", "LichSuDungThuoc_ntabtyp()"));
            sb.append(chiTiet.toString());
            sb.append(");\n");
            
            rs.put(maBenhAn, sb.toString());
            heoPointer++;
        }
        return rs;
    }

    private static Map<String, String> cashFlowGenner (int n) {
        if (n < 1)
            return null;
        final Map<String, String> rs = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int nod = randInRange(1, 10);
            Map<String, String> pigs = pigGenner(nod);
            rs.putAll(pigs);

            Map<Integer, String> detail = cashFlowDetailGenner(
                pigs.size(),
                pigs.keySet().toArray(new String[pigs.size()])
            );
            String id = idRandomer();
            Date occur = dateRandomer();
            int type = randInRange(0, 1);
            int cost = detail.keySet().stream()
            		.reduce(0, (x,y) -> x+y)
            ;
            String compiledDetail = detailCompiler(
                detail.values().toArray(new String[detail.size()])
            );

            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ThuChi VALUES (\n");
            sb.append(String.format("    '%s',\n", id));
            sb.append(String.format("    '%s',\n", date4mat.format(occur)));
            sb.append(String.format("    '%s',\n", "this is a note, believe it :D"));
            sb.append(String.format("    %d,\n", type));
            sb.append(String.format("    %d,\n", cost));
            sb.append(String.format("    %s\n", compiledDetail));
            sb.append(");");

            rs.put(id, sb.toString());
        }
        return rs;
    }

    private static Map<Integer, String> cashFlowDetailGenner (int n, String[] ids) {
        if (n < 1 || ids == null || ids.length != n)
            return null;
        final String prefix = "        ";
        final String ctx_prefix = "            ";
        Map<Integer, String> rs = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int cost = randInRange(50, 250);
            StringBuilder sb = new StringBuilder();
            sb.append(prefix + "ChiTietThuChi_objtyp(\n");
            sb.append(ctx_prefix + String.format("'%s',\n", ids[i]));
            sb.append(ctx_prefix + "'H',\n");
            sb.append(ctx_prefix + String.format("%d,\n", cost));
            sb.append(ctx_prefix + String.format("'%s'\n", "this is some text"));
            sb.append(prefix + ")");

            rs.put(cost, sb.toString());
        }
        return rs;
    }

    private static String detailCompiler (String[] detail) {
        StringBuilder sb = new StringBuilder();
        sb.append("ChiTietThuChi_ntabtyp(\n");
        String prefix = "";
        for (int i = 0; i < detail.length; i++) {
            sb.append(prefix);
            sb.append(detail[i]);
            prefix = ",\n";
        }
        sb.append("    )");
        return sb.toString();
    }

    public static void main (String[] args) {
        System.out.println("#### start work");
        File f = (new File("/tmp/sqlScript/tmp.sql"));
        Map<String, String> pigs = pigGenner(100);
        int idsOfPigs = pigs.size();
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(f));
            pigs.values().forEach(pw::println);
            sickLogGenner(100, pigs.keySet().toArray(new String[idsOfPigs]))
            	.values().forEach(pw::println);
            /*
            cashFlowGenner(100).values().forEach(pw::println);
             */
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pw.close();
            } catch (Exception e) {}
        }
        System.out.println("#### done work");
    }
}
