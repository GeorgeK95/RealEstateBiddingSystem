import {Injectable} from '@angular/core';
import {HttpClientService} from '../http-client.service';
import {NewEstateRequestModel} from '../../model/request/estate/new-estate-request.model';

@Injectable({
  providedIn: 'root'
})
export class EstateService {
  readonly GET_COUNTRIES_URL = 'http://localhost:8080/estates/cities';
  readonly GET_TYPES_URL = 'http://localhost:8080/estates/types';
  readonly GET_PECULIARITIES_URL = 'http://localhost:8080/estates/peculiarities';
  readonly CREATE_ESTATE_URL = 'http://localhost:8080/estates/new';

  constructor(private httpClient: HttpClientService) {
  }

  getCities() {
    return this.httpClient.get(this.GET_COUNTRIES_URL);
  }

  getTypes() {
    return this.httpClient.get(this.GET_TYPES_URL);
  }

  createEstate(requestModel: NewEstateRequestModel) {
    return this.httpClient.post<NewEstateRequestModel>(this.CREATE_ESTATE_URL, requestModel);
  }

  getPeculiarities() {
    return this.httpClient.get(this.GET_PECULIARITIES_URL);
  }
}
