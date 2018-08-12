export class LoginResponseModel {
  constructor(
    public accessToken: string,
    public tokenType: string,
    public username: string
  ) {
  }

}
