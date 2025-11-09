
import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { DocumentsService } from '../../shared/services/documentsService';

@Component({
  selector: 'read',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './read.component.html'
})
export class ReadComponent implements OnInit, OnDestroy {
  pdfUrl?: SafeResourceUrl;
  isLoading = true;
  errorMessage = '';

  constructor(
    private route: ActivatedRoute,
    private documentService: DocumentsService,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit() {
    this.cargarDocumento();
  }

  cargarDocumento() {
    const id = this.route.snapshot.paramMap.get('id');
    
    if (!id) {
      this.errorMessage = 'ID de documento no válido';
      this.isLoading = false;
      return;
    }

    this.documentService.getById(Number(id)).subscribe({
      next: (data) => {
        console.log('Documento cargado:', data);
        
        if (data.document) {
          this.convertirBase64APdf(data.document);
        } else {
          this.errorMessage = 'El documento no contiene un archivo PDF';
          this.isLoading = false;
        }
      },
      error: (err) => {
        console.error('Error al cargar documento:', err);
        this.errorMessage = 'No se pudo cargar el documento';
        this.isLoading = false;
      }
    });
  }

  convertirBase64APdf(base64: string) {
    const byteCharacters = atob(base64);
    const byteNumbers = new Array(byteCharacters.length);
    for (let i = 0; i < byteCharacters.length; i++) {
      byteNumbers[i] = byteCharacters.charCodeAt(i);
    }
    const byteArray = new Uint8Array(byteNumbers);
    const blob = new Blob([byteArray], { type: 'application/pdf' });

    const url = URL.createObjectURL(blob);
    window.open(url, '_blank'); // 👈 abrir en nueva pestaña
  }

  ngOnDestroy() {
    // Liberar la URL del blob cuando se destruya el componente
    if (this.pdfUrl) {
      const urlString = this.pdfUrl.toString();
      if (urlString.startsWith('blob:')) {
        URL.revokeObjectURL(urlString);
      }
    }
  }
}