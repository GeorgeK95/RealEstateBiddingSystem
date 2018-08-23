import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {EstateService} from '../../../core/service/estate/estate.service';
import {EstateViewModel} from '../../../core/model/response/estate/estate-view.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  readonly ESTATE_DETAILS_URL = '/estates/';
  private estates: EstateViewModel[];

  constructor(private router: Router, private estateService: EstateService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.estateService.getEstates()
      .subscribe((res: EstateViewModel[]) => {
        this.estates = res;
      });
  }

  navigateToDetails(estateId: number) {
    this.router.navigate([this.ESTATE_DETAILS_URL + estateId]);
  }
}
