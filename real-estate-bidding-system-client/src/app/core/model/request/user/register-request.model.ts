export class RegisterRequestModel {
  constructor(
    public email: string,
    public password: string,
    public confirm: string,
    public firstName: string,
    public lastName: string,
    public telephone: string,
    public town: string
  ) {
  }
}
