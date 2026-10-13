import { Routes } from '@angular/router';
import { authGuard } from './core/auth/auth.guard';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'login' },
  {
    path: 'login',
    title: 'Sign in | Fund Manager',
    loadComponent: () =>
      import('./features/login/login/login.component').then((m) => m.LoginComponent),
  },
  {
    path: 'portfolios',
    title: 'Portfolios | Fund Manager',
    canActivate: [authGuard],
    loadComponent: () =>
      import('./features/portfolios/portfolios-page/portfolios-page.component').then(
        (m) => m.PortfoliosPageComponent,
      ),
  },
  {
    path: 'analytics',
    title: 'Analytics | Fund Manager',
    canActivate: [authGuard],
    loadComponent: () =>
      import('./features/analytics/analytics-page/analytics-page.component').then(
        (m) => m.AnalyticsPageComponent,
      ),
  },
  { path: '**', redirectTo: 'login' },
];
