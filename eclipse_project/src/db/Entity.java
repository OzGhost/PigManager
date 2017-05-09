
package db;

public class Entity {
    public static String idGenner(
            String tableName,
            String idColumn,
            String objtyp,
            String objQuery) {
        return  "DECLARE " +
                "   ida idAssembling; " +
                "   seq varchar2(4); " +
                "   e "+objtyp+"; " +
                "BEGIN" +
                "   ida := idAssembling('', 0);" +
                "   seq := '';" +
                "   e := "+objQuery+";" +
                "   SELECT SUBSTR(id, 0, 8), TO_NUMBER(SUBSTR(id, 9, 4))" +
                "   INTO ida.prefix, ida.last_seq" +
                "   FROM (" +
                "       SELECT " + idColumn + " id" +
                "       FROM (" +
                "           SELECT " + idColumn +
                "           FROM " + tableName +
                "           UNION ALL " +
                "           SELECT "+
                "           TO_CHAR(CURRENT_DATE, 'YYYYMMDD') || '0000' " +
                            idColumn +
                "           FROM Dual" +
                "       )" +
                "       ORDER BY id DESC" +
                "   )" +
                "   WHERE ROWNUM = 1;" +
                "   ida.last_seq := 1 + ida.last_seq;" +
                "   IF (ida.last_seq < 1000 AND ida.last_seq >= 100) THEN" +
                "       seq := '0' || TO_CHAR(ida.last_seq);" +
                "   ELSIF (ida.last_seq < 100 AND ida.last_seq >= 10) THEN" +
                "       seq := '00' || TO_CHAR(ida.last_seq);" +
                "   ELSIF (ida.last_seq < 10) THEN" +
                "       seq := '000' || TO_CHAR(ida.last_seq);" +
                "   END IF;" +
                "   e." + idColumn + " := ida.prefix || seq;" +
                "   INSERT INTO "+tableName+" VALUES (e);" +
                "END;";
    }
}
