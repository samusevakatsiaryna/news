package web.model;

public class User {

    private String email;
    private String password;
    private int roleId;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, int roleId) {
        this.email = email;
        this.password = password;
        this.roleId = roleId;
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

    public String getRole() {
        return roleId == 1 ? "admin" : "customer"; // 1 = admin, 2 = customer
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

}
