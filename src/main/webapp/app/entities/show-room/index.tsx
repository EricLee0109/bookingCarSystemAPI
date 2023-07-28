import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ShowRoom from './show-room';
import ShowRoomDetail from './show-room-detail';
import ShowRoomUpdate from './show-room-update';
import ShowRoomDeleteDialog from './show-room-delete-dialog';

const ShowRoomRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<ShowRoom />} />
    <Route path="new" element={<ShowRoomUpdate />} />
    <Route path=":id">
      <Route index element={<ShowRoomDetail />} />
      <Route path="edit" element={<ShowRoomUpdate />} />
      <Route path="delete" element={<ShowRoomDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ShowRoomRoutes;
