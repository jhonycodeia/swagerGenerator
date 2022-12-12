package com.paymentchain.billing.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Este modelo contiene las propiedades relacionadas a garantia extendida")
public class ExtendedWarranty {

	@ApiModelProperty(name = "isExtendedWarranty",required = false,example = "true", value = "indica si el producto posee garantia extendida")
	private boolean isExtendedWarranty;	
	@ApiModelProperty(name = "isExtendedWarrantyApplied",required = false,example = "true", value = "Indica si tiene aplicada garantia extendida")
	private boolean isExtendedWarrantyApplied;	
	@ApiModelProperty(name = "isExtendedWarrantyProduct",required = false,example = "true", value = "Indica si el producto posee garantia extendida")
	private boolean isExtendedWarrantyProduct;	
	@ApiModelProperty(name = "price",required = false,example = "true", value = "Indica el costo de la garantia")
	private String price;	
	@ApiModelProperty(name = "deliveryPointOfService",required = false,example = "true", value = "codigo utilizado en el form")
	private String deliveryPointOfService;	
}
