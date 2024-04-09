package in.ashokit.service;

import in.ashokit.entity.Counsellor;

public interface ICounsellorService {
	 public Boolean saveCounsellor(Counsellor councellor);
	 public Counsellor getCounsellor(String email,String pwd); 
}
