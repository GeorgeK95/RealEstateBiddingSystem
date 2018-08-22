import {Component, OnInit} from '@angular/core';
import {EstateViewModel} from '../../../core/model/response/estate/estate-view.model';
import {EstateService} from '../../../core/service/estate/estate.service';
import {ActivatedRoute} from '@angular/router';
import {BidRequestModel} from '../../../core/model/request/bid/bid-request.model';

@Component({
  selector: 'app-estate-details',
  templateUrl: './estate-details.component.html',
  styleUrls: ['./estate-details.component.css']
})
export class EstateDetailsComponent implements OnInit {
  private estate: EstateViewModel;
  private bidRequestModel: BidRequestModel;

  constructor(private estateService: EstateService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    const id = Number(this.route.snapshot.url[0].path);

    this.estateService.getEstateById(id)
      .subscribe((res: EstateViewModel) => {
        this.estate = res;
      });
  }

  placeBid() {
    this.estateService.createBid(this.bidRequestModel, this.estate.id)
      .subscribe((res: EstateViewModel) => {
        this.estate = res;
        console.log('bidd');
      });
  }
}
