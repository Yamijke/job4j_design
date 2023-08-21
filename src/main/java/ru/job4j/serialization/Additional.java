package ru.job4j.serialization;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Additional {
    @XmlAttribute
    private String attachment;
    private String bonus;

    public Additional() {
    }

    public Additional(String attachment, String bonus) {
        this.attachment = attachment;
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "Additional{"
                + "attachment='" + attachment + '\''
                + ", bonus='" + bonus + '\''
                + '}';
    }
}
