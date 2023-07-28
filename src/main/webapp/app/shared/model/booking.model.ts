import dayjs from 'dayjs';
import { IEmployee } from 'app/shared/model/employee.model';
import { ICar } from 'app/shared/model/car.model';
import { ICustomer } from 'app/shared/model/customer.model';

export interface IBooking {
  id?: number;
  timeSlot?: number;
  date?: string;
  employee?: IEmployee | null;
  car?: ICar | null;
  customer?: ICustomer | null;
}

export const defaultValue: Readonly<IBooking> = {};
