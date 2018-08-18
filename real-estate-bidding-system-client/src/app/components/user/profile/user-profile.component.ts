import {Component, OnInit} from '@angular/core';
import {HttpClientService} from '../../../core/service/http-client.service';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../../../core/service/user/user.service';
import {UserProfileResponseModel} from '../../../core/model/response/user/user-profile-response.model';
import {EditProfileRequestModel} from '../../../core/model/request/profile/edit-profile-request.model';
import {ProfileService} from '../../../core/service/user/profile/profile.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  private hasAuthorities: boolean;
  private isEditBtnClicked: boolean;
  private isDeleteBtnClicked: boolean;
  private user: UserProfileResponseModel; // who is current
  private editUserRequestModel: EditProfileRequestModel; // who will be edited editvam
  private profilePageUser: UserProfileResponseModel; // which profile we are looking at
  readonly ID = 'id';
  readonly VALID = 'VALID';
  readonly RADIX = 10;
  readonly PROFILE_PAGE_URL = `/users/details/`;

  constructor(
    private http: HttpClientService,
    private route: ActivatedRoute,
    private router: Router,
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
      });

    this.editUserRequestModel = new EditProfileRequestModel('', '', '', '', '',
      '', '');
  }

  onEdit() {
    this.isEditBtnClicked = !this.isEditBtnClicked;
    this.isDeleteBtnClicked = false;
  }

  onDelete() {
    this.isEditBtnClicked = false;
    this.isDeleteBtnClicked = !this.isDeleteBtnClicked;
  }

  onProfileEditSubmit() {
    this.profileService.editProfile(this.editUserRequestModel, this.profilePageUser.id)
      .subscribe((res) => {
        // toastr
        this.router.navigate([this.PROFILE_PAGE_URL + this.profilePageUser.id]);
      });
  }

  onProfileDeleteSubmit() {
    this.profileService.deleteProfile(this.profilePageUser.id)
      .subscribe((res) => {
        // toastr
        this.router.navigate([this.PROFILE_PAGE_URL + this.profilePageUser.id]);
      });
  }
}
