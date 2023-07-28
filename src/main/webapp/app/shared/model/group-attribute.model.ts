import { IAttribute } from 'app/shared/model/attribute.model';

export interface IGroupAttribute {
  id?: number;
  groupAttributeName?: string;
  displayIndex?: number;
  groupattributes?: IAttribute[] | null;
}

export const defaultValue: Readonly<IGroupAttribute> = {};
