package com.example.banking;

import java.time.LocalDateTime;

import com.example.banking.account.Account;
import com.example.banking.account.AccountRepository;
import com.example.banking.member.Member;
import com.example.banking.member.MemberRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BankingApplicationTests {
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private MemberRepository MemberRepository;

	Long testJpaFindAllFun(){
		List<Account> all = this.accountRepository.findAll();
		assertEquals(2, all.size());

		for(Account a: all){
			if(a.getAccountNumber().equals("1234581231"))
				return a.getId();
		}
		assertTrue(false);
		return -1L;
	}
	@Test
	void testJpaDeleteAll() {
		this.accountRepository.deleteAll();
		List<Account> all = this.accountRepository.findAll();
		assertEquals(0, all.size());
	}

	@Test
	void testJpaInsert() {
		List<Member> all = this.MemberRepository.findAll();
		Member m = new Member();

		for(Member member : all){
			if(member.getMid().equals("seungho_yang")) {
				m = member;
			}
		}

		Account q1 = new Account();
		q1.setAccountNumber("1234581231");
		q1.setMember(m);
		q1.setPassword("0000");
		q1.setBankName("한국은행");
		q1.setAmount(0);
		q1.setCreateDate(LocalDateTime.now());

		this.accountRepository.save(q1);  // 첫번째 계좌 저장

		Account q2 = new Account();
		q2.setAccountNumber("1234581232");
		q2.setMember(m);
		q2.setPassword("1020");
		q2.setBankName("저축은행");
		q2.setAmount(0);
		q2.setCreateDate(LocalDateTime.now());

		this.accountRepository.save(q2);  // 두번째 계좌 저장
	}

	@Test
	void testJpaFindAll() {
		testJpaFindAllFun();
	}
	@Test
	void testJpaFindById() {
		Optional<Account> oa = this.accountRepository.findById(testJpaFindAllFun());
		if(oa.isPresent()) {
			Account a = oa.get();
			assertEquals("승호", a.getMember().getName());
		}
		else{
			System.out.println("데이터가 없습니다.");
		}
	}

	@Test
	void testJpaFindByBankName() {
		Account a = this.accountRepository.findByBankName("한국은행");
		assertEquals(testJpaFindAllFun(), a.getId());
	}

	@Test
	void testJpaFindByBankNameAndAccountNumber() {
		Account a = this.accountRepository.findByBankNameAndAccountNumber("한국은행", "1234581231");
		assertEquals(testJpaFindAllFun(), a.getId());
	}

	@Test
	void testJpaFindByBankNameLike() {
		List<Account> aList = this.accountRepository.findByBankNameLike("%은행");
		Account a = aList.get(0);
		assertEquals("승호", a.getMember().getName());
	}

	@Test
	void testJpaUpdateById() {
		String s = new String("국민은행");
		Account a = this.accountRepository.findByBankName("저축은행");
		a.setBankName(s);
		this.accountRepository.save(a);

		Account ab = this.accountRepository.findByBankName(s);
	}
}
