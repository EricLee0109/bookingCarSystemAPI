import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Wallet from './wallet';
import WalletDetail from './wallet-detail';
import WalletUpdate from './wallet-update';
import WalletDeleteDialog from './wallet-delete-dialog';

const WalletRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Wallet />} />
    <Route path="new" element={<WalletUpdate />} />
    <Route path=":id">
      <Route index element={<WalletDetail />} />
      <Route path="edit" element={<WalletUpdate />} />
      <Route path="delete" element={<WalletDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default WalletRoutes;
