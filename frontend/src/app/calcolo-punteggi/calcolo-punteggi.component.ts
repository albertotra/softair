import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../services/api.service';
import { CommonModule, JsonPipe } from '@angular/common';
import { NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormArray, FormBuilder, FormControl } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';

interface RowForm {
  selectValue: FormControl<string | null>;
  inputValue: FormControl<string | null>;
}

@Component({
  selector: 'app-calcolo-punteggi',
  imports: [CommonModule, NgbTooltipModule, ReactiveFormsModule],
  templateUrl: './calcolo-punteggi.component.html',
  styleUrl: './calcolo-punteggi.component.scss'
})
export class CalcoloPunteggiComponent implements OnInit{

  idSquadra: number = 0;
  idPunteggio: number = 0;
  punteggio: any = {};

  form!: FormGroup;
  
  // Opzioni per la select (da modificare in base alle tue esigenze)
  options = [
    { value: 'option1', label: 'Opzione 1' },
    { value: 'option2', label: 'Opzione 2' },
    { value: 'option3', label: 'Opzione 3' }
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
      this.getPunteggio();
    });

    this.form = this.fb.group({
      rows: this.fb.array([this.createRow()])
    });
  }

  getPunteggio(): void {
    this.service.getPunteggioByIdPunteggio(this.idPunteggio).subscribe({
      next: (result) => {
        this.punteggio = result;
        console.log(JSON.stringify(this.punteggio));
      }
    });
  }

  get rows(): FormArray {
    return this.form.get('rows') as FormArray;
  }

  createRow(): FormGroup {
    return this.fb.group({
      selectValue: [''],
      inputValue: ['']
    }) as FormGroup;
  }

  addRow(): void {
    this.rows.push(this.createRow());
  }

  removeRow(index: number): void {
    this.rows.removeAt(index);
  }

  onSubmit(): void {
    console.log(this.form.value);
  }
}
