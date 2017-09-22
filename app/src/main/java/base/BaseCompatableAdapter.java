package base;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 通用的Adapter，你的Adapter可以继承自这个类，子类中只要实现最重要的getView方法即可 方便使用
 * 
 * @author Michael
 * */
public abstract class BaseCompatableAdapter<T> extends BaseAdapter
{

	protected Context context;
	protected List<T> data;
	protected LayoutInflater layoutInflater;

	public BaseCompatableAdapter(Context context, List<T> data)
	{
		this.context = context;
		this.data = data;
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount()
	{
		if (data == null)
		{
			return 0;
		}

		return data.size();
	}

	@Override
	public T getItem(int position)
	{

		return data.get(position);
	}

	@Override
	public long getItemId(int position)
	{

		return position;
	}

	/**
	 * 刷新数据源
	 * */
	public void setAll(List<T> data)
	{
		if(data != null)
		{
			this.data.clear();
			this.data.addAll(data);
			this.notifyDataSetChanged();
		}
	}
}
