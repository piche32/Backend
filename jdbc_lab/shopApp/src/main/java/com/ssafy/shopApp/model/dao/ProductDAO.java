package com.ssafy.shopApp.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.shopApp.model.dto.GiftProductDTO;
import com.ssafy.shopApp.model.dto.ProductDTO;

/**
 * 상품정보를 다루는 데이터 접근 로직
 */
public interface ProductDAO {

	// @Todo :  상품 정보 등록
    public void insertProduct(ProductDTO product) throws SQLException;
    
	// @Todo :  상품 정보 조회
    public ProductDTO getProductById(int productId) throws SQLException;
    
	// @Todo :  상품 정보 수정
    public void updateProduct(ProductDTO product) throws SQLException;
	// @Todo :  상품 정보 삭제
    public void deleteProduct(int productId) throws SQLException ;
    
	// @Todo :  상품 목록 조회
    public List<ProductDTO> getAllProducts() throws SQLException;
    
	// @Todo :  사은품 정보 등록
    public void insertGiftProduct(GiftProductDTO gift) throws SQLException;
}
