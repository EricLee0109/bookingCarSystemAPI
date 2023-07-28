import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './car-image.reducer';

export const CarImageDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const carImageEntity = useAppSelector(state => state.carImage.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="carImageDetailsHeading">
          <Translate contentKey="bookingCarApiApp.carImage.detail.title">CarImage</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{carImageEntity.id}</dd>
          <dt>
            <span id="imageDescription">
              <Translate contentKey="bookingCarApiApp.carImage.imageDescription">Image Description</Translate>
            </span>
          </dt>
          <dd>{carImageEntity.imageDescription}</dd>
          <dt>
            <span id="carImageUrl">
              <Translate contentKey="bookingCarApiApp.carImage.carImageUrl">Car Image Url</Translate>
            </span>
          </dt>
          <dd>{carImageEntity.carImageUrl}</dd>
          <dt>
            <Translate contentKey="bookingCarApiApp.carImage.car">Car</Translate>
          </dt>
          <dd>{carImageEntity.car ? carImageEntity.car.name : ''}</dd>
        </dl>
        <Button tag={Link} to="/car-image" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/car-image/${carImageEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CarImageDetail;
