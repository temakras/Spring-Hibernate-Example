import {Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Product} from "../../model/product";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
import {regex} from "../../constants/regex";

@Component({
    selector: 'product-update-component',
    templateUrl: 'product-update.component.html'
})
export class ProductUpdateComponent implements OnInit {
    productForm: FormGroup;
    loading: boolean;
    product: Product;

    constructor(private productService: CommonService, private router: Router, private route: ActivatedRoute) {
        this.loading = false;
    }

    ngOnInit(): void {
        this.createEmptyForm();
        this.fillForm();
    }

    private createEmptyForm(): void {
        this.productForm = new FormGroup({
            name: new FormControl('', Validators.required),
            cost: new FormControl('', [Validators.required, Validators.pattern(regex.DOUBLE)]),
            type: new FormControl(''),
            details: new FormControl('')
        });
    }

    private fillForm(): void {
        this.productService.loadById(api.PRODUCT, this.route.snapshot.params['id'])
            .subscribe(
                product => {
                    this.product = product;
                    this.productForm.setValue({
                        name: this.product.name,
                        cost: this.product.cost,
                        type: this.product.type,
                        details: this.product.details
                    });
                },
                error => this.logError(error));
    }

    onSubmit() {
        this.loading = true;
        this.fillUpdatedProduct();
        this.productService.update(api.PRODUCT, this.product)
            .subscribe(
                () => this.router.navigate(['product/product-content']),
                err => this.logError(err));
    }

    private fillUpdatedProduct(): void {
        this.product.name = this.productForm.value.name;
        this.product.cost = this.productForm.value.cost;
        this.product.type = this.productForm.value.type;
        this.product.details = this.productForm.value.details;
    }

    logError(error) {
        this.loading = false;
        console.error('There was an error: ' + error.message ? error.message : error.toString());
        this.router.navigate(['/']);
    }
}