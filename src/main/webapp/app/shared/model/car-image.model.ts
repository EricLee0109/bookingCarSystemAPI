import { ICar } from 'app/shared/model/car.model';

export interface ICarImage {
  id?: number;
  imageDescription?: string;
  carImageUrl?: string;
  car?: ICar;
}

export const defaultValue: Readonly<ICarImage> = {};
