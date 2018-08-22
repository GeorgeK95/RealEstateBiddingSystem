import {PeculiarityViewModel} from '../../view/peculiarity-view.model';

export class EstateViewModel {
  constructor(
    public id: number,
    public action: any,
    public type: string,
    public city: string,
    public area: number,
    public coverImage: string,
    public firstImage: string,
    public secondImage: string,
    public thirdImage: string,
    public additionalInfo: string,
    public peculiarities: PeculiarityViewModel[]
  ) {
  }
}
