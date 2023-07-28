import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IBooking } from 'app/shared/model/booking.model';
import { getEntities } from './booking.reducer';

export const Booking = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const bookingList = useAppSelector(state => state.booking.entities);
  const loading = useAppSelector(state => state.booking.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };
  console.log(bookingList);

  return (
    <div>
      <h2 id="booking-heading" data-cy="BookingHeading">
        <Translate contentKey="bookingCarApiApp.booking.home.title">Bookings</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="bookingCarApiApp.booking.home.refreshListLabel">Refresh List</Translate>
          </Button>
          {/* <Link to="/booking/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="bookingCarApiApp.booking.home.createLabel">Create new Booking</Translate>
          </Link> */}
        </div>
      </h2>
      <div className="table-responsive">
        {bookingList && bookingList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="bookingCarApiApp.booking.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="bookingCarApiApp.booking.timeSlot">Time Slot</Translate>
                </th>
                <th>
                  <Translate contentKey="bookingCarApiApp.booking.date">Date</Translate>
                </th>
                <th>
                  <Translate contentKey="bookingCarApiApp.booking.employee">Employee</Translate>
                </th>
                <th>
                  <Translate contentKey="bookingCarApiApp.booking.car">Car</Translate>
                </th>
                <th>
                  <Translate contentKey="bookingCarApiApp.booking.customer">Customer</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {bookingList.map((booking, i) => {
                let slot=''
                if(booking?.timeSlot===1){
                  slot='7h-9h'
                }else if(booking?.timeSlot===2){
                  slot='9h-11h'
                }else if(booking?.timeSlot===3){
                  slot='1h-3h'
                }else if(booking?.timeSlot===4){
                  slot='3h-5h'
                }
                return (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>
                      <Button tag={Link} to={`/booking/${booking.id}`} color="link" size="sm">
                        {booking.id}
                      </Button>
                    </td>
                    <td>{slot}</td>
                    <td>{booking.date ? <TextFormat type="date" value={booking.date} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                    <td>
                      {booking.employee ? (
                        <Link to={`/employee/${booking.employee.id}`}>
                          {booking.employee?.user?.firstName} {booking.employee?.user?.lastName}
                        </Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>{booking.car ? <Link to={`/car/${booking.car.id}`}>{booking.car.name}</Link> : ''}</td>
                    <td>
                      {booking.customer ? (
                        <Link to={`/customer/${booking.customer.id}`}>
                          {booking.customer?.user?.firstName} {booking.customer?.user?.lastName}
                        </Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-end">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`/booking/${booking.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        {/* <Button tag={Link} to={`/booking/${booking.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button> */}
                        {/* <Button tag={Link} to={`/booking/${booking.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                          <FontAwesomeIcon icon="trash" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.delete">Delete</Translate>
                          </span>
                        </Button> */}
                      </div>
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="bookingCarApiApp.booking.home.notFound">No Bookings found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Booking;
