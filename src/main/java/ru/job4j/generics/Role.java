package ru.job4j.generics;

public class Role extends Base {
    private final String roletitle;

    public Role(String id, String name) {
        super(id);
        this.roletitle = name;
    }

    public String getRoletitle() {
        return roletitle;
    }
}
