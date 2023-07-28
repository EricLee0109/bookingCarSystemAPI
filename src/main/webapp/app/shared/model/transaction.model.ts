import dayjs from 'dayjs';
import { IWallet } from 'app/shared/model/wallet.model';

export interface ITransaction {
  id?: number;
  issueDate?: string;
  amount?: number;
  wallet?: IWallet | null;
}

export const defaultValue: Readonly<ITransaction> = {};
