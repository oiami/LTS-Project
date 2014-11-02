package model;

public class Alphabet {

	public static enum symbol
	{
		press,
		hold,
		release
	}
	public static String getString(symbol sym)
	{
		switch(sym)
		{
		case hold:
			return "hold";
			
		case press:
			return "press";
		case release:
			return "release";
		default:
			break;
		
		}
		return null;
	}
}
