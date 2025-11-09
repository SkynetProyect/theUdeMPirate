package com.studyup.studyup.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "tbl_documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tittle;
    private String description;
    private byte[] document;
    private Integer authorId;
    private Timestamp createdAt;

    public Document() {
    }

    public Document(Integer id, String tittle, String description, byte[] document, Integer authorId, Timestamp createdAt) {
        this.id = id;
        this.tittle = tittle;
        this.description = description;
        this.document = document;
        this.authorId = authorId;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
