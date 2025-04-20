import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../services/api.service';
import { CommonModule } from '@angular/common';
import { NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-dettaglio-torneo',
  imports: [CommonModule, NgbTooltipModule, RouterLink],
  templateUrl: './dettaglio-torneo.component.html',
  styleUrl: './dettaglio-torneo.component.scss'
})
export class DettaglioTorneoComponent {
  torneoId: number = 0;
  listaTorneoSquadre: any[] = [];
  
  constructor(
    private route: ActivatedRoute,
    private service: ApiService
  ) { }
  
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.torneoId = +params['id']; // Il + converte la stringa in numero
      this.caricaSquadre(this.torneoId);
    });
  }
  
  caricaSquadre(id: number) {
    this.service.getTorneoSquadraByIdTorneo(id).subscribe({
      next: (result) => {
        this.listaTorneoSquadre = result;
      }
    });
  }
}
