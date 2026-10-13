import { Injectable, computed, signal } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly user = signal<string | null>(null);

  readonly username = this.user.asReadonly();
  readonly isLoggedIn = computed(() => this.user() !== null);

  login(username: string): void {
    this.user.set(username);
  }

  logout(): void {
    this.user.set(null);
  }
}
