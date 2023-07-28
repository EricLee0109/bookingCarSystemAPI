import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './car-model.reducer';

export const CarModelDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const carModelEntity = useAppSelector(state => state.carModel.entity);
  
  
  
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="carModelDetailsHeading">
        CarModel
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{carModelEntity.id}</dd>
          <dt>
            <span id="carModelName">
              <Translate contentKey="bookingCarApiApp.carModel.carModelName">Car Model Name</Translate>
            </span>
          </dt>
          <dd>{carModelEntity.carModelName}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="bookingCarApiApp.carModel.type">Type</Translate>
            </span>
          </dt>
          <dd>{carModelEntity.type}</dd>
          <dt>
            <span id="yearOfManufacture">
              <Translate contentKey="bookingCarApiApp.carModel.yearOfManufacture">Year Of Manufacture</Translate>
            </span>
          </dt>
          <dd>
            {carModelEntity.yearOfManufacture ? (
              <TextFormat value={carModelEntity.yearOfManufacture} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="bookingCarApiApp.carModel.brand">Brand</Translate>
          </dt>
          <dd>{carModelEntity.brand ? carModelEntity.brand.name : ''}</dd>
        </dl>
        <Button tag={Link} to="/car-model" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/car-model/${carModelEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CarModelDetail;
