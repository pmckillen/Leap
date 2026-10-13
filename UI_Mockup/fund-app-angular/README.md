# Fund App (Angular)

Angular skeleton for the Fund Manager wireframes in `../Fund App HTML`. Every component is wired into its page with placeholder markup, ready to be filled in.

## Run

```
npm install
npm start
```

Open http://localhost:4200, click **Sign in (demo)** and use the header to move between Portfolios and Analytics.

```
npm test        # unit tests
npm run build   # production build
```

## Structure

- `core/` auth service, route guard and data models
- `shared/components/` header, KPI tiles and toasts, used by more than one page
- `features/login`, `features/portfolios`, `features/analytics` each page with its child components and services

Routes are in `app.routes.ts`; `/portfolios` and `/analytics` are protected by `authGuard`.
