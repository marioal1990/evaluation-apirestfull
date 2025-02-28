package cl.api.apiuser.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private DateUtil() {}

    /**
     * Método que parsea una fecha tipo java.util.Date a String
     * @param date Fecha obtenida de tipo java.util.Date
     * @return Fecha transformada a String
     */
    public static String dateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(ConstantesUtil.DATETIME_FORMAT_DMY_HMS);
        return sdf.format(date);
    }
}
