package com.ssafy.shopApp.model.service;

import java.util.List;
import com.ssafy.shopApp.model.dto.ProductDTO;

/**
 * 상품을 관리하는 비즈니스 로직
 */
public interface ProductService {

	// @Todo :  상품 정보 등록
	public void insertProduct(ProductDTO product) throws Exception;

	// @Todo :  상품 정보 조회
	public ProductDTO getProductById(int productId) throws Exception;

	// @Todo :  상품 정보 수정
	public void updateProduct(ProductDTO product) throws Exception;
	
	// @Todo :  상품 정보 삭제
	public void deleteProduct(int productId) throws Exception;
	
	// @Todo :  상품 목록 조회
	public List<ProductDTO> getAllProducts() throws Exception;

}