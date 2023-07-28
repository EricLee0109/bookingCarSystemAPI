import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import GroupAttribute from './group-attribute';
import Attribute from './attribute';
import CarAttribute from './car-attribute';
import Car from './car';
import CarModel from './car-model';
import Brand from './brand';
import CarImage from './car-image';
import ShowRoom from './show-room';
import Wallet from './wallet';
import Transaction from './transaction';
import Customer from './customer';
import Booking from './booking';
import Employee from './employee';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="group-attribute/*" element={<GroupAttribute />} />
        <Route path="attribute/*" element={<Attribute />} />
        <Route path="car-attribute/*" element={<CarAttribute />} />
        <Route path="car/*" element={<Car />} />
        <Route path="car-model/*" element={<CarModel />} />
        <Route path="brand/*" element={<Brand />} />
        <Route path="car-image/*" element={<CarImage />} />
        <Route path="show-room/*" element={<ShowRoom />} />
        <Route path="wallet/*" element={<Wallet />} />
        <Route path="transaction/*" element={<Transaction />} />
        <Route path="customer/*" element={<Customer />} />
        <Route path="booking/*" element={<Booking />} />
        <Route path="employee/*" element={<Employee />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
