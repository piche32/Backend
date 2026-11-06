package com.ssafy.shopApp.model.service;

import java.sql.Connection;
import java.util.List;

import com.ssafy.shopApp.model.dao.BasicProductDAO;
import com.ssafy.shopApp.model.dao.ProductDAO;
import com.ssafy.shopApp.model.dto.GiftProductDTO;
import com.ssafy.shopApp.model.dto.ProductDTO;
import com.ssafy.shopApp.util.DBUtil;

//Product Service
public class BasicProductService implements ProductService {
    // 싱글톤 패턴 적용
    private static BasicProductService instance = new BasicProductService();
    private ProductDAO productDAO = BasicProductDAO.getInstance();

    private BasicProductService() {}

    public static BasicProductService getInstance() {
        return instance;
    }

    @Override
    public void insertProduct(ProductDTO product) throws Exception {

    }

    @Override
    public ProductDTO getProductById(int productId) throws Exception {
    	return null;
    }

    @Override
    public void updateProduct(ProductDTO product) throws Exception {

    }

    @Override
    public void deleteProduct(int productId) throws Exception {

    }

    @Override
    public List<ProductDTO> getAllProducts() throws Exception {
    	return null;
    }

}