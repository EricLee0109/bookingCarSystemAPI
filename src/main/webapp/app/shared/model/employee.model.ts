import { IShowRoom } from 'app/shared/model/show-room.model';
import { IBooking } from 'app/shared/model/booking.model';

export interface IEmployee {
  id?: number;
  gender?: string;
  imageUrl?: string;
  showroom?: IShowRoom;
  bookings?: IBooking[] | null;
}

export const defaultValue: Readonly<IEmployee> = {};
