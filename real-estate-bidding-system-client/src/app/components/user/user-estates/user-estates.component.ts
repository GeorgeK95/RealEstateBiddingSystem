import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {EstateService} from '../../../core/service/estate/estate.service';
import {EstateViewModel} from '../../../core/model/response/estate/estate-view.model';
import {UserService} from '../../../core/service/user/user.service';
import {UserResponseModel} from '../../../core/model/response/user/user-response.model';

@Component({
  selector: 'app-home',
  templateUrl: './user-estates.component.html',
  styleUrls: ['./user-estates.component.css']
})
export class UserEstatesComponent implements OnInit {

  readonly ESTATE_DETAILS_URL = '/estates/';
  private estates: EstateViewModel[];

  constructor(private router: Router, private userService: UserService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.userService.getCurrentlyLoggedInUser()
      .subscribe((user: UserResponseModel) => {
        this.userService.getUserEstates(user.id)
          .subscribe((estates: EstateViewModel[]) => {
            this.estates = estates;
          });
      });
  }

  navigateToDetails(estateId: number) {
    this.router.navigate([this.ESTATE_DETAILS_URL + estateId]);
  }
}
