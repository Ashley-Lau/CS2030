class Roster extends KeyableMap<String, String, Student> { 
    public Roster(String name) {
        super(name); 
    }

    public Roster put(Student student) {
        map.put(student.getKey(), student);
        return this;
    }
     
    public String getGrade(String name, String module, String assessmentName) throws NoSuchRecordException { 
        try {
            String grade = map.get(name).get(module).get(assessmentName).getGrade();    
            return grade; 
        }catch (NullPointerException e) {
            throw new NoSuchRecordException("No such record: " + name + " " + module + " " + assessmentName);
        } 
    }
}
