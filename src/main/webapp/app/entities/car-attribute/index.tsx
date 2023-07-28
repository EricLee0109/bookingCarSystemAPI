import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import CarAttribute from './car-attribute';
import CarAttributeDetail from './car-attribute-detail';
import CarAttributeUpdate from './car-attribute-update';
import CarAttributeDeleteDialog from './car-attribute-delete-dialog';

const CarAttributeRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<CarAttribute />} />
    <Route path="new" element={<CarAttributeUpdate />} />
    <Route path=":id">
      <Route index element={<CarAttributeDetail />} />
      <Route path="edit" element={<CarAttributeUpdate />} />
      <Route path="delete" element={<CarAttributeDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CarAttributeRoutes;
