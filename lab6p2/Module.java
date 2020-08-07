class Module extends KeyableMap<String, String, Assessment> {
    public Module(String name) { 
        super(name); 
    }
    
    @Override
    public Module put(Assessment assessment) {
        map.put(assessment.getKey(), assessment);  
        return this;
    }
}
