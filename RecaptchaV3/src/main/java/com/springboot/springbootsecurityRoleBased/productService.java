package com.springboot.springbootsecurityRoleBased;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class productService implements IproductService{

	
	@Autowired
	private productRepository res;
	
	@Override
	public List<product> FindAll() {
		// TODO Auto-generated method stub
		//return null;
		List<product> arr = new ArrayList<product>();
		arr = (List<product>) res.findAll();
		return arr;
	}
	@Override
	public void save(product p) {
		// TODO Auto-generated method stub
	res.save(p);		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		res.deleteById(id);
	}
	@Override
	public product FindOne(Long id) {
		// TODO Auto-generated method stub
		//return null;
		return res.findById(id).get();
	}
}
