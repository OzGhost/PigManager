package model;

import java.io.File;
import java.sql.ResultSet;
import java.util.List;

import common.Watcher;
import db.db;

public class PigsManagerModel extends ModelBase {
	public static ResultSet getData(String cmd)
    {
        return db.sendForResult(cmd);
    }
    
    public static boolean editData(String cmd)
    {
        return db.send(cmd);
    }

    public void makeReport (List<String> ids, File out, Watcher w) {
        SickReportModel s = new SickReportModel(ids, out, w);
        (new Thread(s)).start();
    }
}
