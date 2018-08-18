import {Injectable} from '@angular/core';
import {HttpClientService} from '../../http-client.service';
import {EditProfileRequestModel} from '../../../model/request/profile/edit-profile-request.model';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  readonly USER_DETAILS_URL = `http://localhost:8080/users/details/`;

  constructor(private httpClient: HttpClientService) {
  }

  editProfile(profileRequestModel: EditProfileRequestModel, id: number) {
    return this.httpClient.put<EditProfileRequestModel>(this.USER_DETAILS_URL + id, profileRequestModel);
  }

  deleteProfile(id: number) {
    return this.httpClient.delete(this.USER_DETAILS_URL + id);
  }
}
