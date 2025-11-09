import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { DocumentsService } from '../../shared/services/documentsService';
import { MyDocument } from '../../shared/model/Document';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { autorService } from '../../shared/services/autorService';
import { Autor } from '../../shared/model/Autor';
import { CommonModule } from '@angular/common'; 
import { Materia } from '../../shared/model/Materia';
import { MateriaService } from '../../shared/services/materiaService ';

@Component({
  selector: 'app-my-document-form',
  standalone: true,
  imports: [FormsModule, RouterLink, CommonModule],
  templateUrl: './upload.component.html'
})
export class UploadComponent {
  autores: Autor[] = [];
  materias: Materia[] = []; // 👈 Agregar lista de materias
  
  myDocument: any = {
    tittle: null,
    description: null,
    document: null,
    authorId: null,
    categoryId: null // 👈 Agregar categoryId si no lo tienes
  };

  isLoading = false;
  errorMessage = '';

  constructor(
    private documentService: DocumentsService,
    private router: Router,
    private autorService: autorService,
    private materiaService: MateriaService // 👈 Inyectar servicio de materias
  ) {}

  ngOnInit() {
    this.cargarAutores();
    this.cargarMaterias(); // 👈 Llamar a cargar materias
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

  // 👇 Método para cargar materias
  cargarMaterias() {
    this.materiaService.getAll().subscribe({
      next: (data) => {
        this.materias = data;
        console.log('Materias cargadas:', data);
      },
      error: (err) => console.error('Error al obtener materias:', err)
    });
  }

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    const file = input.files?.[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onload = () => {
      const base64String = (reader.result as string).split(',')[1];
      this.myDocument.document = base64String;
      console.log('Archivo cargado:', file.name);
    };
    reader.onerror = () => {
      console.error('Error al leer el archivo');
      this.errorMessage = 'Error al leer el archivo';
    };
    reader.readAsDataURL(file);
  }

  onSubmit() {
    this.errorMessage = '';

    if (!this.myDocument.tittle || !this.myDocument.authorId) {
      this.errorMessage = 'Por favor completa todos los campos requeridos';
      console.error(this.errorMessage);
      return;
    }
    
    if (!this.myDocument.document) {
      this.errorMessage = 'Por favor selecciona un archivo';
      console.error(this.errorMessage);
      return;
    }

    this.isLoading = true;

    this.documentService.save(this.myDocument).subscribe({
      next: (response) => {
        console.log('Documento guardado exitosamente:', response);
        this.isLoading = false;
        alert('Documento guardado exitosamente');
        this.router.navigate(['/']);
      },
      error: (err) => {
        console.error('Error al guardar documento:', err);
        this.isLoading = false;
        this.errorMessage = 'Error al guardar el documento. Intenta nuevamente.';
      }
    });
  }
}