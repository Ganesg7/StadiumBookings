package com.stadiumbooking.dao;


import java.sql.SQLException;
import java.util.List;

import com.stadiumbooking.model.WalletDetails;

public interface WalletDao {

	public void insertAmount(WalletDetails wallete) throws  SQLException;
	public List<WalletDetails> allUserWalletList() throws SQLException;
	public List<WalletDetails> getUserWalletListById(int userId) throws SQLException;
	
	
}
