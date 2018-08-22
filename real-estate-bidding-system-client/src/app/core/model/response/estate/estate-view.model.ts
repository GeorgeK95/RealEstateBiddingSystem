import {PeculiarityViewModel} from '../../view/peculiarity/peculiarity-view.model';
import {ImageViewModel} from '../../view/image/image-view.model';
import {UserViewModel} from '../../view/user/user-view.model';

export class EstateViewModel {
  constructor(
    public id: number,
    public action: any,
    public type: string,
    public city: string,
    public area: number,
    public price: number,
    public coverImage: ImageViewModel,
    public images: ImageViewModel[],
    // public firstImage: string,
    // public secondImage: string,
    // public thirdImage: string,
    public additionalInfo: string,
    public author: UserViewModel,
    public peculiarities: PeculiarityViewModel[]
  ) {
  }
}
