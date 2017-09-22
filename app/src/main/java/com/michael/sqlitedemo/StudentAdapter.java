package com.michael.sqlitedemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import base.BaseCompatableAdapter;
import model.Student;

/**
 * 学生Adapter
 *
 * Created by michael on 2017/9/22.
 */

class StudentAdapter extends BaseCompatableAdapter<Student>
{
    StudentAdapter(Context context, List<Student> data)
    {
        super(context, data);
    }

    private class ViewHolder
    {
        TextView tvId;
        TextView tvName;
        TextView tvAge;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = super.layoutInflater.inflate(R.layout.list_item_student, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvId = (TextView) convertView.findViewById(R.id.tvId);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvAge = (TextView) convertView.findViewById(R.id.tvAge);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Student student = getItem(position);
        viewHolder.tvId.setText(student.id);
        viewHolder.tvName.setText(student.name);
        viewHolder.tvAge.setText(student.age);
        return convertView;
    }
}
