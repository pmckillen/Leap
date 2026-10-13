export type OrderSide = 'Buy' | 'Sell';
export type OrderType = 'Market' | 'Limit';
export type OrderStatus = 'New' | 'Partially filled' | 'Filled' | 'Cancelled' | 'Rejected';

export interface Order {
  id: string;
  time: string;
  side: OrderSide;
  ticker: string;
  type: OrderType;
  qty: number;
  limit?: number;
  filled: number;
  avgPrice?: number;
  status: OrderStatus;
}
