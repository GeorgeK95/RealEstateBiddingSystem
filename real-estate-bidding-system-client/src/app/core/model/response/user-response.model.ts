import {RoleResponseModel} from './role-response.model';

export class UserResponseModel {
  constructor(
    public id: string,
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
