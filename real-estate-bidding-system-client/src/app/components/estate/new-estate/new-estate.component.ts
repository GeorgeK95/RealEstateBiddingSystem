import {Component, OnInit} from '@angular/core';
import {NewEstateRequestModel} from '../../../core/model/request/estate/new-estate-request.model';
import {EstateService} from '../../../core/service/estate/estate.service';
import {CityResponseModel} from '../../../core/model/response/city/city-response.model';

@Component({
  selector: 'app-new-estate',
  templateUrl: './new-estate.component.html',
  styleUrls: ['./new-estate.component.css']
})
export class NewEstateComponent implements OnInit {
  private countries: any;
  private cityResponseModel: CityResponseModel;
  private requestModel: NewEstateRequestModel;

  constructor(private estateService: EstateService) {
  }

  ngOnInit() {
    this.requestModel = new NewEstateRequestModel(null, '', '', '', 0, 0,
      '', '', []);

    this.estateService.getCities()
      .subscribe((res: any) => {
        this.cityResponseModel = res.body;
      });

    this.estateService.getTypes()
      .subscribe((res) => {
        console.log(res);
      });
  }

  onNewEstateFormSubmit() {
    console.log('newww');
  }
}
