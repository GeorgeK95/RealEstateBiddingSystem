import {Injectable} from '@angular/core';
import {HttpClientService} from '../../http-client.service';
import {EditProfileRequestModel} from '../../../model/request/profile/edit-profile-request.model';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  readonly EDIT_PROFILE_URL = `http://localhost:8080/users/details/`;

  constructor(private httpClient: HttpClientService) {
  }

  editProfile(profileRequestModel: EditProfileRequestModel, id: number) {
    return this.httpClient.put<EditProfileRequestModel>(this.EDIT_PROFILE_URL + id, profileRequestModel);
  }

}
