import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Car from './car';
import CarDetail from './car-detail';
import CarUpdate from './car-update';
import CarDeleteDialog from './car-delete-dialog';

const CarRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Car />} />
    <Route path="new" element={<CarUpdate />} />
    <Route path=":id">
      <Route index element={<CarDetail />} />
      <Route path="edit" element={<CarUpdate />} />
      <Route path="delete" element={<CarDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CarRoutes;
