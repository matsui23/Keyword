package com.matt.Keyword.models.data;


import com.matt.Keyword.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AccountDao extends CrudRepository<Account, Integer> {
}
