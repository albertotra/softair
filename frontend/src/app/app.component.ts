import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ApiService } from './services/api.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {

  title = 'frontend';

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
   this.caricaDati();
  }

  caricaDati() {
    this.apiService.getAllTornei().subscribe({
      next: (result) => {
        console.log(JSON.stringify(result));
      }
    });
  }
}
