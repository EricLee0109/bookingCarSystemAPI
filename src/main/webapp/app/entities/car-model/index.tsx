import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import CarModel from './car-model';
import CarModelDetail from './car-model-detail';
import CarModelUpdate from './car-model-update';
import CarModelDeleteDialog from './car-model-delete-dialog';

const CarModelRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<CarModel />} />
    <Route path="new" element={<CarModelUpdate />} />
    <Route path=":id">
      <Route index element={<CarModelDetail />} />
      <Route path="edit" element={<CarModelUpdate />} />
      <Route path="delete" element={<CarModelDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CarModelRoutes;
