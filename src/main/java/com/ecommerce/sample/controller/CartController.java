package com.ecommerce.sample.controller;


import com.ecommerce.sample.common.ApiResponse;
import com.ecommerce.sample.dto.AddToCartDto;
import com.ecommerce.sample.dto.CartDto;
import com.ecommerce.sample.model.User;
import com.ecommerce.sample.repository.UserDao;
import com.ecommerce.sample.service.CartService;
import com.ecommerce.sample.service.ProductService;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductService productService;

    @Autowired
    ServletContext servletContext;


    private final TemplateEngine templateEngine;
    public CartController(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> adttToCart (@RequestBody AddToCartDto addToCartDto, @RequestParam("userid") String userid) throws Exception {
        User user = userDao.findById(userid).get();

        cartService.addToCart(addToCartDto,user);

        return new ResponseEntity<>(new ApiResponse(true,"added to cart"), HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<CartDto> ViewAllCart(@PathVariable("username") String uname) {
        User user = userDao.findById(uname).get();

        CartDto cartDto = cartService.listcartitems(user);
        return new ResponseEntity<>(cartDto,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemid}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemid")int cartItemid, @RequestParam("userid") String userid) throws Exception {
        User user = userDao.findById(userid).get();
        cartService.deleteItem(cartItemid,user);
        return new ResponseEntity<>(new ApiResponse(true,"Deleted from cart"), HttpStatus.OK);
    }

    @PostMapping (path = "/pdf{username}")
    public ResponseEntity<?> getPDF(@PathVariable("username")String username, HttpServletRequest request, HttpServletResponse response) throws IOException {

        /* Do Business Logic*/

        User user = userDao.findById(username).get();

        CartDto cartDto = cartService.listcartitems(user);

        /* Create HTML using Thymeleaf template Engine */

        WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("cartitems", cartDto);
        String orderHtml = templateEngine.process("order", context);

        /* Setup Source and target I/O streams */

        ByteArrayOutputStream target = new ByteArrayOutputStream();

        /*Setup converter properties. */
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:8080");

        /* Call convert method */
        HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

        /* extract output as bytes */
        byte[] bytes = target.toByteArray();


        /* Send the response as downloadable PDF */

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=order.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);

    }

}
