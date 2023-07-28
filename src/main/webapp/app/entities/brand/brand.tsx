import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IBrand } from 'app/shared/model/brand.model';
import { getEntities } from './brand.reducer';

export const Brand = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const brandList = useAppSelector(state => state.brand.entities);
  const loading = useAppSelector(state => state.brand.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="brand-heading" data-cy="BrandHeading">
        <Translate contentKey="bookingCarApiApp.brand.home.title">Brands</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="bookingCarApiApp.brand.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/brand/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="bookingCarApiApp.brand.home.createLabel">Create new Brand</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {brandList && brandList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="bookingCarApiApp.brand.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="bookingCarApiApp.brand.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="bookingCarApiApp.brand.logoUrl">Logo Url</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {brandList.map((brand, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/brand/${brand.id}`} color="link" size="sm">
                      {brand.id}
                    </Button>
                  </td>
                  <td>{brand.name}</td>
                  <td>{brand.logoUrl}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/brand/${brand.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/brand/${brand.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/brand/${brand.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="bookingCarApiApp.brand.home.notFound">No Brands found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Brand;
