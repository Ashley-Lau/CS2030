class Box<type> {
    public final type content; 
    private static final Box<? extends Object> EMPTY_BOX = new Box<>(null); 

    private Box (type content)	{
        this.content = content; 
    }

    @SuppressWarnings("unchecked")
        public static <type> Box<type> empty() {	
            return (Box<type>) EMPTY_BOX; 
        } 

    public static <type> Box<type> of(type content)	{
        if (content == null)	{
            return null;
        } else {
            return new Box<>(content);
        }
    } 

    public boolean isPresent()	{
        if (content == null) 	{
            return false; 
        } else{
            return true; 
        } 
    }

    public static <type> Box<type> ofNullable(type content)	{
        if(content == null)	{
            return Box.empty(); 
        } else { 
            return Box.of(content);
        }  
    } 

    @SuppressWarnings("unchecked") 
        @Override 	
        public boolean equals(Object obj) 	{
            if(!(obj instanceof Box)) {
                return false;
            } else if (obj == this)	{
                return true; 
            } else {
                Box<type> box = (Box<type>) obj;
                if (this.content == box.content) {
                    return true;
                } else if (this.content == null) {
                    return false; 
                } else {
                    return this.content.equals(box.content); 
                }
            } 
        }

    public Box<type> filter(BooleanCondition<? super type> t) {
        if (t.test(this.content)) {
            return this; 	
        } else {
            return empty(); 
        } 
    }

    public <U> Box<U> map(Transformer<? super type, U> t)	{
        if (this.content == null) {
            return empty(); 
        } else { 
            return new Box<U>(t.transform(this.content)); 
        } 
    }   		 

    @Override 
        public String toString()	{
            if (content == null)	{
                return "[]";
            } else {
                return "[" + content + "]";
            }
        }  
}
