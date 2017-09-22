package database.student;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import model.Student;

import static android.content.ContentValues.TAG;

/**
 * 对Student表的操作进行包装
 *
 * Created by michael on 2017/9/20.
 */

public class StudentContact
{
    /**
     * 表名
     * */
    public static final String TABLE_NAME = "student";

    /**
     * 所有的字段
     * */
    private static final String[] AVAILABLE_PROJECTION = new String[]{
            StudentColumns.SID,
            StudentColumns.NAME,
            StudentColumns.AGE,
    };

    /**
     * 创建表的语句
     * */
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + StudentColumns._ID + " INTEGER PRIMARY KEY,"
            + StudentColumns.SID + " TEXT,"
            + StudentColumns.NAME + " TEXT,"
            + StudentColumns.AGE + " INTEGER"
            + ")";

    /**
     * 删除表的语句
     * */
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    /**
     * 判断下所请求的字段是否都存在，分支出现操作的字段不存在的错误
     */
    private static void checkColumns(String[] projection)
    {
        if (projection != null)
        {
            HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
            HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(AVAILABLE_PROJECTION));
            if (!availableColumns.containsAll(requestedColumns))
            {
                throw new IllegalArgumentException(TAG + "checkColumns()-> Unknown columns in projection");
            }
        }
    }

    /**
     * 记录是否存在
     * */
    public static boolean isExist(SQLiteOpenHelper helper, String studentId)
    {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, AVAILABLE_PROJECTION, StudentColumns.SID + " =? ", new String[] {studentId}, null, null, null);
        if (cursor.moveToFirst())
        {
            Log.d(TAG, "缓存存在");
            cursor.close();
            return true;
        }
        else
        {
            Log.d(TAG, "缓存不存在");
            cursor.close();
            return false;
        }
    }

    /**
     * 查询所有的学生
     * */
    public static List<Student> query(SQLiteOpenHelper helper)
    {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, AVAILABLE_PROJECTION, null, null, null, null, null, null);
        List<Student> students = new ArrayList<>();
        while (cursor.moveToNext())
        {
            students.add(getStudentFromCursor(cursor));
        }
        cursor.close();
        return students;
    }

    /**
     * 查询某个学生
     */
    public static Student query(SQLiteOpenHelper helper, String studentId)
    {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, AVAILABLE_PROJECTION, StudentColumns.SID + " =? ", new String[] {studentId}, null, null, null, null);
        Student student = null;
        if (cursor != null)
        {
            cursor.moveToFirst();
            student = getStudentFromCursor(cursor);
        }
        return student;
    }

    /**
     * 更新学生对象
     * */
    public static void update(SQLiteOpenHelper helper, Student student)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.update(TABLE_NAME, toContentValues(student), StudentColumns.SID + " =? ", new String[] {student.id});
    }

    /**
     * 插入新数据
     * */
    public static void insert(SQLiteOpenHelper helper, Student student)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.insert(TABLE_NAME, null, toContentValues(student));
    }

    /**
     * 插入新数据，如果已经存在就替换
     * */
    public static void insertOrReplace(SQLiteOpenHelper helper, Student student)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.insertWithOnConflict(TABLE_NAME, null, toContentValues(student), SQLiteDatabase.CONFLICT_REPLACE);
    }

    /**
     * 删除某条记录
     * */
    public static void delete(SQLiteOpenHelper helper, String studentId)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(TABLE_NAME, StudentColumns.SID + " =? ", new String[] {studentId});
    }

    /**
     * 清空学生表
     */
    public static void clear(SQLiteOpenHelper helper)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }

    /**
     * 将对象保证成ContentValues
     * */
    private static ContentValues toContentValues(Student student)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StudentColumns.SID, student.id);
        contentValues.put(StudentColumns.NAME, student.name);
        contentValues.put(StudentColumns.AGE, student.age);
        return contentValues;
    }

    /**
     * 将学生对象从Cursor中取出
     * */
    private static Student getStudentFromCursor(Cursor cursor)
    {
        Student student = new Student();
        student.id = cursor.getString(cursor.getColumnIndex(StudentColumns.SID));
        student.name = cursor.getString(cursor.getColumnIndex(StudentColumns.NAME));
        student.age = cursor.getString(cursor.getColumnIndex(StudentColumns.AGE));
        return student;
    }
}
