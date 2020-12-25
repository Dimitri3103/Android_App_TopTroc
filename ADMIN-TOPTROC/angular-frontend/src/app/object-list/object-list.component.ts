import { Component, OnInit } from '@angular/core';
import { ObjectModel } from '../objectmodel'
import { ObjectService } from '../object.service'
import { Router } from '@angular/router';

@Component({
  selector: 'app-object-list',
  templateUrl: './object-list.component.html',
  styleUrls: ['./object-list.component.css']
})
export class ObjectListComponent implements OnInit {

  objects: ObjectModel[];

  constructor(private objectService: ObjectService,
    private router: Router) { }

  ngOnInit(): void {
    this.getObjects();
  }

  private getObjects(){
    this.objectService.getObjectsList().subscribe(data => {
      this.objects = data;
    });
  }

  objectDetails(id: number){
    this.router.navigate(['object-details', id]);
  }

  updateObject(id: number){
    this.router.navigate(['update-object', id]);
  }

  deleteObject(id: number){
    this.objectService.deleteObject(id).subscribe( data => {
      console.log(data);
      this.getObjects();
    })
  }

}
