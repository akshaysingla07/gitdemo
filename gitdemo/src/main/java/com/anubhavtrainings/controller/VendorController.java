package com.anubhavtrainings.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anubhavtrainings.entities.Vendor;
import com.anubhavtrainings.service.vendorService;

@RestController
//@RequestMapping("/vendor")
public class VendorController {
	

	@Autowired
	vendorService vendorservice;
	
	@RequestMapping("/vendor")
	public List<Vendor> getVendors(){

			return  vendorservice.readAllVendors();
	
	}
	@RequestMapping("/vendor/{vendorCode}")
	public  Vendor getVendorById(@PathVariable("vendorCode") Long code) {
	Optional<Vendor> searchresult = vendorservice.getSingleVendor(code);
	if(!searchresult.isPresent()) {
		return new Vendor((long)0 , "","","","", null, null, null);
	}
	return searchresult.get();
	} 
	
	@RequestMapping(method=RequestMethod.PUT , value = "/vendor")
	
	public  Vendor updateVendor(@RequestBody Vendor vendor) {
		return vendorservice.changeVendor(vendor);
		}
	
	@PostMapping("/vendor")
	public Vendor createVendor(@RequestBody Vendor myPostBody) {
	return vendorservice.createVendor(myPostBody);
		
	}
//	test using localhost:8080/vendor/search?company=BSC
	@RequestMapping("/vendor/search")
	public List<Vendor> searchByCompanyName(@RequestParam String company){
		return vendorservice.searchByCompanyName(company);
	}
	//test using localhost:8080/vendor/lookip/GSTNo=00
//	@RequestMapping("/vendor/lookup")
//	public List<Vendor> searchVendorBYGST(@RequestParam String GSTNo){
//		return vendorservice.lookupVendorbByGST(GSTNo); 
//				}
	//test using localhost:8080/vendor/lookup/00
	@RequestMapping("/vendor/lookup/{gstNo}")
	public List<Vendor> searchVendorBYGST(@PathVariable("gstNo") String GSTNo){
		return vendorservice.lookupVendorbByGST(GSTNo); 
				}
	
    @RequestMapping(method=RequestMethod.DELETE ,value="/vendor/{id}")
    public String removeVendor(@PathVariable("id") Long id ) {
    	
    	return vendorservice.deleteVendor(id);
    }

}
