package ru.job4j.serialization;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private boolean old;
    @XmlAttribute
    private int age;
    private Additional additional;
    @XmlElementWrapper(name = "owners")
    @XmlElement(name = "owner")
    private String[] ownerses;

    public Car() {
    }

    public Car(boolean old, int age, Additional additional, String... ownerses) {
        this.old = old;
        this.age = age;
        this.additional = additional;
        this.ownerses = ownerses;
    }

    @Override
    public String toString() {
        return "Car{"
                + "old=" + old
                + ", age=" + age
                + ", additional=" + additional
                + ", owners=" + Arrays.toString(ownerses)
                + '}';
    }

    public boolean isOld() {
        return old;
    }

    public int getAge() {
        return age;
    }

    public Additional getAdditional() {
        return additional;
    }

    public String[] getOwnerses() {
        return ownerses;
    }
}
