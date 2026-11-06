package com.ssafy.shopApp.test;

import java.util.List;

import com.ssafy.shopApp.model.dto.GiftProductDTO;
import com.ssafy.shopApp.model.dto.ProductDTO;
import com.ssafy.shopApp.model.service.BasicProductService;
import com.ssafy.shopApp.model.service.ProductService;

public class ProductTest {
    public static void main(String[] args){
        try {
            ProductService productService = BasicProductService.getInstance();

            // Insert Product

            ProductDTO newProduct = new ProductDTO();
            newProduct.setName("갤럭시26");
            newProduct.setCategory("모바일");
            newProduct.setBrand("삼성");
            newProduct.setPrice(299);
            newProduct.setStockQuantity(10);
            
            List<GiftProductDTO> gifts = List.of(
                    new GiftProductDTO("무선 충전 패드"),
                    new GiftProductDTO("C타입 고속 충전 케이블")
                );
            newProduct.setGifts(gifts);
            productService.insertProduct(newProduct);
            System.out.println("상품 등록 : 성공 - "+newProduct.getProductId());

            {
            	System.out.println("=====상품 목록=====");
            	for (ProductDTO p: productService.getAllProducts()) {
					System.out.println(p);
				}

            }
           
            // Get Product by ID
            ProductDTO fetchedProduct = productService.getProductById(1);
            System.out.println("상품 조회 : " + fetchedProduct);

            // Update Product
            fetchedProduct.setPrice(1100);
            productService.updateProduct(fetchedProduct);
            System.out.println("상품 가격 수정후 조회 : " + productService.getProductById(1));

            // Delete Product
            productService.deleteProduct(11);
            System.out.println("상품 삭제 : 성공");
            {
            	System.out.println("=====상품 목록=====");
            	for (ProductDTO p: productService.getAllProducts()) {
					System.out.println(p);
				}

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
