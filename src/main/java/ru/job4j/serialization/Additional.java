package ru.job4j.serialization;

public class Additional {
    private final String attachment;
    private final String bonus;

    public Additional(String attachment, String bonus) {
        this.attachment = attachment;
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "Additional{" +
                "attachment='" + attachment + '\'' +
                ", bonus='" + bonus + '\'' +
                '}';
    }
}
