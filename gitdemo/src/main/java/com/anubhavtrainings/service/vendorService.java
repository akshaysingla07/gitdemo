package com.anubhavtrainings.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anubhavtrainings.entities.Vendor;
@Component
public class vendorService {

	@Autowired
	IVendorPersistence vendor;
	
	public List<Vendor> readAllVendors(){
//		fillVendor();
		return vendor.findAll();
	}
	
//	public Vendor getSingleVendorById(String vendorCode) {
//		fillVendor();
//		return (Vendor)vendors.get(vendorCode);	
//	}
//	public Vendor changeVendor(Vendor vendor) {
//		return vendor;
//	}
//	
	public Vendor createVendor(Vendor vendorObj) {
//		vendor.code = "NEW";
//		return vendor;
		return vendor.save(vendorObj);
	}
//	private void fillVendor() {
//		// TODO Auto-generated method stub
//		vendors.put("1", x);
//		vendors.put("2", y);
//		vendors.put("3", z); 
//	}
	public List<Vendor> searchByCompanyName(String companyName){
		return vendor.findByCompanyName(companyName);
	}
	
	public List<Vendor> lookupVendorbByGST(String GSTNo){
		return vendor.lookupVendorByGST(GSTNo);
		
		
	}

	public Optional<Vendor> getSingleVendor(Long id) {
   return vendor.findById(id);
}

	public Vendor changeVendor(Vendor payload ) {
	     System.out.println("debugging");
		Optional<Vendor> myVendor = vendor.findById(payload.getId());
//	     Optional<Vendor> myVendor = vendor.findById((long)4);
		if (!myVendor.isPresent()) {
			return new Vendor((long)0 , "","","","","", null, null);
			
		}
		return vendor.save(payload);
		
	}
	
	public String deleteVendor(Long Id) {
		vendor.deleteById(Id);
		return "Deleted Sucessfully";
	}
	
}
