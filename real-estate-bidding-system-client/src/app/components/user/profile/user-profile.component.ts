import {Component, OnInit} from '@angular/core';
import {HttpClientService} from '../../../core/service/http-client.service';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../../../core/service/user/user.service';
import {UserProfileResponseModel} from '../../../core/model/response/user/user-profile-response.model';
import {EditProfileRequestModel} from '../../../core/model/request/profile/edit-profile-request.model';
import {ProfileService} from '../../../core/service/user/profile/profile.service';
import {RoleResponseModel} from '../../../core/model/response/role/role-response.model';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  private hasAuthorities: boolean;
  private isEditBtnClicked: boolean;
  private isDeleteBtnClicked: boolean;
  private makeAdmin: boolean;
  private user: UserProfileResponseModel; // who is current
  private editUserRequestModel: EditProfileRequestModel; // who will be edited editvam
  private profilePageUser: UserProfileResponseModel; // which profile we are looking at
  readonly ROLE_ADMIN = 'ROLE_ADMIN';
  readonly ID = 'id';
  readonly RADIX = 10;
  // readonly HOME_PAGE_URL = '/';

  constructor(
    private http: HttpClientService,
    private route: ActivatedRoute,
    // private router: Router,
    private userService: UserService,
    private profileService: ProfileService
  ) {
  }

  ngOnInit() {
    const profilePageUrlId = parseInt(this.route.snapshot.paramMap.get(this.ID), this.RADIX);

    this.userService.getCurrentlyLoggedInUserForProfile()
      .subscribe((res: UserProfileResponseModel) => {
        this.user = res;
        this.hasAuthorities = (res.rolesList.length > 1) || (profilePageUrlId === res.id);
      });

    this.userService.getUserById(profilePageUrlId)
      .subscribe((res: UserProfileResponseModel) => {
        this.profilePageUser = res;
        this.makeAdmin = this.profilePageUser.rolesList.length > 1;
      });

    this.editUserRequestModel = new EditProfileRequestModel('', '', '', '', '',
      '', '', []);
  }

  onProfileEditSubmit() {
    if (this.makeAdmin) {
      this.editUserRequestModel.roles.push(new RoleResponseModel(this.ROLE_ADMIN));
    }

    this.profileService.editProfile(this.editUserRequestModel, this.profilePageUser.id)
      .subscribe((res) => {
        // this.router.navigate([this.HOME_PAGE_URL]);
      });
  }

  onProfileDeleteSubmit() {
    this.profileService.deleteProfile(this.profilePageUser.id)
      .subscribe((res) => {
        this.userService.logout();
      });
  }

  onEdit() {
    this.isEditBtnClicked = !this.isEditBtnClicked;
    this.isDeleteBtnClicked = false;
  }

  onDelete() {
    this.isEditBtnClicked = false;
    this.isDeleteBtnClicked = !this.isDeleteBtnClicked;
  }

}
