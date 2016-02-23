//Ashley Dumaine
//CSE2100-001
//Fall 2013
//Lab 06
//November 18, 2013
public class Entry
{
	private String coordinates;
	private int _key;
	private Object _value;
	private int _position;
	public Entry()
	{
		coordinates = "";
		_key = 0;
		_value = null;
	}
	public Entry(int key, Object value)
	{
		coordinates = "";
		_key = key;
		_value = value;
	}
	public void setPosition(int position)
	{
		_position = position;
	}
	public int getPosition()
	{
		return _position;
	}
	public int getKey()
	{
		return _key;
	}
	public void setKey(int key)
	{
		_key = key;
	}
	public Object getValue()
	{
		return _value;
	}
	public void setValue(Object value)
	{
		_value = value;
	}
	
	public void setCoordinates(String coordinateString)
	{
		coordinates = coordinateString;
	}
	public String getCoordinates()
	{
		return coordinates;
	}
	
	@Override
	public String toString()
	{
		return (String) this.getValue();
	}
}
