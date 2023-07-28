import { ICar } from 'app/shared/model/car.model';
import { IEmployee } from 'app/shared/model/employee.model';

export interface IShowRoom {
  id?: number;
  address?: string;
  area?: string;
  cars?: ICar[] | null;
  employees?: IEmployee[] | null;
}

export const defaultValue: Readonly<IShowRoom> = {};
