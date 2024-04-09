package in.ashokit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import in.ashokit.dto.Dashboard;
import in.ashokit.entity.Counsellor;
import in.ashokit.entity.Enquiry;
import in.ashokit.repo.CounsellorRepository;
import in.ashokit.repo.EnquiryRepository;

public class EnquiryServiceImpl implements IEnquiryService {
	@Autowired
	private EnquiryRepository enqRepo;
	
	@Autowired
	private CounsellorRepository counsellorRepo;
	
	@Override
	public Dashboard getDashboardInfo(Integer counsellorId) {
	Long totalEnq=enqRepo.getAllEnquiries(counsellorId);
	Long newEnq=enqRepo.getEnquiriesByStatus(counsellorId,"new");
	Long enrolledEnq=enqRepo.getEnquiriesByStatus(counsellorId,"enrolled");
	Long lostEnq=enqRepo.getEnquiriesByStatus(counsellorId,"lost");
		Dashboard d=new Dashboard();
		d.setTotalEnqs(totalEnq);
		d.setNewEnqs(newEnq);
		d.setEnrolledEnqs(enrolledEnq);
		d.setLostEnqs(lostEnq);
		return d;
	}

	@Override
	public Boolean addEnquiry(Enquiry enquiry, Integer counsellorId) {
		Counsellor counsellor=counsellorRepo.findById(counsellorId).orElseThrow();
		//Integer id=counsellor.getCounsellorId();
		enquiry.setCounsellor(counsellor);
		Enquiry savedEnquiry=enqRepo.save(enquiry);
		return savedEnquiry.getEnqId()!=null;
		
	}

	@Override 
	public List<Enquiry> getEnquiries(Enquiry enquiry, Integer counsellorId) {
		Counsellor counsellor=counsellorRepo.findById(counsellorId).orElseThrow();
		enquiry.setCounsellor(counsellor);
		Example<Enquiry> of=Example.of(enquiry);
		return enqRepo.findAll(of);	
		
	}

	@Override
	public Enquiry editEnquiry(Integer enqId) {
		return enqRepo.findById(enqId).orElseThrow();
	}

}
