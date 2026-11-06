package com.ssafy.shopApp.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.shopApp.model.dto.GiftProductDTO;
import com.ssafy.shopApp.model.dto.ProductDTO;

public class BasicProductDAO implements ProductDAO {
    // 싱글톤 패턴 적용
    private static BasicProductDAO instance = new BasicProductDAO();

    private BasicProductDAO() {}

    public static BasicProductDAO getInstance() {
        return instance;
    }

	@Override
	public void insertProduct(ProductDTO product) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProductDTO getProductById(int productId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProduct(ProductDTO product) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProduct(int productId) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductDTO> getAllProducts() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertGiftProduct(GiftProductDTO gift) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}