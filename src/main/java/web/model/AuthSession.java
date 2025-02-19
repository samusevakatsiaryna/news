package web.model;

public class AuthSession {
    private User user;

    public AuthSession(User User) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

//    public boolean isAdmin() {
//       return user.getRole() == UserRole.ADMIN;
//    }
//
//    public boolean isCustomer() {
//        return user.getRole() == UserRole.CUSTOMER;
//    }

}
