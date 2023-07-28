import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICarImage } from 'app/shared/model/car-image.model';
import { getEntities } from './car-image.reducer';

export const CarImage = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const carImageList = useAppSelector(state => state.carImage.entities);
  const loading = useAppSelector(state => state.carImage.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);
  console.log(carImageList);
  

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="car-image-heading" data-cy="CarImageHeading">
        <Translate contentKey="bookingCarApiApp.carImage.home.title">Car Images</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="bookingCarApiApp.carImage.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/car-image/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="bookingCarApiApp.carImage.home.createLabel">Create new Car Image</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {carImageList && carImageList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="bookingCarApiApp.carImage.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="bookingCarApiApp.carImage.imageDescription">Image Description</Translate>
                </th>
                <th>
                  <Translate contentKey="bookingCarApiApp.carImage.carImageUrl">Car Image Url</Translate>
                </th>
                <th>
                  <Translate contentKey="bookingCarApiApp.carImage.car">Car</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {carImageList.map((carImage, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/car-image/${carImage.id}`} color="link" size="sm">
                      {carImage.id}
                    </Button>
                  </td>
                  <td>{carImage.imageDescription}</td>
                  <td><img src={carImage.carImageUrl} style={{maxWidth:'100px',maxHeight:'100px'}}/></td>
                  <td>{carImage.car ? <Link to={`/car/${carImage.car.id}`}>{carImage.car.name}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/car-image/${carImage.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/car-image/${carImage.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/car-image/${carImage.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="bookingCarApiApp.carImage.home.notFound">No Car Images found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default CarImage;
