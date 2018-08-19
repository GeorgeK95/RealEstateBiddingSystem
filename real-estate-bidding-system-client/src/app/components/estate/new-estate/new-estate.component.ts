import {Component, OnInit} from '@angular/core';
import {NewEstateRequestModel} from '../../../core/model/request/estate/new-estate-request.model';
import {EstateService} from '../../../core/service/estate/estate.service';
import {CityResponseModel} from '../../../core/model/response/city/city-response.model';
import {TypeResponseModel} from '../../../core/model/response/city/type-response.model';
import {PeculiarityViewModel} from '../../../core/model/view/peculiarity-view.model';

@Component({
  selector: 'app-new-estate',
  templateUrl: './new-estate.component.html',
  styleUrls: ['./new-estate.component.css']
})
export class NewEstateComponent implements OnInit {
  readonly COMMA = ',';
  private cities: CityResponseModel[];
  private types: TypeResponseModel[];
  private requestModel: NewEstateRequestModel;

  private peculiarities = [
    {name: 'Panel', id: '1', checked: false},
    {name: 'Brick', id: '2', checked: false},
    {name: 'LFS', id: '3', checked: false},
    {name: 'CFW', id: '4', checked: false},
    {name: 'Elevator', id: '5', checked: false},
    {name: 'In Build', id: '6', checked: false},
    {name: 'Pool', id: '7', checked: false},
    {name: 'Trimmer Joists', id: '8', checked: false},
    {name: 'AirCool', id: '9', checked: false},
    {name: 'Garage', id: '10', checked: false},
    {name: 'Furnished', id: '11', checked: false},
    {name: 'Panorama View', id: '12', checked: false}
  ];

  constructor(private estateService: EstateService) {
  }

  ngOnInit() {
    this.requestModel = new NewEstateRequestModel(null, '', null, 0, 0,
      '', '', []);

    this.estateService.getCities()
      .subscribe((res: any) => {
        this.cities = res.body;
      });

    this.estateService.getTypes()
      .subscribe((res: any) => {
        this.types = res.body;
      });
  }

  onNewEstateFormSubmit() {
    this.requestModel.peculiarities = this.peculiarities.filter(p => p.checked);

    this.estateService.createEstate(this.requestModel)
      .subscribe((res) => {
        console.log(res);
      });
  }
}
