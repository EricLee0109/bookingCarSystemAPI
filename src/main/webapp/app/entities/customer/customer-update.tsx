import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICustomer } from 'app/shared/model/customer.model';
import { getEntity, updateEntity, createEntity, reset } from './customer.reducer';

export const CustomerUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const customerEntity = useAppSelector(state => state.customer.entity);
  const loading = useAppSelector(state => state.customer.loading);
  const updating = useAppSelector(state => state.customer.updating);
  const updateSuccess = useAppSelector(state => state.customer.updateSuccess);

  const handleClose = () => {
    navigate('/customer');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...customerEntity,
      ...values,
    };
    console.log(entity);
    
    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...customerEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bookingCarApiApp.customer.home.createOrEditLabel" data-cy="CustomerCreateUpdateHeading">
            <Translate contentKey="bookingCarApiApp.customer.home.createOrEditLabel">Create or edit a Customer</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="customer-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label="Login"
                id="login"
                name="login"
                data-cy="username"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label="FirstName"
                id="firstName"
                name="firstName"
                data-cy="firstName"
                type="text"
                // validate={{
                //   required: { value: true, message: translate('entity.validation.required') },
                //   validate: v => isNumber(v) || translate('entity.validation.number'),
                // }}
              />
              <ValidatedField
                label="LastName"
                id="lastName"
                name="lastName"
                data-cy="lastName"
                type="text"
                // validate={{
                //   required: { value: true, message: translate('entity.validation.required') },
                //   validate: v => isNumber(v) || translate('entity.validation.number'),
                // }}
              />
              <ValidatedField
                label="Email"
                id="email"
                name="email"
                data-cy="email"
                type="email"
                // validate={{
                //   required: { value: true, message: translate('entity.validation.required') },
                //   validate: v => isNumber(v) || translate('entity.validation.number'),
                // }}
              />
              <ValidatedField
                label="Password"
                id="password"
                name="password"
                data-cy="password"
                type="password"
                // validate={{
                //   required: { value: true, message: translate('entity.validation.required') },
                //   validate: v => isNumber(v) || translate('entity.validation.number'),
                // }}
              />
              <ValidatedField
                label="Phone"
                id="phone"
                name="phone"
                data-cy="phone"
                type="tel"
                // validate={{
                //   required: { value: true, message: translate('entity.validation.required') },
                //   validate: v => isNumber(v) || translate('entity.validation.number'),
                // }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/customer" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default CustomerUpdate;
