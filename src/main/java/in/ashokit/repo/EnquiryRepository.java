package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.Enquiry;

public interface EnquiryRepository extends JpaRepository<Enquiry,Integer> {
	@Query(
			value="select count(*) from Enquiry where counsellorId=:counsellorId",nativeQuery=true
			)
	public Long getAllEnquiries(Integer counsellorId);
	
	@Query(
			value="select count(*) from Enquiry where counsellorId=:counsellorId and status=:status",nativeQuery=true
			)
	public Long getEnquiriesByStatus(Integer counsellorId, String status);

}
