import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '',
        loadComponent: () => import('./home/home.component').then(m => m.HomeComponent)
    },
    {
        path: 'tornei',
        loadComponent: () => import('./tornei/tornei.component').then(m => m.TorneiComponent)
    }
];
