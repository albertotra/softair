import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';
import html2canvas from 'html2canvas';

@Component({
  selector: 'app-classifica',
  imports: [CommonModule],
  templateUrl: './classifica.component.html',
  styleUrl: './classifica.component.scss'
})
export class ClassificaComponent implements OnInit {

  idTorneo: number = 0;
  torneo: any = {};
  classifica: any[] = [];
  constructor(
    private route: ActivatedRoute,
    private service: ApiService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.idTorneo = +params['idTorneo'];
      this.getTorneo();
      this.calcolaClassifica();
    });
  }

  getTorneo() {
    this.service.getTorneoById(this.idTorneo).subscribe({
      next: (result) => {
        this.torneo = result;
      }
    });
  }

  calcolaClassifica() {
    this.service.getClassificaByIdTorneo(this.idTorneo).subscribe({
      next: (result) => {
        this.classifica = result;
      }
    });
  }

  generaPdfClassifica() {
    // Create a new PDF document
    const doc = new jsPDF();

    // Imposta sfondo scuro per l'intero documento
    doc.setFillColor(26, 31, 26); // #1a1f1a
    doc.rect(0, 0, doc.internal.pageSize.width, doc.internal.pageSize.height, 'F');

    // Configura il titolo con colore chiaro
    doc.setTextColor(212, 220, 204); // #d4dccc
    doc.setFontSize(18);
    doc.setFont("helvetica", "bold"); // Simula il font monospace
    doc.text('Classifica Finale OP. No Trust - J.S.O.C. Softair Galatina', 14, 20);

    // Aggiungi logo o immagine se necessario
    // doc.addImage('path/to/logo.png', 'PNG', 14, 25, 30, 30);

    // Converti i dati per autoTable
    const tableColumn = ["Posizione", "Nome Squadra", "Punteggio"];
    const tableRows: any[] = [];

    this.classifica.forEach((item, index) => {
      const rowData = [
        index + 1,
        item.squadra.nome,
        item.punteggio
      ];
      tableRows.push(rowData);
    });

    // Genera la tabella con stile personalizzato
    autoTable(doc, {
      head: [tableColumn],
      body: tableRows,
      startY: 30,
      styles: {
        font: "helvetica",
        fontSize: 12,
        textColor: [212, 220, 204], // #d4dccc
      },
      headStyles: {
        fillColor: [45, 51, 45], // versione leggermente più chiara di #1a1f1a
        textColor: [212, 220, 204], // #d4dccc
        fontStyle: 'bold',
      },
      alternateRowStyles: {
        fillColor: [35, 40, 35], // righe alternate leggermente più chiare
      },
      bodyStyles: {
        fillColor: [26, 31, 26], // #1a1f1a
      },
      tableLineColor: [100, 110, 100], // colore delle linee della tabella
      margin: { top: 30 },
    });

    // Salva il PDF
    doc.save('classifica-op-no-trust.pdf');
  }

  dettaglioPunteggio(idSquadra: number) {
    let punteggiSquadra: any[] = [];
    this.service.getPunteggioSquadraByIdSquadraAndTorneoId(idSquadra, this.idTorneo).subscribe({
      next: (result) => {
        punteggiSquadra = result;
        this.generaPdfDettaglioSquadra(punteggiSquadra, idSquadra);
      }
    });
  }

  generaPdfDettaglioSquadra(punteggiSquadra: any[], idSquadra: number) {
    let squadra: any = {};
    this.service.getSquadraById(idSquadra).subscribe({
      next: (result2) => {
        squadra = result2;
      }
    });

    const doc = new jsPDF();

    // Sfondo
    doc.setFillColor(26, 31, 26); // #1a1f1a
    doc.rect(0, 0, doc.internal.pageSize.width, doc.internal.pageSize.height, 'F');

    // Titolo
    doc.setTextColor(212, 220, 204); // #d4dccc
    doc.setFontSize(20);
    doc.setFont("helvetica", "bold");
    doc.text(`Dettaglio punteggi Squadra: ${squadra.nome}`, 14, 20);

    // Imposta stile per il testo delle righe
    

    let y = 35; // altezza iniziale
    punteggiSquadra.forEach((item, index) => {
      doc.setFontSize(16);
      doc.setFont("helvetica", "bold");
      const obiettivo = item.punteggio.obiettivo.nome;
      const punteggio = item.totale.toString();
      const riga = `OBJ#${index + 1}. Nome: ${obiettivo} - Totale: ${punteggio}`;
      doc.text(riga, 14, y);

      y += 10;

      item.dettagli.forEach((element: {
        valore: string; chiave: any;
      }) => {
        const chiave = element.chiave;
        let nome = "";
        let dettaglio = "";
        let punteggioDettaglio = "";

        switch (chiave) {
          case "RIBELLI_ELIMINATI":
            nome = "Ribelli eliminati";
            dettaglio = (+element.valore / 30).toString();
            punteggioDettaglio = (+element.valore).toString();
            break;
          case "DIFENSORI_ELIMINATI":
            nome = "DIfensori eliminati";
            dettaglio = (+element.valore / 40).toString();
            punteggioDettaglio = (+element.valore).toString();
            break;
          case "CIVILI_ELIMINATI":
            nome = "Civili eliminati";
            dettaglio = (+element.valore / 25).toString();
            punteggioDettaglio = (+element.valore).toString();
            break;
          case "FASE_E":
            let splitValue: string[] = element.valore.split('|');
            nome = "Fase E";
            dettaglio = splitValue[0];
            punteggioDettaglio = splitValue[1];
            break;
          case "MINUTI_RISPARMIATI":
            nome = "Minuti risparmiati";
            dettaglio = (+element.valore / 10).toString();
            punteggioDettaglio = (+element.valore).toString();
            break;
          case "FUORI_FINESTRA":
            nome = "Fuori finestra";
            dettaglio = "Punteggio massimo detratto";
            punteggioDettaglio = (+element.valore).toString();
            break;
          default:
            nome = element.chiave;
            dettaglio = element.chiave;
            punteggioDettaglio = element.valore;
            break;
        }
        nome.padEnd(40, ' ');
        dettaglio.padStart(3, ' ');
        punteggioDettaglio.padStart(3, ' ');
        const riga = `${nome}: ${dettaglio}, punteggio: ${punteggioDettaglio}`;

        doc.setFontSize(14);
        doc.setFont("helvetica", "normal");
        doc.text(riga, 24, y);
        y += 10;
      });
    });

    doc.save('classifica-righe.pdf');
  }

}
