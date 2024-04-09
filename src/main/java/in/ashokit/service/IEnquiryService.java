package in.ashokit.service;

import java.util.List;

import in.ashokit.dto.Dashboard;
import in.ashokit.entity.Enquiry;

public interface IEnquiryService {
	public Dashboard getDashboardInfo(Integer counsellorId);
	public Boolean addEnquiry(Enquiry enquiry,Integer counsellorId);
	public List<Enquiry> getEnquiries(Enquiry enquiry,Integer counsellorId);
	public Enquiry editEnquiry(Integer enqId);	
}
