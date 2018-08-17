export class EditProfileRequestModel {
  constructor(
    // public id: string,
    // public email: string,
    public currentPassword: string,
    public newPassword: string,
    public confirm: string,
    public town: string,
    public telephone: string,
    public firstName: string,
    public lastName: string,
  ) {
  }

}
