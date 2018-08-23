import {RoleResponseModel} from '../role/role-response.model';
import {EstateViewModel} from '../estate/estate-view.model';

export class UserResponseModel {
  constructor(
    public id: number,
    public email: string,
    public firstName: string,
    public lastName: string,
    public telephone: string,
    public town: string,
    public isBanned: boolean,
    public estates: EstateViewModel[],
    public rolesList: RoleResponseModel[]
  ) {
  }
}
