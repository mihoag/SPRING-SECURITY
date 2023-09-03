package com.springboot.springbootsecurityRoleBased;

import java.util.List;


public interface IproductService {
	    public List<product> FindAll();
	    public product FindOne(Long id);
	    public void save(product p);
	    public void delete(Long id);
}
