public class Student extends User {

    public Student(String id, String name, String email) {
        super(id, name, email);
    }

    @Override
    public String getRole() {
        return "Student";
    }
}
