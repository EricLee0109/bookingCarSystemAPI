import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './group-attribute.reducer';

export const GroupAttributeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const groupAttributeEntity = useAppSelector(state => state.groupAttribute.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="groupAttributeDetailsHeading">
          <Translate contentKey="bookingCarApiApp.groupAttribute.detail.title">GroupAttribute</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{groupAttributeEntity.id}</dd>
          <dt>
            <span id="groupAttributeName">
              <Translate contentKey="bookingCarApiApp.groupAttribute.groupAttributeName">Group Attribute Name</Translate>
            </span>
          </dt>
          <dd>{groupAttributeEntity.groupAttributeName}</dd>
          <dt>
            <span id="displayIndex">
              <Translate contentKey="bookingCarApiApp.groupAttribute.displayIndex">Display Index</Translate>
            </span>
          </dt>
          <dd>{groupAttributeEntity.displayIndex}</dd>
        </dl>
        <Button tag={Link} to="/group-attribute" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/group-attribute/${groupAttributeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default GroupAttributeDetail;
