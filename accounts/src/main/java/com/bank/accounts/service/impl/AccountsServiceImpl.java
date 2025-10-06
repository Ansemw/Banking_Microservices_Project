package com.bank.accounts.service.impl;

import com.bank.accounts.dto.AccountsDto;
import com.bank.accounts.dto.CustomerDto;
import com.bank.accounts.exception.ResourceNotFoundException;
import com.bank.accounts.repository.AccountRepository;
import com.bank.accounts.repository.CustomerRepository;
import com.bank.accounts.constants.AccountConstants;
import com.bank.accounts.entity.Account;
import com.bank.accounts.entity.Customer;
import com.bank.accounts.exception.CustomerAlreadyExistsException;
import com.bank.accounts.mapper.AccountsMapper;
import com.bank.accounts.mapper.CustomerMapper;
import com.bank.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {


    private CustomerRepository customerRepository;

    private AccountRepository accountRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {


        Customer customer= CustomerMapper.mapToCustomer(new Customer(), customerDto);
        if(customerRepository.findByMobileNumber(customer.getMobileNumber()).isEmpty()){
            Customer savedCustomer=customerRepository.save(customer);
            accountRepository.save(createNewAccount(savedCustomer));
        }
       else throw new CustomerAlreadyExistsException("Mobile number "+customer.getMobileNumber() +" is already being used");
    }

    private Account createNewAccount(Customer customer){
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNum = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNum);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);

        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {

        Optional<Customer> customer=customerRepository.findByMobileNumber(mobileNumber);
        if (customer.isPresent()) {
            CustomerDto customerDto=CustomerMapper.mapToCustomerDto(customer.get(),new CustomerDto());
            Optional<Account> account=accountRepository.findByCustomerId(customer.get().getCustomerId());
            if(account.isEmpty())
                throw new ResourceNotFoundException("customer","customer Id", Long.toString(customer.get().getCustomerId()));
            else {
                customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account.get(),new AccountsDto()));
                return customerDto;
            }
        }
        throw new ResourceNotFoundException("customer","mobile number", mobileNumber);
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated=false;
        AccountsDto accountsDto=customerDto.getAccountsDto();
        if(accountsDto!=null){

            Optional<Account> account=accountRepository.findById(accountsDto.getAccountNumber());
            if(account.isEmpty())
                throw new ResourceNotFoundException("account", "account number", Long.toString(accountsDto.getAccountNumber()));
            Account acc=AccountsMapper.mapToAccount(account.get(),accountsDto);
            accountRepository.save(acc);
            Long custId=acc.getCustomerId();
            Optional<Customer> cust=customerRepository.findById(custId);
            if (cust.isEmpty())
                throw new ResourceNotFoundException("customer", "customerId", Long.toString(custId));
            customerRepository.save(CustomerMapper.mapToCustomer(cust.get(),customerDto));
            isUpdated=true;

        }

        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        boolean isDeleted=false;
        Optional<Customer> cust=customerRepository.findByMobileNumber(mobileNumber);
        if(cust.isEmpty())
            throw new ResourceNotFoundException("customer","mobile number", mobileNumber);
        else{
            Customer customer=cust.get();
            accountRepository.deleteByCustomerId(customer.getCustomerId());
            customerRepository.deleteById(customer.getCustomerId());
            isDeleted = true;
        }
       return isDeleted;
    }
}
