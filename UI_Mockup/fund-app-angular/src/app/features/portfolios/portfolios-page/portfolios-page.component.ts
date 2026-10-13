import { Component } from '@angular/core';
import { AppHeaderComponent } from '../../../shared/components/app-header/app-header.component';
import { KpiTilesComponent } from '../../../shared/components/kpi-tiles/kpi-tiles.component';
import { PortfolioListComponent } from '../components/portfolio-list/portfolio-list.component';
import { PortfolioTitleBarComponent } from '../components/portfolio-title-bar/portfolio-title-bar.component';
import { LivePricesComponent } from '../components/live-prices/live-prices.component';
import { OrderEntryComponent } from '../components/order-entry/order-entry.component';
import { OrdersComponent } from '../components/orders/orders.component';
import { ExecutionLogComponent } from '../components/execution-log/execution-log.component';
import { AssetAllocationComponent } from '../components/asset-allocation/asset-allocation.component';
import { PnlByAssetClassComponent } from '../components/pnl-by-asset-class/pnl-by-asset-class.component';
import { HoldingsComponent } from '../components/holdings/holdings.component';
import { RecentTradesComponent } from '../components/recent-trades/recent-trades.component';

@Component({
  imports: [
    AppHeaderComponent,
    KpiTilesComponent,
    PortfolioListComponent,
    PortfolioTitleBarComponent,
    LivePricesComponent,
    OrderEntryComponent,
    OrdersComponent,
    ExecutionLogComponent,
    AssetAllocationComponent,
    PnlByAssetClassComponent,
    HoldingsComponent,
    RecentTradesComponent,
  ],
  selector: 'app-portfolios-page',
  styleUrl: './portfolios-page.component.scss',
  templateUrl: './portfolios-page.component.html',
})
export class PortfoliosPageComponent {}
