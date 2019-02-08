package com.example.historical.repository;

import com.example.historical.model.Account;

/**
 * Created by stephan on 20.03.16.
 */
public interface AccountRepository extends CrudRepository<Account> {
	Account findByUsername(String username);
	
	Account findByLiveId(Long liveId);
}
