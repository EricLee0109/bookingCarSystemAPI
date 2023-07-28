import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICar } from 'app/shared/model/car.model';
import { getEntities as getCars } from 'app/entities/car/car.reducer';
import { ICarImage } from 'app/shared/model/car-image.model';
import { getEntity, updateEntity, createEntity, reset } from './car-image.reducer';

export const CarImageUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();
  const [img, setImg] = useState<any | []>([]);
  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const cars = useAppSelector(state => state.car.entities);
  const carImageEntity = useAppSelector(state => state.carImage.entity);
  const loading = useAppSelector(state => state.carImage.loading);
  const updating = useAppSelector(state => state.carImage.updating);
  const updateSuccess = useAppSelector(state => state.carImage.updateSuccess);

  const handleClose = () => {
    navigate('/car-image');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCars({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...carImageEntity,
      ...values,
    };
    console.log(entity);

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };
  interface TempItem {
    data: File;
    url: string;
  }
  function handleChangeImg(e) {
    const tempArr: TempItem[] = [];
    [...e.target.files].forEach(file => {
      // console.log('file >>> ', file);
      tempArr.push({
        data: file,
        url: URL.createObjectURL(file),
      });

      // console.log('pictures >> ', img);
    });

    setImg(tempArr);
    // console.log([...e.target.files]);
    // console.log(tempArr);
  }
  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...carImageEntity,
          car: carImageEntity?.car?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bookingCarApiApp.carImage.home.createOrEditLabel" data-cy="CarImageCreateUpdateHeading">
            <Translate contentKey="bookingCarApiApp.carImage.home.createOrEditLabel">Create or edit a CarImage</Translate>
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
                  id="car-image-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label="Image Description"
                id="car-image-imageDescription"
                name="imageDescription"
                data-cy="imageDescription"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label="Car Image Url"
                id="car-image-carImageUrl"
                name="carImageUrl"
                data-cy="carImageUrl"
                type="file"
                // validate={{
                //   required: { value: true, message: translate('entity.validation.required') },
                // }}
                multiple
                onChange={handleChangeImg}
              />
              {img.map(item => (
                <img style={{ maxHeight: '300px', maxWidth: '300px' }} src={item.url} />
              ))}
              <ValidatedField
                id="car-image-car"
                name="carId"
                data-cy="car"
                label={translate('bookingCarApiApp.carImage.car')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/car-image" replace color="info">
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

export default CarImageUpdate;
