package com.rishab;

import com.rishab.dao.AccountDAO;
import com.rishab.dao.MembershipDAO;

import com.rishab.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO accountDAO,
                                               MembershipDAO membershipDAO,
                                               TrafficFortuneService trafficFortuneService) {
        return args -> {
            // testBeforeAdvice(accountDAO, membershipDAO);
            // testAfterReturningAdvice(accountDAO);
            // testAfterThrowingAdvice(accountDAO);
            // testAfterAdvice(accountDAO);
            // testAroundAdvice(trafficFortuneService);

            testAroundAdviceHandleException(trafficFortuneService);
        };
    }

    private void testAroundAdviceHandleException(TrafficFortuneService trafficFortuneService) {
        System.out.println("Calling getFortune()");
        boolean tripWire = true;
        String data = trafficFortuneService.getFortune(true);
        System.out.println("Fortune: " + data);
    }

    private void testAroundAdvice(TrafficFortuneService trafficFortuneService) {
        System.out.println("Calling getFortune()");
        String data = trafficFortuneService.getFortune();
        System.out.println("Fortune: " + data);
    }

    private void testAfterAdvice(AccountDAO accountDAO) {
        List<Account> accounts = accountDAO.findAccounts();
        System.out.println("Accounts: " + accounts);
    }

    private void testAfterThrowingAdvice(AccountDAO accountDAO) {
        List<Account> accounts = null;
        try {
            boolean tripWire = true;
            accounts = accountDAO.findAccounts(tripWire);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        System.out.println("Accounts: " + accounts);
    }

    private void testAfterReturningAdvice(AccountDAO accountDAO) {
        List<Account> accounts = accountDAO.findAccounts();
        System.out.println("Accounts: " + accounts);
    }

    private void testBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {
        Account account = new Account();
        account.setName("Charlie");
        account.setLevel("Platinum");

        accountDAO.addAccount(account, true);
        accountDAO.didAccountAdd();

        accountDAO.setName("AccountDAOImpl");
        accountDAO.setServiceCode("1234");
        accountDAO.getName();
        accountDAO.getServiceCode();

        membershipDAO.addMembershipAccount();
        membershipDAO.logMembership();
    }
}
