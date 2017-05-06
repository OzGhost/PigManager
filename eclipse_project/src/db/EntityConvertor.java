package db;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.List;

public class EntityConvertor<T> {
    public T toObject(ResultSet r, List<Field> columns, Class<T> rsType) {
        T typ = null;
        try {
            typ = (T) rsType.newInstance();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
        final T rs = typ;
        try {
            if (r.next()){
                columns.forEach(field -> {
                    try {
                        Method m = rs.getClass().getMethod("set" + field.getFunctionName(), field.getDataType());
                        Object param = null;
                        short currentDataTypeCode = field.getDataTypeCode();
                        
                        if (currentDataTypeCode == Field.DATA_TYPE_STRING){
                            param = (Object) r.getString(field.getLabel());
                        } else if
                            (currentDataTypeCode == Field.DATA_TYPE_FLOAT){
                            param = (Object) r.getFloat(field.getLabel());
                        } else if
                            (currentDataTypeCode == Field.DATA_TYPE_INT){
                            param = (Object) r.getInt(field.getLabel());
                        } else if
                            (currentDataTypeCode == Field.DATA_TYPE_LONG){
                            param = (Object) r.getLong(field.getLabel());
                        } else if
                            (currentDataTypeCode == Field.DATA_TYPE_SHORT){
                            param = (Object) r.getShort(field.getLabel());
                        } else if
                            (currentDataTypeCode == Field.DATA_TYPE_DOUBLE){
                            param = (Object) r.getDouble(field.getLabel());
                        }
                        
                        m.invoke(rs, param);
                    } catch(Exception e) {
                        // catch stuff
                        e.printStackTrace();
                    }
                });
            }
        } catch(Exception e) {
            // catch stuff
        }
        return rs;
    }
}
