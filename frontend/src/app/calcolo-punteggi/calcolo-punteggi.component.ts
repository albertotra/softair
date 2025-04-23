import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../services/api.service';
import { CommonModule, JsonPipe } from '@angular/common';
import { NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormArray, FormBuilder, FormControl, FormsModule, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-calcolo-punteggi',
  imports: [CommonModule, NgbTooltipModule, ReactiveFormsModule, FormsModule],
  templateUrl: './calcolo-punteggi.component.html',
  styleUrl: './calcolo-punteggi.component.scss'
})
export class CalcoloPunteggiComponent implements OnInit {

  idSquadra: number = 0;
  idPunteggio: number = 0;
  idObiettivo: number = 0;
  punteggio: any = {};
  obiettivo: any = {};
  squadra: any = {};
  
  // Variabili per il form
  form!: FormGroup;

  // Opzioni per la select
  options = [
    { value: 'OPERATORE_SQUALIFICATO', label: 'Operatore squalificato' },
    { value: 'FASCIA_NON_ESPOSTA', label: 'Fascia non esposta' },
    { value: 'OPERATORE_NON_DICHIARATO', label: 'Operatore non dichiarato' },
    { value: 'INTERFERENZA_ARBITRALE', label: 'Interferenza arbitrale' },
    { value: 'COMPORTAMENTO_ANTISPORTIVO', label: 'Comportamento antisportivo' },
    { value: 'ASG_OVERJOULE', label: 'ASG overjoule' },
    { value: 'MARCATURA_ASG_ASSENTE', label: 'Marcatura ASG assente' }
  ];

  constructor(
    private route: ActivatedRoute,
    private service: ApiService,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.idSquadra = +params['idSquadra'];
      this.idPunteggio = +params['idPunteggio'];
      this.idObiettivo = +params['idObiettivo'];
      this.getPunteggio();
      this.caricaObiettivo();
      this.caricaSquadra();
    });

    // Inizializzazione del form unificato
    this.form = this.fb.group({
      inizioObj: [''],
      fineObj: [''],
      minutiImpiegati: [null, [Validators.required, Validators.min(1), Validators.max(30)]],
      numeroRibelli: [null, [Validators.min(0), Validators.max(12)]],
      numeroDifensori: [null, [Validators.min(0), Validators.max(5)]],
      numeroCivili: [null, [Validators.min(0), Validators.max(5)]],
      contestazione: ['', [Validators.maxLength(500)]],
      note: ['', [Validators.maxLength(500)]],
      fuoriFinestra: [false],
      punteggiE: this.fb.array([]),
      penalita: this.fb.array([this.createRow()])
    });
  }

  // Metodi per caricare dati
  caricaObiettivo(): any {
    this.service.getObiettivoByIdObiettivo(this.idObiettivo).subscribe({
      next: (result) => {
        this.obiettivo = result;
      }
    });
  }

  caricaSquadra(): any {
    this.service.getSquadraById(this.idSquadra).subscribe({
      next: (result) => {
        this.squadra = result;
      }
    });
  } 

  getPunteggio(): void {
    this.service.getPunteggioByIdPunteggio(this.idPunteggio).subscribe({
      next: (result) => {
        this.punteggio = result;
        console.log(JSON.stringify(this.punteggio));
        
        // Inizializzazione del FormArray per i punteggiE
        if (this.punteggio.punteggiE && this.punteggio.punteggiE.length > 0) {
          const punteggiEArray = this.form.get('punteggiE') as FormArray;
          punteggiEArray.clear();
          
          this.punteggio.punteggiE.forEach((punteggioE: any) => {
            punteggiEArray.push(this.fb.group({
              id: [punteggioE.id],
              descrizione: [punteggioE.descrizione],
              valore: [punteggioE.valore],
              selezionato: [punteggioE.selezionato || false]
            }));
          });
        }
      }
    });
  }

  // Getter per accedere facilmente al FormArray
  get penalita(): FormArray {
    return this.form.get('penalita') as FormArray;
  }
  
  get punteggiE(): FormArray {
    return this.form.get('punteggiE') as FormArray;
  }

  // Metodi per la gestione delle righe ripetibili
  createRow(): FormGroup {
    return this.fb.group({
      selectValue: [''],
      inputValue: ['']
    });
  }

  addRow(): void {
    this.penalita.push(this.createRow());
  }

  removeRow(index: number): void {
    this.penalita.removeAt(index);
  }

  // Metodo onSubmit per gestire l'invio di tutto il form
  onSubmit(): void {
    if (this.form.valid) {
      console.log('Form completo:', this.form.value);
      // Qui puoi aggiungere la logica per l'invio dei dati al server
    } else {
      // Marca tutti i campi come touched per mostrare gli errori di validazione
      this.markFormGroupTouched(this.form);
      console.log('Form non valido');
    }
  }
  
  // Utility per marcare tutti i controlli come touched
  markFormGroupTouched(formGroup: FormGroup) {
    Object.values(formGroup.controls).forEach(control => {
      control.markAsTouched();
      
      if (control instanceof FormGroup) {
        this.markFormGroupTouched(control);
      }
    });
  }
}