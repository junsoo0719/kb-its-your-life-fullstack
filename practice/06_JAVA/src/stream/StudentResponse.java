package stream;

public class StudentResponse {
    long id;
    String name;
    String filterOption;

    public StudentResponse(long id, String name, String filterOption) {
        this.id = id;
        this.name = name;
        this.filterOption = filterOption;
    }

    @Override
    public String toString() {
        return "StudentResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", filterOption='" + filterOption + '\'' +
                '}';
    }
}
