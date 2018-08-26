import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {EstateService} from '../../../core/service/estate/estate.service';
import {EstateViewModel} from '../../../core/model/response/estate/estate-view.model';
import {EstateRequestModel} from '../../../core/model/request/estate/estate-request.model';
import {CityResponseModel} from '../../../core/model/response/city/city-response.model';
import {TypeResponseModel} from '../../../core/model/response/city/type-response.model';
import {EstateSearchModel} from '../../../core/model/request/search/estate-search.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  readonly ESTATE_DETAILS_URL = '/estates/';
  private estates: EstateViewModel[];

  private cities: CityResponseModel[];
  private types: TypeResponseModel[];
  private searchModel: EstateSearchModel;

  private currentPage = 0;

  constructor(private router: Router, private estateService: EstateService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.searchModel = new EstateSearchModel('', 0, 0);

    this.estateService.getEstates(0)
      .subscribe((res: any) => {
        this.estates = res.content;
      });

    this.estateService.getCities()
      .subscribe((res: any) => {
        this.cities = res.body;
      });

    this.estateService.getTypes()
      .subscribe((res: any) => {
        this.types = res.body;
      });
  }

  navigateToDetails(estateId: number) {
    this.router.navigate([this.ESTATE_DETAILS_URL + estateId]);
  }

  loadNextPage() {
    this.estateService.getEstates(++this.currentPage)
      .subscribe((res: any) => {
        for (const current of res.content) {
          this.estates.push(current);
        }
      });
  }

  onFilter() {
    this.estateService.getEstates(0, this.searchModel.city, this.searchModel.area, this.searchModel.price)
      .subscribe((res: any) => {
        this.estates = res.content;
      });
  }
}
