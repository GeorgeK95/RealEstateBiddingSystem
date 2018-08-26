import {PeculiarityViewModel} from '../../view/peculiarity/peculiarity-view.model';
import {ImageViewModel} from '../../view/image/image-view.model';
import {UserViewModel} from '../../view/user/user-view.model';

export class EditEstateRequestModel {
  constructor(
    public id: number,
    public action: string,
    public type: string,
    public city: string,
    public area: number,
    public price: number,
    public coverImage: ImageViewModel,
    public additionalInfo: string,
    public author: UserViewModel,
    public peculiarities: PeculiarityViewModel[],
    public lastBid: number,
    public firstImage: string,
    public secondImage: string,
    public thirdImage: string,
  ) {
  }
}
