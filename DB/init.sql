-- db/init.sql
-- crea extensiones útiles para búsqueda
CREATE EXTENSION IF NOT EXISTS pg_trgm;
CREATE EXTENSION IF NOT EXISTS unaccent;

-- BORRAR si ya existen (útil en desarrollo)
DROP TABLE IF EXISTS tbl_categories CASCADE;
DROP TABLE IF EXISTS tbl_tags CASCADE;
DROP TABLE IF EXISTS tbl_authors CASCADE;
DROP TABLE IF EXISTS tbl_documents CASCADE;
DROP TABLE IF EXISTS tbl_documentos_categories CASCADE;
DROP TABLE IF EXISTS tbl_document_tags CASCADE;

-- CATEGORIAS
CREATE TABLE tbl_categories (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(150) NOT NULL UNIQUE
);
INSERT INTO tbl_categories(nombre) values('Proyecto de Ingeniería I');
INSERT INTO tbl_categories(nombre) values('INVESTIGACIÓN DE OPERACIONES');
INSERT INTO tbl_categories(nombre) values('Diseño Detallado Y Arquitectura De Software');
INSERT INTO tbl_categories(nombre) values('Arquitectura De Computadores Y Sistemas Operativos');
INSERT INTO tbl_categories(nombre) values('GESTIÓN DE LA CONFIGURACIÓN');

-- TAGS
CREATE TABLE tbl_tags (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL UNIQUE
);
-- AUTHORS
CREATE TABLE tbl_authors(
  id SERIAL PRIMARY KEY,
  author VARCHAR(100) NOT NULL UNIQUE
);

INSERT INTO tbl_authors(author) values('Jose Ignacio Lopez Velez');
INSERT INTO tbl_authors(author) values('Ricardo Adolfo Muñoz Jaramillo');
INSERT INTO tbl_authors(author) values('Gustavo Palermo Gómez');
INSERT INTO tbl_authors(author) values('Luisa Fernanda Villa Montoya');
INSERT INTO tbl_authors(author) values('Bell Manrique Losada');

-- DOCUMENTOS
CREATE TABLE tbl_documents (
  id SERIAL PRIMARY KEY,
  tittle VARCHAR(300) NOT NULL,
  description TEXT,
  document BYTEA,
  author_id INTEGER REFERENCES tbl_authors(id) ON DELETE SET NULL,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
  updated_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);
-- relacion M:N documentos - categorias
CREATE TABLE tbl_documents_categories (
  document_id INTEGER NOT NULL REFERENCES tbl_documents(id) ON DELETE CASCADE,
  category_id INTEGER NOT NULL REFERENCES tbl_categories (id) ON DELETE CASCADE,
  PRIMARY KEY (document_id,category_id)
);
-- Relación M:N documentos - tags
CREATE TABLE tbl_document_tags (
  document_id INTEGER NOT NULL REFERENCES tbl_documents(id) ON DELETE CASCADE,
  tag_id INTEGER NOT NULL REFERENCES tbl_tags(id) ON DELETE CASCADE,
  PRIMARY KEY (document_id, tag_id)
);