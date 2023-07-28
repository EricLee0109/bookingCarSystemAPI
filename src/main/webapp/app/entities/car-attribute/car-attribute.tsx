import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICarAttribute } from 'app/shared/model/car-attribute.model';
import { getEntities } from './car-attribute.reducer';

export const CarAttribute = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const carAttributeList = useAppSelector(state => state.carAttribute.entities);
  const loading = useAppSelector(state => state.carAttribute.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="car-attribute-heading" data-cy="CarAttributeHeading">
        <Translate contentKey="bookingCarApiApp.carAttribute.home.title">Car Attributes</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="bookingCarApiApp.carAttribute.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/car-attribute/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="bookingCarApiApp.carAttribute.home.createLabel">Create new Car Attribute</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {carAttributeList && carAttributeList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="bookingCarApiApp.carAttribute.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="bookingCarApiApp.carAttribute.attributeValue">Attribute Value</Translate>
                </th>
                <th>
                  <Translate contentKey="bookingCarApiApp.carAttribute.attribute">Attribute</Translate>
                </th>
                <th>
                  <Translate contentKey="bookingCarApiApp.carAttribute.car">Car</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {carAttributeList.map((carAttribute, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/car-attribute/${carAttribute.id}`} color="link" size="sm">
                      {carAttribute.id}
                    </Button>
                  </td>
                  <td>{carAttribute.attributeValue}</td>
                  <td>
                    {carAttribute.attribute ? (
                      <Link to={`/attribute/${carAttribute.attribute.id}`}>{carAttribute.attribute.attributeName}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{carAttribute.car ? <Link to={`/car/${carAttribute.car.id}`}>{carAttribute.car.name}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/car-attribute/${carAttribute.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/car-attribute/${carAttribute.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/car-attribute/${carAttribute.id}/delete`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
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
              <Translate contentKey="bookingCarApiApp.carAttribute.home.notFound">No Car Attributes found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default CarAttribute;
