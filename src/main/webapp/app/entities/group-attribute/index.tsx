import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import GroupAttribute from './group-attribute';
import GroupAttributeDetail from './group-attribute-detail';
import GroupAttributeUpdate from './group-attribute-update';
import GroupAttributeDeleteDialog from './group-attribute-delete-dialog';

const GroupAttributeRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<GroupAttribute />} />
    <Route path="new" element={<GroupAttributeUpdate />} />
    <Route path=":id">
      <Route index element={<GroupAttributeDetail />} />
      <Route path="edit" element={<GroupAttributeUpdate />} />
      <Route path="delete" element={<GroupAttributeDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default GroupAttributeRoutes;
