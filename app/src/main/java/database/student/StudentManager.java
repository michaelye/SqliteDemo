package database.student;

import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import java.util.List;

import model.Student;

/**
 * 方便对Student表的访问
 *
 * Created by michael on 2017/9/22.
 */

public class StudentManager
{
    private SQLiteOpenHelper dbHelper;

    public StudentManager(SQLiteOpenHelper dbHelper)
    {
        this.dbHelper = dbHelper;
    }

    /**
     * 记录是否存在
     * */
    public boolean isExist(String studentId)
    {
        if(TextUtils.isEmpty(studentId))
        {
            return false;
        }
        return StudentContact.isExist(dbHelper, studentId);
    }

    /**
     * 查询所有学生
     * */
    public List<Student> query()
    {
        return StudentContact.query(dbHelper);
    }

    /**
     * 查询某个学生
     * */
    public Student query(String studentId)
    {
        if(TextUtils.isEmpty(studentId))
        {
            return null;
        }
        return StudentContact.query(dbHelper, studentId);
    }

    /**
     * 插入一条数据
     * */
    public void insert(Student student)
    {
        if(student == null)
        {
            return;
        }
        StudentContact.insert(dbHelper, student);
    }

    /**
     * 插入某条记录，如果已经存在就覆盖
     * */
    public void insertOrReplace(Student student)
    {
        if(student == null)
        {
            return;
        }
        StudentContact.insertOrReplace(dbHelper, student);
    }

    /**
     * 更新某条记录
     * */
    public void update(Student student)
    {
        if(student == null)
        {
            return;
        }
        StudentContact.update(dbHelper, student);
    }

    /**
     * 删除某条记录
     * */
    public void delete(String studentId)
    {
        if(TextUtils.isEmpty(studentId))
        {
            return;
        }
        StudentContact.delete(dbHelper, studentId);
    }

    /**
     * 清空表
     * */
    public void clear()
    {
        StudentContact.clear(dbHelper);
    }
}
