import { Component, OnInit } from '@angular/core';
import { ObjectModel } from '../objectmodel';
import { ActivatedRoute } from '@angular/router';
import { ObjectService } from '../object.service';

@Component({
  selector: 'app-object-details',
  templateUrl: './object-details.component.html',
  styleUrls: ['./object-details.component.css']
})
export class ObjectDetailsComponent implements OnInit {

  id: number
  object: ObjectModel

  constructor(private route: ActivatedRoute, private objectService: ObjectService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.object = new ObjectModel();
    this.objectService.getObjectById(this.id).subscribe( data => {
      this.object = data;
    });
  }

}
