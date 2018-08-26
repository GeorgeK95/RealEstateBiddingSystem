import {Injectable} from '@angular/core';
import {HttpClientService} from '../http-client.service';
import {EstateRequestModel} from '../../model/request/estate/estate-request.model';
import {EstateViewModel} from '../../model/response/estate/estate-view.model';
import {BidRequestModel} from '../../model/request/bid/bid-request.model';

@Injectable({
  providedIn: 'root'
})
export class EstateService {
  readonly GET_COUNTRIES_URL = 'http://localhost:8080/estates/cities';
  readonly GET_TYPES_URL = 'http://localhost:8080/estates/types';
  readonly GET_PECULIARITIES_URL = 'http://localhost:8080/estates/peculiarities';
  readonly GET_ESTATES_URL = 'http://localhost:8080/estates/all';
  readonly GET_ESTATE_URL = 'http://localhost:8080/estates/';
  readonly ESTATE_URL = 'http://localhost:8080/estates/new';

  constructor(private httpClient: HttpClientService) {
  }

  getCities() {
    return this.httpClient.get(this.GET_COUNTRIES_URL);
  }

  getTypes() {
    return this.httpClient.get(this.GET_TYPES_URL);
  }

  createEstate(requestModel: any) {
    return this.httpClient.post<EstateRequestModel>(this.ESTATE_URL, requestModel);
  }

  getPeculiarities() {
    return this.httpClient.get(this.GET_PECULIARITIES_URL);
  }

  getEstates(page: number, city?: string, area?: number, price?: number) {
    const pageString = `?page=${page}`;
    const priceString = `&city=${city}`;
    const cityString = `&area=${area}`;
    const areaString = `&price=${price}`;
    const url = this.GET_ESTATES_URL + pageString + priceString + cityString + areaString;
    return this.httpClient.get<EstateViewModel[]>(url);
  }

  getEstateById(id: number) {
    return this.httpClient.get<EstateViewModel>(this.GET_ESTATE_URL + id);
  }

  createBid(bid: BidRequestModel, id: number) {
    const BID_URL = `http://localhost:8080/estates/${id}/bids`;
    return this.httpClient.post(BID_URL, bid);
  }

  deleteEstate(id: number) {
    const BID_URL = `http://localhost:8080/estates/${id}`;
    return this.httpClient.delete(BID_URL);
  }

  editEstate(editModel: any) {
    return this.httpClient.put(this.ESTATE_URL, editModel);
  }
}
