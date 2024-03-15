package java.banking.service.impl;

import java.banking.dto.AccountDto;
import java.banking.entity.Account;
import java.banking.mapper.AccountMapper;
import java.banking.repository.AccountRepository;
import java.banking.services.AccountService;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;

	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountdto) {
		Account account = AccountMapper.mapToAccount(accountdto);
		Account savedAcount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAcount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does t exists"));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does t exists"));

		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account savedaccount = accountRepository.save(account);

		return AccountMapper.mapToAccountDto(savedaccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does t exists"));
		if (account.getBalance() < amount) {
			throw new RuntimeException("Insufficient Amount");
		}

		double total = account.getBalance() - amount;
		account.setBalance(total);
		Account savedAcount = accountRepository.save(account);

		return AccountMapper.mapToAccountDto(savedAcount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());

	}

	@Override
	public void deleteAccount(Long id) {

		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does t exists"));

		accountRepository.deleteById(id);

	}

}
