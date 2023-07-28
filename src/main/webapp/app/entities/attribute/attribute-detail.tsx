import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './attribute.reducer';

export const AttributeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const attributeEntity = useAppSelector(state => state.attribute.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="attributeDetailsHeading">
          <Translate contentKey="bookingCarApiApp.attribute.detail.title">Attribute</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{attributeEntity.id}</dd>
          <dt>
            <span id="attributeName">
              <Translate contentKey="bookingCarApiApp.attribute.attributeName">Attribute Name</Translate>
            </span>
          </dt>
          <dd>{attributeEntity.attributeName}</dd>
          <dt>
            <span id="displayIndex">
              <Translate contentKey="bookingCarApiApp.attribute.displayIndex">Display Index</Translate>
            </span>
          </dt>
          <dd>{attributeEntity.displayIndex}</dd>
          <dt>
            <Translate contentKey="bookingCarApiApp.attribute.groupattribute">Groupattribute</Translate>
          </dt>
          <dd>{attributeEntity.groupattribute ? attributeEntity.groupattribute.groupAttributeName : ''}</dd>
        </dl>
        <Button tag={Link} to="/attribute" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/attribute/${attributeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AttributeDetail;
