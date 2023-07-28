import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { AUTHORITIES } from 'app/config/constants';

import { IShowRoom } from 'app/shared/model/show-room.model';
import { getEntities as getShowRooms } from 'app/entities/show-room/show-room.reducer';
import { ICustomer } from 'app/shared/model/customer.model';
import { getEntities as getCustomers } from 'app/entities/customer/customer.reducer';
import { ICarModel } from 'app/shared/model/car-model.model';
import { getEntities as getCarModels } from 'app/entities/car-model/car-model.reducer';
import { getEntities as getEmployees } from 'app/entities/employee/employee.reducer';
import { getEntities as getAttributes } from '../attribute/attribute.reducer';
import { ICar } from 'app/shared/model/car.model';
import { getEntity, updateEntity, createEntity, reset } from './car.reducer';
import { hasAnyAuthority } from '../../shared/auth/private-route';

export const CarUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const showRooms = useAppSelector(state => state.showRoom.entities);
  const customers = useAppSelector(state => state.customer.entities);
  const carModels = useAppSelector(state => state.carModel.entities);
  const attributes = useAppSelector(state => state.attribute.entities);
  const employees = useAppSelector(state => state.employee.entities);

  const carEntity = useAppSelector(state => state.car.entity);
  
  const loading = useAppSelector(state => state.car.loading);
  const updating = useAppSelector(state => state.car.updating);
  const updateSuccess = useAppSelector(state => state.car.updateSuccess);
  const [carattributes, setCarattributes] = useState({});
  const isAdmin = useAppSelector(state => {
    console.log('state', state);
    
    return hasAnyAuthority(state.authentication.account.authorities, [AUTHORITIES.ADMIN])
  });
  console.log("isAdmin",isAdmin);
  
  console.log("customer",customers);
  
  console.log('carattributes', carattributes);

  const handleClose = () => {
    navigate('/car');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
    dispatch(getShowRooms({}));
    dispatch(getCustomers({}));
    dispatch(getCarModels({}));
    dispatch(getAttributes({}));
    dispatch(getEmployees({}))
  }, []);

  
  
  const separateObject = (items: Record<number, string>): { attributeId: number; attributeValue: string }[] => {
    const result: { attributeId: number; attributeValue: string }[] = [];
    for (const property in items) {
      result.push({
        attributeId: +property,
        attributeValue: items[property],
      });
    }

    return result;
  };

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    console.log(values);
    
    const entity = {
      ...carEntity,
      ...values,
      carModelId: +values.carModelId,
      customerId: +values.customerId,
      employeeId: +values.employeeId,
      showroomId: +values.showroomId,
      // showroomId: showRooms.find(it => it.id.toString() === values.showroomId.toString()).id,
      // customerId: customers.find(it => it.id.toString() === values.customerId.toString()).id,
      // carmodelId: carModels.find(it => it.id.toString() === values.carModelId.toString()).id,
      // employeeId: employees.find(it => it.id.toString() === values.employeeId.toString()).id,
      // attribute: attributes.find(it => it.id.toString() === values.attribute.toString()),
      carattributes: separateObject(carattributes),
    };
    console.log(carEntity);
    const entityUpdate ={
      ...carEntity,
      ...values,
      showroom: showRooms.find(it => it.id.toString() === values.showroomId.toString()),
      carattributes: separateObject(carattributes),

    }

    console.log('entity', entity);
    console.log('222',entityUpdate);
    

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entityUpdate));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...carEntity,
          status:carEntity?.status,
          showroomId: carEntity?.showroom?.id,
          customerId: carEntity?.customer?.id,
          carModelId: carEntity?.carmodel?.id,
          employeeId: carEntity?.employee?.id,
          // carattributes: carEntity?.carattributes,
        };

  function handleChange(e) {
    let name = e.target.name;
    let value = e.target.value;
    setCarattributes({
      ...carattributes,
      [name]: value,
    });
  }

  // interface TempItem {
  //   data: File;
  //   url: string;
  // }

  // function handleChangeImg(e) {
  //   const tempArr: TempItem[] = [];
  //   [...e.target.files].forEach(file => {
  //     console.log('file >>> ', file);
  //     tempArr.push({
  //       data: file,
  //       url: URL.createObjectURL(file),
  //     });

  //     console.log('pictures >> ', img);
  //   });
  //   setImg(URL.createObjectURL(e.target.files[0]));
  //   console.log([...e.target.files]);
  //   console.log(tempArr);
  // }
  // console.log(img);

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bookingCarApiApp.car.home.createOrEditLabel" data-cy="CarCreateUpdateHeading">
            <Translate contentKey="bookingCarApiApp.car.home.createOrEditLabel">Create or edit a Car</Translate>
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
                  id="car-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label="licensePlate"
                id="licensePlate"
                name="licensePlate"
                data-cy="licensePlate"
                type="text"
                // validate={{
                //   required: { value: true, message: translate('entity.validation.required') },
                //   validate: v => isNumber(v) || translate('entity.validation.number'),
                // }}
              />
              <ValidatedField
                label={translate('bookingCarApiApp.car.price')}
                id="car-price"
                name="price"
                data-cy="price"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('bookingCarApiApp.car.name')}
                id="car-name"
                name="name"
                data-cy="name"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('bookingCarApiApp.car.status')}
                id="car-status"
                name="status"
                data-cy="status"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              {isAdmin &&<ValidatedField
                id="employee"
                name="employeeId"
                data-cy="employee"
                label="Employee"
                type="select"
                required
              >
                <option value="" key="0" />
                {employees
                  ? employees.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.user?.login}
                      </option>
                    ))
                  : null}
              </ValidatedField>}
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              <ValidatedField
                id="car-showroom"
                name="showroomId"
                data-cy="showroom"
                label={translate('bookingCarApiApp.car.showroom')}
                type="select"
                required
              >
                <option value="" key="0" />
                {showRooms
                  ? showRooms.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.area}: {otherEntity.address}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              <ValidatedField
                id="car-customer"
                name="customerId"
                data-cy="customer"
                label={translate('bookingCarApiApp.car.customer')}
                type="select"
                required
              >
                <option value="" key="0" />
                {customers
                  ? customers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.user?.login}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              <ValidatedField
                id="car-carmodel"
                name="carModelId"
                data-cy="carmodel"
                label={translate('bookingCarApiApp.car.carmodel')}
                type="select"
                required
              >
                <option value="" key="0" />
                {carModels
                  ? carModels.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.carModelName}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              {/* <ValidatedField
                id="car-attribute"
                name="attribute"
                data-cy="attribute"
                label={'Attribute'}
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
              </FormText> */}
              {attributes.map(otherEntity => (
                <>
                  <ValidatedField
                    name={otherEntity.id}
                    data-cy="attribute"
                    label={otherEntity.attributeName}
                    type="text"
                    required
                    onChange={handleChange}
                  >
                    {/* <option value="" key="0" />
                {attributes
                  ? attributes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.attributeName}
                      </option>
                    ))
                  : null} */}
                    {/* <input type='text'/> */}
                  </ValidatedField>
                  <FormText>
                    <Translate contentKey="entity.validation.required">This field is required.</Translate>
                  </FormText>
                </>
              ))}
              {/* <h5>Add hình ảnh cho xe của bạn</h5>
              <input onChange={handleChangeImg} type="file" multiple accept=".png, .jpg, .jpeg" />
              <img style={{ maxHeight: '300px', maxWidth: '300px' }} src={img} /> */}
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/car" replace color="info">
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

export default CarUpdate;
