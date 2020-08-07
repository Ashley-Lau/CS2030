class Assessment implements Keyable<String> {
    private String name; 
    private String grade; 

    public Assessment(String name, String grade) { 
        this.name = name; 
        this.grade = grade; 
    }

    public String getKey() {
        return this.name;
    }

    public String getGrade() { 
        return this.grade; 
    }

    public String toString() { 
        return "{" + name + ": " + grade + "}";
    }
}
