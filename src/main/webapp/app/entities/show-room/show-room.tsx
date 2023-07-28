import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IShowRoom } from 'app/shared/model/show-room.model';
import { getEntities } from './show-room.reducer';

export const ShowRoom = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const showRoomList = useAppSelector(state => state.showRoom.entities);
  const loading = useAppSelector(state => state.showRoom.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="show-room-heading" data-cy="ShowRoomHeading">
        <Translate contentKey="bookingCarApiApp.showRoom.home.title">Show Rooms</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="bookingCarApiApp.showRoom.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/show-room/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="bookingCarApiApp.showRoom.home.createLabel">Create new Show Room</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {showRoomList && showRoomList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="bookingCarApiApp.showRoom.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="bookingCarApiApp.showRoom.address">Address</Translate>
                </th>
                <th>
                  <Translate contentKey="bookingCarApiApp.showRoom.area">Area</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {showRoomList.map((showRoom, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/show-room/${showRoom.id}`} color="link" size="sm">
                      {showRoom.id}
                    </Button>
                  </td>
                  <td>{showRoom.address}</td>
                  <td>{showRoom.area}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/show-room/${showRoom.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/show-room/${showRoom.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/show-room/${showRoom.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="bookingCarApiApp.showRoom.home.notFound">No Show Rooms found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default ShowRoom;
