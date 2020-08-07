import java.util.Optional;

class Roster extends KeyableMap<String, String, Student> { 
    public Roster(String name) {
        super(name); 
    }

    public Roster put(Student student) {
        map.put(student.getKey(), student);
        return this;
    }

    public String getGrade(String name, String module, String assessmentName) throws NoSuchRecordException { 
            return this.get(name).flatMap(s -> s.get(module)).flatMap(y -> y.get(assessmentName)).map
                (z -> z.getGrade()).orElseThrow(() -> new NoSuchRecordException
                           ("No such record: " + name + " " + module + " " + assessmentName));
    }
}
