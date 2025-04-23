import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../services/api.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-punteggi',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './punteggi.component.html',
  styleUrls: ['./punteggi.component.scss']
})
export class PunteggiComponent implements OnInit{
  idTorneo: number = 0;
  idSquadra: number = 0;

  obiettivi: any[] = [];

  constructor(
    private route: ActivatedRoute,
    private service: ApiService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.idTorneo = +params['idTorneo'];
      this.idSquadra = +params['idSquadra'];
      this.caricaPunteggi(this.idTorneo, this.idSquadra);
    });
  }

  caricaPunteggi(idTorneo: number, idSquadra: number): any {

    this.service.getObiettiviByIdTorneo(idTorneo).subscribe({
      next: (result) => {
        this.obiettivi = result;
      }
    });

    console.log(idTorneo, idSquadra);
  } 

}