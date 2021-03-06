import {PeculiarityViewModel} from '../../view/peculiarity/peculiarity-view.model';
import {ImageViewModel} from '../../view/image/image-view.model';
import {UserViewModel} from '../../view/user/user-view.model';

export class EstateViewModel {
  constructor(
    public id: number,
    public action: string,
    public type: string,
    public city: string,
    public area: number,
    public price: number,
    public coverImage: ImageViewModel,
    public images: ImageViewModel[],
    public additionalInfo: string,
    public author: UserViewModel,
    public peculiarities: PeculiarityViewModel[],
    public lastBid: number
  ) {
  }
}
