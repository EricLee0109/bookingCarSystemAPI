import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './car.reducer';

export const CarDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const carEntity = useAppSelector(state => state.car.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="carDetailsHeading">
          <Translate contentKey="bookingCarApiApp.car.detail.title">Car</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{carEntity.id}</dd>
          <dt>
            <span id="id">
              Bảng số xe
            </span>
          </dt>
          <dd>{carEntity.licensePlate}</dd>
          <dt>
            <span id="price">
              <Translate contentKey="bookingCarApiApp.car.price">Price</Translate>
            </span>
          </dt>
          <dd>{carEntity.price}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="bookingCarApiApp.car.name">Name</Translate>
            </span>
          </dt>
          <dd>{carEntity.name}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="bookingCarApiApp.car.status">Status</Translate>
            </span>
          </dt>
          <dd>{carEntity.status}</dd>
          <dt>
            <Translate contentKey="bookingCarApiApp.car.showroom">Showroom</Translate>
          </dt>
          <dd>{carEntity.showroom ? carEntity.showroom.address : ''}</dd>
          <dt>
            <Translate contentKey="bookingCarApiApp.car.customer">Customer</Translate>
          </dt>
          <dd>{carEntity.customer ? carEntity.customer?.user?.login : ''}</dd>
          <dt>
            <Translate contentKey="bookingCarApiApp.car.carmodel">Carmodel</Translate>
          </dt>
          <dd>{carEntity.carmodel ? carEntity.carmodel.carModelName : ''}</dd>
          
        </dl>
        <Button tag={Link} to="/car" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/car/${carEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CarDetail;
