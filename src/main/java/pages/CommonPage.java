package pages;

import utils.WebUtil;

public class CommonPage extends CommonPageOR {
	private WebUtil wt;
	public CommonPage(WebUtil wa){
		super(wa);
		this.wt=wa;
	}
	
	
	public void logOutbutton() {
		wt.Click(logoutBT);

	}	
	public void quit() {
		try {
			wt.myQuit();
			System.out.println("All window related close successfuly");
		}catch(Exception e) {
			System.out.println("socket exception handle successfully");
		}
}
}