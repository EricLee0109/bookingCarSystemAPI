import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './employee.reducer';

export const EmployeeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const employeeEntity = useAppSelector(state => state.employee.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="employeeDetailsHeading">
          <Translate contentKey="bookingCarApiApp.employee.detail.title">Employee</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.id}</dd>
          <dt>
            <span id="gender">
              <Translate contentKey="bookingCarApiApp.employee.gender">Gender</Translate>
            </span>
          </dt>
          <dd>{employeeEntity.gender}</dd>
          <dt>
            <span id="phone">
              Phone
            </span>
          </dt>
          <dd>{employeeEntity.phone}</dd>
          <dt>
            <span id="email">
              Email
            </span>
          </dt>
          <dd>{employeeEntity.user?.email}</dd>
          <dt>
            <span id="FullName">
              FullName
            </span>
          </dt>
          <dd>{employeeEntity.user?.firstName+' '+ employeeEntity.user?.lastName}</dd>
          <dt>
            <Translate contentKey="bookingCarApiApp.employee.showroom">Showroom</Translate>
          </dt>
          <dd>{employeeEntity.showroom ? employeeEntity.showroom.address : ''}</dd>
          <dt>
            <span id="Area">
              Area
            </span>
          </dt>
          <dd>{employeeEntity.showroom?.area}</dd>
        </dl>
        <Button tag={Link} to="/employee" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/employee/${employeeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default EmployeeDetail;
