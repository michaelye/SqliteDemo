package database.student;

import android.provider.BaseColumns;

/**
 * 学生表对应的表结构
 *
 * 继承自BaseColumns，通过源码可以看到它自带有_ID和_COUNT两个字段
 *
 * Created by michael on 2017/9/20.
 */

public interface StudentColumns extends BaseColumns
{
    String SID = "sid";
    String NAME = "name";
    String AGE = "age";
}
