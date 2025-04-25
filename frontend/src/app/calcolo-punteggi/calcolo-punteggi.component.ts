import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../services/api.service';
import { CommonModule, JsonPipe } from '@angular/common';
import { NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormArray, FormBuilder, FormControl, FormsModule, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';

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
    { value: 'FASCIA_NON_ESPOSTA', label: 'Fascia non esposta' },
    { value: 'OPERATORE_NON_DICHIARATO_1', label: 'Operatore non dichiarato 1' },
    { value: 'OPERATORE_NON_DICHIARATO_2', label: 'Operatore non dichiarato 2' },
    { value: 'INTERFERENZA_ARBITRALE', label: 'Interferenza arbitrale' },
    { value: 'COMPORTAMENTO_ANTISPORTIVO', label: 'Comportamento antisportivo' },
    { value: 'ASG_OVERJOULE', label: 'ASG overjoule' },
    { value: 'MARCATURA_ASG_ASSENTE', label: 'Marcatura ASG assente' }
  ];

  constructor(
    private route: ActivatedRoute,
    private service: ApiService,
    private fb: FormBuilder,
    private router: Router
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
      idObiettivo: this.idObiettivo,
      idSquadra: this.idSquadra,
      idPunteggio: this.idPunteggio,
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

  onSubmit(): void {
    if (this.form.valid) {
      const formValue = this.form.value;
  
      this.service.salvaPunteggio(formValue).subscribe({
        next: (res) => {
          console.log('Punteggio salvato con successo', res);
  
          // Visualizza un messaggio di successo (puoi usare un toasty, snackbar, o simile)
          alert('Punteggio salvato con successo!');
  
          // Ottieni idTorneo e idSquadra dalla risposta (o dal form, se li hai)
          const idTorneo = res.idTorneo;  // Assicurati che la risposta contenga questi valori
          const idSquadra = res.idSquadra;
  
          // Reindirizza alla nuova route con idTorneo e idSquadra
          this.router.navigate([`/punteggi/${idTorneo}/${idSquadra}`]);
        },
        error: (err) => {
          console.error('Errore durante il salvataggio del punteggio', err);
          alert('Si è verificato un errore durante il salvataggio del punteggio. Riprova!');
        }
      });
    } else {
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