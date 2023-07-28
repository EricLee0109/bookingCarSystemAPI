import { ICarModel } from 'app/shared/model/car-model.model';

export interface IBrand {
  id?: number;
  name?: string;
  logoUrl?: string;
  carmodels?: ICarModel[] | null;
}

export const defaultValue: Readonly<IBrand> = {};
