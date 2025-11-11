package com.studyup.studyup.repository;

import com.studyup.studyup.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Integer> {

    @Query(value = """
        SELECT td.* FROM tbl_documents td
        INNER JOIN tbl_documents_categories tdc ON tdc.document_id = td.id
        WHERE tdc.category_id = :idCategory
    """, nativeQuery = true)
    List<Document> getByCategory(@Param("idCategory") Integer idCategory);

    @Transactional
    @Modifying
    @Query(value = """
        INSERT INTO tbl_documents_categories (category_id,document_id) VALUES (:idCategory, :id)
    """, nativeQuery = true)
    void setCategoryToDoc(@Param("idCategory") Integer idCategory, @Param("id") Integer id);

}
