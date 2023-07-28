import React from 'react';
import { Translate } from 'react-jhipster';
import {useAppSelector} from '../config/store'
import MenuItem from '../shared/layout/menus/menu-item';
import './style.css'
import { hasAnyAuthority } from '../shared/auth/private-route';
import { AUTHORITIES } from '../config/constants';

const EntitiesMenu = () => {
  const isAdmin = useAppSelector(state => {
    console.log('state', state);
    
    return hasAnyAuthority(state.authentication.account.authorities, [AUTHORITIES.ADMIN])
  });
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/show-room">
        <Translate contentKey="global.menu.entities.showRoom" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/brand">
        <Translate contentKey="global.menu.entities.brand" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/car-model">
        <Translate contentKey="global.menu.entities.carModel" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/group-attribute">
        <Translate contentKey="global.menu.entities.groupAttribute"/>
      </MenuItem>
      <MenuItem icon="asterisk" to="/attribute">
        <Translate contentKey="global.menu.entities.attribute" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/car-attribute">
        <Translate contentKey="global.menu.entities.carAttribute" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/car">
        <Translate contentKey="global.menu.entities.car" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/car-image">
        <Translate contentKey="global.menu.entities.carImage" />
      </MenuItem>      
      <MenuItem icon="asterisk" to="/booking">
        <Translate contentKey="global.menu.entities.booking" />
      </MenuItem>
      {isAdmin && <MenuItem icon="asterisk" to="/employee">
        <Translate contentKey="global.menu.entities.employee" />
      </MenuItem>}      
      <MenuItem icon="asterisk" to="/customer">
        <Translate contentKey="global.menu.entities.customer" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
