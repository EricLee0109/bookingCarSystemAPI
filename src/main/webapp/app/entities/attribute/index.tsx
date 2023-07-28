import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Attribute from './attribute';
import AttributeDetail from './attribute-detail';
import AttributeUpdate from './attribute-update';
import AttributeDeleteDialog from './attribute-delete-dialog';

const AttributeRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Attribute />} />
    <Route path="new" element={<AttributeUpdate />} />
    <Route path=":id">
      <Route index element={<AttributeDetail />} />
      <Route path="edit" element={<AttributeUpdate />} />
      <Route path="delete" element={<AttributeDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default AttributeRoutes;
