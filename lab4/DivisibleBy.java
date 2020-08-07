class DivisibleBy implements BooleanCondition<Integer>	{
	private final Integer number;
	public DivisibleBy(Integer number)	{
		this.number = number; 
	}
	public boolean test(Integer num)	{
		if (num == null) {
			return true;
		} else {
			return  ((num % number) == 0);
		}  
	}  	
}

