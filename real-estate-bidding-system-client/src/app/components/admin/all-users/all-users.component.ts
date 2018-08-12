import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpClientService} from '../../../core/service/http-client.service';
import {UserService} from '../../../core/service/user/user.service';

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.css']
})
export class AllUsersComponent implements OnInit {
  private user: any;

  constructor(
    private http: HttpClientService,
    private route: ActivatedRoute,
    private service: UserService
  ) {
  }

  ngOnInit() {
    this.service.getUsers()
      .subscribe((res: any) => { // UsersResponseModel
        this.user = res;
      });
  }


}
