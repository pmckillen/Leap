import { Holding } from './holding.model';
import { Trade } from './trade.model';

export type RiskLevel = 'Low' | 'Medium' | 'High';

export interface Portfolio {
  id: string;
  name: string;
  risk: RiskLevel;
  benchmark: string;
  cash: number;
  realisedYtd: number;
  holdings: Holding[];
  trades: Trade[];
}
