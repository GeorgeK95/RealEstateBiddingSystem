import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClientService} from '../../../core/service/http-client.service';
import {UserService} from '../../../core/service/user/user.service';
import {UserResponseModel} from '../../../core/model/response/user/user-response.model';

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.css']
})
export class AllUsersComponent implements OnInit {
  readonly USER_BY_ID_URL = '/users/';
  readonly SEPARATOR = ', ';
  private users: UserResponseModel[];
  private cloned: UserResponseModel[];
  private page = 1;
  private itemsPerPage = 6;
  private currentlyLoggedInEmail = 'ivan@abv.bg';
  readonly ALL_USERS = 'allUsers';

  constructor(
    private http: HttpClientService,
    private route: ActivatedRoute,
    private router: Router,
    private service: UserService
  ) {
  }

  ngOnInit() {
    this.service.getUsers()
      .subscribe((res: UserResponseModel[]) => {
        this.users = res;
        this.cloned = res;
      });

    this.service.getCurrentlyLoggedInUser()
      .subscribe((res: UserResponseModel) => {
        this.currentlyLoggedInEmail = res.email;
      });
  }

  searchByUsername(targetUsername: string) {
    if (!targetUsername) {
      this.users = this.cloned;
      return;
    }

    const found = this.cloned.filter(
      (user: UserResponseModel) => user.email.includes(targetUsername) ||
        user.firstName.includes(targetUsername) ||
        user.lastName.includes(targetUsername) ||
        (user.telephone && user.telephone.includes(targetUsername)) ||
        user.town.includes(targetUsername)
    );

    if (found) {
      this.users = found;
    }
  }

  navigateToProfile(id: number) {
    this.router.navigate([this.USER_BY_ID_URL + id]);
  }
}
