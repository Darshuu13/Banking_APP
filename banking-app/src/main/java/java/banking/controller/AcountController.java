package java.banking.controller;

import java.banking.dto.AccountDto;
import java.banking.services.AccountService;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AcountController {

	private AccountService accountService;

	public AcountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {

		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {

		AccountDto accountDto = accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);

	}

	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {

		double amount = request.get("amount");
		AccountDto accountDto = accountService.deposit(id, amount);

		return ResponseEntity.ok(accountDto);
	}

	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {

		double amount = request.get("amount");
		AccountDto accountDto = accountService.withdraw(id, amount);

		return ResponseEntity.ok(accountDto);
	}

	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts() {

		List<AccountDto> accounts = accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccountById(@PathVariable Long id) {
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account is Deleted Successfully");

	}
}
