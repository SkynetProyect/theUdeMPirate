import { Routes } from '@angular/router';
import { HomeComponent } from './page/home/home.component';
import { UploadComponent } from './page/upload/upload.component';
import { ReadComponent } from './page/read/read.component';

export const routes: Routes = [

    {
        path:"", 
        component: HomeComponent, 
        //canActivate:[true]
    },
    {
        path:"upload", 
        component: UploadComponent, 
        //canActivate:[true]
    },
    {
        path:"document/:id", 
        component: ReadComponent, 
        //canActivate:[true]
    },
    {
        path:"**", 
        redirectTo: "",
        pathMatch: "full"
    }
   

];
