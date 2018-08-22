import {Component, OnInit} from '@angular/core';
import {NewEstateRequestModel} from '../../../core/model/request/estate/new-estate-request.model';
import {EstateService} from '../../../core/service/estate/estate.service';
import {CityResponseModel} from '../../../core/model/response/city/city-response.model';
import {TypeResponseModel} from '../../../core/model/response/city/type-response.model';
import {PeculiarityViewModel} from '../../../core/model/view/peculiarity-view.model';
import {Router} from '@angular/router';
import {UserService} from '../../../core/service/user/user.service';

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
  private peculiarities: PeculiarityViewModel[];
  private showMore = false;

  readonly HOME_PAGE_URL = '/';

  constructor(
    private estateService: EstateService,
    private router: Router,
    private userService: UserService
  ) {
  }

  ngOnInit() {
    this.requestModel = new NewEstateRequestModel(null, '', '', 0, null,
      '', '', '', '', [], '');

    this.estateService.getCities()
      .subscribe((res: any) => {
        this.cities = res.body;
      });

    this.estateService.getTypes()
      .subscribe((res: any) => {
        this.types = res.body;
      });

    this.estateService.getPeculiarities()
      .subscribe((res: any) => {
        this.peculiarities = res.body;
      });
  }

  onNewEstateFormSubmit() {
    this.requestModel.peculiarities = this.peculiarities.filter(p => p.checked);
    this.requestModel.authorToken = this.userService.authToken;

    this.estateService.createEstate(this.requestModel)
      .subscribe((res) => {
        this.router.navigate([this.HOME_PAGE_URL]);
      });
  }

  onArrowClick() {
    this.showMore = !this.showMore;
  }
}
