package database;

import android.content.Context;

import database.student.StudentManager;

/**
 * 方便对数据库的访问
 *
 * Created by michael on 2017/9/21.
 */

public class DBManager
{
    /**
     * 单例对象本身
     * */
    private static volatile DBManager dbManager;

    private StudentManager studentManager;

    /**
     * 单例暴露给外部调用
     * */
    public static DBManager getInstance(Context context)
    {
        synchronized (DBManager.class)
        {
            if(dbManager == null)
            {
                dbManager = new DBManager(context);
            }
        }
        return dbManager;
    }

    private DBManager(Context context)
    {
        DBHelper dbHelper = new DBHelper(context);
        studentManager = new StudentManager(dbHelper);
    }

    /**
     * 学生表
     * */
    public StudentManager getStudentManager()
    {
        return this.studentManager;
    }
}
