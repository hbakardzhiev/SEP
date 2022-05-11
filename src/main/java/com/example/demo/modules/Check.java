package com.example.demo.modules;


import javax.persistence.*;

@Entity
@Table(name = "CHECK")
public class Check {

    @Id
    @Column(unique = true)
    private String name;

    @Column(name = "document source")
    private String docSource;

    private String attribute;
//    TODO: think for first level check and type of second-level check
//    idea: maybe for now we can hard code the types of the second level check


//    public Check() {
//    }

    public Check(String name, String docSource, String attribute) {
        this.name = name;
        this.docSource = docSource;
        this.attribute = attribute;
    }

    public String getCheckName() {
        return name;
    }

    public void setCheckName(String name) {
        this.name = name;
    }

    public String getDocSource() {
        return docSource;
    }

    public void setDocSource(String docSource) {
        this.docSource = docSource;
    }

    public String getCheckAttribute() {
        return attribute;
    }

    public void setCheckAttribute(String attribute) {
        this.attribute = attribute;
    }
}
