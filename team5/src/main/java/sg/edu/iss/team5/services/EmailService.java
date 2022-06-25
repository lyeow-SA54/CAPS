package sg.edu.iss.team5.services;

public interface EmailService {
	public void sendRegistrationEmail(String sendTo, String stuName, String courseName);
}
