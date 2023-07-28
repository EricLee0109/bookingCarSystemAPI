import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IBrand } from 'app/shared/model/brand.model';
import { getEntities as getBrands } from 'app/entities/brand/brand.reducer';
import { ICarModel } from 'app/shared/model/car-model.model';
import { getEntity, updateEntity, createEntity, reset } from './car-model.reducer';

export const CarModelUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const brands = useAppSelector(state => state.brand.entities);
  const carModelEntity = useAppSelector(state => state.carModel.entity);
  const loading = useAppSelector(state => state.carModel.loading);
  const updating = useAppSelector(state => state.carModel.updating);
  const updateSuccess = useAppSelector(state => state.carModel.updateSuccess);

  const handleClose = () => {
    navigate('/car-model');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getBrands({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...carModelEntity,
      ...values,
      brand: brands.find(it => it.id.toString() === values.brand.toString()),
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
          ...carModelEntity,
          brand: carModelEntity?.brand?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bookingCarApiApp.carModel.home.createOrEditLabel" data-cy="CarModelCreateUpdateHeading">
            <Translate contentKey="bookingCarApiApp.carModel.home.createOrEditLabel">Create or edit a CarModel</Translate>
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
                  id="car-model-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('bookingCarApiApp.carModel.carModelName')}
                id="car-model-carModelName"
                name="carModelName"
                data-cy="carModelName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('bookingCarApiApp.carModel.type')}
                id="car-model-type"
                name="type"
                data-cy="type"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('bookingCarApiApp.carModel.yearOfManufacture')}
                id="car-model-yearOfManufacture"
                name="yearOfManufacture"
                data-cy="yearOfManufacture"
                type="date"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                id="car-model-brand"
                name="brand"
                data-cy="brand"
                label={translate('bookingCarApiApp.carModel.brand')}
                type="select"
                required
              >
                <option value="" key="0" />
                {brands
                  ? brands.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.name}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/car-model" replace color="info">
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

export default CarModelUpdate;
