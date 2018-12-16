package it.zan.view;

import java.util.ArrayList;
import java.util.List;

public class UserListView {
	
	private List<UserView> userList = new ArrayList<UserView>();
	
	public List<UserView> getUserList() {
		return userList;
	}

	public void setUserList(List<UserView> userList) {
		this.userList = userList;
	}

}
