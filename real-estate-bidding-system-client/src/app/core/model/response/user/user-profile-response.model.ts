import {RoleResponseModel} from '../role/role-response.model';

export class UserProfileResponseModel {
  constructor(
    public id: number,
    public email: string,
    public firstName: string,
    public lastName: string,
    public telephone: string,
    public town: string,
    public isBanned: boolean,
    public rolesList: RoleResponseModel[]
  ) {
  }
}
