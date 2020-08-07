class Student extends KeyableMap<String, String, Module>{ 
    public Student(String name) {
        super(name); 
    }
    
    @Override 
    public Student put(Module module) {
        map.put(module.getKey(), module); 
        return this;
    } 
}
