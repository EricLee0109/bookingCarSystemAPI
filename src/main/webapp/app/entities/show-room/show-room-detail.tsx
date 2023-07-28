import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './show-room.reducer';

export const ShowRoomDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const showRoomEntity = useAppSelector(state => state.showRoom.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="showRoomDetailsHeading">
          <Translate contentKey="bookingCarApiApp.showRoom.detail.title">ShowRoom</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{showRoomEntity.id}</dd>
          <dt>
            <span id="address">
              <Translate contentKey="bookingCarApiApp.showRoom.address">Address</Translate>
            </span>
          </dt>
          <dd>{showRoomEntity.address}</dd>
          <dt>
            <span id="area">
              <Translate contentKey="bookingCarApiApp.showRoom.area">Area</Translate>
            </span>
          </dt>
          <dd>{showRoomEntity.area}</dd>
        </dl>
        <Button tag={Link} to="/show-room" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/show-room/${showRoomEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ShowRoomDetail;
