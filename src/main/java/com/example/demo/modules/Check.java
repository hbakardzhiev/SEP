package com.example.demo.modules;


import javax.persistence.*;

@Entity
@Table(name = "rule")
public class Check {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private String name;

    @Column(name = "document_source")
    private String docSource;

    @Column(name = "attribute")
    private String attribute;

    @Column(name = "action")
    private ActionTypes action;

    @Column(name = "value")
    private String value;

    public ActionTypes getCheckAction() {
        return action;
    }

    public void setCheckAction(ActionTypes action) {
        this.action = action;
    }

    public String getCheckValue() {
        return value;
    }

    public void setCheckValue(String value) {
        this.value = value;
    }



    public Check() {
    }

    public Check(String name, String docSource, String attribute,
                                ActionTypes action, String value) {
        this.name = name;
        this.docSource = docSource;
        this.attribute = attribute;
        this.action = action;
        this.value = value;
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
