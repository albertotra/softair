import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '',
        loadComponent: () => import('./home/home.component').then(m => m.HomeComponent)
    },
    {
        path: 'tornei',
        loadComponent: () => import('./tornei/tornei.component').then(m => m.TorneiComponent)
    },
    {
        path: 'dettaglio-torneo/:id',
        loadComponent: () => import('./dettaglio-torneo/dettaglio-torneo.component').then(m => m.DettaglioTorneoComponent)
    },
    {
        path: 'punteggi/:idTorneo/:idSquadra',
        loadComponent: () => import('./punteggi/punteggi.component').then(m => m.PunteggiComponent)
    }
];
