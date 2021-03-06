import {Component, OnInit} from '@angular/core';
import {EstateViewModel} from '../../../core/model/response/estate/estate-view.model';
import {EstateService} from '../../../core/service/estate/estate.service';
import {ActivatedRoute, Router} from '@angular/router';
import {BidRequestModel} from '../../../core/model/request/bid/bid-request.model';
import {UserService} from '../../../core/service/user/user.service';
import {UserResponseModel} from '../../../core/model/response/user/user-response.model';
import {CityResponseModel} from '../../../core/model/response/city/city-response.model';
import {TypeResponseModel} from '../../../core/model/response/city/type-response.model';
import {PeculiarityViewModel} from '../../../core/model/view/peculiarity/peculiarity-view.model';
import {ImageViewModel} from '../../../core/model/view/image/image-view.model';
import {EditEstateRequestModel} from '../../../core/model/request/estate/edit-estate-request.model';

@Component({
  selector: 'app-estate-details',
  templateUrl: './estate-details.component.html',
  styleUrls: ['./estate-details.component.css']
})
export class EstateDetailsComponent implements OnInit {
  // readonly HOME_PAGE_URL = '/';
  private currentUserId: number;
  private estate: EstateViewModel;
  private bidRequestModel: BidRequestModel;
  private hasAuthorities = false;
  private showEditForm = false;
  private showDeleteForm = false;
  private showMore = false;

  private editModel: EditEstateRequestModel;

  private selectedImage: ImageViewModel;

  private cities: CityResponseModel[];
  private types: TypeResponseModel[];
  private peculiarities: PeculiarityViewModel[];

  constructor(
    private estateService: EstateService,
    private userService: UserService,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
    const id = Number(this.route.snapshot.url[0].path);

    this.estateService.getEstateById(id)
      .subscribe((res: any) => {
        this.estate = res;
        this.editModel = res;
        this.selectedImage = res.images[0] || res.coverImage;
        this.bidRequestModel = new BidRequestModel(0);
        this.bidRequestModel.price = res.lastBid;

        if (this.isLoggedIn()) {
          this.userService.getCurrentlyLoggedInUser()
            .subscribe((userResModel: UserResponseModel) => {
              this.hasAuthorities = (userResModel.rolesList.length > 1) || (userResModel.id === this.estate.author.id);
            });
        }
      });

    this.estateService.getCities()
      .subscribe((res: any) => {
        this.cities = res.body;
      });

    this.estateService.getTypes()
      .subscribe((res: any) => {
        this.types = res.body;
      });

    this.estateService.getPeculiarities()
      .subscribe((res: any) => {
        this.peculiarities = res.body;
      });

    if (this.isLoggedIn()) {
      this.userService.getCurrentlyLoggedInUser()
        .subscribe((res: UserResponseModel) => {
          this.currentUserId = res.id;
        });
    }
  }

  placeBid() {
    this.estateService.createBid(this.bidRequestModel, this.estate.id)
      .subscribe((res: EstateViewModel) => {
        this.estate = res;
      });
  }

  onEditEstateShow() {
    this.showEditForm = !this.showEditForm;
  }

  onEditEstateFormSubmit() {
    this.estateService.editEstate(this.editModel).subscribe();
  }

  onDeleteEstateShow() {
    this.showDeleteForm = !this.showDeleteForm;
  }

  onDeleteEstateFormSubmit() {
    this.estateService.deleteEstate(this.estate.id).subscribe();
  }

  isLoggedIn() {
    return this.userService.checkIfLoggedIn();
  }

  onArrowClick() {
    this.showMore = !this.showMore;
  }

  isAuthor() {
    return this.currentUserId === this.estate.author.id;
  }

  changeSelectedImage(image: ImageViewModel) {
    this.selectedImage = image;
  }
}
