package model;

import database.DataClass;

import java.util.List;

public final class AdminUser extends User implements DataClass<String> {

    public AdminUser() {
        super();
    }

    public AdminUser(final String email, final String name, final String password, final String salt) {
        super(email, name, password, salt);
    }

    public AdminUser(final String email, final String name, final String input) {
        super(email, name, input);
    }

    public AdminUser(final List<String> values) {
        super(values);
    }

    @Override
    public DataClass<String> fromRow(final List<String> row) {
        return new AdminUser(row);
    }

    @Override
    public String toString() {
        return "AdminUser: " + super.toString() + '}';
    }
}
