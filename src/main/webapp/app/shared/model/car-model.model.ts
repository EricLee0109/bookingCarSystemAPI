import dayjs from 'dayjs';
import { ICar } from 'app/shared/model/car.model';
import { IBrand } from 'app/shared/model/brand.model';

export interface ICarModel {
  id?: number;
  carModelName?: string;
  type?: string;
  yearOfManufacture?: string;
  cars?: ICar[] | null;
  brand?: IBrand;
}

export const defaultValue: Readonly<ICarModel> = {};
