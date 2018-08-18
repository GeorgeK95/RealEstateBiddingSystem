export class NewEstateRequestModel {
  constructor(
    public action: boolean,
    public type: string,
    public country: string,
    public town: string,
    public floor: number,
    public area: number,
    public image: string, // object[]
    public additionalInfo: string,
    public peculiarities: string[]
  ) {
  }
}
