package com.example.demo.modules;

import javax.persistence.*;

@Entity
@Table
public class DemoClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long ID;

    private String Name;

    public DemoClass() {}

    public DemoClass(String name) {
        Name = name;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
