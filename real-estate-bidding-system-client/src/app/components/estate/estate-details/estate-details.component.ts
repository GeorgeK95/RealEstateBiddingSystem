import {Component, OnInit} from '@angular/core';
import {EstateViewModel} from '../../../core/model/response/estate/estate-view.model';
import {EstateService} from '../../../core/service/estate/estate.service';
import {ActivatedRoute} from '@angular/router';
import {BidRequestModel} from '../../../core/model/request/bid/bid-request.model';
import {UserService} from '../../../core/service/user/user.service';
import {UserResponseModel} from '../../../core/model/response/user/user-response.model';

@Component({
  selector: 'app-estate-details',
  templateUrl: './estate-details.component.html',
  styleUrls: ['./estate-details.component.css']
})
export class EstateDetailsComponent implements OnInit {
  private estate: EstateViewModel;
  private bidRequestModel: BidRequestModel;
  private hasAuthorities = false;

  constructor(
    private estateService: EstateService,
    private userService: UserService,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
    const id = Number(this.route.snapshot.url[0].path);

    this.estateService.getEstateById(id)
      .subscribe((res: EstateViewModel) => {
        this.estate = res;
        this.bidRequestModel = new BidRequestModel('', 0);
        this.bidRequestModel.price = res.lastBid;

        if (this.isLoggedIn()) {
          this.userService.getCurrentlyLoggedInUser()
            .subscribe((userResModel: UserResponseModel) => {
              this.hasAuthorities = (userResModel.rolesList.length > 1) || (userResModel.id === this.estate.author.id);
            });
        }
      });
  }

  placeBid() {
    // this.bidRequestModel.authorToken = this.userService.authToken;

    this.estateService.createBid(this.bidRequestModel, this.estate.id)
      .subscribe((res: EstateViewModel) => {
        this.estate = res;
      });
  }

  onEditEstate() {
    console.log('edittt');
  }

  onDeleteEstate() {
    this.estateService.deleteEstate(this.estate.id).subscribe();
  }

  isLoggedIn() {
    return this.userService.checkIfLoggedIn();
  }
}
