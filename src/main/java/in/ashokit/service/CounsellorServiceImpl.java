package in.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Counsellor;
import in.ashokit.repo.CounsellorRepository;

@Service
public class CounsellorServiceImpl implements ICounsellorService {
	@Autowired
	CounsellorRepository counsellorRepo;
	
	@Override
	public Boolean saveCounsellor(Counsellor counsellor) {
		Counsellor findByEmail=counsellorRepo.findByEmail(counsellor.getEmail());
				if(findByEmail!=null)
				{return false;}
				else
				{
		Counsellor savedCounsellor=counsellorRepo.save(counsellor);
		//Integer cid=savedCounsellor.getCounsellorId();
		/*if(cid!=null)
			return true;
		else 
			return false;*/
		return savedCounsellor.getCounsellorId()!=null;
				}
	}

	@Override
	public Counsellor getCounsellor(String email, String pwd) {
		return counsellorRepo.findByEmailAndPwd(email,pwd);
		
	}

}
