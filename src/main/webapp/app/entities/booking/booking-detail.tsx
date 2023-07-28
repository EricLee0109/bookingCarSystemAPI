import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './booking.reducer';

export const BookingDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const bookingEntity = useAppSelector(state => state.booking.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="bookingDetailsHeading">
          <Translate contentKey="bookingCarApiApp.booking.detail.title">Booking</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{bookingEntity.id}</dd>
          <dt>
            <span id="timeSlot">
              <Translate contentKey="bookingCarApiApp.booking.timeSlot">Time Slot</Translate>
            </span>
          </dt>
          <dd>{bookingEntity.timeSlot}</dd>
          <dt>
            <span id="date">
              <Translate contentKey="bookingCarApiApp.booking.date">Date</Translate>
            </span>
          </dt>
          <dd>{bookingEntity.date ? <TextFormat value={bookingEntity.date} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="bookingCarApiApp.booking.employee">Employee</Translate>
          </dt>
          <dd>{bookingEntity.employee ? bookingEntity.employee.id : ''}</dd>
          <dt>
            <Translate contentKey="bookingCarApiApp.booking.car">Car</Translate>
          </dt>
          <dd>{bookingEntity.car ? bookingEntity.car.id : ''}</dd>
          <dt>
            <Translate contentKey="bookingCarApiApp.booking.customer">Customer</Translate>
          </dt>
          <dd>{bookingEntity.customer ? bookingEntity.customer.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/booking" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        {/* <Button tag={Link} to={`/booking/${bookingEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button> */}
      </Col>
    </Row>
  );
};

export default BookingDetail;
