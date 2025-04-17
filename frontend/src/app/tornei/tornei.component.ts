import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';
import { CommonModule } from '@angular/common';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MapDialogComponent } from '../map-dialog/map-dialog.component';

@Component({
  selector: 'app-tornei',
  imports: [CommonModule],
  templateUrl: './tornei.component.html',
  styleUrl: './tornei.component.scss'
})
export class TorneiComponent implements OnInit {
  tornei: any[] = [];
  
  constructor(private apiService: ApiService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.caricaDati();
  }

  caricaDati() {
    this.apiService.getAllTornei().subscribe({
      next: (result) => {
        this.tornei = result;
      }
    });
  }


  apriMappaDialog(luogo: string, parcheggio: string, fob: string): void {
    const modalRef = this.modalService.open(MapDialogComponent);
    modalRef.componentInstance.luogoNome = luogo;
    modalRef.componentInstance.parcheggio = parcheggio;
    modalRef.componentInstance.fob = fob;
  }
}
