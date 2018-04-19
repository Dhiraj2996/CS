public class RandomNumber {

	long PreviousNumber;
	int NumberLenght = 4;
	public RandomNumber()
	{
		PreviousNumber = System.currentTimeMillis() % Integer.MAX_VALUE;
	}

	public RandomNumber(int Seed)
	{
		PreviousNumber = Seed;
		String number = Long.toString(PreviousNumber);
		NumberLenght = number.length();
	}

	public int generate()
	{
		PreviousNumber++;
		PreviousNumber = PreviousNumber * PreviousNumber;
		String number = Long.toString(PreviousNumber);
		int a = (number.length()/2) - (NumberLenght/2);
		StringBuilder NewNumber = new StringBuilder();
		for(int i = a; i < a + NumberLenght && i >= 0; i++)
		{
			NewNumber.append(number.charAt(i));
		}
		PreviousNumber = Long.parseLong(NewNumber.toString());
		return (int) PreviousNumber;
	}
}
