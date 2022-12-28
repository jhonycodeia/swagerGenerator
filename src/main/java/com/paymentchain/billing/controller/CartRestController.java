/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.billing.controller;

import com.paymentchain.billing.common.InvoiceRequestMapper;
import com.paymentchain.billing.common.InvoiceResposeMapper;
import com.paymentchain.billing.dto.BaseOption;
import com.paymentchain.billing.dto.CartData;
import com.paymentchain.billing.dto.InvoiceRequest;
import com.paymentchain.billing.dto.InvoiceResponse;
import com.paymentchain.billing.dto.CartItems;
import com.paymentchain.billing.dto.CartItemsDataLayer;
import com.paymentchain.billing.dto.Prices;
import com.paymentchain.billing.entities.Invoice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.paymentchain.billing.respository.InvoiceRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Optional;

/**
 *
 * @author sotobotero
 */
@Api(tags = "CartAjax API")
@RestController
@RequestMapping("/newajaxcart")
public class CartRestController {
    
    /*@Autowired
    InvoiceRepository billingRepository;
    
    @Autowired
    InvoiceRequestMapper irm;
    
    @Autowired
    InvoiceResposeMapper irspm;*/
	
	@ApiOperation(value = "Retorna toda la informacion relacionada a los productos", notes = "Este servicio se encarga de consultar los productos del Cart")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Ocurre cuando se realiza una peticion y el Cart no tiene productos o se a perdido los datos de la session",reference = "CartItems"),
        				 })
    @RequestMapping(value = "/products", method = RequestMethod.GET)
	public CartItems products() {
		return new CartItems();
	}
	
	@ApiOperation(value = "Retorna toda la informacion relacionada a los precios", notes = "Este servicio se encarga de consultar los precios del Cart")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Ocurre cuando se realiza una peticion y el Cart no tiene productos o se a perdido los datos de la session",reference = "CartItems"),
        				 })
    @RequestMapping(value = "/prices", method = RequestMethod.GET)
	public Prices prices() {
		return new Prices();
	}
    
	
	@ApiOperation(value = "Retorna todos los productos dentro del carrito junto sus atributo y informacion del Cart", notes = "Este servicio se encarga de eliminar productos del Cart")
	@ApiResponses(value = {
	        @ApiResponse(code = 204, message = "Ocurre cuando se realiza una peticion y el Cart no tiene productos o se a perdido los datos de la session",reference = "CartItems"),
	        @ApiResponse(code = 200, message = "Ocurre cuando el proceso se realiza de forma exitosa puede retornar alguno de estos mensajes:\n\n"
	        		+ "**El producto ha sido removido de su carrito:** *Cuando se ejecuta de forma corresta el proceso* \n",reference = "CartItems"),
	        @ApiResponse(code = 400, message = "Ocurre debido a que el request que se esta enviando no es correcto, puede visualizar alguno de estos mensajes\n\n"
	        		+ "Si no se envia y el formato no es valido para los parametros `entryNumbers` y `actionCode` el servidor responde solo con el codigo de error sin cuerpo\n\n"
	        		+ "**No enum constant de.hybris.platform.acceleratorfacades.cart.action.CartEntryAction. :** *Ocurre cuando `actionCode` no es valido*"
	        		+ "\n\n*example:*\n"
	        		+ "<pre>\n{\n"
	        		+ "  \"carData\": {},\n"
	        		+ "  \"message\": \"No enum constant de.hybris.platform.acceleratorfacades.cart.action.CartEntryAction.\",\n"
	        		+ "  \"success\": false\n"
	        		+ "}\n</pre>"),
	        @ApiResponse(code = 500, message = "Ocurrio algun tipo de error durante el proceso de actualizacion,puede visualizar alguno de estos mensajes\n\n"
	        		+ "**Failed to delete cart entry:** *Error general que se presenta cuando se quiere eliminar un`entryNumber` que no existe* \n"
	        		+ "**No handler found for action {action}:** *Error de comando se presenta cuando `actionCode` no se encuentra*\n"	        		
	        		+ "<pre>\n{\n"
	        		+ "  \"carData\": {},\n"
	        		+ "  \"message\": \"Failed to delete cart entry\",\n"
	        		+ "  \"success\": false\n"
	        		+ "}\n</pre>"),
	        @ApiResponse(code = 501, message = "Ocurre cuando se recibe un errores desconocido\n\n"
	        		+ "**Lo sentimos, ha ocurrido un error inesperado:** *Error generico*\n"	        		
	        		+ "<pre>\n{\n"
	        		+ "  \"carData\": {},\n"
	        		+ "  \"message\": \"Lo sentimos, ha ocurrido un error inesperado\",\n"
	        		+ "  \"success\": false\n"
	        		+ "}\n</pre>")
	        				 })
	@RequestMapping(value = "/entryAsyn/execute/{actionCode:.*}", method = RequestMethod.POST)
    public CartItemsDataLayer executeCartEntryAction(
    		@ApiParam(
    			    name =  "actionCode",    			    
    			    value = "Comando de operacion a ejecutar",
    			    example = "REMOVE",
    			    required = true)
    		@PathVariable(value = "actionCode", required = true) final String actionCode, 
    		@ApiParam(
    			    name =  "entryNumbers",    			    
    			    value = "Lista de items a eliminar",
    			    example = "[0,1,2]",
    			    required = true)
    		@RequestParam("entryNumbers") final Long[] entryNumbers)
    {
		return new CartItemsDataLayer();
    }
    
    @ApiOperation(value = "Retorna todos los productos dentro del carrito junto sus atributo y informacion del Cart", notes = "Este servicio se encarga de actualizar la cantidad de items por producto y eliminar en caso que se requiera si se envia un 0 en el campo quantity")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Ocurre cuando se realiza una peticion y el Cart no tiene productos o se a perdido los datos de la session",reference = "CartItems"),
        @ApiResponse(code = 200, message = "Ocurre cuando el proceso se realiza de forma exitosa puede retornar alguno de estos mensajes:\n\n"
        +"**La cantidad del producto ha sido actualizada:** *Cuando `quantity` es mayor que 0* \n"
        +"**El producto ha sido removido de su carrito:** *Cuando `quantity` es igual a 0*",reference = "CartItems"),
        @ApiResponse(code = 400, message = "Ocurre debido a que el request que se esta enviando no es correcto, puede visualizar alguno de estos mensajes:\n\n"
        		+ "**number must be greater than 0 :** *Ocurre cuando no se envia `entryNumber` Si no llega este campo se le asigna* `-1`\n"
        		+ "**Quantity field cannot be empty :** *No se envia el `Quantity`* \n"
        		+ "**Failed to convert property value of type &#039;java.lang.String&#039; to required type &#039;java.lang.Long&#039; for property &#039;quantity&#039;; nested exception is java.lang.NumberFormatException: For input string: &#034;abc&#034;:** *Formato no valido `Quantity`* \n"
        		+ "**Please provide a positive number to update the quantity of an item:** *El valor de `Quantity` no se encuentra en el rango* \n"
        		+ "\n\n*example:*\n"
        		+ "<pre>\n{\n"
        		+ "  \"carData\": {},\n"
        		+ "  \"message\": \"Quantity field cannot be empty.\",\n"
        		+ "  \"success\": false\n"
        		+ "}\n</pre>"),
        @ApiResponse(code = 500, message = "Ocurrio algun tipo de error durante el proceso de actualizacion,puede visualizar alguno de estos mensajes:\n\n"
        		+ "**Lo sentimos, las unidades superan el límite permitido por cliente en este caso. Puedes comprar hasta un máximo de 4 unidades:** *Excede el limite de unidades*\n"
        		+ "**Lo sentimos, la cantidad disponible en este momento es inferior a la que deseas añadir, continua con las unidades que tienes en el carrito:** *Hay poco Stock*\n"
        		+ "**<a href=\"\" style=\"\">Cel 4G Sams Glxy S7 32Gb Dr</a> ha sido removido del carrito debido a inventario insuficiente:** no hay stock de ese producto por lo cual se elimina\n"
        		+ "**Error occurred while adding to Cart:** *Error General de fallo*"
        		+ "\n\n*example:*\n"
        		+ "<pre>\n{\n"
        		+ "  \"carData\": {},\n"
        		+ "  \"message\": \"Lo sentimos, las unidades superan el límite permitido por cliente en este caso. Puedes comprar hasta un máximo de 4 unidades.\",\n"
        		+ "  \"success\": false\n"
        		+ "}\n</pre>"),
        @ApiResponse(code = 501, message = "Ocurre cuando se recibe un errores desconocido o no controlado\n\n"
        		+ "**Lo sentimos, ha ocurrido un error inesperado:** *Error Generico*\n"	        		
        		+ "<pre>\n{\n"
        		+ "  \"carData\": {},\n"
        		+ "  \"message\": \"Lo sentimos, ha ocurrido un error inesperado\",\n"
        		+ "  \"success\": false\n"
        		+ "}\n</pre>")
        				 })
    @RequestMapping(value = "/updateAsyn", method = RequestMethod.POST)
    public CartItemsDataLayer updateCartQuantities(
    		@ApiParam(
    			    name =  "entryNumber",    			    
    			    value = "Este campo corresponde al numero identificador del producto",
    			    example = "1",
    			    required = true)
    		@RequestParam("entryNumber") final long entryNumber,
    		@ApiParam(
    			    name =  "quantity",
    			    value = "Este es el valor por el cual desea cambiar el numero de items por producto debe ser numero entre 0 y 4",
    			    example = "3",
    			    required = true)
    		 @RequestParam("quantity")final long quantity)
    {
    	return new CartItemsDataLayer();
    }

    
    
    
    
    @ApiOperation(value = "Retorna toda la informacion relacionada a los productos", notes = "Este servicio se actualizar el metodo de envio a recoge en tienda")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Ocurre cuando se realiza una peticion y el Cart no tiene productos o se a perdido los datos de la session",reference = "CartItems"),
        @ApiResponse(code = 200, message = "Ocurre cuando el proceso se realiza de forma exitosa puede retornar alguno de estos mensajes:\n\n"
        		+ "**El metodo de envio ha sido actualizado:** *Cuando se ejecuta de forma corresta el proceso* \n",reference = "CartItems"),
        @ApiResponse(code = 400, message = "Ocurre debido a que el request que se esta enviando no es correcto, puede visualizar alguno de estos mensajes\n\n"
        		+ "**productCodePost field cannot be empty:** *No llega el `productCodePost`*\n"+""
        		+ "**storeNamePost field cannot be empty:** *No llega `storeNamePost`*\n"
        		+ "**entryNumber field cannot be empty:** *No llega `entryNumber`*\n"
        		+ "**isDeliveryMethodUpdated field cannot be empty:** *No llega `isDeliveryMethodUpdated`*\n"
        		+ "**Failed to convert property value of type &#039;java.lang.String&#039; to required type &#039;java.lang.Long&#039; for property &#039;quantity&#039;; nested exception is java.lang.NumberFormatException: For input string: &#034;abc&#034;:** *Formato no valido `hiddenPickupQty`*\n"
        		+ "**Please provide a positive number to update the quantity of an item:** *El valor no esta en rango*"
        		+ "\n\n*example:*\n"
        		+ "<pre>\n{\n"
        		+ "  \"carData\": {},\n"
        		+ "  \"message\": \"productCodePost field cannot be empty.\",\n"
        		+ "  \"success\": false\n"
        		+ "}\n</pre>"),
        @ApiResponse(code = 501, message = "Ocurre cuando se recibe un errores desconocido\n\n"
        		+ "**Lo sentimos, ha ocurrido un error inesperado:** *Error generico*\n"	        		
        		+ "<pre>\n{\n"
        		+ "  \"carData\": {},\n"
        		+ "  \"message\": \"Lo sentimos, ha ocurrido un error inesperado\",\n"
        		+ "  \"success\": false\n"
        		+ "}\n</pre>")})
   
    @RequestMapping(value = "/store-pickup/change", method = RequestMethod.POST)
	public CartItems changeAsynCartEntryToPickUpInStore(
			@ApiParam(
    			    name =  "productCodePost",    			    
    			    value = "Este campo corresponde al codigo del producto",
    			    example = "7701023663106",
    			    required = true)@RequestParam(value ="productCodePost") final String code,
			@ApiParam(
    			    name =  "storeNamePost",    			    
    			    value = "Este campo corresponde al codigo de la tienda selecionada",
    			    example = "KTUNO",
    			    required = true)@RequestParam(value = "storeNamePost") final String storeId,
			@ApiParam(
    			    name =  "entryNumber",    			    
    			    value = "Este campo corresponde al numero identificador del producto",
    			    example = "1",
    			    required = true)@RequestParam(value ="entryNumber") final Long entryNumber,
			@ApiParam(
    			    name =  "isDeliveryMethodUpdated",    			    
    			    value = "Este campo es una constante,que indica si permite el cambio",
    			    example = "true",
    			    required = true)@RequestParam(value = "isDeliveryMethodUpdated") final Boolean isDeliveryMethodUpdated,
			@ApiParam(
    			    name =  "hiddenPickupQty",    			    
    			    value = "Este campo corresponde al la cantidad, sin embargo siempre se enviar 1",
    			    example = "1",
    			    required = true)@RequestParam(value = "hiddenPickupQty") final long hiddenPickupQty) {
		return new CartItems();
	}
    
    
    @ApiOperation(value = "Retorna toda la informacion relacionada a los productos", notes = "Este servicio se actualizar el metodo de envio a entrega el mismo dia")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Ocurre cuando se realiza una peticion y el Cart no tiene productos o se a perdido los datos de la session",reference = "CartItems"),
        @ApiResponse(code = 200, message = "Ocurre cuando el proceso se realiza de forma exitosa puede retornar alguno de estos mensajes:\n\n"
                +"**El metodo de envio ha sido actualizado:** *Cuando se seleciona una ciudad sin tener ninguna previa* \n"
                +"**Hemos cambiado el método de envío de tus productos de acuerdo con la ciudad qué elegiste en tu última selección:** *Cuando atp esta disponible para el `entryNumber`*\n"
                +"**Hemos cambiado la ciudad de envío de acuerdo con tu última selección:** *Cuando se cambia la ciudad de seleccion*",reference = "CartItems"),
        @ApiResponse(code = 400, message = "Ocurre debido a que el request que se esta enviando no es correcto, puede visualizar alguno de estos mensajes\n\n"
        		+ "**productCode field cannot be empty:** *No llega `productCode`*\n"+""
        		+ "**cityCode field cannot be empty:** *No llega `cityCode`* \n"
        		+ "**entryNumber field cannot be empty:** *No llega `entryNumber`*\n"
        		+ "**deliveryModeSelected field cannot be empty:** *No llega `deliveryModeSelected`* \n"
        		+ "**Failed to convert property value of type &#039;java.lang.String&#039; to required type &#039;java.lang.Long&#039; for property &#039;quantity&#039;; nested exception is java.lang.NumberFormatException: For input string: &#034;abc&#034;:** *Formato no valido `Quantity`*\n"
        		+ "**Please provide a positive number to update the quantity of an item:** *El valor no esta en rango*"
        		+ "\n\n*example:*\n"
        		+ "<pre>\n{\n"
        		+ "  \"carData\": {},\n"
        		+ "  \"message\": \"Quantity field cannot be empty.\",\n"
        		+ "  \"success\": false\n"
        		+ "}\n</pre>"),
        @ApiResponse(code = 417, message = "Ocurre cuando envia una solicitud y el samedaydelivery no esta en el rango horario permitido\n\n"
        		+ "**Metodo de envio no disponible en este momento:** *Se presenta cuando se la hora no se encuentra en rango*\n"	        		
        		+ "<pre>\n{\n"
        		+ "  \"carData\": {},\n"
        		+ "  \"message\": \"Metodo de envio no disponible en este momento\",\n"
        		+ "  \"success\": false\n"
        		+ "}\n</pre>"),
        @ApiResponse(code = 501, message = "Ocurre cuando se recibe un errores desconocido\n\n"
        		+ "**Lo sentimos, ha ocurrido un error inesperado:** *Error generico*\n"	        		
        		+ "<pre>\n{\n"
        		+ "  \"carData\": {},\n"
        		+ "  \"message\": \"Lo sentimos, ha ocurrido un error inesperado\",\n"
        		+ "  \"success\": false\n"
        		+ "}\n</pre>")})
   
    @RequestMapping(value = "/same-day-delivery/change", method = RequestMethod.POST)
	public CartItems changeAsynCartEntryToSameDayDelivery(
			@ApiParam(
    			    name =  "productCode",    			    
    			    value = "Este campo corresponde al codigo del producto",
    			    example = "7705855021981",
    			    required = true)@RequestParam(value ="productCode") final String productCode,
			@ApiParam(
    			    name =  "cityCode",    			    
    			    value = "Este campo corresponde al codigo de la ciudad selecionada",
    			    example = "25899",
    			    required = true)@RequestParam(value = "cityCode") final String cityCode,
			@ApiParam(
    			    name =  "entryNumber",    			    
    			    value = "Este campo corresponde al numero identificador del producto",
    			    example = "1",
    			    required = true)@RequestParam(value ="entryNumber") final Long entryNumber,
			@ApiParam(
    			    name =  "quantity",    			    
    			    value = "Este campo es una constante 1,Cantidad de items del producto",
    			    example = "1",
    			    required = true)@RequestParam(value = "quantity") final Long quantity,
			@ApiParam(
    			    name =  "deliveryModeSelected",    			    
    			    value = "Este campo corresponde al identifucador del metodo de envio",
    			    example = "same-day-delivery",
    			    required = true)@RequestParam(value = "deliveryModeSelected") final long deliveryModeSelected) {
		return new CartItems();
	}
    
    @ApiOperation(value = "Retorna toda la informacion relacionada a los productos", notes = "Este servicio se actualizar el metodo envio gratis")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Ocurre cuando se realiza una peticion y el Cart no tiene productos o se a perdido los datos de la session",reference = "CartItems"),
        @ApiResponse(code = 200, message = "Ocurre cuando el proceso se realiza de forma exitosa puede retornar alguno de estos mensajes:\n\n"
        		+ "**El metodo de envio ha sido actualizado:** *Cuando se ejecuta de forma corresta el proceso* \n",reference = "CartItems"),
        @ApiResponse(code = 400, message = "Ocurre debido a que el request que se esta enviando no es correcto, puede visualizar alguno de estos mensajes\n\n"
        		+ "**entryNumber field cannot be empty:** *No llega `entryNumber`*\n"
        		+ "\n\n*example:*\n"
        		+ "<pre>\n{\n"
        		+ "  \"carData\": {},\n"
        		+ "  \"message\": \"entryNumber field cannot be empty.\",\n"
        		+ "  \"success\": false\n"
        		+ "}\n</pre>"),
        @ApiResponse(code = 501, message = "Ocurre cuando se recibe un errores desconocido\n\n"
        		+ "**Lo sentimos, ha ocurrido un error inesperado:** *Error generico*\n"	        		
        		+ "<pre>\n{\n"
        		+ "  \"carData\": {},\n"
        		+ "  \"message\": \"Lo sentimos, ha ocurrido un error inesperado\",\n"
        		+ "  \"success\": false\n"
        		+ "}\n</pre>")})
   
    @RequestMapping(value = "/ship-to-address/change", method = RequestMethod.POST)
	public CartItems changeAsynCartEntryToShiptoAddress(
			@ApiParam(
    			    name =  "entryNumber",    			    
    			    value = "Este campo corresponde al numero identificador del producto",
    			    example = "1",
    			    required = true)@RequestParam(value ="entryNumber") final Long entryNumber,			
			@ApiParam(
    			    name =  "deliveryMode",    			    
    			    value = "Este campo corresponde al identificador del metodo de envio",
    			    example = "ship-to-address",
    			    required = true)@RequestParam(value = "deliveryMode") final String deliveryMode) {
		return new CartItems();
	}
    
    @ApiOperation(value = "Retorna toda la informacion relacionada a los productos", notes = "Este servicio se actualizar el metodo de envio para productos tipo instalacion")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Ocurre cuando se realiza una peticion y el Cart no tiene productos o se a perdido los datos de la session",reference = "CartItems"),
        @ApiResponse(code = 200, message = "Ocurre cuando el proceso se realiza de forma exitosa puede retornar alguno de estos mensajes:\n\n"
        		+ "**El metodo de envio ha sido actualizado:** *Cuando se ejecuta de forma correcta el proceso* \n",reference = "CartItems"),
        @ApiResponse(code = 400, message = "Ocurre debido a que el request que se esta enviando no es correcto, puede visualizar alguno de estos mensajes\n\n"
        		+ "**cityCode field cannot be empty:** *No llega `cityCode`*\n"
        		+ "**entryNumber field cannot be empty:** *No llega `entryNumber`*\n"
        		+ "\n\n*example:*\n"
        		+ "<pre>\n{\n"
        		+ "  \"carData\": {},\n"
        		+ "  \"message\": \"entryNumber field cannot be empty..\",\n"
        		+ "  \"success\": false\n"
        		+ "}\n</pre>"),
        @ApiResponse(code = 501, message = "Ocurre cuando se recibe un errores desconocido\n\n"
        		+ "**Lo sentimos, ha ocurrido un error inesperado:** *Error generico*\n"	        		
        		+ "<pre>\n{\n"
        		+ "  \"carData\": {},\n"
        		+ "  \"message\": \"Lo sentimos, ha ocurrido un error inesperado\",\n"
        		+ "  \"success\": false\n"
        		+ "}\n</pre>")})
   
    @RequestMapping(value = "/installation/change", method = RequestMethod.POST)
	public CartItems changeAsyncInstallationUpdateEntryCity(
			@ApiParam(
    			    name =  "cityCode",    			    
    			    value = "Este campo corresponde al codigo de la ciudad selecionada",
    			    example = "41006",
    			    required = true)@RequestParam(value = "cityCode") final String cityCode,
			@ApiParam(
    			    name =  "entryNumber",    			    
    			    value = "Este campo corresponde al numero identificador del producto",
    			    example = "0",
    			    required = true)@RequestParam(value ="entryNumber") final String entryNumber) {
		return new CartItems();
	}
    
    
    @ApiOperation(value = "Retorna toda la informacion relacionada a los productos", notes = "Este servicio se encarga de agregar productos al carrito")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Ocurre cuando se realiza una peticion y el Cart no tiene productos o se a perdido los datos de la session",reference = "CartItems"),
        @ApiResponse(code = 200, message = "Ocurre cuando el proceso se realiza de forma exitosa puede retornar alguno de estos mensajes:\n\n"
        		+ "**Tu producto ha sido agregado en el carrito satisfactoriamente:** *Cuando se ejecuta de forma corresta el proceso* \n",reference = "CartItems"),
        @ApiResponse(code = 400, message = "Ocurre debido a que el request que se esta enviando no es correcto, puede visualizar alguno de estos mensajes\n\n"
        		+ "**productCodePost field cannot be empty:** *No llega `productCodePost`*\n"
        		+ "**For input string: &#034;code&#034;:** *el `productCodePost` no se valido*\n"
        		+ "**deliveryModeSelected field cannot be empty:** *No llega `deliveryModeSelected`*\n"
        		+ "**Please provide a positive number to update the quantity of an item:** *El `quantity` se encuentra fuera del rango*\n"
        		+ "\n\n*example:*\n"
        		+ "<pre>\n{\n"
        		+ "  \"carData\": {},\n"
        		+ "  \"message\": \"productCodePost field cannot be empty.\",\n"
        		+ "  \"success\": false\n"
        		+ "}\n</pre>"),
        @ApiResponse(code = 500, message = "Ocurrio algun tipo de error durante el proceso de agregar un producto,puede visualizar alguno de estos mensajes:\n\n"
        		+ "**Quantity must not be less than one:** *Ocurre cuando el `quantity` es igual a `0`*\n"
        		+ "**Lo sentimos, las unidades superan el límite permitido por cliente en este caso. Puedes comprar hasta un máximo de 4 unidades:** *Ocurre cuando excedes el limite de unidades por producto`*\n"
        		+ "**A lower quantity of this product has been added to your cart due to insufficient stock:** *Ocurre cuando hay poco stock del producto*\n"
        		+ "**Lo sentimos, no hay suficiente inventario para su carrito:** *Ocurre cuando no hay stock del producto*\n"
        		+ "**Has llegado al máximo permitido de 15 referencias diferentes en tu carrito:** *Ocurre cuando superar la cantidad de productos que puede tener en el cart(max=15) y tratas de adicionar otro*\n"
        		+ "\n\n*example:*\n"
        		+ "<pre>\n{\n"
        		+ "  \"carData\": {},\n"
        		+ "  \"message\": \"o sentimos, las unidades superan el límite permitido por cliente en este caso. Puedes comprar hasta un máximo de 4 unidades.\",\n"
        		+ "  \"success\": false\n"
        		+ "}\n</pre>"),
        @ApiResponse(code = 501, message = "Ocurre cuando se recibe un errores desconocido\n\n"
        		+ "**Lo sentimos, ha ocurrido un error inesperado:** *Error generico*\n"	        		
        		+ "<pre>\n{\n"
        		+ "  \"carData\": {},\n"
        		+ "  \"message\": \"Lo sentimos, ha ocurrido un error inesperado\",\n"
        		+ "  \"success\": false\n"
        		+ "}\n</pre>")})
   
    @RequestMapping(value = "add/product", method = RequestMethod.POST)
	public CartItemsDataLayer addToCartAsyncProduct(
			@ApiParam(
    			    name =  "productCodePost",    			    
    			    value = "Este campo corresponde al codigo del producto o ean",
    			    example = "7707076601034",
    			    required = true)@RequestParam(value = "productCodePost") final String code,
			@ApiParam(
    			    name =  "potentialPromotionId",    			    
    			    value = "Este campo corresponde al id de la promocion",
    			    example = "7701023663106-7707076601034-free",
    			    required = false)@RequestParam(value ="potentialPromotionId",required = false) final String potentialPromotionId,
			@ApiParam(
    			    name =  "multipleBSelectedProduct",    			    
    			    value = "Este campo esta relacionado a la promocion",
    			    example = "",
    			    required = false)@RequestParam(value ="multipleBSelectedProduct",required = false) final String multipleBSelectedProduct,
			@ApiParam(
    			    name =  "deliveryModeSelected",    			    
    			    value = "Este campo corresponde al metodo de envio a utilizar",
    			    example = "ship-to-address",
    			    required = true)@RequestParam(value ="deliveryModeSelected") final String deliveryModeSelected,
			@ApiParam(
    			    name =  "cmsPageID",    			    
    			    value = "Este campo corresponde al identificador para toogle, para no invocar GEO",
    			    example = "productList",
    			    required = false)@RequestParam(value ="cmsPageID",required = false) final String page,
			@ApiParam(
    			    name =  "qty",    			    
    			    value = "Este campo corresponde a la cantidad de unidades adicionar del producto",
    			    example = "71",
    			    required = false)@RequestParam(value ="qty",required = false) final long qty) {
		return new CartItemsDataLayer();
	}
    
    

    @ApiOperation(value = "Retorna toda la informacion relacionada a los productos", notes = "Este servicio se encarga de agregar instalaciones al carrito")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Ocurre cuando se realiza una peticion y el Cart no tiene productos o se a perdido los datos de la session",reference = "CartItems"),
        @ApiResponse(code = 200, message = "Ocurre cuando el proceso se realiza de forma exitosa puede retornar alguno de estos mensajes:\n\n"
        		+ "**Tu producto ha sido agregado en el carrito satisfactoriamente:** *Cuando se ejecuta de forma correcta el proceso* \n",reference = "CartItems"),
        @ApiResponse(code = 400, message = "Ocurre debido a que el request que se esta enviando no es correcto, puede visualizar alguno de estos mensajes\n\n"
        		+ "**productCode field cannot be empty:** *No llega `productCode`*\n"
        		+ "**cityCode field cannot be empty:** *No llega `cityCode`*\n"
        		+ "**potentialPromotionId field cannot be empty:** *No llega `potentialPromotionId`*\n"
        		+ "**deliveryModeSelected field cannot be empty:** *No llega `deliveryModeSelected`*\n"
        		+ "\n\n*example:*\n"
        		+ "<pre>\n{\n"
        		+ "  \"carData\": {},\n"
        		+ "  \"message\": \"productCodePost field cannot be empty.\",\n"
        		+ "  \"success\": false\n"
        		+ "}\n</pre>"),
        @ApiResponse(code = 501, message = "Ocurre cuando se recibe un errores desconocido\n\n"
        		+ "**Lo sentimos, ha ocurrido un error inesperado:** *Error generico*\n"	        		
        		+ "<pre>\n{\n"
        		+ "  \"carData\": {},\n"
        		+ "  \"message\": \"Lo sentimos, ha ocurrido un error inesperado\",\n"
        		+ "  \"success\": false\n"
        		+ "}\n</pre>")})
   
    @RequestMapping(value = "add/installation", method = RequestMethod.POST)
	public CartItemsDataLayer addToCartAsyncInstallation(
			@ApiParam(
    			    name =  "productCode",    			    
    			    value = "Este campo corresponde al codigo del producto o ean del producto A",
    			    example = "6947681532205",
    			    required = true)@RequestParam(value = "productCodePost") final String code,
			@ApiParam(
    			    name =  "potentialPromotionId",    			    
    			    value = "Este campo corresponde al id de la promocion",
    			    example = "6947681532205-9705946375344-discount",
    			    required = true)@RequestParam(value ="potentialPromotionId") final String potentialPromotionId,
			@ApiParam(
    			    name =  "quantity",    			    
    			    value = "Este corresponde a la cantidad aunque no se usa",
    			    example = "1",
    			    required = false)@RequestParam(value ="quantity",required = false) final long quantity,
			@ApiParam(
    			    name =  "deliveryModeSelected",    			    
    			    value = "Este campo corresponde al metodo de envio a utilizar",
    			    example = "ship-to-address",
    			    required = true)@RequestParam(value ="deliveryModeSelected") final String deliveryModeSelected,
			@ApiParam(
    			    name =  "cmsPageID",    			    
    			    value = "Este campo corresponde al identificador para toogle, para no invocar GEO",
    			    example = "cartPage",
    			    required = false)@RequestParam(value ="cmsPageID",required = false) final String page,
			@ApiParam(
    			    name =  "cityCode",    			    
    			    value = "Este campo corresponde codigo dane o el identificador de la ciudad para la instalacion",
    			    example = "50006",
    			    required = true)@RequestParam(value ="cityCode") final String cityCode) {
		return new CartItemsDataLayer();
	}
    

	@ApiOperation(value = "Retorna toda la informacion relacionada a los productos", notes = "Permite eliminar la garantia extendida a productos que estan habilitados para ello")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Ocurre cuando se realiza una peticion y el Cart no tiene productos o se a perdido los datos de la session",reference = "CartItems"),
        @ApiResponse(code = 200, message = "Ocurre cuando el proceso se realiza de forma exitosa puede retornar alguno de estos mensajes:\n\n"
        		+ "**El metodo de envio ha sido actualizado:** *Cuando se ejecuta de forma corresta el proceso* \n",reference = "CartItems"),
        @ApiResponse(code = 400, message = "Ocurre debido a que el request que se esta enviando no es correcto, puede visualizar alguno de estos mensajes\n\n"
        		+ "**entryNumber field cannot be empty:** *No llega `entryNumber`*\n"
        		+ "\n\n*example:*\n"
        		+ "<pre>\n{\n"
        		+ "  \"carData\": {},\n"
        		+ "  \"message\": \"entryNumber field cannot be empty..\",\n"
        		+ "  \"success\": false\n"
        		+ "}\n</pre>"),
        @ApiResponse(code = 501, message = "Ocurre cuando se recibe un errores desconocido\n\n"
        		+ "**Lo sentimos, ha ocurrido un error inesperado:** *Error generico*\n"	        		
        		+ "<pre>\n{\n"
        		+ "  \"carData\": {},\n"
        		+ "  \"message\": \"Lo sentimos, ha ocurrido un error inesperado\",\n"
        		+ "  \"success\": false\n"
        		+ "}\n</pre>")})
   
    @RequestMapping(value = "/extendedWarranty/updateAsync", method = RequestMethod.POST)
	public CartItemsDataLayer entendedWarrantyUpdate(
			@ApiParam(
    			    name =  "entryNumber",    			    
    			    value = "Este campo corresponde al numero identificador del producto",
    			    example = "1",
    			    required = true)@RequestParam(value = "entryNumber") final String entryNumber) {
		return new CartItemsDataLayer();
	}

@ApiOperation(value = "Retorna toda la informacion relacionada a los productos", notes = "Permite agregar la garantia extendida a productos que estan habilitados para ello")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Ocurre cuando se realiza una peticion y el Cart no tiene productos o se a perdido los datos de la session",reference = "CartItems"),
        @ApiResponse(code = 200, message = "Ocurre cuando el proceso se realiza de forma exitosa",reference = "CartItems"),
        @ApiResponse(code = 400, message = "Ocurre debido a que el request que se esta enviando no es correcto, puede visualizar alguno de estos mensajes\n\n"
        		+ "**associateProductCode field cannot be empty:** *No llega `associateProductCode`*\n"
        		+ "**extendedWarrantyQuantity field cannot be empty:** *No llega `extendedWarrantyQuantity`*\n"
				+ "**deliveryModeSelected field cannot be empty:** *No llega `deliveryModeSelected`*\n"
				+ "**toggleStatus field cannot be empty:** *No llega `toggleStatus`*\n"
        		+ "\n\n*example:*\n"
        		+ "<pre>\n{\n"
        		+ "  \"carData\": {},\n"
        		+ "  \"message\": \"extendedWarrantyQuantity field cannot be empty..\",\n"
        		+ "  \"success\": false\n"
        		+ "}\n</pre>"),
        @ApiResponse(code = 501, message = "Ocurre cuando se recibe un errores desconocido\n\n"
        		+ "**Lo sentimos, ha ocurrido un error inesperado:** *Error generico*\n"	        		
        		+ "<pre>\n{\n"
        		+ "  \"carData\": {},\n"
        		+ "  \"message\": \"Lo sentimos, ha ocurrido un error inesperado\",\n"
        		+ "  \"success\": false\n"
        		+ "}\n</pre>")})
   
    @RequestMapping(value = "/extendedWarranty/addAsync", method = RequestMethod.POST)
	public CartItemsDataLayer entendedWarrantyAdd(
			@ApiParam(
    			    name =  "associateProductCode",    			    
    			    value = "Este campo corresponde al codigo del producto",
    			    example = "888462062060",
    			    required = true)@RequestParam(value = "associateProductCode") final String associateProductCode,
			@ApiParam(
    			    name =  "extendedWarrantyQuantity",    			    
    			    value = "Este campo corresponde a la misma cantidad del producto asociado a la garantia extendida",
    			    example = "1",
    			    required = true)@RequestParam(value ="extendedWarrantyQuantity") final String extendedWarrantyQuantity,
			@ApiParam(
    			    name =  "deliveryModeSelected",    			    
    			    value = "Este campo corresponde al identificador del metodo de envio",
    			    example = "ship-to-address",
    			    required = true)@RequestParam(value ="deliveryModeSelected") final String deliveryModeSelected,
			@ApiParam(
    			    name =  "deliveryPointOfService",    			    
    			    value = "Este campo corresponde al punto de servicio para entrega",
    			    example = "AKB30",
    			    required = false)@RequestParam(value ="deliveryPointOfService") final String deliveryPointOfService,
			@ApiParam(
    			    name =  "toggleStatus",    			    
    			    value = "toggle que verifica que el producto tiene garantia extendida",
    			    example = "true",
    			    required = true)@RequestParam(value ="toggleStatus") final String toggleStatus) {
		return new CartItemsDataLayer();
	}
}

