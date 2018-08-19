import {CityResponseModel} from '../../response/city/city-response.model';
import {PeculiarityViewModel} from '../../view/peculiarity-view.model';

export class NewEstateRequestModel {
  constructor(
    public action: any,
    public type: string,
    public city: string,
    public floor: number,
    public area: number,
    public image: string, // object[]
    public additionalInfo: string,
    public peculiarities: PeculiarityViewModel[]
  ) {
  }
}
