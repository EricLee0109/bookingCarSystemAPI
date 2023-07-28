import { ICar } from 'app/shared/model/car.model';
import { IBooking } from 'app/shared/model/booking.model';

export interface ICustomer {
  id?: number;
  username?: string;
  phone?: number;
  cars?: ICar[] | null;
  bookings?: IBooking[] | null;
}

export const defaultValue: Readonly<ICustomer> = {};
