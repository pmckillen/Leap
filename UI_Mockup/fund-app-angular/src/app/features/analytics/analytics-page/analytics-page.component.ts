import { Component } from '@angular/core';
import { AppHeaderComponent } from '../../../shared/components/app-header/app-header.component';
import { KpiTilesComponent } from '../../../shared/components/kpi-tiles/kpi-tiles.component';
import { AnalyticsTitleBarComponent } from '../components/analytics-title-bar/analytics-title-bar.component';
import { PerformanceChartComponent } from '../components/performance-chart/performance-chart.component';
import { DrawdownChartComponent } from '../components/drawdown-chart/drawdown-chart.component';
import { VarChartComponent } from '../components/var-chart/var-chart.component';
import { RiskSummaryComponent } from '../components/risk-summary/risk-summary.component';

@Component({
  imports: [
    AppHeaderComponent,
    KpiTilesComponent,
    AnalyticsTitleBarComponent,
    PerformanceChartComponent,
    DrawdownChartComponent,
    VarChartComponent,
    RiskSummaryComponent,
  ],
  selector: 'app-analytics-page',
  styleUrl: './analytics-page.component.scss',
  templateUrl: './analytics-page.component.html',
})
export class AnalyticsPageComponent {}
