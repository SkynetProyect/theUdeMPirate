import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { DocumentsService } from '../../shared/services/documentsService';
import { MyDocument } from '../../shared/model/Document';
import { Autor } from '../../shared/model/Autor';
import { autorService } from '../../shared/services/autorService';
import { Materia } from '../../shared/model/Materia';
import { MateriaService } from '../../shared/services/materiaService ';
import { CommonModule } from '@angular/common'; 

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterLink, CommonModule],
  templateUrl: './home.component.html'
})
export class HomeComponent {

  documentos: MyDocument[] = [];
  documentosTodos: MyDocument[] = [];
  autores: Autor[] = [];      // lista de autores
  autorSeleccionado?: string; // el valor del <select>
  materias: Materia[] = [];

 constructor(private documentService: DocumentsService, private autorService: autorService, private materiaService: MateriaService 
  ) {}

  ngOnInit() {
    this.cargarAutores();
    this.traerTodosDocumentos();
    this.cargarMaterias();
  }

  cargarAutores() {
    this.autorService.getAll().subscribe({
      next: (data) => {
        this.autores = data;
        console.log('Autores cargados:', data);
      },
      error: (err) => console.error('Error al obtener autores:', err)
    });
  }

  cargarMaterias() {  // 👈 AGREGAR ESTE MÉTODO
    this.materiaService.getAll().subscribe({
      next: (data) => {
        this.materias = data;
        console.log('Materias cargadas:', data);
      },
      error: (err) => console.error('Error al obtener materias:', err)
    });
  }

  traerTodosDocumentos() {
    this.documentService.getAll().subscribe({
      next: (data) => {
        this.documentos = data;
        this.documentosTodos = data;
        console.log('Todos los documentos:', data);
      },
      error: (err) => console.error('Error al traer todos los documentos:', err)
    });
  }

  obtenerNombreAutor(authorId: number | null | undefined): string {
    if (!authorId) return 'Autor desconocido';
    
    const autor = this.autores.find(a => Number(a.id) === authorId);
    return autor ? autor.author : 'Autor desconocido';
  }

  buscarPorAutores(idAutor: string) {
    console.log('Autor ingresado:', idAutor);

    const idNum = Number(idAutor); // convertir a número

    if (!idNum) {
      console.warn('ID inválido o vacío');
      // opcional: restaurar todos
      this.documentos = this.documentosTodos;
      return;
    }

    // filtra los documentos cuyo authorid coincide con el id seleccionado
    this.documentos = this.documentosTodos.filter(
      doc => Number(doc.authorId) === idNum
    );

    console.log('Documentos filtrados:', this.documentos);
  }

  buscarPorMateria( idMateria: string) {
    console.log('materia escogida:',idMateria);
    const idNum = Number(idMateria);
    if (!idNum) {
      console.warn('ID inválido o vacío');
      // opcional: restaurar todos
      // this.documentosFiltrados = this.documentos;
      return;
    }

    this.documentService.getByCategory(idNum).subscribe({
        next: (data) => {
        this.documentos = data;
        console.log('docs por categoria', data);
      },
      error: (err) => console.error('Error al traer los documentos por materia:', err)

    });

  }

  buscar(valor: string) {
    console.log('Texto ingresado:', valor);

    if (!valor.trim()) {
      this.documentos = this.documentosTodos;
      return;
    }

    const texto = valor.toLowerCase();
    this.documentos = this.documentosTodos.filter(doc =>
      doc.tittle?.toLowerCase().includes(texto)
    );
  }

  openPdf(id: number) {
    
    this.documentService.getById(Number(id)).subscribe({
      next: (data) => {
        console.log('Documento cargado:', data);
        
        const byteCharacters = atob(data.document);
        const byteNumbers = new Array(byteCharacters.length);
        for (let i = 0; i < byteCharacters.length; i++) {
          byteNumbers[i] = byteCharacters.charCodeAt(i);
        }
        const byteArray = new Uint8Array(byteNumbers);
        const blob = new Blob([byteArray], { type: 'application/pdf' });

        // Crear una URL temporal para el Blob
        const fileURL = URL.createObjectURL(blob);

        // Abrir el PDF en una nueva pestaña del navegador
        window.open(fileURL); 
      }
    });
  }
}
