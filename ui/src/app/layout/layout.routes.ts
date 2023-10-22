import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from './layout.component';
const routes: Routes = [
  { path: '', component: LayoutComponent, children: [
    { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
    { path: 'dashboard', loadChildren: '../home/home.module#HomeModule' },
    //{ path: 'another-page', loadChildren: '../another/another.module#AnotherModule' },
  ]}
];

export const ROUTES = RouterModule.forChild(routes);
