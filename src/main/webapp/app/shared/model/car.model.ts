import { ICarAttribute } from 'app/shared/model/car-attribute.model';
import { ICarImage } from 'app/shared/model/car-image.model';
import { IShowRoom } from 'app/shared/model/show-room.model';
import { ICustomer } from 'app/shared/model/customer.model';
import { ICarModel } from 'app/shared/model/car-model.model';
import { IBooking } from 'app/shared/model/booking.model';
import {IEmployee} from '../model/employee.model'
export interface ICar {
  id?: number;
  // licensePlate?:number;
  price?: number;
  name?: string;
  status?: string;
  carattributes?: ICarAttribute[] | null;
  images?: ICarImage[] | null;
  showroom?: IShowRoom;
  customer?: ICustomer;
  carmodel?: ICarModel;
  bookings?: IBooking[] | null;
  employees?: IEmployee[];
}

export const defaultValue: Readonly<ICar> = {};
