import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpClientService} from '../../../core/service/http-client.service';
import {UserService} from '../../../core/service/user/user.service';
import {UserResponseModel} from '../../../core/model/response/user-response.model';

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.css']
})
export class AllUsersComponent implements OnInit {
  readonly SEPARATOR = ', ';
  private users: UserResponseModel;
  private page = 1;
  private itemsPerPage = 6;
  private currentlyLoggedInEmail = 'ivan@abv.bg';
  readonly ALL_USERS = 'allUsers';

  constructor(
    private http: HttpClientService,
    private route: ActivatedRoute,
    private service: UserService
  ) {
  }

  ngOnInit() {
    this.service.getUsers()
      .subscribe((res: UserResponseModel) => {
        this.users = res;
      });

    this.service.getCurrentlyLoggedInUser()
      .subscribe((res: UserResponseModel) => {
        this.currentlyLoggedInEmail = res.email;
      });
  }

  searchByUsername(targetUsername: string) {
    /*const found = this.users.sortedByPopulation.filter(
      (user: UserResponseModel) => user.username.includes(targetUsername)
    );

    if (found) {
      this.statistics.users = found;
    }*/
  }

}
