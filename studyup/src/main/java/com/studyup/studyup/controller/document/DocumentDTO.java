package com.studyup.studyup.controller.document;

public class DocumentDTO {
    private Integer id;
    private String tittle;
    private String description;
    private String document;
    private Integer authorId;
    private Integer categoryId;


    public DocumentDTO() {
    }

    public DocumentDTO(Integer id, String tittle, String description, String document, Integer authorId, Integer categoryId) {
        this.id = id;
        this.tittle = tittle;
        this.description = description;
        this.document = document;
        this.authorId = authorId;
        this.categoryId= categoryId;
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

    public Integer getCategoryId(){
        return this.categoryId;
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

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

}
