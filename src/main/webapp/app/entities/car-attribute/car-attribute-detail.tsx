import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './car-attribute.reducer';

export const CarAttributeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const carAttributeEntity = useAppSelector(state => state.carAttribute.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="carAttributeDetailsHeading">
          <Translate contentKey="bookingCarApiApp.carAttribute.detail.title">CarAttribute</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{carAttributeEntity.id}</dd>
          <dt>
            <span id="attributeValue">
              <Translate contentKey="bookingCarApiApp.carAttribute.attributeValue">Attribute Value</Translate>
            </span>
          </dt>
          <dd>{carAttributeEntity.attributeValue}</dd>
          <dt>
            <Translate contentKey="bookingCarApiApp.carAttribute.attribute">Attribute</Translate>
          </dt>
          <dd>{carAttributeEntity.attribute ? carAttributeEntity.attribute.attributeName : ''}</dd>
          <dt>
            <Translate contentKey="bookingCarApiApp.carAttribute.car">Car</Translate>
          </dt>
          <dd>{carAttributeEntity.car ? carAttributeEntity.car.name : ''}</dd>
        </dl>
        <Button tag={Link} to="/car-attribute" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/car-attribute/${carAttributeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CarAttributeDetail;
