package com.example.banking.member;

import com.example.banking.DataNotFoundException;
import com.example.banking.account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public List<Member> getList(){
        return this.memberRepository.findAll();
    }

    public Member getMember(Long id) {
        Optional<Member> member = this.memberRepository.findById(id);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new DataNotFoundException("member not found");
        }
    }

    public void create(Member member) {
        member.setCreateDate(LocalDateTime.now());
        this.memberRepository.save(member);
    }
}
