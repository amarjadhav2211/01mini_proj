package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.entity.Enquiry;
import in.ashokit.service.ICounsellorService;
import in.ashokit.service.IEnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	@Autowired
	private IEnquiryService enqService;
	
		
	//add enquiry-pg display
	@GetMapping("/enquiry")
	public String addEnquiry(Model model)
	{
		model.addAttribute("enq", new Enquiry());
		return "addEnq";
	}
	
	@PostMapping("enquiry")
	public String saveEnquiry(Enquiry enq,HttpServletRequest req,Model model)
	{	HttpSession session=req.getSession(false);
		Integer cid=(Integer)session.getAttribute("cid");
		boolean status=enqService.addEnquiry(enq,cid);
		if(status)
		{
			model.addAttribute("smsg","Enquiry Saved");
		}
		else
		{
			model.addAttribute("emsg","Enquiry Not Saved");
		}
		return "addEnq";
	}
	//view Enqs
	@GetMapping("/enquiries")
	public String getEnquiries(HttpServletRequest req,Model model)
	{
		HttpSession session=req.getSession(false);
		Integer cid=(Integer)session.getAttribute("cid");
		List<Enquiry> list=enqService.getEnquiries(new Enquiry(), cid);
				model.addAttribute("enqs", list);
				model.addAttribute("enq", new Enquiry());
		return "viewEnquiries";
	}
	
	//filter enquiry search
	@PostMapping("/filter-enqs")
	public String filterEnqs(Enquiry enq,HttpServletRequest req,Model model)
	{
		HttpSession session=req.getSession(false);
		Integer cid=(Integer)session.getAttribute("cid");
		List<Enquiry> list=enqService.getEnquiries(enq,cid);
		model.addAttribute("enqs", list);
		return "viewEnquiries";
	}
	
	//edit and update enqs
	@GetMapping("/edit")
	public String editEnquiry(@RequestParam("enqId") Integer enqId,Model model)
	{	Enquiry enquiry=enqService.editEnquiry(enqId);
		model.addAttribute("enq", enquiry);
		return "addEnq";
	}
}
