import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Brand from './brand';
import BrandDetail from './brand-detail';
import BrandUpdate from './brand-update';
import BrandDeleteDialog from './brand-delete-dialog';

const BrandRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Brand />} />
    <Route path="new" element={<BrandUpdate />} />
    <Route path=":id">
      <Route index element={<BrandDetail />} />
      <Route path="edit" element={<BrandUpdate />} />
      <Route path="delete" element={<BrandDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default BrandRoutes;
