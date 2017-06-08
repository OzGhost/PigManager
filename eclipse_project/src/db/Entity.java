
package db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.RefPayload;

/**
 * Helper class for entity operations
 * @author ducnh
 * create: 15-04-2017
 */
public class Entity {
    
    private static String RefPayloadResolve (List<RefPayload> refs) {
        if (refs == null)
            return "";
        final StringBuilder sb = new StringBuilder();
        refs.forEach(sb::append);
        return sb.toString();
    }
    
    /**
     * Generate PS/SQL block: generate id automation
     * and insert record to table specified in parameter
     * @param tableName
     * @param idColumn
     * @param objtyp
     * @param objQuery
     * @return
     */
    public static String idGenner(
            String tableName,
            String idColumn,
            String objtyp,
            String objQuery,
            List<RefPayload> refs
    ) {
        final String Payload = RefPayloadResolve(refs);
        
        return  "DECLARE " +
                "   ida idAssembling; " +
                "   seq varchar2(4); " +
                "   e "+objtyp+"; " +
                "BEGIN" +
                "   ida := idAssembling('', 0);" +
                "   seq := '';" +
                "   e := "+objQuery+";" +
                Payload +
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
                "   seq := numberToSequenceChars(ida.last_seq);" +
                "   e." + idColumn + " := ida.prefix || seq;" +
                "   INSERT INTO "+tableName+" VALUES (e);" +
                "END;";
    }
    
    public static String idGenner(
        String tableName,
        String idColumn,
        String objtyp,
        String objQuery
    ){
        return idGenner(tableName, idColumn, objtyp, objQuery, new ArrayList<>(0));
    }
    
    public static String idGenner(
            String tableName,
            String idColumn,
            String objtyp,
            String objQuery,
            RefPayload ref
        ){
            return idGenner(tableName, idColumn, objtyp, objQuery, Arrays.asList(ref));
        } 
}
