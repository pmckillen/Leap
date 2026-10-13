export type AssetClass = 'Stock' | 'ETF' | 'Bond';

export interface Holding {
  name: string;
  ticker: string;
  type: AssetClass;
  qty: number;
  cost: number;
  price: number;
  prev: number;
}
