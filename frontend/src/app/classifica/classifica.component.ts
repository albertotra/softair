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
    this.service.getSquadraById(idSquadra).subscribe({
      next: (element) => {
        const doc = new jsPDF();
        const squadra: any = element;
  
        const setSfondoETesto = () => {
          // Sfondo scuro
          doc.setFillColor(26, 31, 26); // #1a1f1a
          doc.rect(0, 0, doc.internal.pageSize.width, doc.internal.pageSize.height, 'F');
          // Testo bianco
          doc.setTextColor(212, 220, 204); // #d4dccc
        };
  
        setSfondoETesto();
  
        // Titolo
        doc.setFontSize(20);
        doc.setFont("helvetica", "bold");
        doc.text(`Dettaglio punteggi Squadra: ${squadra.nome}`, 14, 20);
  
        let y = 35; // altezza iniziale
        const maxHeight = doc.internal.pageSize.height - 20; // Margine inferiore di 20
  
        punteggiSquadra.forEach((item, index) => {
          doc.setFontSize(16);
          doc.setFont("helvetica", "bold");
          const obiettivo = item.punteggio.obiettivo.nome;
          const punteggio = item.totale.toString();
          const riga = `OBJ#${index + 1}. Nome: ${obiettivo} - Totale: ${punteggio}`;
          doc.text(riga, 14, y);
          y += 10;
  
          if (y > maxHeight) {
            doc.addPage();
            setSfondoETesto();
            y = 20;
          }
  
          item.dettagli.forEach((element: { valore: string; chiave: any }) => {
            let nome = "", dettaglio = "", punteggioDettaglio = "";
            switch (element.chiave) {
              case "RIBELLI_ELIMINATI":
                nome = "Ribelli eliminati";
                dettaglio = (+element.valore / 30).toString();
                punteggioDettaglio = (+element.valore).toString();
                break;
              case "DIFENSORI_ELIMINATI":
                nome = "Difensori eliminati";
                dettaglio = (+element.valore / 40).toString();
                punteggioDettaglio = (+element.valore).toString();
                break;
              case "CIVILI_ELIMINATI":
                nome = "Civili eliminati";
                dettaglio = (+element.valore / 25).toString();
                punteggioDettaglio = (+element.valore).toString();
                break;
              case "FASE_E":
                let splitValue = element.valore.split('|');
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
  
            const rigaDettaglio = `${nome}: ${dettaglio}, punteggio: ${punteggioDettaglio}`;
            doc.setFontSize(14);
            doc.setFont("helvetica", "normal");
            doc.text(rigaDettaglio, 24, y);
            y += 10;
  
            if (y > maxHeight) {
              doc.addPage();
              setSfondoETesto();
              y = 20;
            }
          });
        });
  
        let nomeSquadraFile: string = squadra.nome.replace(" ", "-").toLowerCase();
        doc.save(`classifica-${nomeSquadraFile}.pdf`);
      },
      error: (err) => {
        console.error('Errore nel recupero della squadra:', err);
      }
    });
  }
  

  generaPdfClassificaDettaglio() {
    // Create a new PDF document
    const doc = new jsPDF();
    let currentPage = 1;
    
    const setSfondoETesto = () => {
      // Sfondo scuro
      doc.setFillColor(26, 31, 26); // #1a1f1a
      doc.rect(0, 0, doc.internal.pageSize.width, doc.internal.pageSize.height, 'F');
      // Testo bianco
      doc.setTextColor(212, 220, 204); // #d4dccc
    };
    
    setSfondoETesto();
    
    // Prima parte: Classifica generale (come in generaPdfClassifica)
    doc.setFontSize(18);
    doc.setFont("helvetica", "bold");
    doc.text('Classifica Finale OP. No Trust - J.S.O.C. Softair Galatina', 14, 20);
    
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
    
    // Seconda parte: Dettaglio punteggi per ogni squadra
    doc.addPage();
    setSfondoETesto();
    currentPage++;
    
    // Creiamo una funzione per aggiungere i dettagli di ogni squadra
    const elaboraDettagliSquadra = (index: number) => {
      if (index >= this.classifica.length) {
        // Tutte le squadre sono state elaborate, salviamo il PDF
        doc.save('classifica-con-punteggi.pdf');
        return;
      }
      
      const squadra = this.classifica[index].squadra;
      
      // Ottieni i punteggi dettagliati della squadra
      this.service.getPunteggioSquadraByIdSquadraAndTorneoId(squadra.id, this.idTorneo).subscribe({
        next: (punteggiSquadra) => {
          // Titolo per questa squadra
          doc.setFontSize(20);
          doc.setFont("helvetica", "bold");
          doc.text(`Dettaglio punteggi Squadra: ${squadra.nome}`, 14, 20);
          
          let y = 35; // altezza iniziale
          const maxHeight = doc.internal.pageSize.height - 20; // Margine inferiore di 20
          
          punteggiSquadra.forEach((item: any, idx: number) => {
            doc.setFontSize(16);
            doc.setFont("helvetica", "bold");
            const obiettivo = item.punteggio.obiettivo.nome;
            const punteggio = item.totale.toString();
            const riga = `OBJ#${idx + 1}. Nome: ${obiettivo} - Totale: ${punteggio}`;
            doc.text(riga, 14, y);
            y += 10;
            
            if (y > maxHeight) {
              doc.addPage();
              setSfondoETesto();
              currentPage++;
              y = 20;
            }
            
            item.dettagli.forEach((element: { valore: string; chiave: any }) => {
              let nome = "", dettaglio = "", punteggioDettaglio = "";
              switch (element.chiave) {
                case "RIBELLI_ELIMINATI":
                  nome = "Ribelli eliminati";
                  dettaglio = (+element.valore / 30).toString();
                  punteggioDettaglio = (+element.valore).toString();
                  break;
                case "DIFENSORI_ELIMINATI":
                  nome = "Difensori eliminati";
                  dettaglio = (+element.valore / 40).toString();
                  punteggioDettaglio = (+element.valore).toString();
                  break;
                case "CIVILI_ELIMINATI":
                  nome = "Civili eliminati";
                  dettaglio = (+element.valore / 25).toString();
                  punteggioDettaglio = (+element.valore).toString();
                  break;
                case "FASE_E":
                  let splitValue = element.valore.split('|');
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
              
              const rigaDettaglio = `${nome}: ${dettaglio}, punteggio: ${punteggioDettaglio}`;
              doc.setFontSize(14);
              doc.setFont("helvetica", "normal");
              doc.text(rigaDettaglio, 24, y);
              y += 10;
              
              if (y > maxHeight) {
                doc.addPage();
                setSfondoETesto();
                currentPage++;
                y = 20;
              }
            });
          });
          
          // Passa alla squadra successiva su una nuova pagina
          if (index < this.classifica.length - 1) {
            doc.addPage();
            setSfondoETesto();
            currentPage++;
            // Elabora la prossima squadra
            elaboraDettagliSquadra(index + 1);
          } else {
            // Tutte le squadre sono state elaborate, salviamo il PDF
            doc.save('classifica-con-punteggi.pdf');
          }
        },
        error: (err) => {
          console.error(`Errore nel recupero dei punteggi della squadra ${squadra.nome}:`, err);
          // Continuiamo con la squadra successiva anche in caso di errore
          if (index < this.classifica.length - 1) {
            doc.addPage();
            setSfondoETesto();
            currentPage++;
            elaboraDettagliSquadra(index + 1);
          } else {
            doc.save('classifica-con-punteggi.pdf');
          }
        }
      });
    };
    
    // Inizia il processo con la prima squadra
    if (this.classifica.length > 0) {
      elaboraDettagliSquadra(0);
    } else {
      // Non ci sono squadre nella classifica
      doc.setFontSize(16);
      doc.text("Nessuna squadra presente nella classifica.", 14, 50);
      doc.save('classifica-con-punteggi.pdf');
    }
  }

}
