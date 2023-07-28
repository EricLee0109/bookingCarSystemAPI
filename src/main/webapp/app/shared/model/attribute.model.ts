import { ICarAttribute } from 'app/shared/model/car-attribute.model';
import { IGroupAttribute } from 'app/shared/model/group-attribute.model';

export interface IAttribute {
  id?: number;
  attributeName?: string;
  displayIndex?: number;
  carAttributes?: ICarAttribute[] | null;
  groupattribute?: IGroupAttribute;
}

export const defaultValue: Readonly<IAttribute> = {};
