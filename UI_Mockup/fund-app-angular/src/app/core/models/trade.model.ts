import { AssetClass } from './holding.model';
import { OrderSide } from './order.model';

export interface Trade {
  date: string;
  side: OrderSide;
  ticker: string;
  type: AssetClass;
  qty: number;
  price: number;
  status: string;
}
