import {PeculiarityViewModel} from '../../view/peculiarity-view.model';

export class NewEstateRequestModel {
  constructor(
    public action: any,
    public type: string,
    public city: string,
    public area: number,
    public coverImage: File,
    /*public image1: File,
    public image2: File,
    public image3: File,*/
    public additionalInfo: string,
    public peculiarities: PeculiarityViewModel[]
  ) {
  }
}
