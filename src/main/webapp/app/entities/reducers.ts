import groupAttribute from 'app/entities/group-attribute/group-attribute.reducer';
import attribute from 'app/entities/attribute/attribute.reducer';
import carAttribute from 'app/entities/car-attribute/car-attribute.reducer';
import car from 'app/entities/car/car.reducer';
import carModel from 'app/entities/car-model/car-model.reducer';
import brand from 'app/entities/brand/brand.reducer';
import carImage from 'app/entities/car-image/car-image.reducer';
import showRoom from 'app/entities/show-room/show-room.reducer';
import wallet from 'app/entities/wallet/wallet.reducer';
import transaction from 'app/entities/transaction/transaction.reducer';
import customer from 'app/entities/customer/customer.reducer';
import booking from 'app/entities/booking/booking.reducer';
import employee from 'app/entities/employee/employee.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  groupAttribute,
  attribute,
  carAttribute,
  car,
  carModel,
  brand,
  carImage,
  showRoom,
  wallet,
  transaction,
  customer,
  booking,
  employee,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
