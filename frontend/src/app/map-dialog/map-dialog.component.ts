import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-map-dialog',
  imports: [CommonModule],
  standalone: true,
  templateUrl: './map-dialog.component.html',
  styleUrl: './map-dialog.component.scss'
})
export class MapDialogComponent {
  @Input() luogoNome: string = '';
  @Input() parcheggio: string = '';
  @Input() fob: string = '';

  constructor(public activeModal: NgbActiveModal) {}

  onParcheggioClick(path: string): void {
    window.location.href = path;
  }

  onFobClick(path: string): void {
    window.location.href = path;
  }

}
