package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.dto.Dashboard;
import in.ashokit.entity.Counsellor;
import in.ashokit.service.ICounsellorService;
import in.ashokit.service.IEnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	@Autowired
	private ICounsellorService counsellorService;
	@Autowired
	private IEnquiryService enqService;
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req,Model model)
	{
		HttpSession session=req.getSession(false);
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/register")
	public String register(Model model)
	{
		model.addAttribute("counsellor", new Counsellor());
		return "registerView";
	}
	@PostMapping("/register")
	public String handleRegister(Counsellor counsellor,Model model)
	{
		Boolean status=counsellorService.saveCounsellor(counsellor);
		if(status)
		{
			model.addAttribute("smsg", "Registration successful");
		}
		else
		{
			model.addAttribute("emsg", "Cousellor with entered email has been already registered" );
		}
		return "registerView";
	}
	
	@GetMapping("/")
	public String login(Model model)
	{
		model.addAttribute("counsellor", new Counsellor());
		return "index";
	}
	public String handleLogin(Counsellor counsellor,HttpServletRequest req,Model model)
	{
	Counsellor c=counsellorService.getCounsellor(counsellor.getEmail(), counsellor.getPwd());
	if(c==null)
	{
	   model.addAttribute("emsg","Invalid Credentials");
	   return "index";
	}else
	{
		//set counsellor-id in session
	     HttpSession session=req.getSession(true); //for every user a new session will be created 
	     session.setAttribute("cid", c.getCounsellorId());
	     
		 Dashboard dbinfo=enqService.getDashboardInfo(c.getCounsellorId());
	     model.addAttribute("dashboard",dbinfo);
	     return "dashboard";
	}
	
	}
}
