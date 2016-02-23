//Ashley Dumaine
//CSE2100-001
//Fall 2013
//Lab 05
//October 30, 2013
public class Entry 
{
	private long _key;
	private Object _value;
	public Entry ()
	{
		_key = 0;
		_value = null;
	}
	public Entry (long key, Object value)
	{
		_key = key;
		_value = value;
	}
	public long getKey()
	{
		return _key;
	}
	public void setKey(long key)
	{
		_key = key;
	}
	public Object getValue()
	{
		return _value;
	}
}
