import { ITransaction } from 'app/shared/model/transaction.model';

export interface IWallet {
  id?: number;
  balance?: number;
  transactions?: ITransaction[] | null;
}

export const defaultValue: Readonly<IWallet> = {};
