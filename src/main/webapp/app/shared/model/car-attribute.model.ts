import { IAttribute } from 'app/shared/model/attribute.model';
import { ICar } from 'app/shared/model/car.model';

export interface ICarAttribute {
  id?: number;
  attributeValue?: string;
  attribute?: IAttribute;
  car?: ICar;
}

export const defaultValue: Readonly<ICarAttribute> = {};
