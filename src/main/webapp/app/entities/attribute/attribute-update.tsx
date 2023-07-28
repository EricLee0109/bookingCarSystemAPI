import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IGroupAttribute } from 'app/shared/model/group-attribute.model';
import { getEntities as getGroupAttributes } from 'app/entities/group-attribute/group-attribute.reducer';
import { IAttribute } from 'app/shared/model/attribute.model';
import { getEntity, updateEntity, createEntity, reset } from './attribute.reducer';

export const AttributeUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const groupAttributes = useAppSelector(state => state.groupAttribute.entities);
  const attributeEntity = useAppSelector(state => state.attribute.entity);
  const loading = useAppSelector(state => state.attribute.loading);
  const updating = useAppSelector(state => state.attribute.updating);
  const updateSuccess = useAppSelector(state => state.attribute.updateSuccess);

  const handleClose = () => {
    navigate('/attribute');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getGroupAttributes({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...attributeEntity,
      ...values,
      groupattribute: groupAttributes.find(it => it.id.toString() === values.groupattribute.toString()),
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
          ...attributeEntity,
          groupattribute: attributeEntity?.groupattribute?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bookingCarApiApp.attribute.home.createOrEditLabel" data-cy="AttributeCreateUpdateHeading">
            <Translate contentKey="bookingCarApiApp.attribute.home.createOrEditLabel">Create or edit a Attribute</Translate>
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
                  id="attribute-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('bookingCarApiApp.attribute.attributeName')}
                id="attribute-attributeName"
                name="attributeName"
                data-cy="attributeName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('bookingCarApiApp.attribute.displayIndex')}
                id="attribute-displayIndex"
                name="displayIndex"
                data-cy="displayIndex"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                id="attribute-groupattribute"
                name="groupattribute"
                data-cy="groupattribute"
                label={translate('bookingCarApiApp.attribute.groupattribute')}
                type="select"
                required
              >
                <option value="" key="0" />
                {groupAttributes
                  ? groupAttributes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.groupAttributeName}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/attribute" replace color="info">
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

export default AttributeUpdate;
