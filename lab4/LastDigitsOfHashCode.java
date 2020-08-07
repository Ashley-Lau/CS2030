class LastDigitsOfHashCode implements Transformer<Object, Integer>	{
	private final int d; 
	public LastDigitsOfHashCode(int d) {
		this.d = d; 
	} 

	public Integer transform(Object t)	{
		int counter = 1;
		int counter2 = d; 
		int ref =  0;  
		if (t instanceof String) {
			String temp = (String) t;
			return transform(Math.abs(temp.hashCode())); 
		} else {
			int temp = (int) t;
			while(counter2 != 0) { 
				ref = ref + (temp % 10 * counter);
				counter *= 10;
				temp /= 10; 
				counter2 -= 1;   
			} 
			return ref; 
		}
	}  
}
