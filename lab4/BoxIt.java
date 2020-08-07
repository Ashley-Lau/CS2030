class BoxIt<T> implements Transformer<T, Box<T>>	{
	public Box<T> transform(T t) { 
		if (t == null) {
			return null;
		} else { 
			return Box.of(t);
		} 
	}
} 
