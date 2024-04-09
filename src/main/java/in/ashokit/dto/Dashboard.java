package in.ashokit.dto;

public class Dashboard {
	private Long totalEnqs;
	private Long newEnqs;
	private Long enrolledEnqs;
	private Long lostEnqs;
	public Long getTotalEnqs() {
		return totalEnqs;
	}
	public void setTotalEnqs(Long totalEnqs) {
		this.totalEnqs = totalEnqs;
	}
	public Long getNewEnqs() {
		return newEnqs;
	}
	public void setNewEnqs(Long newEnqs) {
		this.newEnqs = newEnqs;
	}
	public Long getEnrolledEnqs() {
		return enrolledEnqs;
	}
	public void setEnrolledEnqs(Long enrolledEnqs) {
		this.enrolledEnqs = enrolledEnqs;
	}
	public Long getLostEnqs() {
		return lostEnqs;
	}
	public void setLostEnqs(Long lostEnqs) {
		this.lostEnqs = lostEnqs;
	}
	
	
}
