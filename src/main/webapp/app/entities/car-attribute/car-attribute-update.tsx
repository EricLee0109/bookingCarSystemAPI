import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IAttribute } from 'app/shared/model/attribute.model';
import { getEntities as getAttributes } from 'app/entities/attribute/attribute.reducer';
import { ICar } from 'app/shared/model/car.model';
import { getEntities as getCars } from 'app/entities/car/car.reducer';
import { ICarAttribute } from 'app/shared/model/car-attribute.model';
import { getEntity, updateEntity, createEntity, reset } from './car-attribute.reducer';

export const CarAttributeUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const attributes = useAppSelector(state => state.attribute.entities);
  const cars = useAppSelector(state => state.car.entities);
  const carAttributeEntity = useAppSelector(state => state.carAttribute.entity);
  const loading = useAppSelector(state => state.carAttribute.loading);
  const updating = useAppSelector(state => state.carAttribute.updating);
  const updateSuccess = useAppSelector(state => state.carAttribute.updateSuccess);

  const handleClose = () => {
    navigate('/car-attribute');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getAttributes({}));
    dispatch(getCars({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...carAttributeEntity,
      ...values,
      attribute: attributes.find(it => it.id.toString() === values.attribute.toString()),
      car: cars.find(it => it.id.toString() === values.car.toString()),
    };

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
          ...carAttributeEntity,
          attribute: carAttributeEntity?.attribute?.id,
          car: carAttributeEntity?.car?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bookingCarApiApp.carAttribute.home.createOrEditLabel" data-cy="CarAttributeCreateUpdateHeading">
            <Translate contentKey="bookingCarApiApp.carAttribute.home.createOrEditLabel">Create or edit a CarAttribute</Translate>
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
                  id="car-attribute-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('bookingCarApiApp.carAttribute.attributeValue')}
                id="car-attribute-attributeValue"
                name="attributeValue"
                data-cy="attributeValue"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                id="car-attribute-attribute"
                name="attribute"
                data-cy="attribute"
                label={translate('bookingCarApiApp.carAttribute.attribute')}
                type="select"
                required
              >
                <option value="" key="0" />
                {attributes
                  ? attributes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.attributeName}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              <ValidatedField
                id="car-attribute-car"
                name="car"
                data-cy="car"
                label={translate('bookingCarApiApp.carAttribute.car')}
                type="select"
                required
              >
                <option value="" key="0" />
                {cars
                  ? cars.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.name}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/car-attribute" replace color="info">
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

export default CarAttributeUpdate;
