import java.util.Scanner; 

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        int totalStudents = sc.nextInt(); 
        Roster roster = new Roster("AY1920"); 
        String str = "";
        int tracker = 0; 

        for(int i = 0; i < totalStudents; i++) {
            String name = sc.next(); 
            String module = sc.next();
            String assessment = sc.next(); 
            String grade = sc.next();
            Module mod = new Module(module); 
            Assessment assess = new Assessment(assessment, grade); 
            Student student = new Student(name);
            roster.get(name).ifPresentOrElse(
                v -> v.get(module).ifPresentOrElse(y -> y.put(assess), () -> v.put(mod.put(assess)))
                    , () -> roster.put(student.put(mod.put(assess))));
        }

        while(sc.hasNext()) {
            String name = sc.next(); 
            String module = sc.next(); 
            String assessment = sc.next();
            if (tracker != 0) { 
                str = str + "\n"; 
            }
            if (tracker == 0) {
                tracker = 1; 
            }
            try {
                str = str + roster.getGrade(name, module, assessment);
            } catch (NoSuchRecordException e) {
                str = str + "NoSuchRecordException: " + e.getMessage(); 
            }
        }
        System.out.println(str); 
    }
}
