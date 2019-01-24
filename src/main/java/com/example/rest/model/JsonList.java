package com.example.rest.model;

import javax.persistence.*;

@Entity
@Table(name = "data_table")
public class JsonList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "hash")
    private String hash;
    @Column(name = "value")
    private String value;

    public JsonList() {
    }

    public JsonList(String hash, String value) {
        this.hash = hash;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
