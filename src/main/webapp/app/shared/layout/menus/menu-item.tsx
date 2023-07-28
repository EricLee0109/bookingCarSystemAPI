import React from 'react';
import { DropdownItem } from 'reactstrap';
import { NavLink as Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IconProp } from '@fortawesome/fontawesome-svg-core';
import './style.css'
export interface IMenuItem {
  children: React.ReactNode;
  icon: IconProp;
  to: string;
  id?: string;
  'data-cy'?: string;
}

const MenuItem = (props: IMenuItem) => {
  const { to, icon, id, children } = props;

  return (
    <DropdownItem tag={Link} to={to} id={id} data-cy={props['data-cy']} style={{paddingBottom:'20px'}}>
      <div className='key'>{children}</div>
    </DropdownItem>
  );
};

export default MenuItem;
