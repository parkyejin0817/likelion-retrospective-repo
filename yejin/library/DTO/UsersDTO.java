package library.DTO;

public class UsersDTO {
    private int id;
    private String name;
    private String email;
    private String password;

    public UsersDTO(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String toString() {
        return "UsersDTO{id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pasword='" + password + '\'' +
                '}';
    }
}