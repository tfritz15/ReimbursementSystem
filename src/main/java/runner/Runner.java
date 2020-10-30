package runner;

import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.repo.ReimbursementDao;
import com.revature.repo.UserDao;

public class Runner {
	private static UserDao ud = new UserDao();
	private static ReimbursementDao rd = new ReimbursementDao();

	public static void main(String[] args) {
		User user = ud.findById(4000);
		System.out.println(user.toString());
		List<Reimbursement> reimbursements = rd.retrieveUserReimbursements(user);
		System.out.println(reimbursements);
	}
}
