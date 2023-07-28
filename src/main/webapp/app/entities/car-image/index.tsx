import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import CarImage from './car-image';
import CarImageDetail from './car-image-detail';
import CarImageUpdate from './car-image-update';
import CarImageDeleteDialog from './car-image-delete-dialog';

const CarImageRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<CarImage />} />
    <Route path="new" element={<CarImageUpdate />} />
    <Route path=":id">
      <Route index element={<CarImageDetail />} />
      <Route path="edit" element={<CarImageUpdate />} />
      <Route path="delete" element={<CarImageDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CarImageRoutes;
